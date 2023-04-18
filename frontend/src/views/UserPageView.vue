<template>
    <div>
        <h1>user acount page</h1>
        <br><br><br>
        <img src="" alt="ユーザプロフィール画像">
        <br><br>
        <span>{{ anotherName }}</span>
        <br><br><br>
        <button @click="follow">follow</button>
        <button @click="unfollow">unfollow</button>
    </div>
</template>
<script>
import {Service} from "@/service/service"
import store from '@/store'
export default {
    name:'UserPageView',
    data(){
        return{
            anotherName: '森上',
            anotherId: 20
        }
    },
    methods:{
        follow(){
        Service.post("follow",{
            Following_id : store.state.id,  //自分のId
            Follower_id : this.anotherId    //フォロしたいユーザのId
        }).then(response =>{
            alert(response)
        }).catch(error =>{
            alert(error)
        })
        },
        unfollow(){
            Service.post("unfollow",{
                Following_id : store.state.id,  //自分のId
                Follower_id : this.anotherId    //フォロしたくないユーザのId
            }).then(response =>{
                alert(response)
            }).catch(error =>{
                alert(error)
            })
        }
    }
 
}
</script>