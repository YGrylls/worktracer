import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

Vue.config.productionTip = false;
axios.defaults.baseURL = "http://118.178.181.104:8080";
axios.defaults.withCredentials=true;
Vue.prototype.$http=axios;

Vue.use(ElementUI);

const {createProxyMiddleware} = require('http-proxy-middleware');
Vue.use(
    '/',
    createProxyMiddleware({
      target: 'http://118.178.181.104:8080',
      changeOrigin: true
    })
);


new Vue({
  el: '#app',
  render: h => h(App),
}).$mount('#app');


