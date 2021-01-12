<template>
<div class="container">
  <div class="moviePoster">
    <img :src="movie.imgUrl" :alt="movie.title" />
  </div>
  <div class="movieInfo">
    <h3>{{ movie.title }}</h3>
    <p>{{ movie.description }}</p>
  <button class="movieDetailsButton">Boka</button>
  </div>
</div>

<iframe class="movieTrailer" :src="movie.trailerUrl" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</template>

<script>
export default {
  computed: {
    id() {
      return this.$route.params.id;
    },
    movie() {
      return this.$store.state.movies.filter((movie) => movie.id == this.id)[0];
    },
  },
};
</script>

<style scoped>
.container{
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  margin: 0 auto;
  margin-top: 10vh;
  margin-bottom: 10vh;
  background-color: #333;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0px 0px 15px 1px rgb(61, 61, 61);
}
.moviePoster img {
  display: grid;
  max-width: 400px;

}
.movieInfo{
  display: grid;
}
.movieDetailsButton{
  display: grid;
  grid-template-rows: repeat(1, 1fr);
}
.movieTrailer{
 width: 560px; 
 height: 315px;
}
</style>