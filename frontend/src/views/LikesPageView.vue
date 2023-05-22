<template>
  <div id="main">
    <!-- homeTableObject: {{ homeTableObject }}
      qwerty: {{ qwerty }} -->
    <div class="photo-grid">
      <div class="photo" v-for="(homeTable, index) in homeTables " :key="homeTable.id"
        @click="showHomePages(index, homeTable.id, homeTable.userid)">
        <div>
          <img class="home-search-profile" :src="getVueCliUrlProfile(userProfile[index])">
          <span> {{ userOnamae[index] }}</span>
        </div>
        <img class="photo-grid-img" :src="getVueCliUrl(homeTable.image)" alt="投稿画像">
      </div>
      <HomeSearchComponent v-if="showHomeSearchComponent" :homeTableObject="homeTableObject"
        :commentTableObject="commentTableObject" :qwerty="qwerty" :showDeleteButton="showDeleteButton"
        :show="showLikeJudge" @close="showHomeSearchComponent = false"
        @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id, homeTableObject.userid)"
        @refresh-likes="updateLikes(clickImgIndex, homeTableObject.id)" 
        @refresh-page="showHomeSearchComponent = false" />
      <div v-if="showHomeSearchComponent" class="overlay" @click="showHomeSearchComponent = null"></div>
    </div>
  </div>
</template>
<script>
import { Service } from "@/service/service"
import store from '@/store'
import HomeSearchComponent from "../components/HomeSearchComponent.vue"
export default {
  name: 'LikesPageView',
  components: {
    HomeSearchComponent
  },
  data() {
    return {
      showFollowings: true,
      homeTables: null,
      homeTableObject: { "id": 3, "userid": 4, "image": "jkl.jpeg", "caption": "post", "likes": 4 },
      showHomeSearchComponent: false,
      clickImgIndex: null,
      commentTableObject: null,
      qwerty: { "id": 4, "name": "矢口", "password": "pass", "profile_picture": "images.png" },
      showDeleteButton: false,
      showLikeJudge: false,
      userOnamae: [],
      userProfile: []
    }
  },
  created() {
    this.getLikes()
  },
  methods: {
    getLikes() {
      Service.post("getlikes", store.state.id
      ).then(response => {
        console.log(response);
        this.homeTables = response.data;
        //拡張for文でユーザ情報を取ってきてる
        this.homeTables.forEach((homeTable) => {
          Service.post('getuser', homeTable.userid).then(response => {
            console.log(response);
            this.userOnamae.push(response.data.name);
            this.userProfile.push(response.data.profile_picture);
          }).catch(error => {
            alert(error);
          })
        })
      }).catch(error => {
        alert(error)
      })
    },
    showHomePages(index, postId, userId) {
      this.showHomeSearchComponent = true;
      this.homeTableObject = this.homeTables[index];
      this.likeJudge(postId);
      //クリックした投稿のユーザが自分かどうか判断する
      if (store.state.id == userId) {
        this.showDeleteButton = true;
      } else {
        this.showDeleteButton = false;
      }
      //クリックした写真のコメントテーブルを取得する
      Service.post('getcom', postId//commentテーブルのpost_id
      ).then(response => {
        console.log(response)
        this.commentTableObject = response.data;
        this.clickImgIndex = index;
      }).catch(error => {
        alert(error)
      })
      //ユーザー情報取得
      Service.post('getuser', this.homeTableObject.userid).then(response => {
        console.log(response)
        this.qwerty = response.data;
        return true
      }).catch(error => {
        alert(error)
      })
    },
    updateLikes(index, postId) {
      Service.post("getlikes", store.state.id).then(response => {
        console.log(response);
        this.homeTables = response.data;
        this.homeTableObject = this.homeTables[index];
        this.clickImgIndex = index;
        this.likeJudge(postId);
      }).catch(error => {
        alert(error)
      })
    },
    getVueCliUrl(imgUrl) {
      const imgUrls = imgUrl.split(',')
      return require(`../assets/post/${imgUrls[0]}`);
    },
    getVueCliUrlProfile(imgUrl) {
      return require(`../assets/profile/${imgUrl}`);
    },
    likeJudge(postId) {
      Service.post('/likejudge', {
        postid: postId,
        userid: store.state.id
      }).then(response => {
        console.log(response);
        this.showLikeJudge = response.data;
      }).catch(error => {
        alert(error);
      })
    },
  }
}
</script>
  