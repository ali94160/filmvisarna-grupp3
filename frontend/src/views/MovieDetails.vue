<template>
<div class="container">
  <div class="moviePoster">
    <img :src="movie.imgUrl" :alt="movie.title" />
  </div>
  <div class="movieInfo">

    <h3>{{ movie.title }}</h3>
    <p><span>Åldersgräns:</span> {{ movie.age }}</p>
    <p><span>Språk:</span> {{ movie.languages[0] }}</p>
    <p><span>Undertext:</span> {{ movie.subtitles[0] }}</p>
    <p class="actors"><span>Skådespelare:</span></p>
    <span class="movieActors" v-for="actor in movie.actors" :key="actor.id"> {{ actor }}, </span>

    <p class="description1"><span>Handling:</span></p>
    <p class="description2">{{ movie.description }}</p>


    <button v-if="online" class="movieDetailsButton">Boka</button>
    <button @click="signIn" v-if="!online" class="signInToBook">Logga in</button>
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
    online(){
      return this.$store.state.online;
    }
  },
  methods:{
    signIn(){
      this.$router.push('/login');
    }
  }
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
  margin-left: 5%;
  max-width: 400px;
  border-radius: 10px;
  box-shadow: 0 0 7px 0.1px #7e7e7e;
}
span{
  font-weight: bolder;
  color: white;
}
.actors {
  margin-bottom: 2px;
}
.movieActors{
  font-weight: normal;
  margin-top: 2px;
}
.description1 {
  margin-bottom: 0;
}
.description2 {
  margin-top: 2px;
}
.movieInfo {
  margin-right: 5%;
}
.movieDetailsButton{
  display: block;
  float: right;
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  width: 220px;
  height: 50px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: bold;
  font-size: large;
  background-color: rgb(238, 238, 238);
}
.signInToBook{
  display: block;
  float: right;
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  width: 220px;
  height: 50px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: bold;
  font-size: large;
  background-color: rgb(238, 238, 238);
}
.movieDetailsButton:hover, .signInToBook:hover{
  opacity: 70%;
  cursor: pointer;
}
.movieTrailer{
 width: 65vw; 
 height: 65vh;
 border-radius: 10px;
}
.trailerDiv{
  display: block;
  margin: 0 auto;
  grid-column: 1/3;
  grid-row: 2/3;
}
.selection{
  display: block; 
  z-index: 100;
}
</style>