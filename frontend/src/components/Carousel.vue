<template>
  <div v-if="movies" class="carousel carousel-slider center">
    <div class="carousel-item white-text" href="#one!">
        <button @click="guideMe('8Ai8Vq6mWIYfJEVxqnc_O')" id="btnSize" class="carousel-fixed-item btn center transparent" >Boka</button>
      <img src="../assets/carousel-harry-potter.png" alt="">
    </div>
      <div class="carousel-item white-text" href="">
        <button @click="guideMe('gtR4rG_xXGSqvFUbYFotn')" id="btnSize" class="carousel-fixed-item btn center transparent" >Boka</button>
        <img src="../assets/carousel-batman.png" alt="">
    </div>

    <div class="carousel-item white-text height" href="">
      <button @click="guideMe('M66J4NuEIMJuoKgpQ6f0x')" id="btnSize" class="carousel-fixed-item btn center transparent" >Boka</button>
      <img src="../assets/carousel-frozen.jpg" alt="">
    </div>

    <div class="carousel-item amber white-text" href="">
      <router-link to="/about">
        <button id="btnSize" class="toInfo btn right transparent" >Se våra salonger!</button>
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
    guideMe(id){
      this.$router.push('/moviedetails/'+id)
    },
    changeSlide(instance){
      setTimeout(() => {
        changeSlide(instance);
      },3000)
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

.carousel-fixed-item{
  top: 50%;
  margin: 0 auto;
  height: 10%;
  width: 10%;
  font-weight: bolder;
}

#btnSize{
  font-size: 1.5em;
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