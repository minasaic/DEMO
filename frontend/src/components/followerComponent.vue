<template>
    <div class="photo-details-wrapper">
        <div class="photo-details-dialog">
            <div class="dialog-content">
                フォロー一覧 {{ clickUserData }}
                <!-- <img class="mypage-img" :src="getVueCliUrl(postTableObject.image)" alt="post"> -->
                <div >
                    <!-- followers: userTable -->
                    <div v-for="(follower,index) in followers" :key="follower.id"> 
                        <router-link to="/userpage" >
                            <button @click="setStoreUserId(index,)">
                                <img class="round-image" :src="getVueCliUrl(follower.profile_picture)" alt="プロフィール画像">
                                {{ follower.name }}
                            </button>
                        </router-link>
                    </div>
                </div>
                <button class="close-button" @click="$emit('close')">X</button>
            </div>
        </div>
    </div>
</template>
<script>
import store from '@/store'

export default {
    name: 'followerComponent',
    created(){
        this.$emit('getFollowers');
    },
    props: {
        followers: {
            type: Array,
            require: true
        }
    },
    data() {
        return {
            clickUserData: null
            // followings: [
            //     { id:'1',userName: 'yaguchi', profil: 'homeimg4.jpeg' },
            //     { id:'2',userName: 'morigami', profil: 'homeimg4.jpeg' },
            //     { id:'3',userName: 'sai', profil: 'homeimg4.jpeg' },
            //     { id:'4',userName: 'inagaki', profil: 'homeimg4.jpeg' },
            // ]
        }
    },
    methods: {
        getVueCliUrl(imgUrl) {
            return require(`../assets/profile/${imgUrl}`);
        },
        setStoreUserId(index,){
            this.clickUserData = this.followers[index];
            store.commit('SETUSERID',this.clickUserData.id);
            sessionStorage.setItem('user_id',this.clickUserData.id);
            alert('setStorUserId  '+store.state.userId);
        },
    }
}
</script>
<style>
</style>