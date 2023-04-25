<template>
    <div id="main">
      <button @click="showFollowing">Following</button>
      <button @click="showRandom">Random</button>
        <div v-if="showFollowings">
            <br>
            <img src="../assets/homeimg1.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>&nbsp;
            <img src="../assets/homeimg2.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>
            <br>
            <router-link to="userpage">{{ username }}</router-link>
        </div>
        <div v-if="showRandoms">
            <img src="../assets/homeimg3.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>&nbsp;
            <img src="../assets/homeimg4.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>
            <br>
            <router-link to="userpage">{{ username }}</router-link>
        </div>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
export default {
  name: 'CreateView',
  data(){
    return{
        username:'森上',
        showFollowings: true,
        showRandoms: false,
        path: null,
    }
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
      Service.post("home",{
        id:store.state.id
      }).then(response =>{
        console.log(response);
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

<style>
#main {
  box-sizing: border-box;
  margin-left: 220px;
  padding: 20px 40px;
}
</style>