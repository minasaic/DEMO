<template>
    <div>
        <h1>検索</h1>
        <input type="search" v-model=searchInput>
        <button @click="getSearch">search</button>
        <br><br><br>
        <div >{{ returnSearch }}</div>
    </div>
</template>

<script>
import {Service} from "@/service/service"
    export default{
        name:'SearchView',
        data(){
            return{
                searchInput:null,
                returnSearch:''
            }
        },
        methods:{
            getSearch(){
                Service.post('search',this.searchInput).then(response => {
                    console.log(response);
                    this.returnSearch = response.data;
                }).catch(error => {
                    alert(error)
                })

            }
        }
    }
</script>