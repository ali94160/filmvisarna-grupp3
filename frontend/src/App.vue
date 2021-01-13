<template>
  <Navbar />
  <router-view></router-view>
</template>

<script>
import Navbar from './baseComps/Navbar.vue'
export default {
  name: 'App',
  components: {
    Navbar
  },
  created(){
    this.$store.dispatch('fetchMovie');
    this.$store.dispatch('fetchSalon');
  }
}
</script>

<style scoped>
body {
  background: black;
}
</style>
