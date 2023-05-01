<template>
    <div>
        <div id="titles" style="text-align: center;">
            <h1>Login</h1>
            <br><br><br><br>
            <img src="../assets/system/main.png" alt="LOGO" width="200" height="200">
            <br><br><br><br>
            <br><br>
            <span>名前:</span>
            <input type="id" name="username" v-model=valueName />
            <br><br>
            <span>PASS:</span>
            <input type="password" name="userpass" v-model=valuePass @keyup.enter="logins" />
            <br><br>
            <br>
            <button type="button" @click="logins">ログイン</button>
            <br>
            <button type="button" @click="goToSigup">アカウント新規作成へ</button>
        </div>

    </div>
</template>

<script>
import { Service } from '@/service/service'
import store from '@/store'
export default {
    name: 'LoginView',
    created() {

    },
    data() {
        return {

            valueName: null,
            valuePass: null
        }
    },
    methods: {

        goToSigup() {
            store.commit('SETSIGNSIGU', false);
        },
        logins() {
            Service.post("login", {
                name: this.valueName,
                password: this.valuePass
            }).then(response => {       //ログイン成功時の処理(axios通信成功時)
                console.log(response);
                if (response.data.id !== null) {
                    store.commit('SETPAGEBOOLEAN', true);
                    store.commit('SETID', response.data.id);        //responseされたIdをストア内stateのidにセット
                    store.commit('SETNAME', response.data.name);
                    store.commit('SETPROFILE', response.data.profile_picture);
                    // セッションストレージに保存
                    sessionStorage.setItem('id', response.data.id);
                    sessionStorage.setItem('name', response.data.name);
                    sessionStorage.setItem('profile_picture', response.data.profile_picture);
                    alert('ID : ' + store.state.id + '\nName : ' + response.data.name + '\n' + 'PASSWORD : ' + this.valuePass + '\n' + store.state.profile);
                } else {
                    alert("パスワードが間違ってます。");
                }
            }).catch(error => {
                console.log(error);
                alert("名前が間違っているか、アカウントが存在しません。")
            })
        },
    }
}
</script>