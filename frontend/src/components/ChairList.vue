<template>
  <h3>{{ salon.name }}</h3>
  <p>Du vill boka: {{ showSelectedSeats }}</p>
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
        <span
          class="material-icons"
          :class="{
            active: check(row, col),
            booked: checkBooked(row, col),
          }"
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
    };
  },
  methods: {
    changeColor(row, col) {
      if (this.checkBooked(row, col)) {
        return;
      }
      let add = true;
      for (let i = 0; i < this.selectedChairs.length; i++) {
        for (let j = 0; j < this.selectedChairs.length; j++) {
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
      }
      if (add) {
        this.selectedChairs.push([row, col]);
        this.$store.commit("setSelectedSeats", this.selectedChairs.length);
        this.$emit("increaseValue");
      }
    },
    clear() {
      this.selectedChairs = [];
      this.$store.commit("clearSeats");
      this.$emit("clear");
    },
    check(row, col) {
      let check = false;
      for (let i = 0; i < this.selectedChairs.length; i++) {
        for (let j = 0; j < this.selectedChairs.length; j++) {
          if (
            this.selectedChairs[i][0] === row &&
            this.selectedChairs[i][1] === col
          ) {
            check = true;
          }
        }
      }
      return check;
    },
    checkBooked(row, col) {
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
        return booked;
      }
    },
  },
  computed: {
    salon() {
      return this.$store.state.currentSalon;
    },
    movie() {
      return this.$store.state.currentMovie;
    },
    showSelectedSeats() {
      let sc = [];
      for (let s of this.selectedChairs) {
      sc.push(parseInt('' + (s[0] + 1) + (s[1] + 1)))
      }
      sc.sort((a, b) => {
        return a - b;
      });
      this.$emit("updateSelectedChairs", sc);
      return sc;
    },
  },
  created() {
    if(this.movie.seatsTaken !== null){
    if(!this.movie.seatsTaken.includes(34) && !this.movie.seatsTaken.includes(35) ){
        this.selectedChairs.push([3, 4]);
        this.selectedChairs.push([3, 5]);
       this.$store.commit("setSelectedSeats", this.selectedChairs.length);
    }else if(!this.movie.seatsTaken.includes(44) && !this.movie.seatsTaken.includes(45) ){
      this.selectedChairs.push([4, 4]);
        this.selectedChairs.push([4, 5]);
       this.$store.commit("setSelectedSeats", this.selectedChairs.length);
    }else if(!this.movie.seatsTaken.includes(54) && !this.movie.seatsTaken.includes(55)){
      this.selectedChairs.push([5, 5]);
        this.selectedChairs.push([5, 6]);
       this.$store.commit("setSelectedSeats", this.selectedChairs.length);
    }
    }else{
        this.selectedChairs.push([3, 4]);
        this.selectedChairs.push([3, 5]);
       this.$store.commit("setSelectedSeats", this.selectedChairs.length);
    }
  },
};
</script>

<style scoped>
* {
  user-select: none;
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

.buttons {
  margin: 0 auto;
  text-align: center;
}
.booked {
  cursor: default;
  color: red;
}
</style>