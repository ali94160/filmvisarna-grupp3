<template>
  <form v-on:submit.prevent>
    <h3>Logga in</h3>

    <label>Epost:</label>
    <input required v-model="email" type="email" />
    <label>Lösenord:</label>
    <input required v-model="password" type="password" />
    <button>Logga in</button>
    <button>Registrera</button>
    <p class="failedLogin" v-if="true">Ej giltigt Epost/lösenord</p>
  </form>

  <div class="loginFeatures">
    <h4>Varför ska jag logga in?</h4>
    <ul>
      <li>Boka biljetter snabbt och säkert.</li>
      <li>Boka en eller flera platser.</li>
      <li>Få tillgång till dina kommande och använda biljetter.</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      show: false,
    };
  },

  methods: {},
};
</script>

<style scoped>
h3 {
  font-size: 30px;
  text-align: center;
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
  max-width: 500px;
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