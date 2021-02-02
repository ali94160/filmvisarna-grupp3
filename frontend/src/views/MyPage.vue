<template>
  <div class="container">
    <div class="coming">
      <h4>Bokningar</h4>

      <Ticket
        v-for="ticket of getTickets"
        :key="ticket.id"
        :id="ticket.id"
        :ticket="ticket"
      />
    </div>

    <div class="past">
      <h4>Historik</h4>
      <Ticket
        v-for="ticket of historyTickets"
        :key="ticket.id"
        :id="ticket.id"
        :ticket="ticket"
      />
    </div>
  </div>
</template>

<script>
import Ticket from "../components/Ticket.vue";

export default {
  data() {
    return {
      historyTickets: [],
    };
  },
  computed: {
    getTickets() {
      let filteredTickets = [];
      for (let ticket of this.$store.state.currentUserTickets) {
        if (ticket.timeStamp * 1000 > new Date().getTime()) {
          filteredTickets.push(ticket);
          filteredTickets.sort((a, b) => {
            return a.timeStamp - b.timeStamp;
          });
        } else {
          this.historyTickets.push(ticket);
        }
      }
      return filteredTickets;
    },
  },
  created() {
    this.$store.dispatch("fetchTicketsFromUser", this.$store.state.user.id);
  },
  components: {
    Ticket,
  },
};
</script>

<style scoped>
.past {
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

.coming {
  border-radius: 2%;
  background: rgb(29, 29, 29);
}

@media only screen and (max-width: 480px) {
  .container{
    grid-template-columns: none;
  }

  .container div {
    margin: 2vw;
  }
}
</style>