<template>
    <div>
        <div>
            <hr>
            {{ getComments }}
            <hr>
            <img :src="imgFileName" alt="post">
            <div v-for="(getComment) in getComments " :key="getComment.id">
            <span>{{ getComment.comment }}</span>
            </div>
            <br>
            {{ caption }}
            <br>
            <button @click="updateLike">like</button>
            <button @click="showTextarea">comment</button>
            <br>
            <div v-if="showText">
                <textarea  v-model="commentText" cols="30" rows="4"></textarea>
                <br>
                <button @click="updateComment ">確認</button>
                <button>キャンセル</button>
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
        imgFileName:{
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
            showText: false,
            getComments: ''
        }
    },
    methods: {
        updateLike(){

        },
        showTextarea(){
            this.showText = true
            Service.post('getcom',this.id //commentテーブルのpost_id
            ).then(response =>{
                alert(response)
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
                Service.post('getcom',this.id //commentテーブルのpost_id
                ).then(response =>{
                    console.log(response)
                    this.getComments = response.data;
                }).catch(error =>{
                    alert(error)
                })
            }).catch(error => {
                alert(error);
            })
        },
    
    }
}
</script>