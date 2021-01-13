<template>
  <div id="#bookingDiv" v-if="!booked">
    <h1>Booking Page</h1>
    <div class="container">
      <div class="salon"></div>

      <div class="info">
        <span>Platser: </span>
        <div class="value-button" v-on:click="decreaseValue">-</div>
        <input type="number" v-model="chosenSeats" max="8" />
        <div class="value-button" v-on:click="increaseValue">+</div>

        <div :class="{tickets: hasThreeTickets}">
          <BookingInput
            v-for="seat in chosenSeatsInput"
            :key="seat"
            :ticketNumber="seat"
            @updateTotalPrice="updateTotalPrice"
          />
        </div>
        <p>Totala priset: {{ getTotalPrice }}</p>
        <button class="btn" v-on:click="changeBooked">Beställ plats(er)</button>
      </div>
    </div>
  </div>

  <Confirm v-if="booked" :ticketPrices="ticketPrices"/>
</template>

<script>
import BookingInput from "../components/BookingInput.vue";
import Confirm from "../components/Confirm.vue";
export default {
  data() {
    return {
      chosenSeats: 2,
      maxSeats: 8,
      ticketPrices: [0, 0, 0, 0, 0, 0, 0, 0],
      booked: false
    };
  },

  components: {
    BookingInput,
    Confirm
  },

  methods: {
    decreaseValue() {
      if (this.chosenSeats > 1) {
        console.log("before", this.chosenSeats);
        this.chosenSeats--;
        console.log("after", this.chosenSeats);
        this.ticketPrices[this.chosenSeats] = 0;
      }
    },

    increaseValue() {
      if (this.chosenSeats < this.maxSeats) this.chosenSeats++;
      console.log(this.ticketPrices);
    },

    updateTotalPrice(price, ticketNumber) {
      console.log("ticket number is", ticketNumber);
      this.ticketPrices[ticketNumber - 1] = price;
    },
    changeBooked(){
      this.booked = !this.booked
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
        sum += this.ticketPrices[i];
      }
      return sum;
    },
    hasThreeTickets(){
      return this.chosenSeats > 3
    }
  },
};
</script>

<style scoped>
* {
  margin: 0;
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
  width: 100%;
  display: grid;
  grid-template-columns: repeat(2, 3fr);
  grid-gap: 60px;
  padding: 30px;
}

span {
  padding: 5px;
  color: var(--lightgrey);
}

.salon,
p {
  background-color: brown;
}

.salon {
  height: 50vh;
}

.info {
  padding: 10px;
  padding-bottom: 0;
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
</style>
