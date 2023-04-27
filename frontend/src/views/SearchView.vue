<template>
    <div id="main">
        <h1>検索</h1>
        <input @keyup.enter="getSearch" type="search" v-model=searchInput>
        <button @click="getSearch">search</button>
        <br><br><br>
        <div >{{ returnSearchs }}</div>
        <div v-for="(returnSearch) in returnSearchs" :key="returnSearch.id">
            <HomeSearchComponent
            :postImgName="returnSearch.image" 
            :caption = returnSearch.caption
            :userId = returnSearch.userid
            :postId = returnSearch.id
            :userName = returnSearch.userName
            :likesCount = returnSearch.likes
            @update-likes="updateLikes($event,returnSearch.id)"
          />
        <div >
            当てはまる投稿がないです。
        </div>
        </div>
    </div>
</template>

<script>
import {Service} from "@/service/service"
import HomeSearchComponent from "../components/HomeSearchComponent.vue"
    export default{
        name:'SearchView',
        components:{
            HomeSearchComponent
        },
        data(){
            return{
                searchInput: null,
                returnSearchs: null,
                SearchsSize: false
            }
        },
        methods:{
            getSearch(){
                Service.post('search',this.searchInput).then(response => {
                    console.log(response);
                    this.returnSearchs = response.data;
                    if(this.returnSearchs.length === 0) {
                        this.SearchsSize = true;
                    }
                }).catch(error => {
                    alert(error)
                })
            },
            updateLikes(likes,postId) {
                const postIndex = this.returnSearchs.findIndex(post => post.id === postId);
                this.returnSearchs[postIndex].likes = likes;    
            }
        }
    }
</script>

<style scoped>
img{
  width: 300px;
  height: 300px;
}
</style>