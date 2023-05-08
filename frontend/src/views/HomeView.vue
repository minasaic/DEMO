<template>
  <div id="main">
    <button @click="showFollowing">Following</button>
    <button @click="showRandom">Random</button><br>
    homeTables: {{ homeTables }}
    <div v-show="showFollowings" class="photo-grid">
      <div class="photo" v-for="(homeTable, index) in homeTables " :key="homeTable.id"
        @click="showHomePages(index, homeTable.id)">
        <img class="photo-grid-img" :src="getVueCliUrl(homeTable.image)" alt="投稿画像">
      </div>
      <HomeSearchComponent v-show="showHomeSearchComponent" :homeTableObject="homeTableObject"
        :commentTableObject="commentTableObject" @close="showHomeSearchComponent = false"
        @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id)" 
        @refresh-likes="updateLikes(clickImgIndex)"
        />
      <div v-if="showHomeSearchComponent" class="overlay" @click="showHomeSearchComponent = null"></div>
    </div>
    <div v-show="showRandoms">
      <img src="../assets/homeimg3.jpeg" alt="写真" width="300" height="300">
      <img src="../assets/homeimg4.jpeg" alt="写真" width="300" height="300">
      <br>
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
      showRandoms: false,
      homeTables: null,
      homeTableObject: null,
      showHomeSearchComponent: false,
      clickImgIndex: null,
      commentTableObject:null

    }
  },
  created() {
    this.home()
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
    showHomePages(index, postId) {
      this.showHomeSearchComponent = true;
      this.homeTableObject = this.homeTables[index];
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
    updateLikes(index) {
      Service.post("home", store.state.id).then(response => {
        console.log(response);
        this.homeTables = response.data;
        this.homeTableObject = this.homeTables[index];
        this.clickImgIndex = index;
      }).catch(error => {
        alert(error)
      })
    },
    // like() {
    //   Service.post("like", {
    //   }).then(response => {
    //     console.log(response);
    //   }).catch(error => {
    //     alert(error)
    //   })
    // },
    getVueCliUrl(imgUrl) {
      return require(`../assets/post/${imgUrl}`);
    },
  }
}
</script>
