<template>
  <div>
    <h4 class="title">Tack för din beställning!</h4>
    <div class="info">
      <div>
      <img :src="movie.imgUrl" alt="">
      </div>
      <div>
      <h4>{{movie.title}}</h4>
      <p>Datum: {{show.date}}</p>
      <p>Tid: {{show.time}}</p>
      <p>{{salon.name}}</p>
      <p>Antal biljetter: {{amountOfTickets}}</p>
      </div>
    </div>
    <div class="payment">
      <h5>Att betala: {{totalPrice}} kr</h5>
    </div>
    <div class="buttons">
      <button @click="homePage">Startsidan</button>
      <button @click="myPage">Mina sidor</button>
    </div>
  </div>

</template>

<script>
export default {
  props: ['ticketPrices'],
  computed:{
    movie(){
      return this.$store.state.selectedMovie;
    },
    show(){
      return this.$store.state.currentMovie;
    },
    salon(){
      return this.$store.state.currentSalon;
    },
    amountOfTickets(){
      return this.ticketPrices.filter((t) => t !== 0).length;
    },
    totalPrice(){
      let sum = 0;
      for(let i = 0; i < this.ticketPrices.length; i++){
          sum += this.ticketPrices[i];
      }
      return sum;
    }
  },
  methods:{
    myPage(){
      this.$router.push('/mypage');
    },
    homePage(){
      this.$router.push('/');
    }
  },
  created(){
    let ticket= {
      price: this.totalPrice,
      date: this.show.date,
      time: this.show.time,
      seats: this.amountOfTickets,
      userId: this.$store.state.currentUser.id,
      showId: this.show.id,
    };
    this.$store.dispatch("addTicket", ticket);
    
    let showInfo = {showId: this.show.id, seats: this.amountOfTickets}
    this.$store.dispatch("increaseSeatsInShow", showInfo)
  }
}
</script>

<style scoped>
  div {
    text-align: center;
  }
  .title{
    margin: 50px;
  }
  .info{
    background-color: #333;
    box-shadow: 0px 0px 15px 1px rgb(61, 61, 61);
    border-radius: 15px;
    padding: 30px;
    max-width: 40%;
    margin: 30px auto;
    display: grid;
    grid-template-columns: repeat(2,1fr);
  }
  .info img{
     box-shadow: 0 0 7px 0.1px #7e7e7e;
    border-radius: 15px;
    display: block;
    max-width: 200px;
    justify-self: center;
  }
  .info p{
    text-align: start;
    margin: 3px;
    font-size: 1.1em;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  }
  .info h4{
    font-weight: bold;
    text-align: start;
    margin-bottom: 20px;
  }
  .payment{
    max-width: 40%;
    background: var(--red);
    padding: 10px;
    border-radius: 15px;
    margin: 5px auto;
  }
  .buttons{
    max-width: 40%;
    margin: 10px auto;
    display: grid;
    grid-template-columns: repeat(2,1fr);
    grid-gap: 5px;
    }
   .buttons button{
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
  .buttons button:hover{
    opacity: 100%;
  }
</style>