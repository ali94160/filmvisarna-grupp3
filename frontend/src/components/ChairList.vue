<template>
  <div v-for="salon of salons" :key="salon.id" class="container">
    <h3>{{ salon.name }}</h3>
    <p>Du vill boka: {{ chairs }}</p>
    <div class="salon">
      <div
        v-for="(seat, index) of salon.seats"
        :key="index"
        class="seats"
        :class="{
          highlight: chairs.includes(index),
          booked: chairsBooked.includes(index),
        }"
        @click="changeColor(index)"
      >
        <span class="material-icons"> event_seat </span>
      </div>
    </div>
  </div>
</template>

<script>


  export default {
    data() {
      return{
        chairs: [],
        chairsBooked: [3,4,5],
        isHover: false
      }
    },
    methods:{
      changeColor(index){
        if(!this.chairsBooked.includes(index)){
          this.chairs.includes(index) ? this.chairs.splice(this.chairs.indexOf(index), 1) : this.chairs.push(index)
          console.log('you have clicked on:', this.chairs)
        }
        
      }
    },
  computed: {
    salons() {
      return this.$store.state.salons;
    }
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
}
.salon {
  text-align: center;
  margin: 0 auto;
  width: 30rem;
  display: grid;
  grid-template-columns: repeat(9, 1fr);
}

.material-icons {
  font-size: 1.8rem;
  padding: 5px;
}

.seats {
  margin: 0 auto;
}

.seats > div {
  margin: 0;
}

div > .seats:hover {
  cursor: pointer;
  opacity: 70%;
}

div.highlight {
  background: yellow;
}

div.booked {
  background: red;
}
</style>