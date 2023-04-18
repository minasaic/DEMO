<template>
    <div>
        
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
