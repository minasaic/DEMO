<template>
  <div id="main">
      <img src="" alt="プロフィール画像" width="100" height="100">
      <button @click="showModal = true">設定</button>
      <option-modal-view 
        v-if="showModal" 
        :title="modalTitle" 
        @close="showModal = false" 
        @save="saveModal">
      </option-modal-view>
      <br>
      <h1>アカウント：{{ username }}</h1>
      <h2>フォロワー：{{ followerCount }}</h2>
      <h2>フォロウィン：{{ followingCount }}</h2>
      過去の投稿一覧<hr>
      postsテーブル     {{ postTables }}      
      <div v-for="(postTable) in postTables " :key="postTable.id">
        forが動いた
        <GalleryComponent 
          :id="postTable.id"
          :postImgName="getImageUrl(postTable.image)" 
          :caption="postTable.caption"

          />
      </div>

  </div>
  
</template>
<script>
import {Service} from "@/service/service"
import GalleryComponent from "./GalleryComponent.vue"
import store from "@/store"
import OptionModalView from "./OptionModalView.vue"

export default {
  name: 'App',
  components: {
    GalleryComponent,
    OptionModalView
  },
  data(){
    return{
      username: null,
      followerCount: null,
      followingCount: null,
      postTables: null,
      showModal: false,
      modalTitle: 'アカウント情報変更'
    }
  },
  created(){
    this.myPage()
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

    },
    myPage(){
      // alert('やたろ！！！！！')
      Service.post("mypage",store.state.id).then(response =>{
        console.log(response);
        this.postTables = response.data;
      }).catch(error =>{
        alert(error)
      })
    },
    saveModal () {
            // モーダルの保存処理を実行する
            this.showModal = false
    },
  }
}
</script>

<style>
#main {
  box-sizing: border-box;
  margin-left: 220px;
  padding: 20px 40px;
}
</style>