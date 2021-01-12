import { createStore } from 'vuex'

const state = {
  movies: [],
  user: [],
  online: false
}

const mutations = {
  setMovie(state, list) { 
    state.movies = list
  },


}

const actions = {
  async fetchMovie(store) { 
    let list = await fetch('/rest/movie')
    list = await list.json();

    console.log(list);
    store.commit('setMovie', list)
  }
}

export default createStore({ state, mutations, actions })