<template>
  <div class="modal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">アカウント情報編集</h5>
          <span>
            <nobr>
              <img :src="path" alt="選択した画像" width="250px" height="250px">
              <br>
              <label for="file-upload" class="custom-file-upload">
                <i class="fa fa-cloud-upload"></i> プロフィール写真
              </label>
              <input id="file-upload" type="file" @change="saveUserImg">
            </nobr>
            <br>
            <nobr>
              アカウント名：
              <input type="text" v-model="changeUserName" :placeholder="$store.state.name">
              <br>
              パスワード：
              <input v-if="!showPassword" type="password" v-model="changeUserPassword" placeholder="新しいパスワード">
              <input v-else type="text" v-model="changeUserPassword" placeholder="新しいパスワード">
              <a @click="showPassword = !showPassword">👀</a>
              <br>
              自己紹介:
              <br>
              <textarea v-model="changeUserIntroduction" cols="30" rows="10" placeholder="例:はじめまして！〇〇といいます.."
                style="display: inline-block;"></textarea>
              <br>
              性別:
              <select v-model="changeUserSex">
                <option disabled selected value="">選択してください</option>
                <option value="男性">男性</option>
                <option value="女性">女性</option>
                <option value="答えたくない" selected>答えたくない</option>
              </select>
              <br>
              生年月日:
              <div>

                <select @click.once="year" v-model="selectedYear" ref="yearSelect">
                  <option value="">年</option>

                  <!-- 他の年を追加することもできます -->
                </select>

                <select @click.once="month" v-model="selectedMonth" ref="monthSelect">
                  <option value="">月</option>
                  <!-- 他の月を追加することもできます -->
                </select>

                <select @click.once="date" v-model="selectedDay" ref="dateSelect">
                  <option value="">日</option>
                  <!-- 他の日を追加することもできます -->
                </select>

              </div>



              <br>
            </nobr>
          </span>
          <button type="button" class="close-button" @click="$emit('close')">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
        <div class="modal-footer">
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
  data() {
    return {
      changeUserImg: null,
      path: null,
      changeUserName: '',
      changeUserPassword: '',
      changeUserIntroduction: '',
      changeUserSex: '',
      changeUserBirthday: '',
      selectedYear: "",
      selectedMonth: "",
      selectedDay: "",
      profile: null,
      showPassword: false
    }
  },
  methods: {
    saveUserImg(event) {
      this.changeUserImg = event.target.files[0];
      this.profile = this.changeUserImg.name;
      this.path = URL.createObjectURL(this.changeUserImg);
    },
    saveUserChange() {
      this.changeUserBirthday = this.selectedYear + '-' + this.selectedMonth + '-' + this.selectedDay;
      alert(this.changeUserBirthday);

      const formData = new FormData()
      formData.append('file', this.changeUserImg)
      formData.append('name', this.changeUserName)
      formData.append('password', this.changeUserPassword)
      formData.append('introduction', this.changeUserIntroduction)
      formData.append('sex', this.changeUserSex)
      formData.append('birthday', this.changeUserBirthday)
      formData.append('id', store.state.id)
      Service.post('update', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      ).then(response => {
        console.log(response)
        sessionStorage.setItem("profile_picture",response.data);
        store.commit('SETNAME', this.changeUserName);
        store.commit('SETPROFILE', response.data);
        this.$emit('close')
      }).catch(error => {
        alert(error)
      })
    },
    month() {
      for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.text = `${month}月`;
        this.$refs.monthSelect.appendChild(option);
      }
    },
    year() {
      for (let year = 1950; year <= 2023; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.text = `${year}年`;
        this.$refs.yearSelect.appendChild(option);
      }
    },
    date() {
      for (let date = 1; date <= 31; date++) {
        const option = document.createElement('option');
        option.value = date;
        option.text = `${date}日`;
        this.$refs.dateSelect.appendChild(option);
      }
    }
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

.modal-header {}

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