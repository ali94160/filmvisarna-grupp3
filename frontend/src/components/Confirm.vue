<template>
  <div v-if="checkBooking">
    <h4 class="title">Tack för din beställning!</h4>

    <div class="info">
      <div class="poster">
        <img :src="movie.imgUrl" alt="" />
      </div>
      <div>
        <h4>{{ movie.title }}</h4>
        <p>Datum: {{ getDate(show) }}</p>
        <p>Tid: {{ getTime(show) }}</p>
        <p>{{ salon.name }}</p>
        <p>Antal biljetter: {{ amountOfTickets }}</p>
      </div>
    </div>

    <div class="payment">
      <h5>Att betala: {{ totalPrice }} kr</h5>
    </div>

    <div class="buttons">
      <button @click="homePage">Startsidan</button>
      <button @click="myPage">Mina sidor</button>
    </div>
    
  </div>

  <div v-else>
  <h1>Bokning misslyckad!</h1>
  <p> En eller flera platser du valde är redan bokad! Tryck på tillbaka knappen för att boka igen! </p>
  </div>
</template>

<script>
export default {
  data(){
    return {
      isBookingSuccessful: false
    }
  },
  props: ["ticketPrices", "bookedChairs"],
  computed: {
    movie() {
      return this.$store.state.selectedMovie;
    },
    show() {
      return this.$store.state.showById;
    },
    salon() {
      return this.$store.state.currentSalon;
    },
    amountOfTickets() {
      return this.ticketPrices.filter((t) => t !== 0).length;
    },
    totalPrice() {
      let sum = 0;
      for (let i = 0; i < this.ticketPrices.length; i++) {
        sum += this.ticketPrices[i];
      }
      return sum;
    },
  },
  methods: {
    getDate(show) {
      let millis = show.timeStamp * 1000;
      return new Date(millis).toLocaleDateString();
    },
    getTime(show) {
      let millis = show.timeStamp * 1000;
      return new Date(millis).toGMTString().substring(17,22);
    },
    myPage() {
      this.$router.push("/mypage");
    },
    homePage() {
      this.$router.push("/");
    },
    async checkBooking(){
      let ticket = {
      price: this.totalPrice,
      timeStamp: this.show.timeStamp,
      seats: [...this.bookedChairs],
      userId: this.$store.state.user.id,
      showId: this.show.id,
      salonName: this.$store.state.currentSalon.name
      };

      const isBookingSuccessful = await this.$store.dispatch("addTicket", ticket);
      if(isBookingSuccessful){
      let showInfo = { showId: this.show.id, seats: [...this.bookedChairs] + "" };
      this.$store.dispatch("increaseSeatsInShow", showInfo);   
      }
      return isBookingSuccessful;
    }
  },
  created() {
    this.checkBooking();
  }
};
</script>

<style scoped>
div {
  text-align: center;
}
.title {
  margin: 50px;
}
.info {
  background-color: #333;
  box-shadow: 0px 0px 15px 1px rgb(61, 61, 61);
  border-radius: 15px;
  padding: 30px;
  width: 55%;
  min-width: 400px;
  max-width: 600px;
  margin: 30px auto;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 3vw;
}
.info img {
  box-shadow: 0 0 7px 0.1px #7e7e7e;
  border-radius: 15px;
  display: block;
  max-width: 200px;
  width: 100%;
  justify-self: center;
}
.info p {
  text-align: start;
  margin: 3px;
  font-size: 1.1em;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
.info h4 {
  font-weight: bold;
  text-align: start;
  margin-bottom: 20px;
  font-size: calc(15px + 1vw);
}
.payment {
  width: 40%;
  background: var(--red);
  padding: 10px;
  border-radius: 15px;
  margin: 5px auto;
}
.buttons {
  width: 40%;
  margin: 10px auto;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 5px;
}
.buttons button {
  width: 100%;
  padding: 20px;
  border: none;
  background: #4caf50;
  opacity: 75%;
  border-radius: 15px;
  cursor: pointer;
  color: white;
  font-size: 1.2em;
}
.buttons button:hover {
  opacity: 100%;
}
@media only screen and (max-width: 700px)  {

  .payment {
    border-radius: 5px;
  }
  .buttons button{
    padding: 15px;
    font-size: calc(8px + 1vw);
    border-radius: 5px;
  }
}

@media only screen and (max-width: 480px) {
  .info {
    width: 85%;
    min-width: 0;
    grid-template-columns: none;
  }

  .poster {
    margin: 0 auto;
  }

  .payment h5{
    font-size: calc(10px + 1.5vw);
  }

  .payment {
    width: 60%;
    border-radius: 5px;
    padding: 5px;
  }

  .title {
    margin: 2vw;
    margin-top: 0.5vw;
  }

  .buttons {
    margin:0 auto;
    width: 60%;
  }

  .buttons button {
    padding: 10px;
    font-size: calc(8px + 1vw);
    border-radius: 5px;
  }
}
</style>