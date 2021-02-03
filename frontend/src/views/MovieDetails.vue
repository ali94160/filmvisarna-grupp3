<template>
  <div v-if="isMovieFetched">
    <img class="backgroundImage" :src="movie.backgroundUrl" alt="" />
    <div class="container">
      <div class="moviePoster">
        <img :src="movie.imgUrl" :alt="movie.title" class="right" />
      </div>
      <div class="movieInfo">
        <div class="movieInfo2">
          <div class="titleDiv">
            <h3>{{ movie.title }}</h3>
          </div>

          <p><span class="titleTag">Åldersgräns:</span> {{ movie.age }}</p>

          <p>
            <span class="titleTag">Genre:</span>
            <span v-for="genre in movie.genre" :key="genre.id">
              {{ " " + genre }}
            </span>
          </p>
        </div>
      </div>
    </div>
    <div class="descriptionWrapper">
      <div class="moreMovieInfo">
        <p class="description1"><span class="titleTag">Handling:</span></p>
        <p class="description2">{{ movie.description }}</p>

        <p><span class="titleTag">Språk:</span> {{ movie.languages[0] }}</p>
        <p><span class="titleTag">Undertext:</span> {{ movie.subtitles[0] }}</p>
        <p class="actors"><span class="titleTag">Skådespelare:</span></p>
        <span
          class="movieActors"
          v-for="(actor, index) in movie.actors"
          :key="index"
        >
          {{ actor }},  
        </span>

        <p>
          <span class="titleTag">Regissör:</span>
          <span v-for="(director, index) in movie.directors" :key="index">
            {{ " " + director + " " }}</span
          >
        </p>
      </div>

      <div class="selectionWrapper">
        <select
          @change="fullSalon"
          v-model="showId"
          class="selection"
          name="opt"
          id="name"
        >
          <option value="" disabled selected>Välj datum/tid</option>
          <option v-for="show in shows" :key="show.id" :value="show.id">
            {{ getDate(show) }} kl: {{getTime(show)}}
          </option>
        </select>
        <div v-if="isFullSalon" class="fullSalonAlert">Fullbokad</div>
        <button @click="book" v-if="online" class="movieDetailsButton">
          Boka
        </button>
        <button @click="signIn" v-if="!online" class="signInToBook">
          Logga in för att boka
        </button>
      </div>
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
  data() {
    return {
      showId: "",
      isFullSalon: false,
      isMovieFetched: false
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
    movie() {
      return this.$store.state.movies.filter((movie) => movie.id == this.id)[0];
    },
    online() {
      return this.$store.state.user != null;
    },
    shows() {
      let filteredShows = []
      for(let show of this.$store.state.currentShows){
        if((show.timeStamp * 1000) > new Date().getTime()){
            filteredShows.push(show);
        }
      }
      return filteredShows;
    },
  },
  methods: {
    signIn() {
      this.$router.push("/login");
    },

    book() {
      if (!this.isFullSalon && this.showId.length) {
        this.$router.push("/booking/" + this.showId);
      }     
    },
    getDate(show){
      let millis = show.timeStamp * 1000;
      return new Date(millis).toLocaleDateString();
    },
    getTime(show){
      let millis = show.timeStamp * 1000;
      return new Date(millis).toGMTString().substring(17,22);
    },

    async fullSalon() {
      if (this.showId) {
        let show = this.$store.state.currentShows.filter(
          (s) => s.id == this.showId
        )[0];
        this.$store.commit("setCurrentMovie", show);
        await this.$store.dispatch("fetchSpecificSalon", show.id);
        if(!this.$store.state.currentMovie.seatsTaken){
          this.isFullSalon = false;
          return;
        }
        if (
          this.$store.state.currentMovie.seatsTaken.length !==
          this.$store.state.currentSalon.seats
        ) {
          this.isFullSalon = false;
        } else {
          this.isFullSalon = true;
        }
      }
    },
  },
  async created() {
    if(!this.$store.state.movies.length){
      await this.$store.dispatch('whoAmI')
      await this.$store.dispatch("fetchMovie");
      await this.$store.dispatch("fetchSalons");
    }
    await this.$store.commit(
      "setSelectedMovie",
      this.$store.state.movies.filter((movie) => movie.id == this.id)[0]
    );
    await this.$store.dispatch("fetchShows", this.id);
    this.isMovieFetched = true
  },
  mounted() {
    window.scrollTo(0, 0);
  },

};
</script>

<style scoped>
.backgroundImage {
  width: 100%;
}

.selectionWrapper {
  margin-top: 10px;
  justify-content: start;
}

.moreMovieInfo {
  margin-bottom: 20px;
}

.descriptionWrapper {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  -ms-grid-column-align: center;
  max-width: 60%;
  margin: 0 auto;
}

.container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);

  grid-gap: 2vw;
  margin: 0 auto;

  margin-top: -40vh;
  margin-bottom: 10vh;
  background: none;
  padding: 10px;
  border-radius: 10px;
}

.titleDiv {
  width: fit-content;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif;
}

.movieInfo {
  width: 600px;
  margin-top: 68px;
  width: 44vw;
}

.movieInfo p {
  margin-top: 7px;
  margin-bottom: 4px;
}

.moviePoster img {
  margin-left: 120px;
  margin-top: 30px;
  min-width: 100px;
  width: 150px;
  border-radius: 10px;
  box-shadow: 0 0 7px 0.1px #7e7e7e;
}
.titleTag {
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
.movieInfo2{
  width: fit-content;
  background-color: rgba(43, 43, 43, 0.452);
  box-shadow: 0 0 15px 10px rgba(43, 43, 43, 0.466);
  border-radius: 5px;
}
.movieDetailsButton {
  display: block;
  background: rgb(148, 1, 1);
  color: white;
  padding: 10px;
  margin: 10px auto;
  border-radius: 4px;
  border: none;
  width: 220px;
  height: 50px;
  font-family: Georgia, "Times New Roman", Times, serif;
  font-weight: bold;
  font-size: large;
  outline: none;
}
.signInToBook {
  display: block;
  background: rgb(148, 1, 1);
  color: white;
  padding: 10px;
  margin: 10px auto;
  border-radius: 4px;
  border: none;
  width: 220px;
  height: 50px;
  font-family: Georgia, "Times New Roman", Times, serif;
  font-weight: bold;
  font-size: large;
  outline: none;
  
}
.movieDetailsButton:hover,
.signInToBook:hover {
  background: crimson;
  cursor: pointer;
}
.movieTrailer {
  display: block;
  margin: 0 auto;
  width: 20vw;
  height: 55vh;
  border-radius: 10px;
}
.trailerDiv {
  display: block;
  margin: 0 auto;
  width: 80%;
  height: 35vw;
  margin-bottom: 30px;
}

.trailerDiv iframe{
  width: 100%;
  height: 100%;
}

.selection {
  font-weight: bold;
  outline: none;
  display: block;
  width: 220px;
  margin: 10px auto;
  margin-top: 30px;
  padding: 5px;
  height: 40px;
  border-radius: 4px;
  text-align: center;
  cursor: pointer;
  outline: none;
}
.selection:hover {
  opacity: 80%;
}

.fullSalonAlert {
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue",
    Helvetica, Arial, "Lucida Grande", sans-serif;
  font-size: 12px;
  color:white;
  background: var(--red);
  width: fit-content;
  margin: 0 auto;
  margin-top: 10px;
  padding: 10px 15px;
  text-transform: uppercase;
  text-align: center;
  border-radius: 5px;
  letter-spacing: 0.0px;
  font-weight: bold;
}

@media only screen and (max-width: 900px){
 .container{
  grid-template-columns: none;
  margin: 0 auto;
  padding: 0;
}

.moviePoster img {
  margin: 0;
  float: none !important;
  width: 100%;
}

.moviePoster {
  margin: 0 auto;
  width: 50%;
}

.movieInfo {
  float: none !important;
  width: 85%;
  margin: 0;
}

h3 {
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 1.5em;
  font-weight: bolder;
  margin: 0;
}
.movieInfo{
  width: 100%;

}
.movieInfo2{
  margin: 0 auto;
}
.descriptionWrapper {
  grid-template-columns: none;
  margin: 10px auto;
  max-width: 85%;
  }
.selectionWrapper {
  margin: 2px auto;
  margin-top: 10px;
}

.moreMovieInfo {
  margin-bottom: 0;
}

.selection {
  margin:0 auto;
}

.trailerDiv {
  margin-top: 20px;
  margin-bottom: 0;
}
.movieTrailer{
  width: 90vw;
  height: 37vw;
  margin: 0 auto;
}

}

</style>