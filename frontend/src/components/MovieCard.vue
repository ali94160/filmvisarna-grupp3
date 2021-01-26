<template>
  <div @click="goToDetails" class="container">
    <img :src="movie.imgUrl" alt="" />
    <div>
      <h3>{{ movie.title }}</h3>
      <p><span class="titleTag">Åldersgräns:</span> {{ movie.age }}</p>
      <p>
        <span class="titleTag">Genre:</span>
        <span v-for="genre in movie.genre" :key="genre.is">{{
          " " + genre + " "
        }}</span>
      </p>
      <p><span class="titleTag">Språk:</span> {{ movie.languages[0] }}</p>
      <p>
        <span class="titleTag">Handling:</span>
        <span v-if="!smallWidth">{{ movie.description }}</span>
        <span v-else>{{ shortenText(movie.description, window.width/2) }}</span>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      window: {
          width: 0,
          height: 0
      }
    }
  },
  props: ["movie"],
  methods: {
    goToDetails() {
      this.$router.push("/moviedetails/" + this.movie.id);
    },
    handleResize() {
      this.window.width = window.innerWidth;
      this.window.height = window.innerHeight;
    },
    shortenText(text, length){
      let clamp = '...'
      let truncatedText = text
      if(text.length > length){
        truncatedText = text.slice(0, length)
        truncatedText = truncatedText.slice(0, truncatedText.lastIndexOf(' ')) + clamp
      }

      return truncatedText
    }
  },
  created(){
    this.windowWidth = window.innerWidth;
    window.addEventListener('resize', this.handleResize);
    this.handleResize();
  },
  destroyed() {
    window.removeEventListener('resize', this.handleResize);
  },
  computed:{
    smallWidth(){
        return this.window.width < 480
    }
  }

};
</script>

<style scoped>
.overflow{
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
}
.container {
  display: grid;
  grid-template-columns: 1fr 2fr;
  grid-gap: 3vw;
  margin: 0 auto;
  margin-top: 3vh;
  background-color: var(--lightgrey);
  border-radius: 10px;
  color: var(--darkgrey);
  cursor: pointer;
  font-size: calc(0.5vw + 12px);
  box-shadow: 0 0 5px 5px var(--lightgrey);
}

.container:hover {
  opacity: 70%;
}

.container div {
  padding-right: 1.5vw;
}

img {
  width: 90%;
  margin: 5%; 
  border-radius: 10px;
}

h3 {
  font-size: 2.8em;
}

p {
  font-size: 1em;
}

.titleTag {
  font-weight: bold;
  font-size: 1em;
}

@media only screen and (max-width: 480px){
  .container {
    grid-template-columns: none;
    padding: 0;
  }
  div img{
    width: 90%;
    margin: 10px auto;
    padding: 0;
  }
}

</style>
