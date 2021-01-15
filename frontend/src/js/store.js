import { createStore } from 'vuex'

const state = {
  movies: [],
  salons: [],
  user: [],
  currentUser: {},
  //holds the current show
  currentMovie: [],
  currentUserTickets: [],
  //all the shows for the specific movie
  currentShows: [],
  currentSalon: [],
  //the movie the user selected
  selectedMovie: {},
  online: false
}

const mutations = {
  setMovie(state, list) {
    state.movies = list
  },
  setSelectedMovie(state, movie) {
    state.selectedMovie = movie
  },
  setUser(state, list) {
    state.user = list
  },
  setSalons(state, list) {
    state.salons = list
  },
  addUser(state, user) {
    state.user.push(user);
  },
  setCurrentUser(state, user) {
    state.currentUser = user;
  },
  setOnline(state) {
    state.online = true;
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
  }
}

const actions = {
  async fetchMovie(store) {
    let list = await fetch('/rest/movie')
    list = await list.json();

    console.log(list);
    store.commit('setMovie', list)
  },
  async fetchUsers(store) {
    let list = await fetch('/rest/user')
    list = await list.json();

    console.log(list);
    store.commit('setUser', list)
  },
  async addUser(store, user) {
    let newUser =
    {
      firstName: user.name,
      lastName: user.lastName,
      email: user.email,
      password: user.password
    };
    let response = await fetch('/rest/user', {
      method: 'POST',
      body: JSON.stringify(newUser)
    })
    store.commit('addUser', newUser);
  },
  async addTicket(store, ticket) {
    let newTicket =
    {
      price: ticket.price,
      date: ticket.date,
      time: ticket.time,
      seats: ticket.seats,
      childPrice: 65,
      seniorPrice: 75,
      userId: ticket.userId,
      showId: ticket.showId
    };
    let response = await fetch('rest/ticket', {
      method: 'POST',
      body: JSON.stringify(newTicket)
    })
  },

  async fetchTicketsFromUser(store, userId) {
    let list = await fetch('/rest/user/get-ticket/' + userId)
    list = await list.json()
    if (list.length) {
      store.commit('setCurrentUserTickets', list)
    }
  },
  async fetchShows(store, movieId) {
    let list = await fetch('/rest/movie/get-show/' + movieId)
    list = await list.json();
    if (list.length) {
      store.commit('setCurrentShow', list);
    }
  },
  async fetchSalons(store) {
    let list = await fetch('/rest/salon')
    list = await list.json()

    store.commit('setSalons', list)
  },
  async fetchSpecificSalon(store, showId) {
    let list = await fetch('/rest/show/get-salon/' + showId)
    list = await list.json()

    store.commit('setCurrentSalon', list)

  }
}

export default createStore({ state, mutations, actions })