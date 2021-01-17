<template>
  <h1>My page</h1>
  <div class="container">
    <div class="coming">
      <h4>Bokade biljetter</h4>
      <Ticket v-for="ticket of getTickets" :key="ticket.id" :id="ticket.id"/>
    </div>


    <div class="past">
      <h4>Historik</h4>
    </div>
  </div>
</template>

<script>
import Ticket from '../components/Ticket.vue'


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
  components:{
    Ticket
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