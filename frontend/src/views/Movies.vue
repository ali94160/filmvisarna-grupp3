<template>
<div class="searchBar">
  <input v-model="search" placeholder="Sök efter en film.." type="text">
  </div>
  <MovieCard v-for="movie of filteredList" 
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
    },
    filteredList(){ 
      return this.$store.state.movies.filter(movie => {
        return movie.title.toLowerCase().includes(this.search.toLowerCase())
      })
  }
  }
}
</script>

<style scoped>
.searchBar{
  margin: 10px auto;
  max-width: 30%;
}
.searchBar > input{
  color: white;
  border-radius: 10px;
  padding: 10px;
  font-size: 1.5em;
}
</style>