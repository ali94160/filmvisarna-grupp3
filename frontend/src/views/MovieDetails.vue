<template>
  <h1>Movie Detail Page</h1>
  <h3>{{movie.title}}</h3>
</template>

<script>
export default {
computed: {
  id() {
    return this.$route.params.id
  },
      movie() {
      return this.$store.state.movies.filter((movie) => movie.id == this.id)[0];
    },
}
}
</script>

<style scoped>

</style>