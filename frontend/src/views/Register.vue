<template>
  <div class="regrister">
    <form v-on:submit.prevent="becomeMember">
      <h3>Bli medlem</h3>
      <label>Namn</label>
      <input v-model="name" required type="text">
      <label>Efternamn</label>
      <input v-model="lastName" required type="text">
      <label>Email</label>
      <input v-model="email" required type="email">
      <label>Lösenord</label>
      <input v-model="password" required type="password">
      <button>Bli medlem</button>
    </form>
  </div>
</template>

<script>
export default {
data(){
  return{
    name: '',
    lastName: '',
    email: '',
    password: '',   
  }
},  
methods:{
  becomeMember(){

  }
}
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
  border-radius: 10px;
}
label{
  color: black;
  text-align: center;
}
button{
  cursor: pointer;
  display: block;
  margin: 20px auto;
  padding: 10px;
  border: none;
  border-radius: 10px;
  background: var(--red);
  color: white;
}
button:hover{
  zoom: 1.1;
}
p{
  color: red;
}
</style>