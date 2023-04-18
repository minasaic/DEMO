<template>
    <div>
        <!-- <h1 >{{id}}</h1> -->
        <img src="" alt="選択した画像">
        <br><br><br>
        <input type="file">
        <br><br><br>
        <textarea name="" id="" cols="30" rows="10"></textarea>
    </div>
</template>
<script>
// import {Service} from "@/service/service"
// import { mapState } from 'vuex';

export default {

  name: 'CreateView',
  data(){
    return{
    }
  },
  // computed: {
  //   ...mapState(['id'])
  // },
  methods: {
    // post(){
    //   Service.post("post",{
    //     id:this.valueId,
    //     image:this.formData
    //   }).then(response =>{
    //     alert(response)
    //   }).catch(error =>{
    //     alert(error)
    //   })
    // },
  }
}
</script>
