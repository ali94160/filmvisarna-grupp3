export default {
  proxy: {
    "/rest": {
      target: "http://localhost:4000",
      changeOrigin: true,
      ws: true,
    },
    "/api": {
      target: "http://localhost:4000",
      changeOrigin: true,
      ws: true,
    },
  },
};