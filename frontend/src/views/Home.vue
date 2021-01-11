<template>
  <h1>Home page</h1>
  <div class="container">
    <div v-for="movie of movies" :key="movie.id">
      <img :src="movie.imgUrl" alt="">
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
    }
  },
  computed: {
    movies(){
      return this.$store.state.movies
    }
  }
}
</script>

<style scoped>
.container{
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: 2vw;
  padding: 20px;
  text-align: center;
}

img{
  width: 100%;
  cursor: pointer;
  border-radius: 10px;
}

img:hover{
  opacity: 70%;
}

</style>