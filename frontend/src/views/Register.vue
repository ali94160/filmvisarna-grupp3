<template>
  <div class="regrister">
    <form>
      <h3>Bli medlem</h3>
      <label>Namn</label>
      <input type="text">
      <label>Efternamn</label>
      <input type="text">
      <label>Email</label>
      <input type="email">
      <label>Lösenord</label>
      <input type="password">
      <button>Bli medlem</button>
    </form>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
h3{
  margin: 0 0 10px 0;
  color: black;
}
.regrister{
  max-width: 40%;
  background: white;
  margin: 50px auto;
  padding: 20px;
}
label{
  text-align: center;
}
</style>