<template>
  <div id="main">
    <img :src="profilea()" alt="プロフィール画像" width="100" height="100">
    <button @click="showModal = true">設定</button>
    <option-modal-view 
      v-if="showModal" 
      :title="modalTitle" 
      @close="showModal = false" 
      @save="saveModal">
    </option-modal-view>
    <br>
    <b>アカウント：{{ $store.state.name }}</b>
    <br>
    <b>フォロワー：{{ followerCount }}</b>
    <br>
    <b>フォロー：{{ followingCount }}</b>
    <br>
    過去の投稿一覧
    <hr>
    <!-- postsテーブル     {{ postTables }}       -->
    <div v-for="(postTable) in postTables " :key="postTable.id">
      <MyPageComponent 
        :id="postTable.id"
        :postImgName="postTable.image" 
        :caption="postTable.caption"
        :likesCount="postTable.likes"
        @update-likes="updateLikes($event,postTable.id)"
        />
    </div>
  </div>
</template>
<script>
import {Service} from "@/service/service"
import MyPageComponent from "../components/MyPageComponent.vue"
import store from "@/store"
import OptionModalView from "../components/OptionModalView.vue"

export default {
  name: 'MyPageView',
  components: {
    MyPageComponent,
    OptionModalView
  },
  created(){       //このページになったら自動で動くもの
    // this.getProfile()
    this.myPage()
    this.getFollowerCount()
  },
  data(){
    return{
      followerCount: null,
      followingCount: null,
      postTables: null,
      showModal: false,
      modalTitle: 'アカウント情報変更',
      profile: null
    }
  },
  methods:{
    myPage(){
      Service.post("mypage",store.state.id).then(response => {
        console.log(response);
        this.postTables = response.data;
        // this.profile = require('../assets/'+ store.state.profile);
      }).catch(error =>{
        alert(error)
      })
    },
    getFollowerCount(){
      Service.post("followdata",store.state.id).then(response => {
        console.log(response);
        this.followingCount = response.data[0];
        this.followerCount = response.data[1];
      }).catch(error => {
        alert(error)
      })
    },
    saveModal () {
      // モーダルの保存処理を実行する
      this.showModal = false
    },
    deletePost(){
      Service.post("deletePost",{
      }).then(response =>{
        console.log(response)
      }).catch(error =>{
        alert(error)
      })
    },
    updateLikes(likes,postId) {
      const postIndex = this.postTables.findIndex(post => post.id === postId);
      this.postTables[postIndex].likes = likes;    
    },
    profilea(){
      if(store.state.profile !== null){
        return require('../assets/'+ store.state.profile);
      } else {
        return '';
      }
    }
  }
}
</script>