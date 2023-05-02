<!-- <template>
    <div id="main" class="instagram-post">
        <div>
             commentテーブル    {{ getComments }} 
            <hr>
            <img class="postDetelImg" :src="vueCliUrl" alt="post">
            <br>
            <div class="caption">{{ caption }}</div>
            <br>
            <div v-for="(getComment) in getComments " :key="getComment.id" >
                <span >{{ getComment.user_id}} : {{ getComment.comment }}</span>
            </div>
            <br><br>
            <button @click="createLike">{{ likesCount }} &nbsp; like</button>
            <button @click="showTextBox = !showTextBox">comment</button>
            <button @click="deletePost">delete</button>
            <br>
            <div v-show="showTextBox">
                <textarea  v-model="commentText" cols="30" rows="4"></textarea>
                <br>
                <button @click="updateComment">確認</button>
                <button @click="showTextBox = !showTextBox">キャンセル</button>
            </div>
        </div>
    </div>
</template> -->
<!-- <template>
    <div class="photo-details-dialog">
        <div class="dialog-content">
            <img class="mypage-img" :src="vueCliUrl" alt="post">
            <p class="mypage-caption">{{ caption }}</p>
            <p class="mypage-likes">いいね数: {{likesCount}}</p>
            <button @click="createLike">{{ likesCount }} &nbsp; like</button>
            <ul v-for="(getComment) in postTableObject " :key="getComment.id" >
                <li >{{ getComment.user_id}} : {{ getComment.comment }}</li>
            </ul>
            <button @click="showTextBox = !showTextBox">comment</button>
            <button @click="deletePost">delete</button>
            <br>
            <div v-show="showTextBox">
                <textarea  v-model="commentText" cols="30" rows="4"></textarea>
                <br>
                <button @click="updateComment">確認</button> 
                <button @click="showTextBox = !showTextBox">キャンセル</button>
            </div>
        </div>
        <button class="close-button" @click="$emit('close')">X</button>
    </div>
</template> -->
<template>
    <div class="photo-details-wrapper">
      <div class="photo-details-dialog">
        <div class="dialog-content">
          <img class="mypage-img" :src="getVueCliUrl(postTableObject.image)" alt="post">
          <div class="post-info">
            <h2 class="mypage-caption">caption: {{ postTableObject.caption }}</h2>
            <p class="mypage-likes">いいね数: {{postTableObject.likes}}</p>
            <!-- {{ commentTableObject }} -->
            <div class="comment-section">
              <ul class="comment-list" v-for="(comment) in commentTableObject" :key="comment.id">
                <li class="comment-item">{{ comment.user_id }} : {{ comment.comment }}</li>
              </ul>
              <div class="comment-form">
                <button class="comment-btn" @click="showTextBox = !showTextBox">コメントする</button>
                <div v-show="showTextBox" class="comment-box">
                  <textarea class="comment-input" v-model="commentText" cols="30" rows="4"></textarea>
                  <div class="comment-btn-group">
                    <button class="confirm-btn" @click="updateComment">確認</button> 
                    <button class="cancel-btn" @click="showTextBox = !showTextBox">キャンセル</button>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-section">
              <button class="like-btn" @click="createLike">いいね</button>
              <button class="delete-btn" @click="deletePost">削除する</button>
            </div>
          </div>
          <button class="close-button" @click="$emit('close')">X</button>
        </div>
      </div>
    </div>
  </template>
<script>
import { Service } from '@/service/service';
import store from '@/store';
export default {
    name: 'MyPageComponent',
    props:{
        postTableObject: {
            type: Object,
            require: true
        },
        commentTableObject: {
            type: Array,
            require: true
        }
    },
    data(){
        return {
            showTextBox: false,
            vueCliUrl: '',
            commentText: '',
        }
    },
    methods: {
        updateComment(){
            Service.post('comment',{
                user_id:store.state.id,
                postid:this.postTableObject.id,
                comment: this.commentText
            }).then(response => {
                console.log(response);
                this.commentText='';
                //コメントがアップデート成功時に、コメント一覧を更新する
                this.$emit('refresh-data');
            }).catch(error => {
                alert(error);
            })
        },
        getVueCliUrl(img){
            this.vueCliUrl = require('../assets/post/'+img);
            return this.vueCliUrl;
        },
        createLike(){
            Service.post('like',this.postTableObject.id).then(response => { //postidを渡す
                console.log(response);
                this.$emit('refresh-data'); // 最新のデータがreturnする
            }).catch(error => {
                alert(error)
            })
        },
        deletePost(){
            Service.post('deletepost',this.postTableObject.id).then(response => {
                console.log(response);
                alert('削除しました。')
            }).catch(error => {
                alert(error)
            })
        }
    }
}
</script>
<style>
.photo-details-dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

  background-color: rgba(156, 156, 156, 0.5);
  z-index: 100;
}

.dialog-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.mypage-img {
  width: 100%;
  max-width: 600px;
  height: auto;
  display: block;
  margin: 0 auto;
  border-radius: 5px;
}

.mypage-caption {
  font-size: 18px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
}

.mypage-likes {
  margin-bottom: 10px;
}

.comment-list {
  list-style: none;
  padding: 0;
  margin: 0;
  margin-top: 10px;
}

.comment-item {
  font-size: 14px;
  line-height: 1.5;
  margin-bottom: 5px;
}

.comment-form {
  margin-top: 10px;
}

.comment-box {
  margin-top: 10px;
  display: flex;
  flex-direction: column;
}

.comment-input {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 5px;
  font-size: 14px;
  resize: none;
  margin-bottom: 10px;
}

.comment-btn-group {
  display: flex;
  justify-content: space-between;
}

.confirm-btn,
.cancel-btn {
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 14px;
  cursor: pointer;
}

.confirm-btn {
  background-color: #3f51b5;
  color: #fff;
}

.cancel-btn {
  background-color: #fff;
  color: #3f51b5;
  border: 1px solid #3f51b5;
}

.btn-section {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}

.like-btn,
.delete-btn {
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 14px;
  cursor: pointer;
}

.like-btn {
  background-color: #3f51b5;
  color: #fff;
}

.delete-btn {
  background-color: #fff;
  color: #3f51b5;
  border: 1px solid #3f51b5;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  border: none;
  background-color: transparent;
  font-size: 24px;
  cursor: pointer;
}
</style>