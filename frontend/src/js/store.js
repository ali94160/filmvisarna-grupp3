import { createStore } from 'vuex'

const state = {
  movies: [],
  user: [],
  currentUser: [],
  currentMovie: [],
  currentUserTickets: [],
  online: false 
}

const mutations = {
  setMovie(state, list) { 
    state.movies = list
  },
  setUser(state, list) {
    state.user = list
  },
  addUser(state, user) {
    state.user.push(user);
  },
  setCurrentUser(state, user) {
    state.currentUser.push(user);
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
  async fetchTicketsFromUser(store, userId) { 
    let list = await fetch('/rest/user/get-ticket/' + userId)
    list = await list.json()
    store.commit('setCurrentUserTickets', list)
  }
}

export default createStore({ state, mutations, actions })