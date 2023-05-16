<template>
    <div class="popup-wrapper">
        <div class="popup-dialog">
            <button class="close-button" @click="$emit('close')">X</button>
            <div class="popup-body">
                <div class="popup-header">
                    {{followComponentTittle}}
                </div>
                <div v-for="(following, index) in follows" :key="following.id" >
                    <a @click="setStoreUserId(index)" class="user-link">
                        <img class="profile-image" :src="getVueCliUrlProfile(following.profile_picture)" alt="プロフィール画像">
                        <span class="username">
                            {{ following.name }}
                        </span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import store from '@/store'
export default {
    name: 'followingComponent',
    props: {
        follows: {
            type: Array,
            require: true
        },
        followComponentTittle: {
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
.popup-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(171, 171, 171, 0.6);
    z-index: 100;
}

.popup-header {
  font-size: 24px;
  color: #616161d3;
  margin-bottom: 20px;
  text-align: center;
  
}

.popup-body {
    display: flex;
    flex-direction: column;
    /*森上が追加したよーーーーーーーーーーーーーー */
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    padding: 20px;
    /*森上が２０を３０にしたよーーー*/
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(153, 153, 153, 0.863);
    overflow-y: auto;
    max-height: 400px;
    /* position: relative; */
}

.user-link {
  display: flex;
  align-items: center;
  color: #4d4d4dd7;
  margin-bottom: 5px;
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 24px;
    cursor: pointer;
}

.profile-image {
    border-radius: 50%;
    width: 50px ;
    height:50px;
    margin-right: 12px;
}
</style>