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

    console.log(watchData);

    if (model == "Show") {
      if (event == "update") {
        console.log("Show updated");
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
