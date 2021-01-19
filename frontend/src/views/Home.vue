<template>
<div class="movieList">
  <Carousel :movies="movies"/>
  <MovieItemList />
</div>
  
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue"
import TabsSwipe from "../components/TabsSwipe.vue"
import Carousel from "../components/Carousel.vue"

export default {
  components: {
    MovieItemList,
    TabsSwipe,
    Carousel
  },
  computed:{
    movies(){
      return this.$store.state.movies
    }
  }
};
</script>

<style scoped>
.movieList{
  margin-top: 8vw;
  margin-bottom: 18.2vw; /* For the footer */
}
</style>