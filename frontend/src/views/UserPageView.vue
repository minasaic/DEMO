<template>
    <div id="main">
        <img src="" alt="プロフィール画像" width="100" height="100"> &nbsp;&nbsp;
        <b>アカウント：{{ userName }} {{ userId }}</b> 
        <br>
        <b>フォロワー：{{ followerCount }}</b>
        <br>
        <b>フォロー：{{ followingCount }}</b>
        <br>
        <button v-show="aaa" @click="unfollow">unfollow</button>
        <button v-show="!aaa" @click="follow">follow</button>
        <br><br>
        <div>
            過去の投稿一覧
        </div>
        <hr>
        <div v-for="(userData) in userDatas" :key="userData.id">
          poststable {{ userData }}
          <UserPageComponent
            :postImgName="userData.image" 
            :caption = userData.caption
            :userId = userData.userid
            :postId = userData.id
            :likesCount = userData.likes
            @update-likes="updateLikes($event,userData.id)"

          />
        </div>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
import UserPageComponent from "@/components/UserPageComponent.vue"
export default {
    name:'UserPageView',
    components:{
        UserPageComponent
    },
    created(){       //このページになったら自動で動くもの
        this.userId = this.$route.params.userId
        this.followJudgement()
        this.myPage()
        this.getFollowerCount()
    },
    data(){
        return{
            userName: null,
            followerCount: null,
            followingCount: null,
            userDatas: null,
            userId: null,
            followsid: null,
            aaa: null
        }
    },
    mounted(){
        // this.userId = parseInt(this.$route.params.userId)
        
        // alert(this.userId)
    },
    methods:{
        myPage(){
            Service.post("mypage",this.userId).then(response => {
                alert(this.userId)
                console.log(response);
                this.userDatas = response.data;
                console.log( "aaaa" +this.userDatas)
            }).catch(error =>{
                alert(error)
            })
        },
        updateLikes(likes,postId) {
            const postIndex = this.userDatas.findIndex(post => post.id === postId);
            this.userDatas[postIndex].likes = likes;    
        },
        getFollowerCount(){
            Service.post("followdata",this.userId).then(response => {
                console.log(response);
                this.followingCount = response.data[0];
                this.followerCount = response.data[1];
            }).catch(error => {
                alert(error)
            })
        },
        follow(){
            Service.post("follow",{
                followerid : this.userId ,   //フォロしたいユーザのId
                followingid : store.state.id //自分のId
            }).then(response =>{
                console.log(response);
                this.followsid = response.data;
                this.aaa = true;
            }).catch(error =>{
                alert(error)
            })
        },
        unfollow(){
            Service.post("unfollow",this.followsid
            ).then(response =>{
                alert(response)
                this.aaa = false;
            }).catch(error =>{
                alert(error)
            })
        },
        followJudgement(){
            Service.post("followJudge",{
                followerid : this.userId ,   //フォロしたいユーザのId
                followingid : store.state.id //自分のId
            }).then(response =>{
                alert(response.data)
                if(response.data !== null){
                    this.aaa = true;
                    this.followsid = response.data;
                }else{
                    this.aaa = false;
                }
            }).catch(error =>{
                alert("followJudg"+error)
            })
        },

    }
}
</script>

<!-- <style>
#main {
  box-sizing: border-box;
  margin-left: 70px;
  /* padding: 20px 40px; */
}
</style> -->