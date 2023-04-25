<template>
    <div id="main" class="instagram-post">
        <div>
            <hr>
            <!-- commentテーブル    {{ getComments }} -->
            <hr>
            <img :src="vueCliUrl" alt="post">
            <br>
            <div class="caption">{{ caption }}</div>
            <br>
            <div v-for="(getComment) in getComments " :key="getComment.id" >
                <span >{{ getComment.user_id}} : {{ getComment.comment }}</span>
            </div>
            <br><br>
            <button @click="updateLike">{{ likesCount }} &nbsp; like</button>
            <button @click="showTextBox = !showTextBox">comment</button>
            <br>
            <div v-show="showTextBox">
                <textarea  v-model="commentText" cols="30" rows="4"></textarea>
                <br>
                <button @click="updateComment">確認</button>
                <button @click="showTextBox = !showTextBox">キャンセル</button>
            </div>
        </div>
    </div>
</template>
<script>
import { Service } from '@/service/service';
import store from '@/store';
export default {
    name: 'MypageComponent',
    props:{
        postImgName:{
            type: String,
            required: true
        },
        caption:{
            type: String,
            required: true
        },
        id:{
            type: Number,
            required: true
        },
        likesCount:{
            type: Number,
            required: true
        }

    },
    data(){
        return {
            showTextBox: false,
            vueCliUrl: '',
            commentText: '',
            getComments: '',
            likeCount: 0

        }
    },
    created(){
        this.showComments()
        this.getVueCliUrl()
    },
    methods: {
        updateLike(){

        },
        showTextarea(){
            this.showText = true
            Service.post('getcom',this.id //commentテーブルのpost_id
            ).then(response =>{
                console.log(response)
                this.getComments = response.data;
            }).catch(error =>{
                alert(error)
            })
        },
        updateComment(){
            alert(this.id)
            Service.post('comment',{
                user_id:store.state.id,
                postid:this.id,
                comment: this.commentText
            }).then(response => {
                console.log(response);
                this.commentText='';
                //コメントがアップデート成功時に、コメント一覧を更新する
                this.showComments();
            }).catch(error => {
                alert(error);
            })
        },
        showComments(){
            Service.post('getcom',this.id //commentテーブルのpost_id
            ).then(response =>{
                console.log(response)
                this.getComments = response.data;
            }).catch(error =>{
                alert(error)
            })
        },
        getVueCliUrl(){
            this.vueCliUrl = require(`../assets/${this.postImgName}`);
            return this.vueCliUrl;
        },
    }
}
</script>

<style>
#main {
  box-sizing: border-box;
  margin-left: 220px;
  padding: 20px 40px;
}

</style>
<style scoped>
img{
  width: 300px;
  height: 300px;
}

.instagram-post {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.instagram-post img {
  width: 100%;
  max-width: 500px;
}

.instagram-post .caption {
  margin-top: 10px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
}

.instagram-post .likes,
.instagram-post .comments {
  margin-top: 5px;
  font-size: 14px;
  text-align: center;
}
</style>