<template>
<div class="movieList">
<Carousel />
<div class="posters">
  <h4>Aktuellt på bio</h4>
  <MovieItemList />
</div>

</div>
  
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue";
import Carousel from "../components/Carousel.vue"
export default {
  components: {
    MovieItemList,
    Carousel
  },
};
</script>

<style scoped>
.movieList{
  margin-top: 2vw;
  margin-bottom: 18.2vw; /* For the footer */
}
.posters{
  width: fit-content;
  margin: 0 auto;
}
.posters h4{
  margin-left: 20px;
  font-size: calc(16px + 2vw);
}
</style>