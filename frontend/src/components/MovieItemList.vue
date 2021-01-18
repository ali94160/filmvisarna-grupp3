<template>
  <div class="container">
    <MovieItem v-for="movie of movies" :key="movie.id" :movie="movie" />
  </div>
</template>

<script>
import MovieItem from './MovieItem.vue'
export default {
  
  components: {
  MovieItem
  },
  computed: {
    movies() {
      return this.$store.state.movies;
    }
  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: 2vw;
  padding: 0 20px;
  text-align: center;
  width: 100%;
}

@media only screen and (max-width: 960px){
  .container {
    grid-template-columns: repeat(4, 1fr);
  }
}

@media only screen and (max-width: 680px){
  .container {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media only screen and (max-width: 480px){
  .container {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>