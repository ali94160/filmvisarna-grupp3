<template>
  <div class="container">
    <div class="moviePoster">
      <img :src="movie.imgUrl" :alt="movie.title" />
    </div>
    <div class="movieInfo">
      <h3>{{ movie.title }}</h3>
      <p><span class="titleTag">Åldersgräns:</span> {{ movie.age }}</p>

      <p>
        <span class="titleTag">Genre:</span>
        <span v-for="genre in movie.genre" :key="genre.id">
          {{ " " + genre }}
        </span>
      </p>

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

      <p class="description1"><span class="titleTag">Handling:</span></p>
      <p class="description2">{{ movie.description }}</p>

      <select @change="fullSalon" v-model="showId" class="selection" name="opt" id="name">
        <option value="" disabled selected>Välj datum/tid</option>
        <option v-for="show in shows" :key="show.id" :value="show.id">
          {{ show.date }} - kl. {{ show.time }}
        </option>
      </select>
      <p v-if="isFullSalon" class="fullSalonAlert">Fullbokat</p>
      <button @click="book" v-if="online" class="movieDetailsButton">
        Boka
      </button>
      <button @click="signIn" v-if="!online" class="signInToBook">
        Logga in för att boka
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showId: "",
      isFullSalon: false,
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
      return this.$store.state.online;
    },
    shows() {
      return this.$store.state.currentShows;
    },
  },
  methods: {
    signIn() {
      this.$router.push("/login");
    },

    book() {
      if (!this.isFullSalon && this.showId.length) {
        this.$router.push("/booking");
      }
    },

    async fullSalon() {
      if (this.showId) {
        let show = this.$store.state.currentShows.filter(
          (s) => s.id == this.showId
        )[0];
        this.$store.commit("setCurrentMovie", show);
        await this.$store.dispatch("fetchSpecificSalon", show.id);
        if (
          this.$store.state.currentMovie.seatsTaken !==
          this.$store.state.currentSalon.seats
        ) {
          this.isFullSalon = false;
        } else {
          this.isFullSalon = true;
        }
      }
    },
  },
  created() {
    this.$store.commit(
      "setSelectedMovie",
      this.$store.state.movies.filter((movie) => movie.id == this.id)[0]
    );
    this.$store.dispatch("fetchShows", this.id);
  },
  mounted() {
    window.scrollTo(0, 0);
  },
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
  min-width: 100px;
  width: 100%;
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