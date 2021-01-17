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
      <p><span class="titleTag">Handling:</span> {{ movie.description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["movie"],
  methods: {
    goToDetails() {
      this.$router.push("/moviedetails/" + this.movie.id);
    },
  },
};
</script>

<style scoped>
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
  width: 100%;
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
    grid-template-columns: repeat(2, 1fr);
  }
}

</style>
