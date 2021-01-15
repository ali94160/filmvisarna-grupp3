<template>
  <h3>{{ salon.name }}</h3>
  <p>Du vill boka: {{ chairs }}</p>
  <div class="salon">
    <div
      v-for="(seatsPerRow, row) of salon.seatsPerRow"
      :key="row"
      class="rows"
    >
      <div
        v-for="(seat, col) of seatsPerRow"
        :key="col"
        class="seats"
        @click="changeColor(row, col)"
      >
        <span class="material-icons"> event_seat </span>
      </div>
    </div>
  </div>
  <div class="buttons">
    <button @click="clear">Rensa</button>
  </div>
</template>

<script>

/*      
 :class="{
            highlight: chairs.includes(index),
            booked: chairsBooked.includes(row, col),
          }"    
          */


  export default {
    data() {
      return{
        chairs: [],
        chairsBooked: [0][3],
        isHover: false
      }
    },
    methods:{
      changeColor(row, col){
          
          console.log('you have clicked on:', row, col)
      
        /*        
              changeColor(index){
        if(!this.chairsBooked.includes(index)){
          this.chairs.includes(index) ? this.chairs.splice(this.chairs.indexOf(index), 1) : this.chairs.push(index)
          */
        
      },
      clear(){
        this.chairs = [];
      }
    },
  computed: {
    salon() {
      console.log('ozkar', this.$store.state.currentSalon)
      return this.$store.state.currentSalon;
    },

  }
}

</script>

<style scoped>
* {
  user-select: none;
}
h3,
p {
  margin: 0 auto;
  text-align: center;
  padding: 5px 0;
}
.salon {
  text-align: center;
  margin: 0 auto;
  width: 100%;
}

.material-icons {
  font-size: 1.8rem;
  padding: 5px;
}

div > .seats {
  display: inline-block;
}

div > .seats:hover {
  cursor: pointer;
  opacity: 70%;
}

div.highlight {
  color: yellow;
}

div.booked {
  color: red;
}
div.booked:hover {
  cursor: default;
  opacity: 100%;
}
.buttons {
  margin: 0 auto;
  text-align: center;
}
</style>