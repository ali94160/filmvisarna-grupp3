<template>
<div class="movieList">
  <div class="carousel-wrapper">
    <CarouselCSS />
  </div>
  <MovieItemList />
</div>
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue";
import CarouselCSS from '../components/CasouselCSS.vue';
export default {
  components: {
    MovieItemList,
    CarouselCSS
  },
};
</script>

<style scoped>
.movieList{
  margin-top: 1vw;
  margin-bottom: 18.2vw; /* For the footer */
  margin-left: 1vw;
  margin-right: 1cw;
}
.carousel-wrapper {
  width: 100%;
  padding: 0 10px;
  height: 100%;
}
</style>