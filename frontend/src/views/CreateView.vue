<template>
    <div>
        {{ $store.state.id }}
        <img :src="filePath" alt="選択した画像">
        <br><br><br>
        <input type="file" @change="uploadFile">
        <br><br><br>
        <textarea v-model="text" cols="30" rows="10"></textarea>
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
      file: null,
      text: null,
      fileName: null,
      filePath: null
    }
  },
  methods: {
    uploadFile(event) {
      this.file = event.target.files[0]
    },
    post(){
      const formData = new FormData()
      formData.append('file', this.file)
      formData.append('id',store.state.id)
      formData.append('text',this.text)
      Service.post("post",formData,{headers:{
          'Content-Type': 'multipart/form-data'
        }}
      ).then(response =>{
        console.log(response);
        this.filePath = response.data;
        alert(response.data);
      }).catch(error =>{
        alert(error)
        alert('axios通信失敗しました。')
      })
    },
  }
}
</script>
