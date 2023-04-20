<template>
  <div>
      <img src="../assets/profile.png" alt="LOGO" width="100" height="100">
      <h1>アカウント：{{ username }}</h1>
      <h2>フォロワー：{{ followerCount }}</h2>
      <h2>フォロウィン：{{ followingCount }}</h2>
      過去の投稿一覧<hr>
      <!-- postsテーブル     {{ $store.state.mypage }}       -->
      <div v-for="(post) in $store.state.mypage " :key="post.id">
        <GalleryComponent 
          :id="post.id"
          :imgFileName="getImageUrl(post.image)" 
          :caption="post.caption"
          />
      </div>

  </div>
  
</template>
<script>
import {Service} from "@/service/service"
import GalleryComponent from "./GalleryComponent.vue"
import store from "@/store"
export default {

  name: 'App',
  components: {
    GalleryComponent
  },
  
  data(){
    return{
      username: null,
      followerCount: null,
      followingCount: null,
      imgFileNames: store.state.mypage,
    //   message:[{
    //     id:1,name:'yataro',age:27
    //   },
    //   {
    //     id:2,name:'morimori',age:27
    //   },
    //   {
    //     id:3,name:'mina',age:26
    //   }
    // ],
      
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
    },
    getImageUrl(imagePath){
      return require(`../assets/${imagePath}`)

    }
  }
}
</script>
