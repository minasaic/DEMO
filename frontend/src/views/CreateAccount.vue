<template>
    <div>
        <div id="titles" style="text-align: center;">
            <h1>Create Account</h1>
            <br><br><br><br>
            <img src="../assets/system/main.png" alt="LOGO" width="200" height="200">
            <br><br><br><br>
            <br><br>
            <span>ユーザネーム:</span>
            <input type="id" name="username" v-model=valueName />
            <br><br>
            <span>パスワード:</span>
            <input type="password" name="userpass" v-model=valuePass />
            <br><br>
            <br>
            <button type="button" @click="create">アカウント新規作成</button>
            <button @click="goToSign">ログインページへ</button>

        </div>
    </div>
</template>

<script>
import { Service } from '@/service/service'
import store from '@/store'
export default {
    name: 'CreateAccount',
    data() {
        return {
            valueName: null,
            valuePass: null
        }
    },
    methods: {
        goToSign() {
            store.commit('SETSIGNSIGU',true);
        },
        create() {
            Service.post("create", {
                name: this.valueName,
                password: this.valuePass
            }).then(response => {           //ログイン成功時の処理(axios通信成功時)
                console.log(response);
                if (response.data.id !== null) {
                    this.login = true;
                    store.commit('SETPAGEBOOLEAN',true)
                    store.commit('SETID', response.data.id);  //responseされたIdをストア内stateのidにセット
                    store.commit('SETNAME', response.data.name);
                    store.commit('SETPROFILE', response.data.profile_picture);
                    // セッションストレージに保存
                    sessionStorage.setItem('id', response.data.id);
                    sessionStorage.setItem('name', response.data.name);
                    sessionStorage.setItem('profile_picture', response.data.profile_picture);
                    alert('アカウントが新規作成しました。' + '\nID : ' + store.state.id + '\n' + 'PASSWORD : ' + this.valuePass + '\n' + store.state.profile);
                } else {
                    alert('アカウント作成できません。');
                }
            }).catch(error => {
                console.log(error);
                alert('エラー起きました。')
            })
        },
    }
}
</script>