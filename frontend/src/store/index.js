import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    pageBoolean: false,
    signSigu: true,
    id: 0,
    name: null,
    userId: null,
    profile: null,
    userData: null
  },
  actions:{
    setid(context,value){
      context.commit('SETID',value);
    },
  },
  mutations: {
    SETSIGNSIGU(state, value) {
      return state.signSigu = value;
    },
    SETPAGEBOOLEAN(state, value){
      return state.pageBoolean = value;
    },
    SETID(state, value) {
      return state.id = value;
    },
    SETNAME(state, value) {
      return state.name = value;
    },
    SETUSERID(state, value) {
      return state.userId = value;
    },
    SETPROFILE(state,value){
      return state.profile =  value;
    },
    SETUSERDATA(state, value) {
      return state.userData = value;
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

