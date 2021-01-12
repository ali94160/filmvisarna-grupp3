<template>
<div class="container">
  <div class="moviePoster">
    <img :src="movie.imgUrl" :alt="movie.title" />
  </div>
  <div class="movieInfo">
    <button class="movieDetailsButton">Boka</button>
    <h3>{{ movie.title }}</h3>
    <p>{{ movie.description }}</p>
  </div>
  <div class="trailerDiv">
  <iframe class="movieTrailer" :src="movie.trailerUrl" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
  </div>
</div>

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
  grid-gap: 30px;
  margin: 0 auto;
  margin-top: 10vh;
  margin-bottom: 10vh;
  background-color: #333;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0px 0px 15px 1px rgb(61, 61, 61);
}
.moviePoster img {
  max-width: 400px;
}
.movieDetailsButton{
  display: block;
  float: right;
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  width: 120px;
  height: 50px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: bold;
  font-size: large;
  background-color: rgb(238, 238, 238);
}
.movieTrailer{
 width: 60vw; 
 height: 60vh;
}
.trailerDiv{
  display: block;
  margin: 0 auto;
  grid-column: 1/3;
  grid-row: 2/3;
}
</style>