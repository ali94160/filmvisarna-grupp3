<template>
  <h3>Logga in</h3>

  <form v-on:submit.prevent>
    <label>Epost:</label>
    <input type="email" />
    <label>Lösenord:</label>
    <input type="password" />
    <button>Logga in</button>
  </form>

  <div class="loginFeatures">
    <h4>Varför ska jag logga in?</h4>
    <ul>
      <li>Boka biljetter snabbt och säkert.</li>
      <li>Få tillgång till dina kommande och använda biljetter.</li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 50px;
}

form {
  margin: 0 auto;
  border-radius: 10px;
  max-width: 300px;
}

form > button {
  background-color: #4caf50; /* Green */
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
</style>