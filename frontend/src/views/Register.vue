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
      <p v-if="alreadyMember">Emailen är upptagen..</p>
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
    alreadyMember: false   
  }
},  
methods:{
  becomeMember(){
    let user = 
    {
      name: this.name,
      lastName: this.lastName,
      email: this.email,
      password: this.password
    }
    if(!this.users.length){
      this.$store.dispatch('addUser', user)
    }else{
      for(let i = 0; i < this.users.length; i++){
        if(this.users[i].email === user.email){
          this.alreadyMember = true;
        }
      }
      if(!this.alreadyMember){
        this.$store.dispatch('addUser', user)
      }
    }
  }
},
computed:{
  users(){
  return this.$store.state.user
  }
}
}
</script>

<style scoped>
h3{
  margin: 0 0 10px 0;
  color: white;
}
.regrister{
  max-width: 40%;
  background:  rgba(49, 45, 45, 0.664);
  margin: 50px auto;
  padding: 20px;
  border-radius: 10px;
}
label{
  color: white;
  text-align: center;
}
button{
  cursor: pointer;
  display: block;
  margin: 20px auto;
  padding: 10px;
  border: none;
  border-radius: 10px;
  background: #4caf50;
  opacity: 75%;
  color: white;
}
button:hover{
  opacity: 100%;
}
p{
  color: red;
}
</style>