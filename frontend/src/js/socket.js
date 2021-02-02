import store from "./store.js";

let ws;
connect();

function connect() {
  ws = new WebSocket("ws://localhost:4000/watch-collections");
  console.log("Connecting WebSocket");

  ws.onopen = () => {
    console.log("Connected");
  };

  ws.onmessage = (messageEvent) => {
    const watchData = JSON.parse(messageEvent.data);

    // deconstruct model, event and data from watchData
    const { model, event, data } = watchData;

    if (model == "Show") {
      if (event == "update") {
        if (store.state.currentMovie.id === data[0].id) {
          store.commit('setShowById', data[0]);

        }
      }
    }
  };

  ws.onclose = () => {
    console.log("Disconnected");

    // attempt reconnect
    setTimeout(() => {
      connect();
    }, 2000);
  };
}
