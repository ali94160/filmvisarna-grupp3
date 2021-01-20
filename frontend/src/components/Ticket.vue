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
      <div class="infoModal">
        <span class="modal-close material-icons right"> close </span>
        <img class="transLogo right" src="../assets/transLogo.png" alt="" />
        <h3 class="center">Biljett</h3>
        <p class="movieTitleModal">{{ getMovieById(ticket.movieId).title }}</p>
        <br />
        <p>Sittplats{{ addS }}: {{ showSeats }}</p>
        <p>Antal: {{ ticket.seats.length }}</p>
        <p>Datum: {{ ticket.date }}</p>
        <p>Tid: {{ ticket.time }}</p>
      </div>
    </div>

    <div class="modal-footer">
      <span class="ID">{{ id }}</span>
      <img class="barCode left" src="../assets/blippCode.png" alt="" />
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
      let toReturn = ''
      let seatStrings = (this.ticket.seats + '').split(',')
      for(let seat of seatStrings){
        toReturn += 'rad ' + seat.split('')[0] + ' stol ' + seat + ', ' 
      }
      console.log(toReturn);
      return 'rad' + seatStrings
    },
    addS(){
      return (this.ticket.seats.length > 1 ? 'er': '')
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
.ticket p {
  margin-bottom: 0;
  font-family: Arial, Helvetica, sans-serif;
}

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
}
.modal-content {
  background: linear-gradient(red, var(--red));
}
.transLogo {
  opacity: 30%;
  width: 40%;
  position: absolute;
  right: 0;
  bottom: 1px;
}

.infoModal {
  color: white;
}

.modal .modal-footer {
  height: 60%;
  padding-right: 5%;
}

.ID {
  font-size: 20px;
}

.barCode {
  padding: 5px;
}

.movieTitleModal {
  font-weight: bold;
  font-size: 1.5em;
}

.modal-close {
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
</style>