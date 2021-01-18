<template>
  <div class="ticketWrap">
    <a
      class="ticket waves-effect waves-light modal-trigger"
      :href="'#' + modalId"
    >
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
        <p>Salong:</p>
        <p>Antal: {{ ticket.seats }}</p>
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
    },
  },
  mounted() {
    M.AutoInit();
    this.modalId = "modal" + this.id;
  },
};
</script>

<style scoped>
.ticket {
  padding: 10px;
  display: block;
  border: 1px solid var(--lightgrey);
  border-radius: 0.3vw;

  height: calc(70px + 10vh);
  width: 80%;
  color: var(--light);
  margin: 0 auto;
  margin-bottom: 2vh;
  background: linear-gradient(var(--red), crimson);
}

.ticket:hover {
  cursor: pointer;
  opacity: 70%;
}

.movieTitle {
  font-size: 1.5em;
  font-weight: bold;
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
  bottom: 5px;
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
  font-size: 30px;
}

.infoModal p {
  margin: 0;
}
</style>