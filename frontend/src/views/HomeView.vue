<template>
    <div id="main">
      <button @click="showFollowing">Following</button>
      <button @click="showRandom">Random</button><br>
      datas: {{ datas }}
        <div v-show="showFollowings" v-for="(data) in datas" :key="data.id">
          <HomeSearchComponent
            :postImgName="data.image" 
            :caption = data.caption
            :userId = data.userid
            :postId = data.id
            :likesCount = data.likes
            @update-likes="updateLikes($event,data.id)"
          />
        </div>
        <div v-show="showRandoms">
            <img src="../assets/homeimg3.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>&nbsp;
            <img src="../assets/homeimg4.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>
            <br>
        </div>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
import HomeSearchComponent from "../components/HomeSearchComponent.vue"
export default {
  name: 'HomeView',
  components:{
    HomeSearchComponent
  },
  data(){
    return{
        showFollowings: true,
        showRandoms: false,
        datas: null        
    }
  },
  created(){
    this.home()
  },
  methods:{
    showFollowing(){
      this.showFollowings = true
      this.showRandoms = false
    },
    showRandom(){
      this.showFollowings = false
      this.showRandoms = true
    },
    home(){
      Service.post("home",store.state.id
      ).then(response =>{
        console.log(response);
        this.datas = response.data;
      }).catch(error =>{
        alert(error)
      })
    },
    like(){
      Service.post("like",{
      }).then(response =>{
        console.log(response);
      }).catch(error =>{
        alert(error)
      })
    },
    updateLikes(likes,postId) {
      const postIndex = this.datas.findIndex(post => post.id === postId);
      this.datas[postIndex].likes = likes;    
    }
  }
}
</script>
