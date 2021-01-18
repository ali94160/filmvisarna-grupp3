<template>
  <a class="ticket waves-effect waves-light modal-trigger" :href="'#' + modalId">
      <p> {{ ticket.time }} {{ ticket.date }} </p>
      <hr>
      <p class="movieTitle">{{ getMovieById(ticket.movieId).title }}</p>
      <p>Pris: {{ ticket.price }} kr</p>
  </a>

  <div :id="modalId" class="modal">
    <div class="modal-content">
      <span class="modal-close right">x</span>
      <h3 class="center">Biljett</h3>
      <p>Film: {{getMovieById(ticket.movieId).title}}</p>
      <br>
      <p>Plats: </p>
      <p>Datum: {{ ticket.date }}</p>
      <p>Tid: {{ ticket.time }}</p>
    </div>
    <div class="modal-footer">
      <img src="../assets/blippCode.png" alt="">
      <p>{{id}}</p>
    </div>
  </div>

</template>

<script>
import M from 'materialize-css'

export default {
  data(){
    return{
      modalId: null
    }
  },
  props: ['id'],
  computed: {
    ticket(){
      return this.$store.state.currentUserTickets.filter(p => p.id == this.id)[0]
    }
  },
  methods:{
    getMovieById(movieId) {
      return this.$store.state.movies.filter(p => p.id == movieId)[0]
    },
    getId(){
      return this.id
    },
    clicked(){
      console.log(this.id);
    }
  },
  mounted(){
    M.AutoInit()
    this.modalId = "modal" + this.id
  }
}
</script>

<style scoped>
.ticket{
  display: block;
  border: 1px solid var(--lightgrey);
  border-radius: 1vw;
  box-shadow: 0 0 5px 1px;
  height: calc(100px + 10vh);
  width: 80%;
  color: var(--light);
  margin: 0 auto;
  margin-bottom: 2vh;
}

.ticket:hover{
  cursor: pointer;
  opacity: 70%;
}

.movieTitle{
  font-size: 1.5em;
  font-weight: bold;
}

.modal{
  color: black;
  font-size: 1.5em;
}

.modal .modal-footer{
  height: 30%;
  padding-right: 5%;
}

</style>