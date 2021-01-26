<template>
  <nav class="nav-wrapper">
    <a href="#" data-target="mobile-demo" class="sidenav-trigger">
      <i class="material-icons">menu</i>
    </a>

    <router-link to="/" class="brand-logo">
      <img class="loggan" :src="loggan" /><a class="logoName">Filmvisarna</a>
    </router-link>

    <div class="container">
      <ul class="hide-on-med-and-down">
        <li><router-link to="/">Start</router-link></li>
        <li><router-link to="/movies">Filmer</router-link></li>
        <li><router-link to="/info">Biljett info</router-link></li>
        <li><router-link to="/about">Om oss</router-link></li>
      </ul>
      <ul class="hide-on-med-and-down rightNav">
        <li>
          <router-link to="/register" v-if="!online"> Bli medlem</router-link>
        </li>
        <li><router-link to="/login" v-if="!online">Logga in</router-link></li>
        <li>
          <router-link class="userOnline" to="/mypage" v-if="online">{{
            currentUser.firstName
          }}</router-link>
        </li>
        <li>
          <router-link to="/mypage" v-if="online">Mina sidor</router-link>
        </li>
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
    <li>
      <router-link to="/register" v-if="!online"> Bli medlem</router-link>
    </li>
    <li><router-link to="/login" v-if="!online">Logga in</router-link></li>
    <li>
      <router-link class="userOnline" to="/mypage" v-if="online">{{
        currentUser.firstName
      }}</router-link>
    </li>
    <li><router-link to="/mypage" v-if="online">Mina sidor</router-link></li>
    <li><a @click="logOut" v-if="online">Logga ut</a></li>
  </ul>
</template>

<script>
import M from "materialize-css";
export default {
  data() {
    return {
      loggan: "https://i.postimg.cc/SK9Ch5R1/logo-Nyaste-1.png",
    };
  },
  computed: {
    online() {
      console.log("navbar user update");
      return this.$store.state.user != null;
    },
    currentUser() {
      return this.$store.state.user;
    },
  },
  methods: {
    async logOut() {
      await this.$router.push("/");
      M.toast({ html: "✓ Utloggning lyckades", classes: "color: red" });
      fetch("api/logout");
      this.$store.commit("setUser", null);
    },
  },
  mounted() {
    M.AutoInit();
  },
};
</script>

<style scoped>
.logoName {
  position: relative;
  top: -22px;
  font-size: 22px;
}

.loggan {
  margin: 10px 5px 10px 5px;
  max-width: 40px;
}

nav {
  background-color: var(--red);
}

.rightNav {
  position: absolute;
  right: 0;
}
.userOnline::before {
  content: "•";
  margin-right: 8px;
  color: rgb(57, 207, 57);
  font-weight: bolder;
  font-size: 1rem;
}

@media screen and (max-width: 360px) {
  .logoName {
    display: none;
  }
}
</style>
