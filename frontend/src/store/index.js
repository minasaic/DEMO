import Vuex from 'vuex';
import Vue from 'vue';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: 0
  },
  mutations: {
    setId(state, id) {
      state.id = id;
    }
  },
  getters: {
    getId(state) {
      return state.id;
    }
  }
});

