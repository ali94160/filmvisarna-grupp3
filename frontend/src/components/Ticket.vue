<template>
  <div class="ticketWrap">
    <a
      class="ticket waves-effect waves-light modal-trigger"
      :href="'#' + modalId"
    >
      <img
        class="ticketPost left"
        :src="getMovieById(ticket.movieId).imgUrl"
        alt=""
      />
      <p class="movieTitle">{{ getMovieById(ticket.movieId).title }}</p>
      <p>{{ getTime }} {{ getDate }}</p>

      <p>Pris: {{ ticket.price }} kr</p>
    </a>
  </div>

  <div :id="modalId" class="modal">
    <div class="modal-content">
        <span class="modal-close material-icons right"> close </span>
        <TicketPicture v-for="seat of ticket.seats" :key="seat" :ticket="ticket" :movieTitle="getMovieById(ticket.movieId).title" :seat="seat"/>
    </div>
  </div>
</template>

<script>
import M from "materialize-css";
import TicketPicture from "./TicketPicture.vue"

export default {
  data() {
    return {
      modalId: null,
    };
  },
  props: ["id"],
  components:{
    TicketPicture
  },
  computed: {
    ticket() {
      return this.$store.state.currentUserTickets.filter(
        (p) => p.id == this.id
      )[0];
    },
    showSeats(){
      let toReturn = []
      let seatStrings = (this.ticket.seats + '').split(',')
      for(let seat of seatStrings){
        let s = seat.split('')
        if(s.length > 1){
          toReturn.push({row: s[0], seat: s[1]})
        }
      }
      console.log(toReturn);
      return toReturn
    },
    seatRow(seat){
      return (seat + '').split('')[0]
    },
    getDate(){
      let millis = this.ticket.timeStamp * 1000;
      return new Date(millis).toLocaleDateString();
    },
    getTime(){
      let millis = this.ticket.timeStamp * 1000;
      return new Date(millis).toGMTString().substring(17,22);
    },
  },
  methods: {
    getMovieById(movieId) {
      return this.$store.state.movies.filter((p) => p.id == movieId)[0];
    },
    getId() {
      return this.id;
    },
    clicked() {
      console.log(this.id);
    }
  },
  mounted() {
    M.AutoInit();
    this.modalId = "modal" + this.id;
  },
};
</script>

<style scoped>
.ticket {
  padding: 5px;
  display: block;

  border-radius: 0.3vw;
  opacity: 80%;
  height: fit-content;
  width: 80%;
  color: var(--light);
  margin: 0 auto;
  margin-bottom: 2vh;
  background: linear-gradient(var(--red), rgb(80, 20, 20));
}

.ticketPost {
  padding-right: 10px;

  border-radius: 3px;
  width: 17%;
  opacity: 80%;
}

.ticket:hover {
  background: linear-gradient(var(--red), rgb(209, 11, 11));
  cursor: pointer;
  opacity: 100%;
  zoom: 1.1;
}

.movieTitle {
  font-size: 1.2em;
  font-weight: bold;
  margin-top: 0;
}

.modal {
  color: black;
  font-size: 1.5em;
  width: 90%;
  max-width: 800px;
  min-width: 300px;
  border-radius: 10px;
}
.modal-content {
  background: var(--darkgrey);
  color: white;
}

.modal-close {
  position: absolute;
  z-index: 1;
  right: 5%;
  opacity: 80%;
  font-size: 30px;
  margin-bottom: 15px;
}

.modal-close:hover {
  font-weight: bolder;
  opacity: 100%;
}


</style>