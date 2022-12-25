import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import chessBoard from './modules/chessBoard';
import user from './modules/user';
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    chessBoard,
    user,
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
});
