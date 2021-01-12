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
      </ul>
      <ul class="hide-on-med-and-down right">
        <li>
          <router-link to="/register" v-if="!online"> Bli medlem</router-link>
        </li>
        <li><router-link to="/login" v-if="!online">Logga in</router-link></li>
        <li><router-link class="userOnline" to="/mypage" v-if="online">{{currentUser.firstName}}</router-link></li>
        <li><router-link to="/mypage" v-if="online">Mina sidor</router-link></li>
        <li><a @click="logOut" v-if="online">Logga ut</a></li>
      </ul>
    </div>
  </nav>

  <ul class="sidenav sidenav-close" id="mobile-demo">
    <li><router-link to="/">Home</router-link></li>
    <li><router-link to="/movies">Filmer</router-link></li>
    <li><router-link to="/info">Biljett info</router-link></li>
    <li><router-link to="/about">Om oss</router-link></li>
    <li><div class="divider"></div></li>
    <li><router-link v-if="!online" to="/register">Bli medlem</router-link></li>
    <li><router-link v-if="!online" to="/login">logga in</router-link></li>
  </ul>
</template>

<script>
import M from "materialize-css";
export default {
  data() {
    return {};
  },
  computed: {
    online() {
      return this.$store.state.online;
    },
    currentUser(){
      return this.$store.state.currentUser[0];
    }
  },
  methods:{
    async logOut(){
      await this.$router.push('/');
      window.location.reload()
    }
  },
  mounted() {
    M.AutoInit();
  },
};
</script>

<style scoped>
nav {
  background-color: var(--red);
}
.userOnline::before {
  content: "•";
  margin-right: 8px;
  color: rgb(57, 207, 57);
  font-weight: bolder;
  font-size: 1rem;
}
</style>