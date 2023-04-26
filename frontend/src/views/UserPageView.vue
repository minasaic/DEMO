<template>
    <div id="main">
        <img src="" alt="プロフィール画像" width="100" height="100"> &nbsp;&nbsp;
        <b>アカウント：{{ userName }} {{ userId }}</b> 
        <br>
        <b>フォロワー：{{ followerCount }}</b>
        <br>
        <b>フォロウィン：{{ followingCount }}</b>
        <br>
        <button v-if="!followJudgement()" @click="follow">follow</button>
        <button v-else @click="unfollow">unfollow</button>
        <br><br>
        <div>
            過去の投稿一覧
        </div>
        <hr>
        <div v-for="(userData) in userDatas" :key="userData.id">
          <!-- {{ data }} -->
          <UserComponent
            :postImgName="userData.image" 
            :caption = userData.caption
            :userId = userData.userid
            :postId = userData.id
            :likesCount = userData.likes
          />
        </div>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
import UserComponent from "@/components/UserComponent.vue"
export default {
    name:'UserComponents',
    components:{
        UserComponent
    },
    created(){       //このページになったら自動で動くもの
        this.userId = this.$route.params.userId
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
            followsid: null
        }
    },
    mounted(){
        // this.userId = parseInt(this.$route.params.userId)
        
        // alert(this.userId)

    },
    methods:{
        myPage(){
            Service.post("mypage",this.userId).then(response => {
                console.log(response);
                this.userDatas = response.data;
                alert(this.userDatas)
            }).catch(error =>{
                alert(error)
            })
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
            }).catch(error =>{
                alert(error)
            })
        },
        unfollow(){
            Service.post("unfollow",{
                followerid : this.userId ,   //フォロしたくないユーザのId
                followingid : store.state.id  //自分のId
            }).then(response =>{
                alert(response)
            }).catch(error =>{
                alert(error)
            })
        },
        followJudgement(){
            Service.post("followJudge",{
                followerid : this.userId ,   //フォロしたいユーザのId
                followingid : store.state.id //自分のId
            }).then(response =>{
                return response.data
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
  margin-left: 70px;
  /* padding: 20px 40px; */
}
</style>