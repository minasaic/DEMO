<template>
    <div>
        <div>
            <img src="../assets/homeimg1.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>&nbsp;
            <img src="../assets/homeimg2.jpeg" alt="写真" width="300" height="300">
            <button @click="like">like</button>
            <br>
            <router-link to="userpage">{{ username }}</router-link>
        </div>
        <br>
        <div>
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
        username:'森上'
    }
  },
  methods:{
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