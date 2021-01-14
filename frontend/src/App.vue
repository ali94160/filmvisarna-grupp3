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
    this.$store.dispatch('fetchUsers');
    this.$store.dispatch('fetchSalons');
  }
}
</script>

<style scoped>
body{
  background: black;
}
</style>
