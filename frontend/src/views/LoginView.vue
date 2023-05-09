<template>
    <div>
        <div id="titles" style="text-align: center;">
            <br><br><br><br>
            <img src="../assets/system/mainlogo.png" alt="LOGO" width="400" height="100">
            <br><br><br><br>
            <br><br>
            <input type="id" name="username" v-model=valueName placeholder="ユーザーネーム" style="font-size:30px;" />
            <br><br>
            <input type="password" name="userpass" v-model=valuePass @keyup.enter="logins" placeholder="パスワード" style="font-size:30px;"/>
            <br><br>
            <br>
            <span class="button001"><a  type="button" @click="logins">ログイン</a></span>         
            <br>
            <span>アカウントをお持ちではないですか？</span>
            <span style="color:#0000FF;"><a  type="button" @click="goToSigup">登録する</a></span>
            
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
                    sessionStorage.setItem('page_boolean', true);
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

<style>

/* 001 */
.button001 a {
    background: #eee;
    border-radius: 3px;
    position: relative;
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin: 0 auto;
    max-width: 280px;
    padding: 10px 25px;
    color: #313131;
    transition: 0.3s ease-in-out;
    font-weight: 500;
}
.button001 a:hover {
    background: #313131;
    color: #FFF;
}
.button001 a:after {
    content: '';
    width: 5px;
    height: 5px;
    border-top: 3px solid #313131;
    border-right: 3px solid #313131;
    transform: rotate(45deg) translateY(-50%);
    position: absolute;
    top: 50%;
    right: 20px;
    border-radius: 1px;
    transition: 0.3s ease-in-out;
}
.button001 a:hover:after {
    border-color: #FFF;
}


</style>