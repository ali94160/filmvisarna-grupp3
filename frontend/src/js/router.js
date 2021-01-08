import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue'
import Movies from '../views/Movies.vue'
import Info from '../views/Info.vue'
import About from '../views/About.vue'

const routes = [
  {
    name: "Home",
    path: "/",
    component: Home,
  },
  {
    name: "Movies",
    path: "/movies",
    component: Movies
  },
  {
    name: "Info",
    path: "/info",
    component: Info
  },
  {
    name: "About",
    path: "/about",
    component: About
  }
]

export default createRouter({
  history: createWebHistory(),
  routes
});