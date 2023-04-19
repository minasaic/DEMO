import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: 0
  },
  actions:{
    setid(context,value){
      context.commit('SETID',value)
    }
  },
  mutations: {
    SETID(state, value) {
      return state.id = value;
    }
  },
  getters: {
    // setId(state,value) {
    //   state.id = value
    //   console.log(state.id)
    //   return state.id;
    // }
  }
});

