<template>
  <div class="ticketWrapper">
    <img class="transLogo right" src="../assets/transLogo.png" alt="" />
    <div class="ticketBody">
      <h5 class="date">{{ getDate }}</h5>
      <h3 class="ticket center">Biljett</h3>
      <h3 class="movieTitle">{{ movieTitle }}</h3>
      <div class="ticketBodyContent">
        <div>
          <p>Rad</p>
          <p>{{ row }}</p>
        </div>
        <div>
          <p>Stolnr</p>
          <p>{{ seatNumber }}</p>
        </div>
        <div>
          <p>Tid</p>
          <p>{{ getTime }}</p>
        </div>
      </div>
    </div>

    <div class="ticketFooter">
      <span class="ID right">{{ ticket.id }}</span>
      <img class="barCode left" src="../assets/blippCode.png" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  props: ['ticket', 'movieTitle', 'seat'],
  computed:{
    row(){
      if(this.seat > 9){
        return (this.seat + '').split('')[0]
      }
      return 1
    },
    seatNumber(){
      if(this.seat/100 > 1){
        return (this.seat + '').split('')[1] + (this.seat + '').split('')[2]
      }
      return (this.seat + '').split('')[1]
    },
    getDate(){
      let millis = this.ticket.timeStamp * 1000;
      return new Date(millis).toLocaleDateString();
    },
    getTime(){
      let millis = this.ticket.timeStamp * 1000;
      return new Date(millis).toGMTString().substring(17,22);
    }
  }
};
</script>

<style scoped>
.ticketWrapper {
  position: relative;
  display: grid;
  grid-template-rows: 5fr 1fr;
  border-radius: 10px;
  margin: calc(5px + 1vh) auto;
  padding: 0;
  width: 90%;
  font-size: calc(0.5vh + 1vw);
  height: calc(20vh + 10vw);
}

.ticketWrapper p{
  margin-bottom: 0;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 2em;
  padding: 0;
  margin: 0;
}

.ticketBody{
  background: linear-gradient(red, var(--red));
  border-radius: 10px 10px 0 0;
  width: 100%;
}

.ticketBody .ticketBodyContent{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  width: 70%;
}

.ticketBody .ticketBodyContent div{
  text-align: center;
}

.date{
  position: absolute;
  right: 5%;
  font-size: 1.5em;
}

.ticket{
  font-size: 2em;
}

.ticketFooter {
  color: black;
  background: white;
  border-radius: 0 0 10px 10px;
}

.ID {
  margin-top: 2%;
  margin-right: 2%;
  font-size: 1em;
}

.barCode {
  padding: 5px;
  width: 30%;
}

.movieTitle {
  padding-left: 5%;
  font-weight: bold;
  font-size: 2em;
}

.ticketWrapper p {
  z-index: 99;
  margin-bottom: 0;
}

.modal-footer{
  color: var(--darkgrey);
  width: 100%;
  background: white;
  height: 100px;
}

.transLogo {
  opacity: 30%;
  width: 40%;
  position: absolute;
  right: 0;
  bottom: 10%;
}

@media screen and (min-width: 800px) {
  .ticketBodyContent p {
    font-size: calc(16px + 6 * ((100vw - 320px) / 680));
  }
}

@media screen and (max-width: 800px) {
  .ticketWrapper{
    height: 30vh;
  }
}

@media screen and (max-width: 480px) {
  .ticketWrapper{
    height: 28vh;
  }

  .transLogo{
    bottom: 30%;
  }

  .barCode {
    width: 80%;
    float: none !important;
    display: block;
    margin: 0 auto;
  }
}
</style>
