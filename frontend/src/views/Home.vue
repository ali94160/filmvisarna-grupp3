<template>
<div class="movieList">
  <div class="carousel carousel-slider center">
    <div class="carousel-item red white-text" href="#one!">
      <img :src="movies[0].backgroundUrl" alt="">
    </div>
    <div class="carousel-item amber white-text" href="#two!">
      <h2>Second Panel</h2>
      <p class="white-text">This is your second panel</p>
    </div>
    <div class="carousel-item green white-text" href="#three!">
      <h2>Third Panel</h2>
      <p class="white-text">This is your third panel</p>
    </div>
    <div class="carousel-item blue white-text" href="#four!">
      <h2>Fourth Panel</h2>
      <p class="white-text">This is your fourth panel</p>
    </div>
  </div>
  <MovieItemList />
</div>
  
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue"
import TabsSwipe from "../components/TabsSwipe.vue"
import M from 'materialize-css'

export default {
  components: {
    MovieItemList,
    TabsSwipe
  },
  computed:{
    movies(){
      return this.$store.state.movies
    }
  },
  mounted(){
    M.AutoInit()
  }
};
</script>

<style scoped>
.movieList{
  margin-top: 8vw;
  margin-bottom: 18.2vw; /* For the footer */
}
</style>