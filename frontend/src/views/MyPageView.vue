<template>
  <div>
      <img src="../assets/profile.png" alt="LOGO" width="100" height="100">
      <h1>アカウント：</h1>
      <h2>フォロワー：</h2>
  </div>
  
</template>
<script>
import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{

    }
  },
  methods:{
    deletePost(){
      Service.post("deletePost",{
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    }
  }
}
</script>
