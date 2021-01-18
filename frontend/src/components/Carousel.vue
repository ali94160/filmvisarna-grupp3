<template>
  <div class="carousel carousel-slider center">
    <div class="carousel-item red white-text" href="#one!">
      <h2>{{ movies }}</h2>
    </div>
    <div class="carousel-item amber white-text" href="#two!">
      <h2>Second Panel</h2>
      <p class="white-text">This is your second panel</p>
    </div>
    <div class="carousel-item green white-text" href="#three!">
      <h2>Third Panel</h2>
      <p class="white-text">This is your third panel</p>
    </div>
    <div class="carousel-item blue white-text" href="#four!">
      <h2>Fourth Panel</h2>
      <p class="white-text">This is your fourth panel</p>
    </div>
  </div>
</template>

<script>

export default {
  data(){
    return{
      carouselInstance: null,
      dataFetched: false,
      movies: []
    }
  },
  async created(){
    this.movies = await this.$store.dispatch("fetchMovie");
    let elems = document.querySelectorAll('.carousel');
    this.carouselInstance = M.Carousel.init(elems,{
      fullWidth: true,
      indicators: true
    });
    console.log("mounted");
  }
}
</script>

<style>
.carousel{
  width: 50% !important;
  margin: 0 auto;
}
</style>