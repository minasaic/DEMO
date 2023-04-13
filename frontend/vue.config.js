const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    port: 8081,
    proxy: {
      "/api": {
        target: "http://localhost:8080"
      }
    }
  },
  transpileDependencies: true,
})
