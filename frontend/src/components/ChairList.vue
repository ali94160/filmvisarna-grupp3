<template>
  <div class="h3Div">
    <h3>{{ salon.name }}</h3>
    <div>
      <p>{{ movie.title }}</p>
      <p>{{ getDate() }} kl. {{ getTime() }}</p>
    </div>
    <span>FILMDUK</span>
  </div>
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
        :class="{
            active: check(row, col),
            booked: checkBooked(row, col),
        }"
        
      >
        <span
          class="material-icons"
        >
          event_seat
        </span>
      </div>
    </div>
  </div>
  <div class="buttons">
    <button @click="clear">Rensa</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chairs: [],
      selectedChairs: [],
      isHover: false,
      movie: ''
    };
  },
  emits: ['decreaseValues','clear','updateSelectedChairs'],
  methods: {
    changeColor(row, col) {
      this.showSelectedSeats;
      if (this.checkBooked(row, col)) {
        return;
      }
      let add = true;
      for (let i = 0; i < this.selectedChairs.length; i++) {
          if (
            this.selectedChairs[i][0] === row &&
            this.selectedChairs[i][1] === col
          ) {
            this.selectedChairs.splice(i, 1);
            this.$store.commit("decreaseSeats");
            this.$emit("decreaseValues");
            add = false;
            return;
          }
      }
      if (add) {

        this.selectedChairs.push([row, col]);
        this.$store.commit("setSelectedSeatsAmount", this.selectedChairs.length);
      }
    },
    clear() {
      this.showSelectedSeats;
      this.selectedChairs = [];
      this.$store.commit("setSelectedSeatsAmount", 0);
      this.$emit("clear");
    },
    check(row, col) {
      this.showSelectedSeats;
      let check = false;
        for (let i = 0; i < this.selectedChairs.length; i++) {
            if (
              this.selectedChairs[i][0] === row &&
              this.selectedChairs[i][1] === col
            ) {
              check = true;
            }
        }
      
      return check;
    },
    checkBooked(row, col) {
      this.showSelectedSeats;
      let booked = false;
      if (this.show.seatsTaken) {
        for (let i = 0; i < this.show.seatsTaken.length; i++) {
          if (this.show.seatsTaken[i] < 10) {
            if ("0" + this.show.seatsTaken[i] + "" === row + "" + col)
              booked = true;
          } else if (this.show.seatsTaken[i] + "" === row + "" + col) {
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
          this.check(row, col, true)       
        }
        return booked;
      }
    },
    getDate(){
      let millis = this.show.timeStamp * 1000;
      return new Date(millis).toLocaleDateString();
    },
    getTime(){
      let millis = this.show.timeStamp * 1000;
      return new Date(millis).toGMTString().substring(17,22);
    }
  },
  computed: {
    salon() {
      return this.$store.state.currentSalon;
    },
    show() {
      return this.$store.state.showById;
    },
    showSelectedSeats() {
      let sc = [];
      for (let s of this.selectedChairs) {
        sc.push(parseInt("" + s[0] + s[1]));
      }
      this.$emit("updateSelectedChairs", sc);
      return sc;
    }
  },
  async created() {
    await this.$store.dispatch('fetchShowById', this.$route.params.id)
    await this.$store.dispatch('fetchSpecificSalon',this.$route.params.id  )

    if (this.show.seatsTaken !== null) {
      if (
        !this.show.seatsTaken.includes(34) &&
        !this.show.seatsTaken.includes(35)
      ) {
        this.selectedChairs.push([3, 4]);
        this.selectedChairs.push([3, 5]);
        this.$store.commit("setselectedSeatsAmount", this.selectedChairs.length);
      } else if (
        !this.show.seatsTaken.includes(44) &&
        !this.show.seatsTaken.includes(45)
      ) {
        this.selectedChairs.push([4, 4]);
        this.selectedChairs.push([4, 5]);
        
        this.$store.commit("setSelectedSeatsAmount", this.selectedChairs.length);
      } else {
        this.$store.commit("setSelectedSeatsAmount", 0);
      }
    } else {
      this.selectedChairs.push([3, 4]);
      this.selectedChairs.push([3, 5]);
      this.$store.commit("setSelectedSeatsAmount", this.selectedChairs.length);
    }

    this.movie = this.$store.state.movies.filter((movie) => movie.id == this.show.movieId)[0]
  }
};
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
.active {
  color: #4caf50;
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
  font-size: 1.5rem;
  padding: 2px;
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

.buttons {
  margin: 0 auto;
  text-align: center;
}
.booked {
  color: red;
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

@media only screen and (max-width: 700px){

  .material-icons{
    font-size: 4.8vw;
    padding: 2px;
  }
}
</style>