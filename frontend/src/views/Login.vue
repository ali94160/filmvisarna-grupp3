<template>
  <form v-on:submit.prevent="logIn">
    <h3>Logga in</h3>
    <label>Epost:</label>
    <input required v-model="email" type="email" />
    <label>Lösenord:</label>
    <input required v-model="password" type="password" />
    <p class="failedLogin" v-if="errorMessage">Ej giltigt Epost/lösenord</p>
    <button>Logga in</button>
    <button @click="goToRegister">Registrera</button>
  </form>

  <div class="loginFeatures">
    <div class="one">
      <img src="../assets/tickets.png" alt="" />
      <p>Boka biljetter snabbt och säkert</p>
    </div>

    <div class="two">
      <img src="../assets/seats.png" alt="" />
      <p>Boka en eller flera platser</p>
    </div>

    <div class="three">
      <img src="../assets/popcorn.png" alt="" />
      <img class="soda" src="../assets/soda.png" alt="" />
      <p>Erbjudande vid kiosken</p>
    </div>
  </div>
  <div class="container"></div>
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
        password: this.password
      }
      await this.$store.dispatch('login', member) //inloggning
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
.container {
  margin-bottom: 4.7vw; /* For the footer */
}

.loginFeatures {
  display: grid;
  grid-template-columns: 33% 33% 33%;
  text-align: center;
  font-weight: bold;
  grid-gap: 20px;
}
.three {
  max-width: 80%;
}

img {
  max-width: 200px;
  max-height: 100px;
}

.soda {
  max-width: 180px;
  max-height: 85px;
  margin-bottom: 7%;
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
  margin-bottom: 50px;
}

form > button {
  margin: 15px 5px 5px 0px;
  background-color: #4caf50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  width: 100%;
  opacity: 75%;
  cursor: pointer;
}

form > button:hover {
  opacity: 100%;
}

form > input {
  color: white;
}

.loginFeatures {
  padding: 10px;
  max-width: 700px;
  margin: 0 auto;
  background: rgba(49, 45, 45, 0.664);

  border-radius: 8px;
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
  color: rgb(216, 0, 0);
}

.failedLogin::before {
  content: "!";
  margin-right: 8px;
  color: red;
  font-size: 15px;
  font-weight: bolder;
}
</style>