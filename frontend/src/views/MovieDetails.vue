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
      <span class="movieActors" v-for="actor in movie.actors" :key="actor.id">
        {{ actor }},
      </span>

      <p class="description1"><span>Handling:</span></p>
      <p class="description2">{{ movie.description }}</p>

      <select
        v-on:change="fullSalon"
        v-model="showId"
        class="selection"
        name="opt"
        id="name"
      >
        <option value="" disabled selected>Välj datum/tid</option>
        <option v-for="show in shows" :key="show.id" :value="show.id">
          {{ show.date }} - kl. {{ show.time }}
        </option>
      </select>
      <p class="isFullSalonAlert" v-if="isFullSalon">Fullbokat</p>
      <button @click="book" v-if="online" class="movieDetailsButton">
        Boka
      </button>
      <button @click="signIn" v-if="!online" class="signInToBook">
        Logga in för att boka
      </button>
    </div>
    <div class="trailerDiv">
      <iframe
        class="movieTrailer"
        :src="movie.trailerUrl"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
      ></iframe>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      showId: '',
      isFullSalon: false
    }
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
    movie() {
      return this.$store.state.movies.filter((movie) => movie.id == this.id)[0];
    },
    online(){
      return this.$store.state.online;
    },
    shows(){
      return this.$store.state.currentShows;
    }
  },
  methods:{
    signIn(){
      this.$router.push('/login');
    },

    book(){ 
      if(!this.isFullSalon && this.showId.length){
        this.$router.push('/booking');
      }
    },

    async fullSalon(){
      let show = this.$store.state.currentShows.filter((s) => s.id == this.showId)[0];
      this.$store.commit('setCurrentMovie', show);
      await this.$store.dispatch('fetchSpecificSalon', show.id)

      if(this.$store.state.currentMovie.seatsTaken !== this.$store.state.currentSalon.seats){
        this.isFullSalon = false
      }
      else {
        this.isFullSalon = true
      }
    }
  },
  created(){
    this.$store.commit('setSelectedMovie',this.$store.state.movies.filter((movie) => movie.id == this.id)[0]);
    this.$store.dispatch('fetchShows', this.id);
  }
};
</script>

<style scoped>
.container {
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
span {
  font-weight: bolder;
  color: white;
}
.actors {
  margin-bottom: 2px;
}
.movieActors {
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
.movieDetailsButton {
  display: block;
  padding: 10px;
  margin: 10px auto;
  border-radius: 10px;
  width: 220px;
  height: 50px;
  font-family: Georgia, "Times New Roman", Times, serif;
  font-weight: bold;
  font-size: large;
  background-color: rgb(238, 238, 238);
}
.signInToBook {
  display: block;
  padding: 10px;
  margin: 10px auto;
  border-radius: 10px;
  width: 220px;
  height: 50px;
  font-family: Georgia, "Times New Roman", Times, serif;
  font-weight: bold;
  font-size: large;
  background-color: rgb(238, 238, 238);
}
.movieDetailsButton:hover,
.signInToBook:hover {
  opacity: 70%;
  cursor: pointer;
}
.movieTrailer {
  width: 65vw;
  height: 65vh;
  border-radius: 10px;
}
.trailerDiv {
  display: block;
  margin: 0 auto;
  grid-column: 1/3;
  grid-row: 2/3;
}
.selection {
  display: block;
  width: 220px;
  margin: 10px auto;
  margin-top: 30px;
  padding: 5px;
  height: 40px;
  border-radius: 10px;
  text-align: center;
}
</style>