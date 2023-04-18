<template>
    <div>
        <h1 >{{ valueId }}</h1>
        <img src="" alt="選択した画像">
        <br><br><br>
        <input type="file">
        <br><br><br>
        <textarea name="" id="" cols="30" rows="10"></textarea>
    </div>
</template>
<script>
import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{
      // testesId:null,
      // result:"",
      login:false,
      valueId:null,
      valueName:"",
      valuePass:"",
      valueUserId:1,
      valuePostId:1,
      valueComment:""
    }
  },
  computed: {
    id(){
        return  this.$store.getters.id;
    }
  },
  methods:{
    setId(valueId){
        this.$store.state.id;
    },
    post(){
      Service.post("post",{
        id:this.valueId,
        image:this.formData
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
  }
}
</script>
