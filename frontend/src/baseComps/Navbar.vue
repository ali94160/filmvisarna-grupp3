<template>
    <nav class="nav-wrapper">
      <div class="container">
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/movies">Filmer</router-link></li>
      </ul>
      </div>
  </nav>
</template>

<script>
import M from 'materialize-css'
export default {
  mounted(){
    M.AutoInit();
  }
}
</script>

<style scoped>