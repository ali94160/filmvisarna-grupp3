<template>
  <div v-if="movies" class="carousel carousel-slider center">
    <div class="carousel-item red white-text" href="#one!">
      <img src="../assets/harryPotter.png" alt="">
    </div>
    <div class="carousel-item amber white-text" href="#two!">
      <img src="../assets/harryPotter.png" alt="">
    </div>
    <div class="carousel-item green white-text" href="#three!">
      <img src="../assets/harryPotter.png" alt="">
    </div>
    <div class="carousel-item blue white-text" href="#four!">
      <img src="../assets/harryPotter.png" alt="">
    </div>
  </div>
</template>

<script>
import M from 'materialize-css'

export default {
  data(){
    return{
      movie: []
    }
  },
  methods:{
    setMovies(){
      this.movies = this.$store.state.movies
    }
  },
  computed:{
    movies(){
      return this.$store.state.movies
    }
  },
  mounted(){
    let elems = document.querySelectorAll(".carousel");
    let instance = M.Carousel.init(elems, {
    fullWidth: true,
    indicators: true
  });
  }
}
</script>

<style>
.carousel{
  width: 70% !important;
  margin: 0 auto;
  height: 20vh !important;
}

</style>