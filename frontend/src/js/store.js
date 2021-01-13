import { createStore } from 'vuex'

const state = {
  movies: [],
  salons: [],
  user: []
}

const mutations = {
  setMovie(state, list) {
    state.movies = list
  },
  setSalon(state, list) {
    state.salons = list
  }
}

const actions = {
  async fetchMovie(store) {
    let list = await fetch('/rest/movie')
    list = await list.json();

    console.log(list);
    store.commit('setMovie', list)
  },

  async fetchSalon(store) {
    let list = await fetch('rest/salon')
    list = await list.json();

    console.log(list);
    store.commit('setSalon', list)
  }
}

export default createStore({ state, mutations, actions })