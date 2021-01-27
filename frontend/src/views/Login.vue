<template>
  <form v-on:submit.prevent="logIn">
    <h3>Logga in</h3>
    <input required v-model="email" type="email" />
    <label>E-post</label>
    <input required v-model="password" type="password" />
    <label>Lösenord</label>
    <p class="failedLogin" v-if="errorMessage">Ogiltig E-post/Lösenord</p>
    <button id="btn1">Logga in</button>
    <button id="btn2" @click="goToRegister">Registrera</button>
  </form>

  <div class="loginFeatures">
    <div>
      <img src="../assets/tickets.png" alt="" />
      <p>Boka biljetter snabbt och säkert</p>
    </div>

    <div>
      <img src="../assets/seats.png" alt="" />
      <p>Boka en eller flera platser</p>
    </div>

    <div>
      <img src="../assets/popcorn-and-soda.png" alt="" />
      <p>Erbjudande vid kiosken</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      errorMessage: false,
    };
  },

  methods: {
    goToRegister() {
      this.$router.push("/register");
    },
    async logIn() {
      let member = {
        email: this.email,
        password: this.password,
      };
      await this.$store.dispatch("login", member); //inloggning
      if (this.user) {
        this.errorMessage = false;
        await this.$router.push("/");
        //window.location.reload();
        M.toast({ html: "✓ Inloggning lyckades", classes: "color: green" });
      } else {
        console.log("loggin failed");
        this.errorMessage = true;
      }
    },
  },
  computed: {
    user() {
      return this.$store.state.user != null; //false = inte inloggad
    },
  },
};
</script>

<style scoped>
.loginFeatures {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  text-align: center;
  font-weight: bold;
  grid-gap: 20px;
  padding: 10px;
  max-width: 700px;
  width: 80%;
  margin: 0 auto;
  background: rgba(49, 45, 45, 0.664);
  border-radius: 8px;
}

img {
  width: 80%;
  margin: 0 auto;
}

h3 {
  margin-top: 12px;
  font-size: 30px;
  text-align: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.123);
  padding-bottom: 12px;
}
label {
  font-size: 15px;
}

form {
  padding: 1px 15px 1px 15px;
  background: rgba(49, 45, 45, 0.664);
  margin: 30px auto;
  border-radius: 10px;
  max-width: 300px;
  width: 80%;
  margin-bottom: 50px;
}

form > button {
  background-color: #4caf50;
  border: none;
  color: white;
  padding: 12px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  width: 100%;
  opacity: 75%;
  cursor: pointer;
  border-radius: 8px;
  font-size: medium;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

#btn1 {
  margin-top: 20px;
}
#btn2{
  margin-top: 10px;
  margin-bottom: 15px;
}

form > button:hover {
  opacity: 100%;
}

form > input {
  color: white;
}

h4 {
  text-align: center;
}

li::before {
  content: "•";
  margin-right: 8px;
  color: red;
  font-size: 20px;
  font-weight: bolder;
}

.failedLogin {
  text-align: center;
  color:white;
  background-color: crimson;
  width: fit-content;
  margin: 0 auto;
  margin-top: 15px;
  padding: 5px;
  border: crimson;
  border-radius: 10px;
  opacity: 80%;
}
/*
.failedLogin::before {
  content: "!";
  margin-right: 8px;
  color: red;
  font-size: 15px;
  font-weight: bolder;
}

}*/
@media only screen and (max-width: 480px){
  .loginFeatures p{
    font-size: 3vw;
  }
}
</style>