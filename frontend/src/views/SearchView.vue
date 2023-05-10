<template>
    <div id="main">
        <h1>検索</h1>
        <input @keyup.enter="getSearch" type="search" v-model=searchInput>
        <button @click="getSearch">search</button>
        <!-- q...{{ qwerty }} <br>
        s...{{ searchTableObject }} <br>
        ss...{{ searchTables }} -->
        <br><br><br>
        <div class="photo-grid">
            <div class="photo" v-for="(searchTable, index) in searchTables " :key="searchTable.id"
                @click="showSearchPages(index, searchTable.id, searchTable.userid)">
                <img class="photo-grid-img" :src="getVueCliUrl(searchTable.image)" alt="投稿画像">
            </div>
            <HomeSearchComponent v-show="showHomeSearchComponent" 
                :homeTableObject="searchTableObject"
                :commentTableObject="commentTableObject" 
                :qwerty="qwerty" 
                :showDeleteButton="showDeleteButton"
                @close="showHomeSearchComponent = false"
                @refresh-comment="showSearchPages(clickImgIndex, searchTableObject.id, searchTableObject.userid)"
                @refresh-likes="updateLikes(clickImgIndex)" />
            <div v-if="showHomeSearchComponent" class="overlay" @click="showHomeSearchComponent = null"></div>
        </div>
        <div v-show="ppp">
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
    data() {
        return {
            searchInput: null,
            searchTables: null,
            searchTableObject: { "id": 3, "userid": 4, "image": "jkl.jpeg", "caption": "post", "likes": 4 },
            showHomeSearchComponent: false,
            clickImgIndex: null,
            commentTableObject: null,
            ppp: false,
            qwerty: { "id": 4, "name": "矢口", "password": "pass", "profile_picture": "images.jpeg" },
            showDeleteButton: false
        }
    },
    methods: {
        getSearch() {
            Service.post('search', this.searchInput).then(response => {
                console.log(response);
                this.searchTables = response.data;
                this.ppp = false;
                if (response.data[0] == undefined) {
                    this.ppp = true;
                }
            }).catch(error => {
                alert(error)
            })
        },
        showSearchPages(index, postId, userId) {
            this.showHomeSearchComponent = true;
            this.clickImgIndex = index;
            this.searchTableObject = this.searchTables[index];
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
            return require(`../assets/post/${imgUrl}`);
        },
        updateLikes(index) {
            Service.post("search", this.searchInput).then(response => {
                console.log(response);
                this.searchTables = response.data;
                this.searchTableObject = this.searchTables[index];
                this.clickImgIndex = index;
            }).catch(error => {
                alert(error)
            })
        },
    }
}
</script>

<style scoped>
img {
    width: 300px;
    height: 300px;
}

#main {
    overflow-y: hidden;
}
</style>