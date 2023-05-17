<template>
  <div id="main">
    <!-- homeTableObject: {{ homeTableObject }}
    qwerty: {{ qwerty }} -->
    <div class="home-photo-grid">
      <div class="photo" v-for="(homeTable, index) in homeTables " :key="homeTable.id"
        @click="showHomePages(index, homeTable.id, homeTable.userid)">
        <div>
          <img v-if="userProfile[index]" :src="userProfile[index]" alt="user icon">
          <span v-if="userOnamae[index]" >{{ userOnamae[index] }}</span>
          {{   getVueCliUrlProfile(homeTable.userid,index) }}
        </div>
        <img class="home-photo-grid-img" :src="getVueCliUrl(homeTable.image)" alt="投稿画像">
      </div>
      <HomeSearchComponent v-if="showHomeSearchComponent" :homeTableObject="homeTableObject"
        :commentTableObject="commentTableObject" :qwerty="qwerty" :showDeleteButton="showDeleteButton"
        :show="showLikeJudge"
        @close="showHomeSearchComponent = false"
        @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id, homeTableObject.userid)"
        @refresh-likes="updateLikes(clickImgIndex,homeTableObject.id)" />
      <div v-if="showHomeSearchComponent" class="overlay" @click="showHomeSearchComponent = null"></div>
    </div>
  </div>
</template>
<script>
import { Service } from "@/service/service"
import store from '@/store'
import HomeSearchComponent from "../components/HomeSearchComponent.vue"
export default {
  name: 'HomeView',
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
      userDate:{},
      userOnamae: [],
      userProfile: []

    }
  },
  created() {
    this.home()
    this.homeTables.userid.forEach((userId,index) => {
      this.getVueCliUrlProfile(userId,index);
    })
    
  },
  computed:{
  },
  methods: {
    showFollowing() {
      this.showFollowings = true
      this.showRandoms = false
    },
    showRandom() {
      this.showFollowings = false
      this.showRandoms = true
    },
    home() {
      Service.post("home", store.state.id
      ).then(response => {
        console.log(response);
        this.homeTables = response.data;
      }).catch(error => {
        alert(error)
      })
    },
    showHomePages(index, postId, userId) {
      this.showHomeSearchComponent = true;
      this.homeTableObject = this.homeTables[index];
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
      this.likeJudge(postId);

    },
    updateLikes(index,postId) {
      Service.post("home", store.state.id).then(response => {
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
    getVueCliUrlProfile(userId, index) {
      alert('aaaaaa');
      Service.post('getuser',userId).then(response => {
      console.log(response);
      this.userOnamae[index] = response.data.name;
      this.userProfile[index] = require(`../assets/profile/${response.data.profile_picture}`);
      alert(this.userOnamae);
    }).catch(error => {
      alert(error);
    })
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
<style>

.home-photo-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  grid-gap: 20px;
}

.home-photo-grid-img {
  width: 300px;
  height: 300px;
  object-fit: cover;
  cursor: pointer;
  /* grid-row: auto; */
  margin-bottom: 70px;
}
</style>