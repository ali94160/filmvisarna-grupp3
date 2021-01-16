<template>
<div class="movieList">
<MovieItemList />
</div>
  
</template>

<script>
import MovieItemList from "../components/MovieItemList.vue";
export default {
  components: {
    MovieItemList,
  },
};
</script>

<style scoped>
.movieList{
  margin-top: 8vw;
}
</style>