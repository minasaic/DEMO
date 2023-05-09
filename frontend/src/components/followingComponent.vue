<template>
    <div class="photo-details-wrapper">
        <div class="photo-details-dialog">
            <div class="dialog-content">
                フォロー一覧
                <!-- <img class="mypage-img" :src="getVueCliUrl(postTableObject.image)" alt="post"> -->
                <div >
                    <div v-for="(following) in followings" :key="following.id">
                        <button>
                            <img class="round-image" :src="getVueCliUrl(following.profile_picture)" alt="プロフィール画像">
                            {{ following.name }}
                        </button>
                    </div>
                </div>
                <button class="close-button" @click="$emit('close')">X</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'followingComponent',
    created(){
        this.$emit('getFollowings');
    },
    props: {
        followings: {
            type: Array,
            require: true
        }
    },
    data() {
        return {
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
            return require(`../assets/post/${imgUrl}`);
        },
    }
}
</script>
<style></style>