<template>
  <div id="main" class="photo-gallery">
    <img v-if="profilea() !== null" :src="profilea()" class="round-image" alt="プロフィール画像">
    <p v-else>画像をアップロードしてください。</p>
    <button @click="showModal = true">
      <img class="photo" src="../assets/set.png" alt="LOGO" width="20" height="20">
    </button>
    <option-modal-view v-if="showModal" :title="modalTitle" @close="showModal = false" @save="showModal = false">
    </option-modal-view>
    <br>
    <b>アカウント：{{ $store.state.name }} </b>
    <br>
    <b>フォロワー：<button @click="showFollower = true"> {{ followerCount }} </button> 人 </b>
    <FollowerComponent v-show="showFollower" 
      @close="showFollower = false"
      @getFollowers="getFollowers"
      :followers="followers"/>
    <br>
    <b>フォロー：<button @click="showFollowing = true"> {{ followingCount }} </button> 人 </b>
    <FollowingComponent v-show="showFollowing" 
      @close="showFollowing = false"
      @getFollowings="getFollowings"
      :followings="followings"/>
    <br>
    投稿 &nbsp; {{ postCount }}件
    {{ followers }}
    <hr>
    <!-- postsテーブル     {{ postTables}}       -->
    <div class="photo-grid">
      <div v-for="(postTable, index) in postTables " :key="postTable.id"
        @click="showMyPages(index, postTable.id)">
        <img class="photo-grid-img" :src="getVueCliUrl(postTable.image)" alt="投稿画像">
      </div>
      <MyPageComponent v-show="showMyPageComponent" :postTableObject="postTableObject"
        :commentTableObject="commentTableObject" @close="showMyPageComponent = false" 
        @refresh-comment="showMyPages(clickImgIndex,postTableObject.id)"
        @refresh-likes="updateLikes(clickImgIndex)"
        />
      <div v-if="showMyPageComponent" class="overlay" @click="showMyPageComponent = null"></div>
    </div>
  </div>
</template>
<script>
import { Service } from "@/service/service"
import MyPageComponent from "../components/MyPageComponent.vue"
import store from "@/store"
import OptionModalView from "../components/OptionModalView.vue"
import FollowingComponent from "@/components/followingComponent.vue"
import FollowerComponent from "@/components/followerComponent.vue"

export default {
  name: 'MyPageView',
  components: {
    MyPageComponent,
    OptionModalView,
    FollowingComponent,
    FollowerComponent
  },
  created() {       //このページになったら自動で動くもの
    this.myPage()
    this.getFollowerCount()
    this.getPostCount()
  },
  data() {
    return {
      followerCount: null,
      followingCount: null,
      showFollower: false,
      showFollowing: false,
      followers: null,  //フォローワーのユーザ一覧
      followings: null, //フォローのユーザの一覧
      postTables: null,
      showModal: false,
      showMyPageComponent: null,
      postTableObject: null,
      commentTableObject: null,
      clickImgIndex: null,
      modalTitle: 'アカウント情報変更',
      postCount: null,

    }
  },
  methods: {
    myPage() {
      Service.post("mypage", store.state.id).then(response => {
        console.log(response);
        this.postTables = response.data;
      }).catch(error => {
        alert(error)
      })
    },
    showMyPages(index, postId) {
      this.showMyPageComponent = true;
      this.postTableObject = this.postTables[index];
      //クリックした写真のコメントテーブルを取得する
      Service.post('getcom', postId//commentテーブルのpost_id
      ).then(response => {
        console.log(response)
        this.commentTableObject = response.data;
        this.clickImgIndex = index;
      }).catch(error => {
        alert(error)
      })
    },
    updateLikes(index, ) {
      Service.post("mypage", store.state.id).then(response => {
        console.log(response);
        this.postTables = response.data;
        this.postTableObject = this.postTables[index];
        this.clickImgIndex = index;
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
    getFollowers(){
      Service.post("getFollowers",store.state.id).then(response => {
        console.log(response);
        this.followers = response.data;
      }).catch(error => {
        alert(error) 
      })
    },
    getFollowings(){
      Service.post("getFollowings",store.state.id).then(response => {
        console.log(response);
        this.followings = response.data;
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
    profilea() {
      if (store.state.profile !== null) {
        return require('../assets/' + store.state.profile);
      } else {
        return null;
      }
    },
    getVueCliUrl(imgUrl) {
      return require(`../assets/post/${imgUrl}`);
    },
    //投稿数を取得
    getPostCount() {
      Service.post("postdata", store.state.id).then(response => {
        console.log(response);
        this.postCount = response.data;
      }).catch(error => {
        alert(error)
      })
    },
  }
}
</script>

<style>
.round-image {
  border-radius: 50%;
  width: 100px ;
  height:100px;
}

.photo-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  grid-gap: 20px;
}

.photo-grid-img {
  width: 300px;
  height: 300px;
  object-fit: cover;
  cursor: pointer;
  /* grid-row: auto; */
  margin-bottom: 70px;
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
/* #main {
  box-sizing: border-box;
  margin-left: 180px;
  padding:20px 90px; 
} */

#item-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: wrap;
}
.item {
   width: 25%;
}

</style>