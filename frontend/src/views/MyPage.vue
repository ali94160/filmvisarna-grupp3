<template>
 
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
      'fetchTicketsFromUser', this.$store.state.user.id)
  },
  components:{
    Ticket
  }
}
</script>

<style scoped>
.past{
background: rgb(29, 29, 29);
border-radius: 2%;
margin-left: 10px;

}

.container {
  min-height: 88vh;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  width: 100%;
  padding: 20px;
}
h4 {
  margin: 15px auto;
  width: fit-content;
  border-bottom: solid rgba(255, 255, 255, 0.212) 2px;
 
}

.coming{
  border-radius: 2%;
  background: rgb(29, 29, 29);
}


</style>