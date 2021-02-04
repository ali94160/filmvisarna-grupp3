<template>
<div class="wrapper" v-if="user">
  <div id="#bookingDiv" v-if="!booked">
    <div class="container">
      <div class="salon">
        <ChairList @updateSelectedChairs="updateSelectedChairs" @decreaseValues="decreaseValue" @clear="clear"/>
      </div>

        <div class="info">
          <div :class="{ tickets: hasThreeTickets }">
            <BookingInput
              v-for="seat in selectedSeatsAmount"
              :key="seat"
              :ticketNumber="seat"
              @updateTotalPrice="updateTotalPrice"
            />
          </div>
          <div>
            <p id="totalPrice">
              Totala priset: <span class="right">{{ getTotalPrice }} kr</span>
            </p>
            <button class="btn" v-on:click="changeBooked">
              Beställ plats(er)
            </button>
          </div>
        </div>

      </div>
    </div>

    <Confirm
      v-if="booked"
      :ticketPrices="ticketPrices"
      :bookedChairs="selectedChairs"
    />
  </div>
  <div class="errorMessage" v-else>
    <h3>Du måste vara inloggad för att boka en film</h3>
    <button @click="login">Logga in</button>
  </div>
</template>

<script>
import BookingInput from "../components/BookingInput.vue";
import Confirm from "../components/Confirm.vue";
import ChairList from "../components/ChairList.vue";
export default {
  data() {
    return {
      selectedChairs: [],
      maxSeats: 81,
      ticketPrices: [],
      booked: false,
    };
  },

  components: {
    BookingInput,
    Confirm,
    ChairList,
  },

  methods: {
    login(){
      this.$router.push('/login');
    },
    decreaseValue() {
      console.log("decreaseValue called");
        console.log("before ticketPrices",this.ticketPrices);
      if (this.$store.state.selectedSeatsAmount >= 0) {
        //this.ticketPrices[this.$store.state.selectedSeatsAmount] = 0;
        this.ticketPrices.splice(this.$store.state.selectedSeatsAmount,1)
      }
      console.log("after ticketPrices",this.ticketPrices);
    },
    clear(){
      this.ticketPrices = [];
      this.$store.commit("setSelectedSeatsAmount", 0);
    },

    updateTotalPrice(price, ticketNumber) {
      this.ticketPrices[ticketNumber - 1] = price;
    },

    changeBooked(){
      if(this.ticketPrices.filter(p => p !== 0).length === this.$store.state.selectedSeatsAmount 
      && this.$store.state.selectedSeatsAmount){
        this.booked = !this.booked
      }
    },
    updateSelectedChairs(selectedChairs) {
      this.selectedChairs = selectedChairs;
    }
  },

  computed: {
    user(){
      return this.$store.state.user
    },
    showId(){
      return this.$route.params.id
    },
    getTotalPrice() {
      let sum = 0;
      for (let i = 0; i < this.ticketPrices.length; i++) {
        if (this.ticketPrices[i] == undefined) {
          continue;
        }
        sum += this.ticketPrices[i];
      }
      return sum;
    },
    hasThreeTickets() {
      return this.selectedSeatsAmount > 3;
    },
    getCurrentMovie() {
      return this.$store.state.showById;
    },
    selectedSeatsAmount() {
      return this.$store.state.selectedSeatsAmount;
    }
  },
  mounted(){  
    this.showId
    this.$store.dispatch('fetchShowById', this.showId)
  }
};
</script>

<style scoped>
* {
  margin: 0;
}
.wrapper{
  margin-top: calc(5vh + 1vw);
}
.wrapper, #bookingDiv {
  width: 100%;
}
h1 {
  font-size: 5vw;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.container input {
  background-color: white !important;
  border-radius: 10px;
  max-width: calc(2vh + 2vw);
  max-height: calc(2vh + 2vw);
  text-align: center;
  color: black;
}

.container {
  width: 90%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 10px;
  margin: 0 auto;
}

span {
  padding: 5px;
  color: var(--lightgrey);
}

.salon {
  border-radius: 2%;
  background: rgb(29, 29, 29);
}

.info {
  display: grid;
  grid-template-rows: 3fr 1fr;
  padding: 0 20px 2vh;
  border-radius: 2%;
  background: rgb(29, 29, 29);
}

.salon, .info{
  padding-top: 10px;
  padding-bottom: 10px;
}

.tickets {
  width: 400px;
  height: 50vh;
  overflow: scroll;
  overflow-x: hidden;
  width: 100%;
}

#totalPrice {
  padding: 10px;
  font-size: 2vw;
}

.btn {
  background-color: var(--green);
  opacity: 80%;
  display: block;
  margin: 0 auto;
}

.btn:hover {
  background-color: var(--green);
  opacity: 100%;
}
.errorMessage{
  width: 80%;
  margin: 5% auto;
  font-size: calc(12px + 1vw);
  text-align: center;
}

.errorMessage > button{
  margin-top: 20px;
  background-color: #4caf50;
  border: none;
  color: white;
  padding: 12px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  width: 50%;
  opacity: 75%;
  cursor: pointer;
  border-radius: 8px;
  font-size: medium;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

@media only screen and (max-width: 700px) {
  .container {
    grid-template-columns: none;
  }

  .salon {
    width: 100%;
  }

  .info {
    padding: 0 5vw;
    padding-top: 8vw;
    padding-bottom: 4vw;
  }

  #totalPrice {
    font-size: 1.2em;
    margin-top: 5vw;
    margin-bottom: 5vw;
  }
}


</style>
