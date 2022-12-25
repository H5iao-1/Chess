import Vue from 'vue';
import VueResource from 'vue-resource';
import ViewUI from 'view-design';
import App from '@/App.vue';
import store from './store';
import router from './router';
import 'view-design/dist/styles/iview.css';
import '@/permission';

Vue.config.productionTip = false;
Vue.use(VueResource);
Vue.use(ViewUI);
Vue.config.productionTip = false;
const options = {
    namespace: 'pro__',
    name: 'ls',
    storage: 'local'
};

Vue.directive('title', {
    inserted: function (el, binding) {
        document.title = el.dataset.title;
    }
});


new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App),
}).$mount('#app');
