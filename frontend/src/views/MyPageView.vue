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
    <b>フォロワー：<button @click="getFollowers"> {{ followerCount }} </button> 人 </b>
    <FollowingComponent v-show="showFollows" 
      @close="showFollows = false"
      :follows="ff"
      :followComponentTitle="followComponentTitle"
      />
    <br>
    <b>フォロー：<button @click="getFollowings"> {{ followingCount }} </button> 人 </b>
    <FollowingComponent v-show="showFollows" 
      @close="showFollows = false"
      :follows="ff"
      :followComponentTitle="followComponentTitle"
      />
    <br>
    投稿 &nbsp; {{ postCount }}件

    <hr>
    <!-- postsテーブル     {{ postTables}}       -->
    <div class="photo-grid">
      <div v-for="(postTable, index) in postTables " :key="postTable.id"
        @click="showMyPages(index, postTable.id)">
        <img class="photo-grid-img" :src="getVueCliUrl(postTable.image)" alt="投稿画像">
      </div>
      <HomeSearchComponent v-show="showMyPageComponent" 
        :homeTableObject="postTableObject"
        :commentTableObject="commentTableObject" 
        :qwerty="qwerty"
        :showDeleteButton="showDeleteButton"
        @close="showMyPageComponent = false" 
        @refresh-comment="showMyPages(clickImgIndex,postTableObject.id)"
        @refresh-likes="updateLikes(clickImgIndex)"
        />
      <div v-if="showMyPageComponent" class="overlay" @click="showMyPageComponent = null"></div>
    </div>
  </div>
</template>
<script>
import { Service } from "@/service/service"
// import MyPageComponent from "../components/MyPageComponent.vue"
import store from "@/store"
import OptionModalView from "../components/OptionModalView.vue"
import FollowingComponent from "@/components/followingComponent.vue"
import HomeSearchComponent from "@/components/HomeSearchComponent.vue"
export default {
  name: 'MyPageView',
  components: {
    // MyPageComponent,
    OptionModalView,
    FollowingComponent,
    HomeSearchComponent
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
      showFollows: false,
      followers: null,  //フォローワーのユーザ一覧
      followings: null, //フォローのユーザの一覧
      postTables: null,
      showModal: false,
      showMyPageComponent: null,
      postTableObject: { "id": 3, "userid": 4, "image": "homeimg1.jpeg", "caption": "post", "likes": 4 },
      commentTableObject: null,
      clickImgIndex: null,
      modalTitle: 'アカウント情報変更',
      postCount: null,
      ff: null,
      followComponentTitle: null,
      showDeleteButton: true,
      qwerty: {
        "id": store.state.id,
        "name": store.state.name,
        "profile_picture": store.state.profile
      }

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
        this.showFollows = true;
        this.ff = response.data;
        this.followComponentTitle = 'フォロワー一覧';
      }).catch(error => {
        alert(error) 
      })
    },
    getFollowings(){
      Service.post("getFollowings",store.state.id).then(response => {
        console.log(response);
        this.showFollows = true;
        this.ff = response.data;
        this.followComponentTitle = 'フォロー一覧';
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
        return require('../assets/profile/' + store.state.profile);
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