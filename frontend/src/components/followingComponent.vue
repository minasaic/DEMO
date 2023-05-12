<template>
    <div class="photo-details-wrapper">
        <div class="photo-details-dialog">
            <div class="dialog-content">
                {{followComponentTitle}}

                <br><br>
                <!-- <img class="mypage-img" :src="getVueCliUrl(postTableObject.image)" alt="post"> -->
                <div >
                    <div v-for="(following, index) in follows" :key="following.id">
                        <a @click="setStoreUserId(index)">
                            <img class="round-image" :src="getVueCliUrlProfile(following.profile_picture)" alt="プロフィール画像">
                            {{ following.name }}
                        </a>
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
    name: 'followingComponent',
    created(){
        // this.$emit('getFollowings')
        // this.$emit('getFollowers')
    },
    props: {
        follows: {
            type: Array,
            require: true
        },
        followComponentTitle: {
            type: String,
            require: true
        }

    },
    data() {
        return {

        }
    },
    methods: {
        getVueCliUrlProfile(imgUrl) {
            return require(`../assets/profile/${imgUrl}`);
        },
        setStoreUserId(index){
            this.clickUserData = this.follows[index];
            store.commit('SETUSERID',this.clickUserData.id);
            sessionStorage.setItem('user_id',this.clickUserData.id);
            alert('setStorUserId  ' + store.state.userId);
            this.$router.push('/userpage');
        },
    }
}
</script>
<style scoped>
.dialog-content{
    overflow-y: scroll;
}
</style>