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
    <b>アカウント：{{ username }}</b>
    <br>
    <b>フォロワー：{{ followerCount }}</b>
    <br>
    <b>フォロウィン：{{ followingCount }}</b>
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
  name: 'App',
  components: {
    MyPageComponent,
    OptionModalView
  },
  data(){
    return{
      username: store.state.name,
      followerCount: null,
      followingCount: null,
      postTables: null,
      showModal: false,
      modalTitle: 'アカウント情報変更'
    }
  },
  created(){       //このページになったら自動で動くもの
    this.myPage()
    this.getFollowerCount()
  },
  methods:{
    myPage(){
      Service.post("mypage",store.state.id).then(response => {
        console.log(response);
        this.postTables = response.data;
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
  }
}
</script>