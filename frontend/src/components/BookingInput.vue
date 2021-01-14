<template>
  <div class="container row">
    <div class="input-field col l3">
      <span>Vuxen/Barn/Pensionär</span>
    </div>

    <div class="input-field col l3" id="selectDiv">
      <select v-on:change="onChange" class="select text-white" id="selectPrice">
        <option value="" disabled selected>Välj</option>
        <option value="1">Vuxen</option>
        <option value="2">Barn</option>
        <option value="3">Pensionär</option>
      </select>
      <!--<label for="ticket">Biljett {{ticketNumber}}</label>-->
    </div>

    <div class="col l2 right">
      <span>{{price}}kr</span>
      <!--<span v-show="price>0">{{price}} kr</span>-->
    </div>
  </div>
</template>

<script>
import M from "materialize-css";

export default {
  data(){
    return{
      prices: [85, 65, 75],
      price: ''
    }
  },
  props:['ticketNumber'],
  mounted() {
    M.AutoInit();
  },
  methods:{
    onChange(){
     
     //Denna emit funkar
     //Problem: subtraherar inte priset när man gör mindre input fält
      this.price = this.prices[event.target.value - 1]
      
      this.$emit('updateTotalPrice', this.price, this.ticketNumber )
      
    }
  }
};
</script>

<style scoped>
span {
  color: var(--lightgrey);
  display: table-cell;
  vertical-align: bottom;
  padding: 10px;
  font-size: 1em;
}

.container .row{
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
