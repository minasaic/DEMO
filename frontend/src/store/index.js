import Vuex from 'vuex';
import Vue from 'vue';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: null
  },
  mutations: {
    set_Id(state, id) {
      state.id = id;
    }
  },
  actions:{
    setId({commit},id){
        commit('SET_ID',id);
    }
  },
  getters: {
    getId(state) {
      return state.id;
    }
  }
});

