<template>
  <h1>Booking Page</h1>
  <div class="container">
    <div class="salon">

    </div>

    <div class="info">
      <span>Platser: </span>
      <div class="value-button" v-on:click="decreaseValue">-</div>
      <input type="number" v-model="chosenSeats">
      <div class="value-button" v-on:click="increaseValue">+</div>
    </div>
  </div>
        <input type="number" id="number" value="chosenSeats" />
</template>

<script>
import BookingInput from '../components/BookingInput.vue'
export default {
  
  data(){
    return{
      chosenSeats: 2
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
      this.chosenSeats++;
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
  max-width: 20%;
  text-align: center;
  color: black;
}

.container{
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
  width: 20px;
  height: 20px;
  text-align: center;
  vertical-align: middle;
  background: #eee;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  color: black;
}

.value-button:hover {
  cursor: pointer;
}




</style>