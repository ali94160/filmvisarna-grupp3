<template>
  <h1>Booking Page</h1>
  <div class="container">
    <div class="salon">

    </div>

    <div class="info">
      <span>Platser: </span>
      <div class="value-button" v-on:click="decreaseValue">-</div>
      <input type="number" v-model="chosenSeats" max="8">
      <div class="value-button" v-on:click="increaseValue">+</div>

      <BookingInput v-for="seat in chosenSeatsInput" :key="seat" :ticketNumber="seat" @updateTotalPrice="updateTotalPrice" />
      <p>Total pris: {{ totalPrice }}</p>

    </div>
  </div>
</template>

<script>
import BookingInput from '../components/BookingInput.vue'
export default {
  
  data(){
    return{
      chosenSeats: 2,
      maxSeats: 8, 
      totalPrice:0
    }
  },

  components:{
    BookingInput
  },

  methods:{
    decreaseValue(){
      if(this.chosenSeats > 1)
      this.chosenSeats--;
    },

    increaseValue(){
      if(this.chosenSeats < this.maxSeats)
      this.chosenSeats++;
    },
updateTotalPrice(price){
  this.totalPrice+=price;
  console.log('total pris är', this.totalPrice)

}

  },

  computed:{
    chosenSeatsInput(){
      let array = [];
      for(let seat = 1; seat <= this.chosenSeats; seat++){
        array.push(seat)
      }
      return array
    }
  }

}
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;  
}

.container input{
  background-color: white !important;
  border-radius: 10px;
  max-width: 5vh;
  max-height: 5vh;
  text-align: center;
  color: black;
}

.container{
  width: 100%;
  display: grid;
  grid-template-columns: repeat(2, 3fr);
  grid-gap: 60px;
  background-color: burlywood;
  padding: 30px;
 
}

span{
  padding: 5px;
  color: black;

}

.salon, p{
  background-color: brown;
}

.info{
  background-color: pink;
  padding: 10px;
}

.value-button {
  display: inline-block;
  border: 1px solid #ddd;
  width: 5vh;
  height: 5vh;
  text-align: center;
  vertical-align: middle;
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




</style>