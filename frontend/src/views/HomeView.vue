<template>
    <div id="main">
      <button @click="showFollowing">Following</button>
      <button @click="showRandom">Random</button>
        <div v-show="showFollowings" v-for="(data) in datas" :key="data.id">
          <!-- {{ data }} -->
          <UserPageComponent
            :postImgName="data.image" 
            :caption = data.caption
            :userId = data.userid
            :postId = data.id
            :likesCount = data.likes
          />
        </div>
        <div v-show="showRandoms">
            <img src="../assets/homeimg3.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>&nbsp;
            <img src="../assets/homeimg4.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>
            <br>
            <!-- <router-link to="userpage">{{ username }}</router-link> -->
        </div>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
import UserPageComponent from "../components/UserPageComponent.vue"
export default {
  name: 'CreateView',
  components:{
    UserPageComponent
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
  }
}
</script>
