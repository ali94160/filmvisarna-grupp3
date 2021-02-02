<template>
  <div @click="highlight" class="seat">
    <span class="material-icons"
    :class="{active: isClicked}"
    >
      event_seat
    </span>    
  </div>
</template>

<script>
export default {
  props: ['row', 'col', 'booked'],
  data() {
    return {
      isClicked: false,
  }
}, //end of data
  methods: {
    highlight() {
      if(!this.booked){
        this.isClicked = !this.isClicked; //change color of chair on click
      }
    },

 }, //end of methods

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

.active {
  color: #4caf50;
}


</style>