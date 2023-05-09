<template>
    <div>
        <div id="titles" style="text-align: center;">
            <br><br><br><br>
            <img src="../assets/system/mainlogo.png" alt="LOGO" width="400" height="100">
            <br><br><br><br>
            <br><br>
            <input type="id" name="username" v-model=valueName placeholder="ユーザーネーム" style="font-size:30px;" />
            <br><br>
            <input type="password" name="userpass" v-model=valuePass placeholder="パスワード" style="font-size:30px;"/>
            <br><br>
            <br>
            <span class="button001"><a  type="button" @click="create">登録する</a></span> 
            <br>
            <span class="button001"><a  type="button" @click="goToSign">戻る</a></span> 
            <br>
            <br>
            <h3>登録することで、利用規約、プライバシーポリシーに同意するものとします。</h3>

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