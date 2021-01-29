<template>
<div class="searchBar">
  <input  v-model="search" placeholder="Sök efter en film.." type="text">
  </div>
  <MovieCard v-for="movie of movies" 
    :key="movie.id"
    :movie="movie"
  />
</template>

<script>
import MovieCard from '../components/MovieCard.vue'
export default {
  data(){
    return{
      search: ''
    }
  },
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

<style scoped>
.searchBar{
  margin: 0 auto;
  max-width: 50%;
}

</style>