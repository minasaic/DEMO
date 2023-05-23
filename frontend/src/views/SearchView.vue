<template>
    <div id="main">
        <h1>検索</h1>
        <input @input="getSearch" type="search" v-model=searchInput>
        <!-- q...{{ qwerty }} <br>
        s...{{ searchTableObject }} <br>
        ss...{{ searchTables }} -->
        <br><br><br>
        <div class="user-table-container">
            <div v-for="(searchUserTable, index) in searchUserTables" :key="searchUserTable.id" class="user-table">
                <a @click="setStoreUserId(index)" class="user-link">
                    <div class="image-container">
                        <img v-if="searchUserTable.profile_picture !== null" class="round-image"
                            :src="getVueCliUrlProfile(searchUserTable.profile_picture)" alt="ユーザプロフィール">
                        <img v-else src="" alt="プロフィール写真なし">
                    </div>
                    <span>{{ searchUserTable.name }}</span>
                </a>
            </div>
        </div>
        <div class="photo-grid">
            <div class="photo" v-for="(searchTable, index) in searchTables " :key="searchTable.id"
                @click="showSearchPages(index, searchTable.id, searchTable.userid)">
                <div v-if="userProfile[index] != null">  <!--森上あああああああああ-->
                    <img class="home-search-profile" :src="getVueCliUrlProfile(userProfile[index])">
                    <span> {{ userOnamae[index] }}</span>
                </div>
                <img class="photo-grid-img" :src="getVueCliUrl(searchTable.image)" alt="投稿画像">
            </div>
            <HomeSearchComponent v-if="showHomeSearchComponent" :homeTableObject="searchTableObject"
                :commentTableObject="commentTableObject" :qwerty="qwerty" :showDeleteButton="showDeleteButton"
                :show="showLikeJudge" @close="showHomeSearchComponent = false"
                @refresh-comment="showSearchPages(clickImgIndex, searchTableObject.id, searchTableObject.userid)"
                @refresh-likes="updateLikes(clickImgIndex, searchTableObject.id)" />
            <div v-if="showHomeSearchComponent" class="overlay" @click="showHomeSearchComponent = null"></div>
        </div>
        <div v-show="ppp == true && mori == true">
            当てはまる投稿がないです。
        </div>
    </div>
</template>

<script>
import { Service } from "@/service/service"
import HomeSearchComponent from "../components/HomeSearchComponent.vue"
import store from "@/store"
export default {
    name: 'SearchView',
    components: {
        HomeSearchComponent
    },
    created() {
        this.getSearch()
    },
    data() {
        return {
            searchInput: '',
            searchTables: null,
            searchTableObject: { "id": 3, "userid": 4, "image": "jkl.jpeg", "caption": "post", "likes": 4 },
            showHomeSearchComponent: false,
            clickImgIndex: null,
            commentTableObject: null,
            ppp: false,
            qwerty: { "id": 4, "name": "矢口", "password": "pass", "profile_picture": "images.jpeg" },
            showDeleteButton: false,
            showLikeJudge: false,
            searchUserTables: null,
            mori: false,
            searchUserTableObject: null,
            userOnamae: [],
            userProfile: []
        }
    },
    methods: {
        // getSearch() {
        //     Service.post('search', this.searchInput).then(response => {
        //         console.log(response);
        //         this.searchTables = response.data;
        //         this.ppp = false;
        //         if (response.data[0] == undefined) {
        //             this.ppp = true;
        //         }
        //         //拡張for文でユーザ情報を取ってきてる
        //         this.searchTables.forEach((searchTable) => {
        //             Service.post('getuser', searchTable.userid).then(response => {
        //                 console.log(response);
        //                 this.userOnamae.push(response.data.name);
        //                 this.userProfile.push(response.data.profile_picture);
        //             }).catch(error => {
        //                 alert(error);
        //             })
        //         })
        //     }).catch(error => {
        //         alert(error)
        //     }),
        //     Service.post('searchUser', this.searchInput).then(response => {
        //         console.log(response);
        //         this.searchUserTables = response.data;
        //         this.mori = false;
        //         if (response.data[0] == undefined) {
        //             this.mori = true;
        //         }
        //     }).catch(error => {
        //         alert(error)
        //     })
        // },
        //非同期処理を並列実行する森上あああああああああああああああああああああああああああああ
        getSearch() {
  const searchPostPromise = Service.post('search', this.searchInput);
  const searchUserPromise = Service.post('searchUser', this.searchInput);

  Promise.all([searchPostPromise, searchUserPromise])
    .then(([searchPostResponse, searchUserResponse]) => {
      console.log(searchPostResponse);
      console.log(searchUserResponse);

      this.searchTables = searchPostResponse.data;
      this.ppp = false;
      if (searchPostResponse.data[0] == undefined) {
        this.ppp = true;
      }

      this.searchUserTables = searchUserResponse.data;
      this.mori = false;
      if (searchUserResponse.data[0] == undefined) {
        this.mori = true;
      }

      const getPostUserPromises = this.searchTables.map(searchTable => {
        return Service.post('getuser', searchTable.userid);
      });

      const getPostUserResponses = Promise.all(getPostUserPromises);
      console.log(getPostUserResponses);

      return getPostUserResponses;
    })
    .then(userResponses => {
      userResponses.forEach(response => {
        console.log(response);
        this.userOnamae.push(response.data.name);
        this.userProfile.push(response.data.profile_picture);
      });
    })
    .catch(error => {
      alert(error);
    });
},

        showSearchPages(index, postId, userId) {
            this.showHomeSearchComponent = true;
            this.clickImgIndex = index;
            this.searchTableObject = this.searchTables[index];

            this.likeJudge(postId);
            //クリックした投稿のユーザが自分かどうか判断する
            if (store.state.id == userId) {
                this.showDeleteButton = true;
            } else {
                this.showDeleteButton = false;
            }
            //ユーザー情報取得
            Service.post('getuser', userId).then(response => {
                console.log(response);
                this.qwerty = response.data;
                return true
            }).catch(error => {
                alert(error)
            })
            //クリックした写真のコメントテーブルを取得する
            Service.post('getcom', postId//commentテーブルのpost_id
            ).then(response => {
                console.log(response);
                this.commentTableObject = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        getVueCliUrl(imgUrl) {
            const imgUrls = imgUrl.split(',')
            return require(`../assets/post/${imgUrls[0]}`);
        },
        getVueCliUrlProfile(imgUrl) {
            return require(`../assets/profile/${imgUrl}`);
        },
        updateLikes(index, postId) {
            Service.post("search", this.searchInput).then(response => {
                console.log(response);
                this.searchTables = response.data;
                this.searchTableObject = this.searchTables[index];
                this.clickImgIndex = index;
                this.likeJudge(postId);
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
        setStoreUserId(index) {
            this.searchUserTableObject = this.searchUserTables[index];
            if (this.searchUserTableObject.id != store.state.id) {
                store.commit('SETUSERID', this.searchUserTableObject.id);
                sessionStorage.setItem('user_id', this.searchUserTableObject.id);
                this.$router.push('/userpage')
            } else {
                this.$router.push('/mypage')
            }
        },
    }
}
</script>

<style scoped>
#main {
    overflow-y: hidden;
}

.user-table-container {
    display: flex;
    overflow-x: auto;
    white-space: nowrap;
}

.user-table {
    margin: 10px;
    width: 100px;
    height: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}

.user-link {
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.image-container {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    overflow: hidden;
    margin-bottom: 5px;
}

.round-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>