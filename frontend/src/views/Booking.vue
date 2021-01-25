<template>
<div class="wrapper">
  <div id="#bookingDiv" v-if="!booked">
    <h1>{{ getCurrentMovie.title }}</h1>
    <div class="container">
      <div class="salon">
        <ChairList @updateSelectedChairs="updateSelectedChairs" @decreaseValues="decreaseValue" @increaseValue="increaseValue" @clear="clear"/>
      </div>


        <div class="info">
          <div :class="{ tickets: hasThreeTickets }">
            <BookingInput
              v-for="seat in selectedSeats"
              :key="seat"
              :ticketNumber="seat"
              @updateTotalPrice="updateTotalPrice"
            />
          </div>
          <p id="totalPrice">
            Totala priset: <span class="right">{{ getTotalPrice }} kr</span>
          </p>
          <button class="btn" v-on:click="changeBooked">
            Beställ plats(er)
          </button>
        </div>
      </div>
    </div>

  <Confirm v-if="booked" :ticketPrices="ticketPrices" :bookedChairs="selectedChairs"/>
  </div>
</template>

<script>
import BookingInput from "../components/BookingInput.vue";
import Confirm from "../components/Confirm.vue";
import ChairList from "../components/ChairList.vue";
export default {
  data() {
    return {
      chosenSeats: 2,
      selectedChairs: [],
      maxSeats: 81,
      ticketPrices: [],
      booked: false
    };
  },

  components: {
    BookingInput,
    Confirm,
    ChairList,
  },

  methods: {
    decreaseValue() {
      if (this.chosenSeats >= 1) {
        this.chosenSeats--;
        this.ticketPrices[this.chosenSeats] = 0;
      }
    },
    clear(){
      this.chosenSeats = 0;
      this.ticketPrices = [];
      console.log(' in clear');
    },
    increaseValue() {
      if (this.chosenSeats < this.maxSeats) this.chosenSeats++;
    },

    updateTotalPrice(price, ticketNumber) {
      this.ticketPrices[ticketNumber - 1] = price;
    },

    changeBooked(){
      if(this.ticketPrices.filter(p => p !== 0).length === this.selectedChairs.length && this.chosenSeats && this.ticketPrices.length){
        this.booked = !this.booked
      }
    },
    updateSelectedChairs(selectedChairs){
      this.selectedChairs = selectedChairs
    }
  },

  computed: {
    chosenSeatsInput() {
      let array = [];
      for (let seat = 1; seat <= this.chosenSeats; seat++) {
        array.push(seat);
      }
      return array;
    },
    getTotalPrice() {
      let sum = 0;
      for (let i = 0; i < this.ticketPrices.length; i++) {
        if(this.ticketPrices[i] == undefined){ continue; }
        sum += this.ticketPrices[i];
      }
      return sum;
    },
    hasThreeTickets() {
      return this.chosenSeats > 3;
    },
    getCurrentMovie() {
      return this.$store.state.currentMovie;
    },
    selectedSeats() {
      return this.$store.state.selectedSeats;
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
}
.wrapper {
  margin-top: 10vh;
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
  padding-bottom: 5px;
}

.info {
  padding: 20px;
  padding-bottom: 0;
  border-radius: 2%;
  background: rgb(29, 29, 29);
}

.value-button {
  display: inline-block;
  border: 1px solid #ddd;
  width: calc(2vh + 2vw);
  height: calc(2vh + 2vw);
  text-align: center;
  background: rgb(192, 192, 192);
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  color: black;
  font-weight: bold;
  font-size: 3vh;
}

.value-button:hover {
  cursor: pointer;
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

.btn:hover{
background-color: var(--green);
opacity: 100%;
}

@media only screen and (max-width: 992px) {
  .container {
    grid-template-columns: none;
  }
}
</style>
