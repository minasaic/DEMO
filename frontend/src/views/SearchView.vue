<template>
    <div id="main">
        <h1>検索</h1>
        <input @keyup.enter="getSearch" type="search" v-model=searchInput>
        <button @click="getSearch">search</button>
        <br><br><br>
        <div>{{ returnSearchs }}</div>
        <div class="photo-grid">
            <div class="photo" v-for="(searchTable, index) in searchTables " :key="searchTable.id"
                @click="showSearchPages(index, searchTable.id)">
                <img class="photo-grid-img" :src="getVueCliUrl(searchTable.image)" alt="投稿画像">
            </div>
            <HomeSearchComponent v-show="showHomeSearchComponent" :homeTableObject="searchTableObject"
                :commentTableObject="commentTableObject" 
                :qwerty="qwerty"
                @close="showHomeSearchComponent = false"
                @refresh-data="showSearchPages(clickImgIndex, searchTableObject.id)" 
                @refresh-likes="updateLikes(clickImgIndex)"
                />
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
export default {
    name: 'SearchView',
    components: {
        HomeSearchComponent
    },
    data() {
        return {
            searchInput: null,
            searchTables: null,
            searchTableObject: null,
            showHomeSearchComponent: false,
            clickImgIndex: null,
            commentTableObject: null,
            ppp: false,
            qwerty: null
        }
    },
    methods: {
        getSearch() {
            Service.post('search', this.searchInput).then(response => {
                console.log(response);
                this.searchTables = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        showSearchPages(index, postId) {
            this.showHomeSearchComponent = true;
            this.searchTableObject = this.searchTables[index];
            //クリックした写真のコメントテーブルを取得する
            Service.post('getcom', postId//commentテーブルのpost_id
            ).then(response => {
                console.log(response)
                this.commentTableObject = response.data;
                this.clickImgIndex = index;
            }).catch(error => {
                alert(error)
            }),
            //ユーザー情報取得
            Service.post('getuser',this.searchTableObject.userid).then(response =>{
                console.log(response)
                
                this.qwerty = response.data;
                return true
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
#main{
    overflow-y: hidden;
}
</style>