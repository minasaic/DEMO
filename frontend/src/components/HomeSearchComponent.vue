<template>
    <div id="main">
        <div>
            <hr>
            <!-- commentテーブル    {{ getComments }} -->
            <img :src="vueCliUrl" alt="post">
            <br>
            <router-link :to="{name: 'userpage' , params: {userId: userId}}" @click="setStoreUserId">ユーザID : {{ userId }} </router-link>
            <br>
            ・{{ caption }}
            <br><br>
            <div v-for="(getComment) in getComments " :key="getComment.id">
                <span>{{ getComment.user_id }} : {{ getComment.comment }}</span>
            </div>
            <button @click="createLike">{{ likesCount }} &nbsp; like</button>
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
    name: 'HomeSearchComponent',
    props:{
        postImgName:{
            type: String,
            required: true
        },
        caption:{
            type: String,
            required: true
        },
        postId:{
            type: Number,
            required: true
        },
        userId:{
            type: Number,
            required: true
        },
        // userName:{
        //     type: String,
        //     required: true
        // },
        likesCount:{
            type: Number,
            required: true
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
    created(){    //このコンポネートが読み取れた時点、動くもの
        this.showComments()
        this.getVueCliUrl()
        this.setStoreUserId()
    },
    methods: {
        setStoreUserId(){
            store.commit('SETUSERID',this.userId);
            sessionStorage.setItem('user_id',this.userId)
            // alert(store.state.userId);
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
            alert(this.userId)
            Service.post('comment',{
                user_id:store.state.id, //ストアに保存したid
                postid:this.postId,
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
            Service.post('getcom',this.postId //commentsテーブルのpost_id
            ).then(response =>{
                console.log(response)
                this.getComments = response.data;
            }).catch(error =>{
                alert(error)
            })
        },
        getVueCliUrl(){
            this.vueCliUrl = require(`../assets/post/${this.postImgName}`);
            return this.vueCliUrl;
        },
        createLike(){
            Service.post('like',this.postId).then(response => {
                console.log(response);
                this.$emit('update-likes', response.data)
            }).catch(error => {
                alert(error)
            })
        },
    }
}
</script>
<style scoped>
img{
  width: 300px;
  height: 300px;
}
</style>