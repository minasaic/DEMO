<template>
  <div class="modal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ title }}</h5>
          <span>
            <img :src="path" alt="選択した画像" width="250px" height="250px">
            <br>
            プロフィール写真<input type="file" @change="saveUserImg">
            <br>
            アカウント名：<input type="text" v-model="changeUserName">
            <br>
            パスワード：<input type="text" v-model="changeUserPassword">
          </span>
          <button type="button" class="close" @click="$emit('close')">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
        <div class="modal-footer">
          <!-- <button type="button" class="btn btn-primary" @click="$emit('save')">保存</button> -->
          <button type="button" class="btn btn-primary" @click="saveUserChange">保存</button>
          <button type="button" class="btn btn-secondary" @click="$emit('close')">キャンセル</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import { Service } from '@/service/service'
import store from '@/store';
export default {
  name: 'OptionModalView',
  props: {
    title: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      changeUserImg: null,
      path: null,
      changeUserName: '',
      changeUserPassword: '',
      profile: null
    }
  },
  methods: {
    saveUserImg(event) {
      this.changeUserImg = event.target.files[0];
      this.profile = require('../assets/' + this.changeUserImg.name);
      this.path = URL.createObjectURL(this.changeUserImg);
    },
    saveUserChange() {
      const formData = new FormData()
      formData.append('file', this.changeUserImg)
      formData.append('name', this.changeUserName)
      formData.append('password', this.changeUserPassword)
      formData.append('id', store.state.id)
      Service.post('update', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      ).then(response => {
        console.log(response)
        store.commit('SETNAME', this.changeUserName);
        store.commit('SETPROFILE', this.profile);
        this.$emit('close')
      }).catch(error => {
        alert(error)
      })
    },
  }
}
</script>
  
<style>
.modal {
  display: block;
  position: fixed;
  z-index: 9999;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-dialog {
  margin: 10% auto;
  width: 50%;
  min-width: 300px;
}

.modal-content {
  background-color: #fefefe;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
}

.modal-header,
.modal-footer {
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 5px 5px 0 0;
}

.modal-header h5 {
  margin: 0;
}

.modal-body {
  padding: 10px;
}

.close {
  position: absolute;
  right: 10px;
  top: 5px;
  color: #aaa;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>