<template>
  <h1>Movies</h1>
  <MovieCard v-for="movie of movies" 
  :key="movie.id"
  :movie="movie"
/>
</template>

<script>
import MovieCard from '../components/MovieCard.vue'
export default {
  components:{
    MovieCard
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