<template>
  <div v-if="movies" class="carousel carousel-slider center">
    <div class="carousel-item white-text" href="#one!">
      <router-link to="/movies">
        <button class="carousel-fixed-item btn center toMovies transparent" >Se vårt utbud!</button>
      </router-link>
      <img src="../assets/carousel-harry-potter.png" alt="">
    </div>
      <div class="carousel-item white-text" href="">
        <img src="../assets/carousel-batman.png" alt="">
    </div>

    <div class="carousel-item white-text height" href="">
      <img src="../assets/carousel-frozen.jpg" alt="">
    </div>

    <div class="carousel-item amber white-text" href="">
      <router-link to="/about">
        <button class="toInfo btn right transparent" >Se våra salonger!</button>
      </router-link>
      <img src="../assets/emptySalon.jpg" alt="">
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
    let slideTimeDefault = 10000
    let elems = document.querySelectorAll(".carousel");
    let instance = M.Carousel.init(elems, {
    fullWidth: true,
    indicators: true,
    onCycleTo: function(){
      console.log("slic", slideTimeDefault);
    }
    });
    let indicatorItems = document.querySelectorAll('.carousel .indicator-item'),
      slideTime = slideTimeDefault,
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
  }, slideTimeDefault);
  }
}
</script>

<style>
.carousel{
  width: 85% !important;
  margin: 3vh auto 5vh;
  height: 30vw !important;
}

.carousel .indicators{
  width: fit-content;
  margin: 0 auto !important;
}

.carousel-fixed-item.toMovies{
  top: 50%;
  margin: 0 auto;
}

.toInfo{
  position: absolute;
  right: 5%;
  top: 60%;
  margin: 0;
  font-weight: 700;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

img{
  width: 100%;
}

.height{
  height: 100%;
}

</style>