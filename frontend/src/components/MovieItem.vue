<template>
  <div @click="goToDetails" class="card">
    <img :src="movie.imgUrl" alt="" />
  </div>
</template>

<script>

export default {
  props: ['movie'],
  methods: {
    goToDetails() {
        // change route and add the current id as param
        this.$router.push('/moviedetails/' + this.movie.id)
      }
}
}
</script>

<style scoped>
img {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  cursor: pointer;
  border-radius: 10px;
}

img:hover {
  opacity: 70%;
}

.card {
  padding: 0;
  margin: 0;
  background: #161616;
}
</style>