<template>
<div class="movieList">
<Carousel />
<MovieItemList />

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
</style>