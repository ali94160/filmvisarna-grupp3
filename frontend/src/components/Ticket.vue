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
      <p>{{ ticket.time }} {{ ticket.date }}</p>

      <p>Pris: {{ ticket.price }} kr</p>
    </a>
  </div>

  <div :id="modalId" class="modal">
    <div class="modal-content">
        <span class="modal-close material-icons right"> close </span>
      <div class="infoModal" v-for="seat of ticket.seats" :key="seat">
        <img class="transLogo right" src="../assets/transLogo.png" alt="" />
        <div class="ticketBody">
          <h5> {{ticket.date}} </h5>
          <h3 class="center">Biljett</h3>
          <h3 class="movieTitleModal">{{ getMovieById(ticket.movieId).title }}</h3>
          <div class="ticketBodyContent">
            <p>Sittplats: rad stol</p>
            <p>Datum: {{ ticket.date }}</p>
            <p>Tid: {{ ticket.time }}</p>
          </div>
        </div>

        <div class="ticketFooter">
          <span class="ID right">{{ id }}</span>
          <img class="barCode left" src="../assets/blippCode.png" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import M from "materialize-css";

export default {
  data() {
    return {
      modalId: null,
    };
  },
  props: ["id"],
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
    }
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

.transLogo {
  opacity: 30%;
  width: 40%;
  position: absolute;
  right: 0;
  bottom: 1px;
}

.infoModal {
  position: relative;
  display: grid;
  grid-template-rows: 5fr 1fr;
  border-radius: 10px;
  margin: calc(5px + 1vh) auto;
  padding: 0;
  width: 90%;
  font-size: calc(0.5vh + 1vw);
}

.infoModal p {
  margin-bottom: 0;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 1em;
  padding: 0;
  margin: 0;
}

.ticketBody{
  background: linear-gradient(red, var(--red));
  border-radius: 10px 10px 0 0;
}

.ticketBody div{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}

.ticketBody h5{
  position: absolute;
  right: 5%;
}

.ticketFooter {
  color: black;
  background: white;
  border-radius: 0 0 10px 10px;
}

.ID {
  margin-top: 2%;
  margin-right: 2%;
  font-size: 0.8em;
}

.barCode {
  padding: 5px;
}

.movieTitleModal {
  font-weight: bold;
  font-size: 2em;
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

.infoModal p {
  z-index: 99;
  margin-bottom: 0;
}

.modal-footer{
  color: var(--darkgrey);
  width: 100%;
  background: white;
  height: 100px;
}
</style>