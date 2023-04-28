<template>
  <div id="main">
    <!-- {{ $store.state.id }} -->
    <img :src="path" alt="選択した画像">
    <br><br><br>
    <label for="file-upload" class="custom-file-upload">
      <i class="fa fa-cloud-upload"></i> Choose File
    </label>
    <input id="file-upload" type="file" @change="uploadFile">
    <br><br><br>
    <textarea v-model="text" cols="30" rows="10"></textarea>
    <br><br>
    <button @click="post">作成</button>
  </div>
</template>

<script>
import { Service } from "@/service/service"
import store from '@/store'
export default {
  name: 'CreateView',
  data() {
    return {
      file: null,
      text: null,
      fileName: null,
      path: null
    }
  },
  methods: {
    uploadFile(event) {
      this.file = event.target.files[0];
      this.path = URL.createObjectURL(this.file);
      // alert(this.path);
    },
    post() {
      const formData = new FormData()
      formData.append('file', this.file)
      formData.append('id', store.state.id)
      formData.append('text', this.text)
      Service.post("post", formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      ).then(response => {
        console.log(response);
        // this.filePath=require('../assets/' + response.data);
        this.text = '';
      }).catch(error => {
        alert(error)
        alert('axios通信失敗しました。')
      })
    },
  }
}
</script>
<style scoped>
img {
  width: 300px;
  height: 300px;
}
</style>
<style>
input[type="file"] {
  display: none;
}

.custom-file-upload {
  border: 1px solid #ccc;
  display: inline-block;
  padding: 6px 12px;
  cursor: pointer;
}

.custom-file-upload:hover {
  background-color: #f5f5f5;
}
</style>