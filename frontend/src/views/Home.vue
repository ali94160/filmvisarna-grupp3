<template>
<div class="movieList">
  <TabsSwipe />
  <MovieItemList />
</div>
  
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue"
import TabsSwipe from "../components/TabsSwipe.vue"

export default {
  components: {
    MovieItemList,
    TabsSwipe,
    Carousel
  },
};
</script>

<style scoped>
.movieList{
  margin-top: 8vw;
  margin-bottom: 18.2vw; /* For the footer */
}
</style>