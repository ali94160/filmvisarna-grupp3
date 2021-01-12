<template>
  <nav class="nav-wrapper">
    <a href="#" data-target="mobile-demo" class="sidenav-trigger">
        <i class="material-icons">menu</i>
      </a>
    <router-link to="/" class="brand-logo">Filmvisarna</router-link>

    <div class="container">
      <ul class="hide-on-med-and-down">
        <li><router-link to="/">Start</router-link></li>
        <li><router-link to="/movies">Filmer</router-link></li>
        <li><router-link to="/info">Biljett info</router-link></li>
        <li><router-link to="/about">Om oss</router-link></li>
        <li><router-link to="/booking">Boka</router-link></li>
      </ul>
      <ul class="hide-on-med-and-down right">
        <li><router-link to="/register" v-if="!isLoggedIn"> Bli medlem</router-link></li>
        <li><router-link to="/login" v-if="!isLoggedIn">{{loggedIn}}</router-link></li>

        <li><router-link to="/" v-if="isLoggedIn"> Mina sidor</router-link></li>
        <li><a v-if="isLoggedIn" v-on:click="logOut">{{loggedIn}}</a></li>
      </ul>
    </div>
    
  </nav>

  <ul class="sidenav sidenav-close" id="mobile-demo">
    <li><router-link to="/">Home</router-link></li>
    <li><router-link to="/movies">Filmer</router-link></li>
    <li><router-link to="/info">Biljett info</router-link></li>
    <li><router-link to="/about">Om oss</router-link></li>
    <li><div class="divider"></div></li>
    <li><router-link to="/register" v-if="!isLoggedIn"> Bli medlem</router-link></li>
    <li><router-link to="/login" v-if="!isLoggedIn">{{loggedIn}}</router-link></li>

    <li><router-link to="/" v-if="isLoggedIn"> Mina sidor</router-link></li>
    <li><a v-if="isLoggedIn" v-on:click="logOut">{{loggedIn}}</a></li>
  </ul>

</template>

<script>
import M from 'materialize-css'
export default {
  data(){
    return{
      isLoggedIn: false
    }
  },
  computed:{
    loggedIn(){
      if(this.isLoggedIn){
        return 'Logga ut'
      }
      return 'Logga in'
    }
  },
  methods:{
    logOut(){
      this.isLoggedIn = false
    }
  },
  mounted(){
    M.AutoInit();
  }
}
</script>

<style scoped>
  nav{
    background-color: var(--red);
  }
</style>