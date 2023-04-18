<template>
    <div>
        {{ $store.state.id }}
        <img src="" alt="選択した画像">
        <br><br><br>
        <input type="file" @change="uploadFile">
        <br><br><br>
        <textarea name="" id="" cols="30" rows="10"></textarea>
        <br><br>
        <button @click="post" >作成</button>
    </div>
</template>

<script>
import {Service} from "@/service/service"
import store from '@/store'
export default {
  name: 'CreateView',
  data(){
    return{
    }
  },
  methods: {
    uploadFile(file) {
      const formData = new FormData()
      formData.append('file', file)
    },
    post(){
      Service.post("post",{
        id:store.state.id,
        image:this.formData
      }).then(response =>{
        console.log(response);
        alert(response);
        alert(store.state.id);
      }).catch(error =>{
        alert(error)
        alert('axios通信失敗しました。')
      })
    },
  }
}
</script>
