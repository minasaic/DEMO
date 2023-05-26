<template>
  <div id="main" v-cloak>
    <!-- homeTableObject: {{ homeTableObject }}
    qwerty: {{ qwerty }} -->
    <div v-if="homeTables != null" class="home-photo-grid">
      <div class="photo" v-for="(homeTable, index) in homeTables " :key="homeTable.id"
        @click="showHomePages(index, homeTable.id, homeTable.userid)">
        <div  @click="goToUserPage(homeTable.userid)" class="profile-data">
          <!--- 値が入るまで実行されない森上あああああああああああああああああああああああああ -->
          <img v-if="userProfile[index] != null" class="home-search-profile" :src="getVueCliUrlProfile(userProfile[index])">
          <img v-else class="home-search-profile" src="../assets/system/profile.png" alt="">
          <span> {{ userOnamae[index] }}</span>
        </div>
        <!-- {{   getVueCliUrlProfile(homeTable.userid,index) }} -->
        <img class="home-photo-grid-img" :src="getVueCliUrl(homeTable.image)" alt="投稿画像">
      </div>
      <HomeSearchComponent v-if="showHomeSearchComponent" :homeTableObject="homeTableObject"
        :commentTableObject="commentTableObject"  :showDeleteButton="showDeleteButton"
        :show="showLikeJudge" @close="showHomeSearchComponent = false"
        @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id, homeTableObject.userid)"
        @refresh-likes="updateLikes(clickImgIndex, homeTableObject.id)" 
        :qwerty="qwerty"/>
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
      homeTables: [],
      homeTableObject: null,
      showHomeSearchComponent: false,
      clickImgIndex: null,
      commentTableObject: null,
      qwerty: {},
      showDeleteButton: false,
      showLikeJudge: false,
      userDate: {},
      userOnamae: [],
      userProfile: []

    }
  },
  created() {
    this.home()
    // if(this.homeTables) {
    //   this.homeTables.forEach((homeTable,index) => {
    //     alert(homeTable.userid + ' -------- ' + index)
    //     this.getVueCliUrlProfile(homeTable.userid,index);
    //   })
    // }
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
    //プロフィールの順番を整えるために非同期処理を並列実行するようにした森上あああああああああああああああああああああ
    async home() {
      try {
        const response = await Service.post("home", store.state.id);
        console.log(response);
        this.homeTables = response.data;

        const getUserPromises = this.homeTables.map(homeTable => {
          return Service.post('getuser', homeTable.userid);
        });

        const userResponses = await Promise.all(getUserPromises);
        console.log(userResponses);

        userResponses.forEach(response => {
          this.userOnamae.push(response.data.name);
          this.userProfile.push(response.data.profile_picture);
        });
      } catch (error) {
        alert(error);
      }
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
    updateLikes(index, postId) {
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
    getVueCliUrlProfile(profileName) {
      return require(`../assets/profile/${profileName}`);

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
    goToUserPage(userId) {
      if (userId == store.state.id) {
        this.$router.push('/mypage');
      } else {
        sessionStorage.setItem('user_id', userId);
        store.commit('SETUSERID', userId);
        this.$router.push('/userpage');
      }
    }
  }
}
</script>
<style>
[v-cloak] {
  display: none;
}


.home-photo-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  /* grid-gap: 0px; */
}

.home-photo-grid-img {
  width: 300px;
  height: 300px;
  object-fit: cover;
  cursor: pointer;
  /* grid-row: auto; */
  margin-bottom: 10px;
}

.home-search-profile {
  margin-right: 5px;
  /* 画像と名前の間に余白を設ける */
  margin-bottom: -4px;
  /* 画像を少し下げる */
  border-radius: 50%;
  /* 角丸半径を50%にする(=円形にする) */
  width: 30px;
  /* ※縦横を同値に */
  height: 30px;
  /* ※縦横を同値に */
}

.profile-data {
  cursor: pointer;

}
</style>