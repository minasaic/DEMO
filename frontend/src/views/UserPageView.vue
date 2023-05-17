<template>
    <div id="main" class="photo-gallery">
        <div class="moriii">
            <nobr id="sub">
                <img v-if="userName.profile_picture !== null" class="round-image" :src="profilea(userName.profile_picture)" alt="プロフィール画像">
                <span v-else>プロフィール写真なし</span>
            </nobr>
            <nobr class="saimina">
                <b class="userName">{{ userName.name }}</b>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a v-show="aaa" @click="unfollow" class="btn-unfollow">unfollow</a>
                <a v-show="aaa === false" @click="follow" class="btn-follow">follow</a>
                <br><br><br><br>
                <b> &nbsp;&nbsp;&nbsp;&nbsp;投稿 {{ postCount }} 件 </b>
                <b><a @click="getFollowers"> &nbsp;&nbsp;&nbsp;&nbsp;フォロワー {{ followerCount }} 人</a> </b>
                <FollowingComponent v-if="showFollows" 
                :follows="ff" 
                :followComponentTittle="followComponentTittle"
                @close="showFollows = false" 
                />
                <b><a @click="getFollowings"> &nbsp;&nbsp;&nbsp;&nbsp;フォロー中 {{ followingCount }} 人</a> </b>
                <FollowingComponent v-if="showFollows" 
                @close="showFollows = false" 
                :follows="ff" 
                :followComponentTittle="followComponentTittle"
                />
                <br><br>
            </nobr>
        </div>
        <hr>
        <!-- poststable {{ userDatas }} <br>
        h{{ homeTableObject }} <br>
        c{{ commentTableObject }} <br> -->
        <div class="photo-grid">
            <div v-for="(userData, index) in userDatas" :key="userData.id" @click="showHomePages(index, userData.id)">
                <img class="photo-grid-img" :src="getVueCliUrl(userData.image)" alt="投稿画像">
            </div>
        </div>
        <HomeSearchComponent v-if="showHomeSearchComponent" :homeTableObject="homeTableObject"
            :commentTableObject="commentTableObject" :qwerty="qwerty" :showDeleteButton="showDeleteButton"
            :show="showLikeJudge" @close="showHomeSearchComponent = false"
            @refresh-comment="showHomePages(clickImgIndex, homeTableObject.id)"
            @refresh-likes="updateLikes(clickImgIndex, homeTableObject.id)" />
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
        this.getPostCount()

    },
    data() {
        return {
            userName: {
                "id": 3,
                "name": "崔",
                "password": "pass",
                "profile_picture": "pp.jpeg"
            },
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
            qwerty: { "id": 4, "name": "矢口", "password": "pass", "profile_picture": "images.png" },
            homeTableObject: null,
            commentTableObject: null,
            showLikeJudge: false,
            postCount: null,
            followComponentTittle:null
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
            this.likeJudge(postId);
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

        updateLikes(index, postId) {
            Service.post("mypage", this.userId).then(response => {
                console.log(response);
                this.userDatas = response.data;
                this.homeTableObject = this.userDatas[index];
                this.clickImgIndex = index;
                this.likeJudge(postId);
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
                this.followerCount += 1;
            }).catch(error => {
                alert(error)
            })
        },
        unfollow() {
            Service.post("unfollow", this.followsid
            ).then(response => {
                alert(response)
                this.aaa = false;
                this.followerCount -= 1;
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
        profilea(userProfile) {
            if (userProfile !== null) {
                return require('../assets/profile/' + userProfile);
            } else {
                return null;
            }
        },
        getVueCliUrl(imgUrl) {
            const imgUrls = imgUrl.split(',')
            return require(`../assets/post/${imgUrls[0]}`);
        },
        getFollowers() {
            Service.post("getFollowers", store.state.userId).then(response => {
                console.log(response);
                this.showFollows = true;
                this.ff = response.data;
                this.followComponentTittle = 'フォロワー';
            }).catch(error => {
                alert(error)
            })
        },
        getFollowings() {
            Service.post("getFollowings", store.state.userId).then(response => {
                console.log(response);
                this.showFollows = true;
                this.followComponentTittle = 'フォロー中';
                this.ff = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        likeJudge(postId) {
            Service.post('/likejudge', {
                postid: postId,
                userid: store.state.id
            }).then(response => {
                console.log(response);
                this.showLikeJudge = response.data;
            }).catch(error => {
                alert(error);
            })
        },
        //投稿数を取得
        getPostCount() {
            Service.post("postdata", store.state.userId).then(response => {
                console.log(response);
                this.postCount = response.data;
            }).catch(error => {
                alert(error)
            })
        },
    }
}
</script>

<style>
.btn-unfollow {
    color: rgb(198, 21, 21);
    font-weight: 700;
}

.btn-follow {
    color: rgb(2, 114, 220);
    font-weight: 700;
}
</style>