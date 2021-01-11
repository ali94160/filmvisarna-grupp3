<template>
  <h1>Home page</h1>
  <div v-for="movie of movies" :key="movie.id">
    <h1>{{movie.title}}</h1>
  </div>
</template>

<script>
export default {
  data(){
    return{
    }
  },
  computed: {
    movies(){
      return this.$store.state.movies
    }
  }
}
</script>

<style>

</style>