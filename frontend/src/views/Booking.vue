<template>
  <h1>Booking Page</h1>
  <div class="container">
    <div class="salon">

    </div>


      <div class="info">
        <span>Platser:</span>
          <input type="number" value="chosenSeats" v-model="chosenSeats"/>  



    </div>
  </div>
</template>

<script>
import BookingInput from '../components/BookingInput.vue'
export default {
  
data(){
  return{
  chosenSeats:2
  }
},

components:{
  BookingInput
}

}
</script>

<style scoped>
.container input{
  background-color: aliceblue !important;
  max-width: 10vh;
  text-align: center;
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

.salon{
  background-color: brown;
}

.info{
  background-color: pink;
  padding: 10px;
}

</style>