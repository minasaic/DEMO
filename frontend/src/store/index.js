import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: 0,
    name: null,
    userId: null,
    profile: null,
    mypage: null,
  },
  actions:{
    setid(context,value){
      context.commit('SETID',value)
    },
    
  },
  mutations: {
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
    }
    // MYPAGE(state,object){
    //   return state.mypage = object
    // }
  },
  getters: {
    // setId(state,value) {
    //   state.id = value
    //   console.log(state.id)
    //   return state.id;
    // }
  }
});

