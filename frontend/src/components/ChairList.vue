<template>
  <div class="h3Div">
    <h3>{{ salon.name }}</h3>
    <span>FILMDUK</span>
  </div>
  <div class="salon">
    <div
      v-for="(seatsPerRow, row) of salon.seatsPerRow"
      :key="row"
      class="rows"
    >
      <ChairItem
        v-for="(seat, col) of seatsPerRow"
        :key="col"
        :col="col"
        :row="row"
        :booked="isBooked(row, col)"
        :class="{booked: isBooked(row, col)}"
        @click="addToList(row, col)"
      >
      </ChairItem>
    </div>
  </div>
  <div class="buttons">
    <button @click="clear">Rensa</button>
  </div>
</template>

<script>
import ChairItem from './ChairItem.vue'
export default {
  components:{
    ChairItem,
  },
  emits: ['decreaseValues','clear','updateSelectedChairs'],
  data() {
    return {
      selectedChairs: [],
    }
  }, //end of data
  methods: {
    addToList(row, col) {
      let add = true;
      for (let i = 0; i < this.selectedChairs.length; i++) {
          if (this.selectedChairs[i] === row + '' + col) {
            this.selectedChairs.splice(i, 1);
            this.$store.commit("decreaseSeats");
            this.$emit("decreaseValues");
            add = false;
            return;
          }
      }
      if (add && !this.isBooked(row, col)) {
        this.selectedChairs.push(row + '' + col);
        this.$store.commit("setSelectedSeatsAmount", this.selectedChairs.length);
      }

    },
    clear() {
      this.showSelectedSeats;
      this.selectedChairs = [];
      this.$store.commit("setSelectedSeatsAmount", 0);
      this.$emit("clear"); //booking.vue
    },
    isBooked(row, col) {
      this.showSelectedSeats;
      let booked = false;
      if (this.movie.seatsTaken) {
        for (let i = 0; i < this.movie.seatsTaken.length; i++) {
          if (this.movie.seatsTaken[i] < 10) {
            if ("0" + this.movie.seatsTaken[i] + "" === row + "" + col)
              booked = true;
          } else if (this.movie.seatsTaken[i] + "" === row + "" + col) {
            booked = true;
          }
        }
        if(booked){ 
          let holder = row + '' + col
          for(let i = 0; i < this.showSelectedSeats.length; i++){
            if(this.showSelectedSeats[i] == holder){
                this.selectedChairs.splice(i,1);
                this.$store.commit('setSelectedSeatsAmount', this.$store.state.selectedSeatsAmount - 1)
            }
          }        
        }
        return booked;
      }
    }
  }, //end of methods
  computed: {
    salon() {
      return this.$store.state.currentSalon;
    },
    movie() {
      console.log('change happen');
      return this.$store.state.showById;
    },
    showSelectedSeats() {
      let sc = [];
      for (let s of this.selectedChairs) {
        sc.push(parseInt("" + s[0] + s[1]));
      }
      this.$emit("updateSelectedChairs", sc);
      return sc;
    },
  },
  created() {
   
  } //end of created
}
</script>

<style scoped>
* {
  user-select: none;
}
.h3Div span {
  display: block;
  text-align: center;
  color: black;
  background: white;
  margin: 10px auto;
  width: 80%;
}
.h3Div{
  margin-bottom: 30px;
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

.buttons {
  margin: 0 auto;
  text-align: center;
}

.booked:hover {
  opacity: 100% !important;
  cursor: default !important;
}
button {
  text-align: center;
  display: block;
  background: var(--green);
  text-transform: uppercase;
  font-weight: 20px;
  color: white;
  padding: 2px;
  margin: 10px auto;
  border-radius: 2px;
  border: none;
  width: 80px;
  height: 25px;
  font-size: 14px;
  transition: background-color 0.2s ease-out 0s;
  letter-spacing: 0.8px;
  outline: none;
}

button:hover {
  background: crimson;
  cursor: pointer;
}

.booked {
  color: red;
}

@media only screen and (max-width: 700px){

  .material-icons{
    font-size: 4.8vw;
    padding: 2px;
  }
}
</style>