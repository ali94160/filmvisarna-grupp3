<template>
  <div class="infoModal">
    <img class="transLogo right" src="../assets/transLogo.png" alt="" />
    <div class="ticketBody">
      <h5>{{ ticket.date }}</h5>
      <h3 class="center">Biljett</h3>
      <h3 class="movieTitleModal">{{ movieTitle }}</h3>
      <div class="ticketBodyContent">
        <div>
          <p>Rad</p>
          <p>{{ seat }}</p>
        </div>
        <div>
          <p>Stolnr</p>
          <p>{{ seat }}</p>
        </div>
        <div>
          <p>Tid</p>
          <p>{{ ticket.time }}</p>
        </div>
      </div>
    </div>

    <div class="ticketFooter">
      <span class="ID right">{{ id }}</span>
      <img class="barCode left" src="../assets/blippCode.png" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  props: ['ticket', 'movieTitle']
};
</script>

<style scoped>
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

.ticketBody .ticketBodyContent{
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

.transLogo {
  opacity: 30%;
  width: 40%;
  position: absolute;
  right: 0;
  bottom: 1px;
}
</style>
