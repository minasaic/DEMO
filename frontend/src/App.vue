<template>
  <div id="app">

    <div v-if="login">
      <div id="title">
      <h1>      <img src="./assets/1nstagramlogo.png" alt="LOGO" width="150" height="100">
1nstagram</h1>
      </div>
      <nav>
        <span>メニュー</span>
        <br>
        <router-link to="/">Home</router-link>
        <br>
        <router-link to="/search">Search</router-link>
        <br>
        <router-link to="/create">Create</router-link>
        <br>
        <router-link to="/mypage">MyPage</router-link>
      </nav>
      <div id="change">
        <router-view/>
      </div>
    <!-- <div v-if="result">
      <span>{{ result }}</span>
    </div> -->
    </div>

    <div v-else>
      <div id="titles" style="text-align: center;">
      <h1>1nstagram</h1>
      <br><br><br><br>
      <img src="./assets/1nstagramlogo.png" alt="LOGO">
      <br><br><br><br>
      <span>ID:</span>
      <input type="id" name="userid" v-model=valueId>
      <br><br>
      <span>PASS:</span>
      <input type="password" name="userpass" v-model=valuePass>
      <br><br>
      <button type="button" @click="logins">ログイン</button>
      <button>新規登録</button>
      </div>
    </div>
  </div>
</template>

<script>
// import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{
      // testesId:null,
      // result:"",
      login:true,
      valueId:"",
      valuePass:""
    }
  },
  methods:{
    // search(){
    //   Service.get("testes/"+this.testesId).then(response => {
    //     if(!response.data){
    //       this.result="NO DATA"
    //     }else{
    //       this.result="NAME:" + response.data.name
    //     }
    //   })
    // },
    logins(){
      alert(this.valueId+this.valuePass)
    },
    yataro(){

    },
    mori(){

    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  margin-top: 60px;
}

#change {
  text-align: center;
}
</style>
