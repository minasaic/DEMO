<template>
  <div id="main">
      <span class="create-view-tittle">新規投稿を作成</span>
      <a class="post-button" @click="post">シェア</a>
    <div class="image-container">
      <a class="prev-button" @click="prevImage">&lt;</a>
      <img :src="currentImagePath" class="selected-image">
      <a class="next-button" @click="nextImage">&gt;</a>
    </div>
    <div class="image-list">
      <div
        v-for="(path, index) in paths"
        :key="index"
        class="image-item"
        :class="{ 'active': index === currentImageIndex }"
        @click="setCurrentImage(index)"
      >
        <img :src="path" :alt="'Image ' + (index + 1)">
        <a @click="deletefiles(index)">X</a>
      </div>
      <label  v-if="files[0] != null" for="file-upload" class="custom-file-upload">
      <i class="fa fa-cloud-upload"></i> コンピューターから選択
    </label>
    <input id="file-upload" type="file" multiple @change="uploadFile">
    </div>
    <label v-if="files[0] == null" for="file-upload" class="custom-file-upload">
      <i class="fa fa-cloud-upload"></i> コンピューターから選択
    </label>
    <input id="file-upload" type="file" multiple @change="uploadFile">
    <br><br>
    <textarea v-model="text" cols="30" rows="10" placeholder="コメントを入力.." style="display: inline-block;"></textarea>
    <br><br>
  </div>
</template>

<script>
import { Service } from "@/service/service";
import store from "@/store";

export default {
  name: "CreateView",
  data() {
    return {
      files: [],
      text: null,
      paths: [],
      currentImageIndex: 0
    };
  },
  computed: {
    currentImagePath() {
      return this.paths[this.currentImageIndex];
    }
  },
  methods: {
    uploadFile(e) {
      this.files = e.target.files;
      for (let i = 0; i < this.files.length; i++) {
        this.paths.push(URL.createObjectURL(this.files[i]));
      }
    },
    post() {
      if (this.files.length === 0) {
        alert("画像を選択してください。");
        return;
      }

      const formData = new FormData();
      formData.append("id", store.state.id);
      formData.append("text", this.text);
      for (let i = 0; i < this.files.length; i++) {
        formData.append("file", this.files[i]);
      }

      Service.post("post", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
        .then(response => {
          console.log(response);
          this.text = "";
          this.$router.push("/");
        })
        .catch(error => {
          console.log(error);
          alert("投稿に失敗しました。");
        });
    },
    prevImage() {
      if (this.currentImageIndex > 0) {
        this.currentImageIndex--;
      }
    },
    nextImage() {
      if (this.currentImageIndex < this.paths.length - 1) {
        this.currentImageIndex++;
      }
    },
    setCurrentImage(index) {
      this.currentImageIndex = index;
    },
    deletefiles(index) {
      const fileArray = Array.from(this.files);
      fileArray.splice(index, 1);
      this.files = fileArray;
      this.paths.splice(index, 1);
    }
  }
};
</script>

<style scoped>
.image-container {
  position: relative;
  margin-bottom: 20px;
}

.selected-image {
  width: 400px;
  height: 400px;
  background-image: url("../assets/system/drag.png" );
}

.prev-button,
.next-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  padding: 10px;
  color: #1616165e;
  font-weight: bold;
  /* border: none; */
  font-size: 40px;
  
}

.prev-button {
  left: 30.5%;
}

.next-button {
  right: 30.5%;
}

.image-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  align-items: center;
}

.image-item {
  flex: 0 0 100px;
  height: 100px;
  cursor: pointer;
  opacity: 0.5;
  transition: opacity 0.3s ease;
}

.image-item:hover {
  opacity: 1;
}

.image-item.active {
  opacity: 1;
  border: 2px solid #3f3f3f;
}

.image-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

input[type="file"] {
  display: none;
}
.custom-file-upload {
  background-color: #639aed;
  color: #fff;
  border: 1px solid #a4bbe9;
  display: inline-block;
  padding: 6px 12px;
  cursor: pointer;
  border-radius: 11px;
}

textarea {
  width: 50%;
}

.create-view-tittle {
  font-weight: bold;
  font-size: 40px;
}

.post-button {
  margin-left: 40px;
  color: #2f81ce;
  font-weight: bold;
  font-size: 30px;
}
</style>

