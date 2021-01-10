import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue'
import Movies from '../views/Movies.vue'
import Info from '../views/Info.vue'
import About from '../views/About.vue'
import SignIn from '../views/SignIn.vue'
import Register from '../views/Register.vue'

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
  },
  {
    name: "SignIn",
    path: "/signin",
    component: SignIn
  },
  {
    name: "Register",
    path: "/register",
    component: Register
  }
]

export default createRouter({
  history: createWebHistory(),
  routes
});