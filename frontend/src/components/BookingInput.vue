<template>
  <div class="input row">
    <div class="input-field col s12 l4">
      <span>Vuxen/Barn/Pensionär</span>
    </div>

    <div class="input-field col s6 l5">
      <select v-on:change="onChange($event)">
        <option value="1">Vuxen</option>
        <option value="2">Barn</option>
        <option value="3">Pensionär</option>
      </select>
      <!--<label for="ticket">Biljett {{ticketNumber}}</label>-->
    </div>

    <div class="col s6 l2">
      <span>{{price}} kr</span>
    </div>
  </div>
</template>

<script>
import M from "materialize-css";

export default {
  data(){
    return{
      prices: [85, 65, 75],
      price: 85
    }
  },
  props:['ticketNumber'],
  mounted() {
    M.AutoInit();
  },
  methods:{
    onChange(event){
      console.log(event.target.value);
      this.price = this.prices[event.target.value - 1]
    }
  }
};
</script>

<style scoped>
span {
  color: black;
  display: table-cell;
  vertical-align: bottom;
}

.input {
  height: 6vh; 
  margin-bottom: 10vh;
  display: table;
}

.col{
  margin: 0;
  display: table;
  height: 100%;
  color: black;
}



</style>
