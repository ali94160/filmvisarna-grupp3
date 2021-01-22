import { createStore } from 'vuex'

const state = {
  movies: [],
  salons: [],
  user: null,
  //holds the current show
  currentMovie: [],
  currentUserTickets: [],
  //all the shows for the specific movie
  currentShows: [],
  currentSalon: [],
  //the movie the user selected
  selectedMovie: {},
  selectedSeats: '',
}

const mutations = {
  setMovie(state, movies) {
    state.movies = movies
  },
  setSelectedMovie(state, movie) {
    state.selectedMovie = movie
  },
  setUser(state, user) {
    state.user = user
  },
  setSalons(state, list) {
    state.salons = list
  },
  setCurrentMovie(state, movie) {
    state.currentMovie = movie;
  },
  addCurrentUserTickets(state, tickets) {
    state.currentUserTickets.push(tickets)
  },
  setCurrentUserTickets(state, tickets) {
    state.currentUserTickets = tickets
  },
  setCurrentShow(state, shows) {
    state.currentShows = shows
  },
  setCurrentSalon(state, salon) {
    state.currentSalon = salon
  },
  setSelectedSeats(state, amount) {
    state.selectedSeats = amount;
  },
  decreaseSeats(state) {
    state.selectedSeats--;
  },
  clearSeats(state) {
    state.selectedSeats = '';
  }
}

const actions = {
  async fetchMovie(store) {
    let list = await fetch("/rest/movie");
    list = await list.json();
    store.commit("setMovie", list);
  },

  async register(store, member) {
    let newUser = {
      firstName: member.name,
      lastName:member.lastName,
      email: member.email,
      password: member.password,
    };
    let user = await fetch("/api/register", {
      method: "POST",
      body: JSON.stringify(newUser),
    });
    try {
      user = await user.json();
      store.commit("setUser", user);
    } catch (error) {
      console.warn("Bad credentials");
      return false
    }
  },

  async login(store, credentials) {
    let user = await fetch("/api/login", {
      method: "POST",
      body: JSON.stringify(credentials),
    });

    try {
      user = await user.json();
      console.log(user);
      store.commit("setUser", user);
    } catch (error) {
      console.warn("Bad credentials");
    }
  },

  async whoAmI(store) {
    let user = await fetch("/api/whoami");
    try {
      user = await user.json();
      console.log(user);
      store.commit("setUser", user);
    } catch (error) {}
    console.warn("Bad credentials");
  },

  async addTicket(store, ticket) {
    let newTicket = {
      price: ticket.price,
      date: ticket.date,
      time: ticket.time,
      seats: ticket.seats,
      childPrice: 65,
      seniorPrice: 75,
      userId: ticket.userId,
      showId: ticket.showId,
      movieId: state.selectedMovie.id,
    };
    let response = await fetch("rest/ticket", {
      method: "POST",
      body: JSON.stringify(newTicket),
    });
  },

  async fetchTicketsFromUser(store, userId) {
    let list = await fetch("/rest/user/get-ticket/" + userId);
    list = await list.json();
    if (list.length) {
      store.commit("setCurrentUserTickets", list);
    } else {
      store.commit("setCurrentUserTickets", []);
    }
  },

  async fetchShows(store, movieId) {
    let list = await fetch("/rest/movie/get-show/" + movieId);
    list = await list.json();
    if (list.length) {
      store.commit("setCurrentShow", list);
    } else {
      store.commit("setCurrentShow", []);
    }
  },

  async fetchSalons(store) {
    let list = await fetch("/rest/salon");
    list = await list.json();

    store.commit("setSalons", list);
  },

  async fetchSpecificSalon(store, showId) {
    let list = await fetch("/rest/show/get-salon/" + showId);
    try {
      list = await list.json();
      store.commit("setCurrentSalon", list);
    } catch (error) {
      console.warn("No show found");
    }
  },

  async increaseSeatsInShow(store, showInfo) {
    fetch(
      "/rest/show/increase-seats/" + showInfo.showId + "/" + showInfo.seats,
      {
        method: "PUT",
      }
    );
  },
};

export default createStore({ state, mutations, actions })