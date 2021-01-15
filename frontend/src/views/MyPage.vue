<template>
  <h1>My page</h1>
  <div class="container">
    <div class="coming">
      <h4>Bokade biljetter</h4>
      <div v-for="ticket of getTickets" :key="ticket.id">
        {{ ticket.price }} Price
        {{ getMovieById(ticket.movieId).title }}
      </div>
    </div>
    <div class="past">
      <h4>Historik</h4>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    getTickets(){
      return this.$store.state.currentUserTickets;
    }
  },
  created(){
    this.$store.dispatch(
      'fetchTicketsFromUser', this.$store.state.currentUser.id)
  },
  methods: {
    getMovieById(movieId) {
      return this.$store.state.movies.filter(p => p.id == movieId)[0]
    }

  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  width: 100%;
  padding: 20px;
}
h4 {
  text-align: center;
}
</style>