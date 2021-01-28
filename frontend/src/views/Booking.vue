<template>
<div class="wrapper">
  <div id="#bookingDiv" v-if="!booked">
    <div class="container">
      <div class="salon">
        <ChairList @updateSelectedChairs="updateSelectedChairs" @decreaseValues="decreaseValue" @clear="clear"/>
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

    <Confirm
      v-if="booked"
      :ticketPrices="ticketPrices"
      :bookedChairs="selectedChairs"
    />
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
    decreaseValue() {
      if (this.$store.state.selectedSeats >= 1) {
        this.ticketPrices[this.$store.state.selectedSeats] = 0;
      }
    },
    clear(){
      this.ticketPrices = [];
      this.$store.commit("setSelectedSeats", 0);
    },

    updateTotalPrice(price, ticketNumber) {
      this.ticketPrices[ticketNumber - 1] = price;
    },

    changeBooked(){
      if(this.ticketPrices.filter(p => p !== 0).length === this.$store.state.selectedSeats && this.ticketPrices.length){
        this.booked = !this.booked
      }
    },
    updateSelectedChairs(selectedChairs) {
      this.selectedChairs = selectedChairs;
    },
  },

  computed: {
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
      return this.selectedSeats > 3;
    },
    getCurrentMovie() {
      return this.$store.state.selectedMovie;
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
  padding-bottom: 5px;
}

.info {
  padding: 20px;
  padding-bottom: 0;
  border-radius: 2%;
  background: rgb(29, 29, 29);
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

@media only screen and (max-width: 1000px) {
  
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
