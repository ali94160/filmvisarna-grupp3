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
  created() {
    this.$store.dispatch("fetchMovie");
    this.$store.dispatch("fetchUsers");
    this.$store.dispatch("fetchSalons");
  },
};
</script>

<style scoped>

body {
  background: black;
}
</style>
