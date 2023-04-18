<template>
    <div>
        
    </div>
</template>
<!-- <script>
import {Service} from "@/service/service"

export default {

  name: 'App',
  data(){
    return{
      // testesId:null,
      // result:"",
      login:false,
      valueId:1,
      valueName:"",
      valuePass:"",
      valueUserId:1,
      valuePostId:1,
      valueComment:""
    }
  },
  methods:{
    onFileSelected(event) {
      const file = event.target.files[0]
      this.uploadFile(file)
    },
    uploadFile(file) {
      const formData = new FormData()
      formData.append('file', file)
    },
    logins(){
      Service.post("login",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
       //ログイン成功時の処理
       console.log(response);
       if(response.data){
        alert(this.valueId+this.valuePass);
        this.login = response.data;
       } else{
         alert("Wrong id or password.");
       }
      
       
      }).catch(error =>{
        console.log(error);
        alert("sdf")
      })
    },
    create(){
      Service.post("create",{
        username: this.valueName,
        password:this.valuePass
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    home(){
      Service.post("home",{
        id:this.valueId
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
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
    comment(){
      Service.post("comment",{
        id:this.valueId,
        postId:this.valuePostId,
        comment:this.valueComment
      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    follow(){
      Service.post("follow",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    unfollow(){
      Service.post("unfollow",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
    like(){
      Service.post("like",{

      }).then(response =>{
        alert(response)
      }).catch(error =>{
        alert(error)
      })
    },
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
</script> -->
