<template>
  <nav class="nav-wrapper">
    <div class="container">
      <a href="#" data-target="mobile-demo" class="sidenav-trigger">
        <i class="material-icons">menu</i>
      </a>
      <router-link to="/" class="brand-logo">Filmvisarna</router-link>
      <ul class="hide-on-med-and-down">

        <li><router-link to="/">Start</router-link></li>
        <li><router-link to="/movies">Filmer</router-link></li>
        <li><router-link to="/info">Biljett info</router-link></li>
        <li><router-link to="/about">Om oss</router-link></li>
      </ul>
    </div>
  </nav>

  <ul class="sidenav sidenav-close" id="mobile-demo">
    <li><router-link to="/">Home</router-link></li>
    <li><router-link to="/movies">Filmer</router-link></li>
    <li><router-link to="/info">Biljett info</router-link></li>
    <li><router-link to="/about">Om oss</router-link></li>
  </ul>

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
  nav{
    background-color: var(--purpleish);
    text-align: center;
  }
</style>