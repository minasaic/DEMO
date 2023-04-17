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
      <input type="id" name="id" v-model=valueId>
      <br><br>
      <span>名前:</span>
      <input type="id" name="username" v-model=valueName>
      <br><br>
      <span>PASS:</span>
      <input type="password" name="userpass" v-model=valuePass>
      <br><br>
      <span>userid:</span>
      <input type="id" name="userid" v-model=valueUserId>
      <br><br>
      <span>postid:</span>
      <input type="id" name="postid" v-model=valuePostId>
      <br><br>
      <span>写真:</span>
      <input type="file" @change="onFileSelected">
      <br><br>
      <span>コメント:</span>
      <input type="id" name="comment" v-model=valueComment>
      <br><br>
      
      <button type="button" @click="logins">ログイン</button>
      <button type="button" @click="create">新規登録</button>
      <button type="button" @click="home">ホーム</button>
      <button type="button" @click="post">新規投稿</button>
      <button type="button" @click="comment">コメント投稿</button>
      <button type="button" @click="follow">フォロー</button>
      <button type="button" @click="unfollow">フォロー解除</button>
      <button type="button" @click="like">いいね</button>
      <button type="button" @click="deletePost">投稿削除</button>
      </div>
    </div>
  </div>
</template>

<script>
import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{
      // testesId:null,
      // result:"",
      login:false,
      valueId:1,
      valueName:"",
      valuePass:"",
      valueUserId:1,
      valuePostId:1,
      valueComment:""
    }
  },
  methods:{
    onFileSelected(event) {
      const file = event.target.files[0]
      this.uploadFile(file)
    },
    uploadFile(file) {
      const formData = new FormData()
      formData.append('file', file)
    },
    logins(){
      Service.post("login",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
       //ログイン成功時の処理
       console.log(response);
       alert(this.valueId+this.valuePass)
      }).catch(error =>{
        console.log(error);
        alert("sdf")
      })
    },
    create(){
      Service.post("create",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    home(){
      Service.post("home",{
        id:this.valueId
      }).then(response =>{
        alert(response)
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
