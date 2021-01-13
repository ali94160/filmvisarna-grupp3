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
      <p>Totala priset: {{ getTotalPrice }}</p>

    </div>
  </div>

</template>

<script>
import BookingInput from '../components/BookingInput.vue'
import ConfirmButton from '../components/ConfirmButton.vue'
export default {
  
  data(){
    return{
      chosenSeats: 2,
      maxSeats: 8, 
      ticketPrices:[0, 0, 0, 0, 0, 0, 0, 0],
    }
  },

  components:{
    BookingInput,
    ConfirmButton
  },

  methods:{
    decreaseValue(){
      if(this.chosenSeats > 1){
        console.log("before", this.chosenSeats);
        this.chosenSeats--;
        console.log("after", this.chosenSeats);
        this.ticketPrices[this.chosenSeats] = 0
      }
    },

    increaseValue(){
      if(this.chosenSeats < this.maxSeats)
      this.chosenSeats++;
      console.log(this.ticketPrices)
    },

    updateTotalPrice(price, ticketNumber){
   
      console.log('ticket number is', ticketNumber)
      this.ticketPrices[ticketNumber-1]=price;



  }

  },

  computed:{
    chosenSeatsInput(){
      let array = [];
      for(let seat = 1; seat <= this.chosenSeats; seat++){
        array.push(seat)
      }
      return array
    },
    getTotalPrice(){
      let sum=0;
       for(let i=0; i<this.ticketPrices.length; i++){
         sum+=this.ticketPrices[i]
       }
       return sum
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
  padding: 30px;
 
}

span{
  padding: 5px;
  color:white;

}

.salon, p{
  background-color: brown;
}

.info{

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