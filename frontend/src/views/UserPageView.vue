<template>
    <div id="main">
        <img class="round-image" :src="profilea(userName.profile_picture)" alt="プロフィール画像" width="100" height="100">
        &nbsp;&nbsp;
        <b>{{ userName.name }}</b>
        <br>
        <b>ID: {{ $store.state.userId }}</b>
        <br>
        <b>フォロワー：<button @click="getFollowers"> {{ followerCount }} </button> 人 </b>
        <FollowingComponent v-if="showFollows" @close="showFollows = false" :follows="ff" />
        <br>
        <b>フォロー：<button @click="getFollowings"> {{ followingCount }} </button> 人 </b>
        <FollowingComponent v-if="showFollows" @close="showFollows = false" :follows="ff" />
        <br>
        <button v-show="aaa" @click="unfollow">unfollow</button>
        <button v-show="aaa === false" @click="follow">follow</button>
        <br><br>
        <div>
            過去の投稿一覧
        </div>
        <hr>
        poststable {{ userDatas }} <br>
        h{{ homeTableObject }} <br>
        c{{ commentTableObject }} <br>

        <div v-for="(userData,index) in userDatas" :key="userData.id" @click="showHomePages(index, userData.id)">
            <img class="photo-grid-img" :src="getVueCliUrl(userData.image)" alt="投稿画像">
        </div>
        <HomeSearchComponent v-if="showHomeSearchComponent" 
            :homeTableObject="homeTableObject"
            :commentTableObject="commentTableObject"
            :qwerty="qwerty"
            :showDeleteButton="showDeleteButton" 
            @close="showHomeSearchComponent = false"
            @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id)"
            @update-likes="updateLikes(clickImgIndex)" />
    </div>
</template>
<script>
import { Service } from "@/service/service"
import store from '@/store'
import HomeSearchComponent from "@/components/HomeSearchComponent.vue"
import FollowingComponent from "@/components/followingComponent.vue"

export default {
    name: 'UserPageView',
    components: {
        FollowingComponent,
        HomeSearchComponent
    },
    created() {       //このページになったら自動で動くもの
        const userId = sessionStorage.getItem('user_id')
        if (userId) {
            store.commit('SETUSERID', userId)
            this.userId = userId
            alert('getItem' + this.userId)
        }

        this.followJudgement()
        this.myPage()
        this.getFollowerCount()
        this.getUserNameAndImage()

    },
    data() {
        return {
            userName: null,
            followerCount: null,
            followingCount: null,
            userDatas: null,
            userId: null,
            followsid: null,
            aaa: false,
            showFollows: false,
            ff: null,
            clickImgIndex: null,
            showHomeSearchComponent: false,
            showDeleteButton: false,
            qwerty: null,
            homeTableObject: null,
            commentTableObject: null
        }
    },

    methods: {
        myPage() {
            Service.post("mypage", this.userId).then(response => {
                console.log(response);
                this.userDatas = response.data;
                console.log("aaaa" + this.userDatas)
            }).catch(error => {
                alert(error)
            })
        },
        showHomePages(index, postId) {
            this.showHomeSearchComponent = true;
            this.homeTableObject = this.userDatas[index];
            //クリックした写真のコメントテーブルを取得する
            Service.post('getcom', postId//commentテーブルのpost_id
            ).then(response => {
                console.log(response)
                this.commentTableObject = response.data;
                this.clickImgIndex = index;
            }).catch(error => {
                alert(error)
            })
            //ユーザー情報取得
            Service.post('getuser', this.homeTableObject.userid).then(response => {
                console.log(response)
                this.qwerty = response.data;
                return true
            }).catch(error => {
                alert(error)
            })
        },
        updateLikes(index) {
            Service.post("home", store.state.id).then(response => {
            console.log(response);
            this.userDatas = response.data;
            this.homeTableObject = this.userDatas[index];
            this.clickImgIndex = index;
            }).catch(error => {
                alert(error)
            })
        },
        getFollowerCount() {
            Service.post("followdata", this.userId).then(response => {
                console.log(response);
                this.followingCount = response.data[0];
                this.followerCount = response.data[1];
            }).catch(error => {
                alert(error)
            })
        },
        follow() {
            Service.post("follow", {
                followerid: this.userId,   //フォロしたいユーザのId
                followingid: store.state.id //自分のId
            }).then(response => {
                console.log(response);
                this.followsid = response.data;
                this.aaa = true;
            }).catch(error => {
                alert(error)
            })
        },
        unfollow() {
            Service.post("unfollow", this.followsid
            ).then(response => {
                alert(response)
                this.aaa = false;
            }).catch(error => {
                alert(error)
            })
        },
        followJudgement() {
            Service.post("followJudge", {
                followerid: this.userId,   //フォロしたいユーザのId
                followingid: store.state.id //自分のId
            }).then(response => {
                console.log(response.data);
                if (response.data !== 10000) {
                    this.aaa = true;
                    this.followsid = response.data;
                } else if (response.data === 10000) {
                    this.aaa = false;
                }
            }).catch(error => {
                alert("followJudg     " + error)
            })
        },
        getUserNameAndImage() {
            Service.post("getusernameandimage", store.state.userId
            ).then(response => {
                this.userName = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        profilea() {
            if (store.state.profile !== null) {
                return require('../assets/profile/' + store.state.profile);
            } else {
                return null;
            }
            },
        getVueCliUrl(imgUrl) {
            this.vueCliUrl = require('../assets/post/' + imgUrl);
            return this.vueCliUrl;
        },
        getFollowers() {
            Service.post("getFollowers", store.state.userId).then(response => {
                console.log(response);
                this.showFollows = true;
                // this.followers = response.data;
                this.ff = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        getFollowings() {
            Service.post("getFollowings", store.state.userId).then(response => {
                console.log(response);
                this.showFollows = true;
                // this.followings = response.data;
                this.ff = response.data;
            }).catch(error => {
                alert(error)
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