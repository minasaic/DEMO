<template>
  <div id="main">
    <!-- {{ $store.state.id }} -->
    
    <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新規投稿を作成
      <span class="yagu"><a @click="post"  style="color:#0000FF;">&nbsp;&nbsp;&nbsp;&nbsp;シェア</a></span></h1>
    <img :src="path" class="yaguchi">
    <br><br><br>
    <label for="file-upload" class="custom-file-upload">
      <i class="fa fa-cloud-upload"></i> コンピューターから選択
    </label>
    <input id="file-upload" type="file" @change="uploadFile">
    <br><br><br>
    <textarea v-model="text" cols="30" rows="10" placeholder="コメントを入力.." style="display: inline-block;"></textarea>
    <br><br>
    
    
    
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
        // alert(error)
        console.log(error);
        alert('画像を選択してください。')
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
  background-color: #639aed;
  color: #FFF;
  border: 1px solid #a4bbe9;
  display: inline-block;
  padding: 6px 12px;
  cursor: pointer;
}

.yagu{
  font-size: 23px;
  margin-left: auto;

}

a:hover {
    cursor: pointer;
}

.yaguchi{
  background-image: url("../assets/system/drag.png" );


}
</style>