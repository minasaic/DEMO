<template>
    <div>
        <h1>検索</h1>
        <input type="search" v-model=searchInput>
        <br><br><br>
        <div>{{ returnSearch }}</div>
    </div>
</template>

<script>
    export default{
        name:'SearchView',
        data(){
            return{
                searchInput:'sjsj',
                returnSearch:'djdj'
            }
        }
    }
</script>