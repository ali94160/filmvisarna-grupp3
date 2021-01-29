<template>
  <div class="inputWrapper">
    <div class="input-field ">
      <span>Vuxen/Barn/Pensionär</span>
    </div>

    <div class="input-field" id="selectDiv">
      <select v-on:change="onChange" class="select text-white" id="selectPrice">
        <option value="" disabled selected>Välj</option>
        <option value="1">Vuxen</option>
        <option value="2">Barn</option>
        <option value="3">Pensionär</option>
      </select>
      <!--<label for="ticket">Biljett {{ticketNumber}}</label>-->
    </div>

    <div class="input-field">
      <span class="right">{{price}}kr</span>
      <!--<span v-show="price>0">{{price}} kr</span>-->
    </div>
  </div>
</template>

<script>
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
  padding: 0px;
  font-size: calc(10px + 0.5vw);
}

.inputWrapper{
  height: 6vh; 
  margin-bottom: 10vh;
  display: grid;
  grid-template-columns: 1fr 1fr 0.5fr;
  grid-gap: 2vw;
}

.input-field {
  margin: 0 !important;
  align-self: end;
}

.col{
  margin: 0;
  display: table;
  height: 100%;
  color: black;
}

@media only screen and (max-width: 480px) {
  span{
    font-size: 0.6em;
    padding: 0;
  }

}

</style>
