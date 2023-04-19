<template>
  <div>
      <img src="../assets/profile.png" alt="LOGO" width="100" height="100">
      <h1>アカウント：{{ username }}</h1>
      <h2>フォロワー：{{ followerCount }}</h2>
      <h2>フォロウィン：{{ followingCount }}</h2>
      過去の投稿一覧<br>
      <img src="" alt="過去の投稿">
      <img src="" alt="過去の投稿">
  </div>
  
</template>
<script>
import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{
      username: null,
      followerCount: null,
      followingCount: null
    }
  },
  methods:{
    deletePost(){
      Service.post("deletePost",{
      }).then(response =>{
        console.log(response)
      }).catch(error =>{
        alert(error)
      })
    }
  }
}
</script>
