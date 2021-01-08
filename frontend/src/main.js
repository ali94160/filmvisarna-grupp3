import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
//import store from "./js/store.js";
import router from "./js/router.js";
import Material from "materialize-css";
import "materialize-css/dist/css/materialize.css";
import "materialize-css";

let app = createApp(App);

//app.use(store);
app.use(router);
app.use(Material);

app.mount("#app");
