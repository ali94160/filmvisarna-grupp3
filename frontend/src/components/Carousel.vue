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
    },
    changeSlide(instance){
      console.log("before", instance);
      setTimeout(() => {
        console.log(instance)
        changeSlide(instance);
      },3000)
      console.log("after");
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
    indicators: true,
    });
    let indicatorItems = document.querySelectorAll('.carousel .indicator-item'),
      slideTime = 5000,
      activeClass = "active";
      
    setInterval(() => {
    indicatorItems.forEach(el => {
      if (el.classList.contains(activeClass)) {
        let sib = el.nextElementSibling;
        if (sib == null) {
          indicatorItems[0].click();
        } else {
          sib.click()
        }
      }
    });
  }, slideTime);
  }
}
</script>

<style>
.carousel{
  width: 70% !important;
  margin: 0 auto;
  height: 30vw !important;
}

</style>