<template>
    <div class="photo-details-wrapper">
      <div class="photo-details-dialog">
        <div class="dialog-content">
            {{ homeTableObject.image }}
          <img class="mypage-img" :src="getVueCliUrl(homeTableObject.image)" alt="post">
          <div class="post-info">
            <router-link :to="{name: 'userpage' , params: {userId: userId}}">
                <button @click="setStoreUserId">
                    ユーザID : {{ homeTableObject.userid }} 
                </button>
            </router-link>
            <span class="mypage-caption">caption: {{ homeTableObject.caption }}</span>
            <p class="mypage-likes">いいね数: {{ homeTableObject.likes}}</p>
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
    name: 'HomeSearchComponent',
    props:{
        homeTableObject: {
            type: Object,
            require: true
        },
        commentTableObject:{
            type: Array,
            require: true
        }
    },
    data(){
        return {
            showTextBox: false,
            vueCliUrl: '',
            commentText:'',
            getComments: '',
            likeCount: 0
        }
    },
    methods: {
        setStoreUserId(){
            store.commit('SETUSERID',this.homeTableObject.userid);
            sessionStorage.setItem('user_id',this.homeTableObject.userid);
            alert('setStorUserId  '+store.state.userId);
        },
        showTextarea(){
            this.showText = true
            Service.post('getcom',this.postId //commentテーブルのpost_id
            ).then(response =>{
                console.log(response)
                this.getComments = response.data;
            }).catch(error =>{
                alert(error)
            })
        },
        updateComment(){
            Service.post('comment',{
                user_id:store.state.id, //ストアに保存したid
                postid:this.homeTableObject.id,
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
        getVueCliUrl(imgUrl){
            this.vueCliUrl = require('../assets/post/'+imgUrl);
            return this.vueCliUrl;
        },
        createLike(){
            Service.post('like',this.homeTableObject.id).then(response => { //postidを渡す
                console.log(response);
                this.$emit('refresh-likes'); // 最新のデータがreturnする
            }).catch(error => {
                alert(error)
            })
        },
        deletePost(){
            Service.post('deletepost',this.homeTableObject.id).then(response => {
                console.log(response);
                alert('削除しました。')
            }).catch(error => {
                alert(error)
            })
        }
    }
}
</script>
