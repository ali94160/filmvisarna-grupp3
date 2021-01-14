<template>
  <h1>My page</h1>
  <p>{{ getTickets }}</p>
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
      'fetchTicketsFromUser', this.$store.state.currentUser[0].id)
  }
}
</script>

<style>

</style>