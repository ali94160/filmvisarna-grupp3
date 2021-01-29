<template>
  <div @click="select" class="seat">
    <span class="material-icons"
    :class="{active: isClicked, booked: isBooked}">
      event_seat
    </span>    
  </div>
</template>

<script>
export default {
  props: ['row', 'col'],
  data() {
  return {
    isClicked: false,
    isBooked: false,
    selectedChairs: []
  }
},
  methods: {
    select() {
      console.log('you have clicked on ', this.row, this.col)
      this.isClicked = !this.isClicked; //change color of chair on click
      this.selectedChairs.push([this.row, this.col]);
      console.log(this.selectedChairs)
    }
  }
}
</script>

<style scoped>
.material-icons {
  font-size: 1.5rem;
  padding: 2px;
}

div > .seat {
  display: inline-block;
}

div > .seat:hover {
  cursor: pointer;
  opacity: 70%;
}

.active{
  color: yellow;
}

.booked {
  color: red;
}
</style>