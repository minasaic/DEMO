<template>
  <div id="app">
    <div v-if="login">
      <div id="title">
      <router-link to="/">
        <h1><img src="./assets/1nstagramlogo.png" alt="LOGO" width="150" height="100">1nstagram</h1>
        </router-link>
      </div>
      <div class="btn_hover">
        <nav>
          <span>メニュー</span>
          <br>
          <br>
          <img src="./assets/home.png" alt="LOGO" width="20" height="20">      
          <router-link @click="home" to="/home" >ホーム</router-link>
          <br>
          <br>
          <img src="./assets/search.png" alt="LOGO" width="20" height="20">
          <router-link to="/search">   検索</router-link>
          <br>
          <br>
          <img src="./assets/plus.png" alt="LOGO" width="20" height="20">
          <router-link to="/create" >   作成</router-link>
          <br>
          <br>
          <img src="./assets/profile.png" alt="LOGO" width="20" height="20">
          <router-link to="/mypage" > <button @click="mypage">mypage</button></router-link>
        </nav>
      </div>
      <div id="change">
        <router-view/>
      </div>
    </div>

    <div v-else>
      <div id="titles" style="text-align: center;">
      <h1>1nstagram</h1>
      <br><br><br><br>
      <img src="./assets/1nstagramlogo.png" alt="LOGO">
      <br><br><br><br>
      <span>ID:</span>
      <!-- <input type="id" name="id" v-model=valueId> -->
      <br><br>
      <span>名前:</span>
      <input type="id" name="username" v-model=valueName>
      <br><br>
      <span>PASS:</span>
      <input type="password" name="userpass" v-model=valuePass>
      <br><br>
      <button type="button" @click="logins">ログイン</button>
      <button type="button" @click="create">新規登録</button>
      
      </div>
    </div>
  </div>
</template>

<script>
import {Service} from "@/service/service"
import store from "./store"
// import { mapState } from "vuex"
export default {
  name: 'App',
  data(){
    return{
      login:false,
      valueId:0,
      valueName:"",
      valuePass:"",
      valueUserId:1,
      valuePostId:1,
      valueComment:""
    }
  },
  // computed:{
  //   ...mapState(['id']),
  // },
  methods: {
    //ログイン
    logins(){
      Service.post("login",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
       //ログイン成功時の処理(axios通信成功時)
       console.log(response);
       if(response.data){
        this.login = response.data.bool;
        store.commit('SETID',response.data.id);  //responseされたIdをストア内stateのidにセット
        alert('ID : ' + store.state.id +  '\n'  + 'PASSWORD : ' + this.valuePass);
       } else{
         alert("Wrong id or password.");
       }
      }).catch(error =>{
        console.log(error);
        alert("エラー起きました。")
      })

    },
    create(){
      Service.post("create",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
        console.log(response);
        if(response.data){
        this.login = response.data.bool;
        store.commit('SETID',response.data.id);  //responseされたIdをストア内stateのidにセット
        alert('アカウントが新規作成しました。'+'\nID : ' + store.state.id +  '\n'  + 'PASSWORD : ' + this.valuePass);
       } else{
         alert('アカウント作成できません。');
       }
      }).catch(error =>{
        console.log(error);
        alert('エラー起きました。')
      })
    },
    onFileSelected(event) {
      const file = event.target.files[0]
      this.uploadFile(file)
    },
    uploadFile(file) {
      const formData = new FormData()
      formData.append('file', file)
    },
    home(){
      Service.post("home",{
        id:store.state.id
      }).then(response =>{
        console.log(response);
        alert(response.data);
      }).catch(error =>{
        alert(error)
      })
    },
    post(){
      Service.post("post",{
        id:this.valueId,
        image:this.formData
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    comment(){
      Service.post("comment",{
        id:this.valueId,
        postId:this.valuePostId,
        comment:this.valueComment
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    follow(){
      Service.post("follow",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    unfollow(){
      Service.post("unfollow",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    like(){
      Service.post("like",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    deletePost(){
      Service.post("deletePost",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    mypage(){
      alert("崔baka")
      Service.post("mypage",1).then(response =>{
        console.log(response)
        store.commit('MYPAGE',response.data);
      }).catch(error =>{
        alert(error)
      })
    },
  }
}
</script>

<style scoped>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  margin-top: 60px;
}
#title a {
    font-family: Comic Sans MS; /* 筆記体のフォントを指定 */
    text-decoration: none; /* リンクの下線を無効にする */
    color: #090909; /* ボタンのデフォルトの文字色を指定 */
  }

nav a {
    
    text-decoration: none; /* リンクの下線を無効にする */
    color: #090909; /* ボタンのデフォルトの文字色を指定 */
    
  }
nav{
  font-family: Comic Sans MS; /* 筆記体のフォントを指定 */
}

.btn_hover {
  background-color: #ffffff;/*背景色*/
  -webkit-transition: all 0.3s ease;/*ふわっとさせる間隔 Google Chrome、Safari*/
  -moz-transition: all 0.3s ease;/*ふわっとさせる間隔 Firefox*/
  -ms-transition: all 0.3s ease;/*ふわっとさせる間隔 IE*/
  -o-transition: all 0.3s ease;/*ふわっとさせる間隔 Opera*/
  transition: all  0.3s ease;/*ふわっとさせる間隔*/
  padding:50px 0;/*文字と背景の余白*/
  width: 220px;/*ボタン幅*/
  color: #0c0c0c;/*文字の色*/
  text-align: left;/*文字を揃える位置*/
  border-radius: 1px;/*背景の角丸半径*/
  cursor: pointer;/*ホバー時にカーソルの形状をポインターに*/ /* カーソルを合わせた際にボタンの文字色を変える */
}
  /* マウスオーバーした際の背景 */
.btn_hover:hover {
  background-color :#e4e4e4;/*背景色*/
}

#change {
  text-align: center;
}

</style>