<template>
  <div class="input row">
    <div class="input-field col m6 l6 s6 left">
      <span>Vuxen/Barn/Pensionär</span>
    </div>

    <div class="input-field col m6 l6 s6 right">
      <select>
        <option value="1">Vuxen</option>
        <option value="2">Barn</option>
        <option value="3">Pensionär</option>
      </select>
    </div>
  </div>
</template>

<script>
import M from "materialize-css";
export default {
  mounted() {
    M.AutoInit();
  },
};
</script>

<style scoped>
span {
  color: black;
  display: table-cell;
  vertical-align: middle;

}

.input{
  height: 10vh; 
}



.left {
display: table;
  background-color: coral;
  height: 100%;
}
</style>
