import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import Material from "materialize-css";
import "materialize-css/dist/css/materialize.css";
import "materialize-css";


let app = createApp(App)
app.use(Material)
app.mount('#app')
