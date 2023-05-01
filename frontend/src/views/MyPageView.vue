<template>
  <div id="main">
    <img :src="profilea()" alt="プロフィール画像" width="100" height="100">
    <button @click="showModal = true">
      <img  class="photo" src="../assets/set.png" alt="LOGO" width="20" height="20">
    </button>
    <option-modal-view 
      v-if="showModal" 
      :title="modalTitle" 
      @close="showModal = false" 
      @save="showModal = false">
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
    <div class="postContainer">
      <div v-for="(postTable) in postTables " :key="postTable.id">
        <img class="postImg" @click="showMyPageComponent = postTable" :src="getVueCliUrl(postTable.image)" alt="投稿画像">
      </div>
      <MyPageComponent 
        v-show="showMyPageComponent" 
        v-if="showMyPageComponent"
        :id="showMyPageComponent.id" 
        :postImgName="showMyPageComponent.image"
        :caption="showMyPageComponent.caption" 
        :likesCount="showMyPageComponent.likes" 
        @close="showMyPageComponent = null"
        />
        <!-- @update-likes="updateLikes($event, showMyPageComponent.id) -->
        <div v-if="showMyPageComponent" class="overlay" @click="showMyPageComponent = null"></div>
    </div>
  </div>
</template>
<script>
import { Service } from "@/service/service"
import MyPageComponent from "../components/MyPageComponent.vue"
import store from "@/store"
import OptionModalView from "../components/OptionModalView.vue"

export default {
  name: 'MyPageView',
  components: {
    MyPageComponent,
    OptionModalView
  },
  created() {       //このページになったら自動で動くもの
    // this.getProfile()
    this.myPage()
    this.getFollowerCount()
  },
  data() {
    return {
      followerCount: null,
      followingCount: null,
      postTables: null,
      showModal: false,
      showMyPageComponent: null,
      modalTitle: 'アカウント情報変更',
      profile: null
    }
  },
  methods: {
    myPage() {
      Service.post("mypage", store.state.id).then(response => {
        console.log(response);
        this.postTables = response.data;
        // this.profile = require('../assets/'+ store.state.profile);
      }).catch(error => {
        alert(error)
      })
    },
    getFollowerCount() {
      Service.post("followdata", store.state.id).then(response => {
        console.log(response);
        this.followingCount = response.data[0];
        this.followerCount = response.data[1];
      }).catch(error => {
        alert(error)
      })
    },
    saveModal() {
      // モーダルの保存処理を実行する
      this.showModal = false
    },
    deletePost() {
      Service.post("deletePost", {
      }).then(response => {
        console.log(response)
      }).catch(error => {
        alert(error)
      })
    },
    updateLikes(likes, postId) {
      const postIndex = this.postTables.findIndex(post => post.id === postId);
      this.postTables[postIndex].likes = likes;
    },
    profilea() {
      if (store.state.profile !== null) {
        return require('../assets/' + store.state.profile);
      } else {
        return '';
      }
    },
    getVueCliUrl(imgUrl) {
      return require(`../assets/post/${imgUrl}`);
    },
  }
}
</script>
<style>
.postContainer {
  display: flex;
  flex-wrap: wrap;
}

.postImg {
  width: 200px;
  height: 200px;
  margin: 10px;
  object-fit: cover;     /* 画像サイズを無理やり調整せず、正方形に切り取る */
  cursor: pointer;        /* 画像をボタンみたいにボタン、マウスが画像に移動したら指の様になる*/
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 1;
}
</style>