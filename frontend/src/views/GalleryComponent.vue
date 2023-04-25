<template>
    <div id="main">
        <div>
            <hr>
            <!-- commentテーブル    {{ getComments }} -->
            <hr>
            <img :src="postImgName" alt="post">
            <div v-for="(getComment) in getComments " :key="getComment.id">
            <span>{{ getComment.comment }}</span>
            </div>
            <br>
            ・{{ caption }}
            <br>
            <button @click="updateLike">{{ likeCount }} &nbsp; like</button>
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
    name: 'GalleryComponent',
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

    },
    data(){
        return {
            commentText:'',
            showTextBox: false,
            getComments: '',
            likeCount: 0
        }
    },
    created(){
        this.showComments()
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
        }
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