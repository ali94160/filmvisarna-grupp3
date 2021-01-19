<template>
  <div class="grid">
    <Navbar />
    <div class="routerView">
      <router-view></router-view>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from "./baseComps/Navbar.vue";
import Footer from "./baseComps/Footer.vue";
export default {
  name: "App",
  components: {
    Navbar,
    Footer,
  },
  beforeCreate() {
    console.log("before");
    this.$store.dispatch("fetchMovie");
    this.$store.dispatch("fetchUsers");
    this.$store.dispatch("fetchSalons");
    console.log("after");
  }
};
</script>

<style scoped>
.grid{
  min-height: 100vh;
  display: grid;
  grid-template-rows: 64px 1fr 45px;
}
body {
  background: black;
}
</style>
