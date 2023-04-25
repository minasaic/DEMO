<template>
    <div id="main">
        <h1>検索</h1>
        <input type="search" v-model=searchInput>
        <button @click="getSearch">search</button>
        <br><br><br>
        <div >{{ returnSearchs }}</div>
        <div v-for="(returnSearch) in returnSearchs" :key="returnSearch.id">
            <UserPageComponent
            :postImgName="returnSearch.image" 
            :caption = returnSearch.caption
            :userId = returnSearch.userid
            :postId = returnSearch.id
            :userName = returnSearch.userName
            :likesCount = returnSearch.likes
          />
        <div >
            当てはまる投稿がないです。
        </div>
        </div>
    </div>
</template>

<script>
import {Service} from "@/service/service"
import UserPageComponent from "./UserPageComponent.vue"
    export default{
        name:'SearchView',
        components:{
            UserPageComponent
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

        }
    }
</script>
<style>
#main {
  box-sizing: border-box;
  margin-left: 220px;
  padding: 20px 40px;
}
</style>
<style scoped>
img{
  width: 300px;
  height: 300px;
}
</style>