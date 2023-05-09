<template>
    <div class="photo-details-wrapper">
      <div class="photo-details-dialog">
        <div class="dialog-content">
            <div class="sua">
            <!-- {{ homeTableObject }} -->
          <img class="suba" :src="getVueCliUrl(homeTableObject.image)" alt="post">
            </div>
            <nobr class="qaz">
          <div class="post-info">
            <router-link :to="{name: 'userpage' , params: {userId: userId}}">
                
                <img  id="aaa" :src="getVueCliUrl(qwerty.profile_picture)" alt="asdf" @click="setStoreUserId">{{ qwerty.name }} 
                
            </router-link>
            <span class="mypage-caption">{{ homeTableObject.caption }}</span>
            <!-- {{ commentTableObject }} -->
            <div class="morimori">
            <div class="comment-section">
              <ul class="comment-list" v-for="(comment) in commentTableObject" :key="comment.id">
                <li class="comment-item">
                    <img id="aaa" :src="getVueCliUrl(comment.profile)" alt="no image" >
                    {{ comment.name }}<nobr class="balloon1-left">{{ comment.comment }}</nobr>
                </li>
              </ul>
              
              </div>
            </div>
        </div>
            <div class="btn-section">
                <div class="comment-form">
                <nobr class="mypage-likes" >いいね!{{ homeTableObject.likes}}件</nobr>
                <button class="like-btn" @click="createLike">いいね</button>
                <div  class="comment-box">
                  <textarea class="comment-input" v-model="commentText" cols="35" rows="1"></textarea>
                  <nobr class="comment-btn-group">
                    <button class="confirm-btn" @click="updateComment">送信</button> 
                  </nobr>
                </div>
              
            </div>
          </div>
        </nobr>
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
        },
        qwerty: {
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
            likeCount: 0,
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

<style scoped>
 #aaa{
    border-radius: 50%;  /* 角丸半径を50%にする(=円形にする) */
    width:  20px;       /* ※縦横を同値に */
    height: 20px;       /* ※縦横を同値に */
}
.balloon1-left {
  position: relative;
  display: inline-block;
  margin: 1.5em 0 1.5em 15px;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #0c0b0b;
  font-size: 16px;
  background: #bbf3a7;
}

.balloon1-left:before {
  content: "";
  position: absolute;
  top: 50%;
  left: -30px;
  margin-top: -15px;
  border: 15px solid transparent;
  border-right: 15px solid #bbf3a7;
}

.balloon1-left p {
  margin: 0;
  padding: 0;
}
.suba {
  margin-right: auto;
  width: 100%;
  height: 100%;
  max-width: 600px;
  display: block;
  border-radius: 5px;
}
.morimori{
    width: 350px;
    height: 500px;
    overflow-y: scroll;
}

</style>