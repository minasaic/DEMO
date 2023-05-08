<template>
  <div id="app">
    <div v-if="$store.state.pageBoolean">
      <div id="sub">
        <router-link to="/">
          <h1><img src="./assets/system/main.png" alt="LOGO" width="150" height="150">1nstagram</h1>
        </router-link>
        <nav>
          <span>メニュー</span>
          <br>
          <br>
          <router-link to="/"><span class="btn_hover"><img class="photo" src="./assets/home.png" alt="LOGO" width="20"
                height="20">
              ホーム</span></router-link>
          <br>
          <br>
          <router-link to="/search"><span class="btn_hover"><img class="photo" src="./assets/search.png" alt="LOGO"
                width="20" height="20">
              検索</span></router-link>
          <br>
          <br>
          <router-link to="/create"><span class="btn_hover"><img class="photo" src="./assets/plus.png" alt="LOGO"
                width="20" height="20">
              作成</span></router-link>
          <br>
          <br>
          <router-link to="/mypage"><span class="btn_hover"><img class="photo" src="./assets/profile.png" alt="LOGO"
                width="20" height="20">
              プロフィール
            </span></router-link>
          <br><br><br>
          <button @click="logOut">ログアウト</button>
        </nav>
      </div>
      <div id="change">
        <router-view />
      </div>
    </div>

    <div v-else>
      <div v-if="$store.state.signSigu">
        <LoginView/>
      </div>
      <div v-else>
        <CreateAccount/>
      </div>
    </div>

    <!-- <div v-else>
      ログインと新規登録ページ
        <div id="titles" style="text-align: center;">
        <h1>1nstagram</h1>
        <br><br><br><br>
        <img src="./assets/system/main.png" alt="LOGO" width="200" height="200">
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
        <button type="button" @click="create">新規登録</button>
      </div>
    </div> -->
  </div>
</template>

<script>
// import { Service } from "@/service/service"
import store from "./store"
import CreateAccount from "./views/CreateAccount.vue"
import LoginView from "./views/LoginView.vue"
export default {
  name: "App",
  created() {
      //セッションストレージから情報を読み込む
      const id = sessionStorage.getItem("id");
      const name = sessionStorage.getItem("name");
      const profilePicture = sessionStorage.getItem("profile_picture");
      const pageBoolean =sessionStorage.getItem("page_boolean");
      // 読み込んだ情報をストアに保存する
      if (id && name && pageBoolean) {
          store.commit("SETID", id);
          store.commit("SETNAME", name);
          store.commit("SETPROFILE", profilePicture);
          store.commit("SETPAGEBOOLEAN", pageBoolean);
      }
  },
  data() {
    return {
      login: false,
      // valueName: "",
      // valuePass: "",
      // valueComment: "",
    };
  },
  methods: {
    //ログイン
    // logins() {
    //     Service.post("login", {
    //         name: this.valueName,
    //         password: this.valuePass
    //     }).then(response => {
    //         //ログイン成功時の処理(axios通信成功時)
    //         console.log(response);
    //         if (response.data.id !== null) {
    //             this.login = true;
    //             store.commit("SETID", response.data.id); //responseされたIdをストア内stateのidにセット
    //             store.commit("SETNAME", response.data.name);
    //             store.commit("SETPROFILE", response.data.profile_picture);
    //             // セッションストレージに保存
    //             sessionStorage.setItem("id", response.data.id);
    //             sessionStorage.setItem("name", response.data.name);
    //             sessionStorage.setItem("profile_picture", response.data.profile_picture);
    //             alert("ID : " + store.state.id + "\nName : " + response.data.name + "\n" + "PASSWORD : " + this.valuePass + "\n" + store.state.profile);
    //         }
    //         else {
    //             alert("パスワードが間違ってます。");
    //         }
    //     }).catch(error => {
    //         console.log(error);
    //         alert("名前が間違っているか、アカウントが存在しません。");
    //     });
    // },
    //アカウント新規作成
    // create() {
    //     Service.post("create", {
    //         name: this.valueName,
    //         password: this.valuePass
    //     }).then(response => {
    //         console.log(response);
    //         if (response.data.id !== null) {
    //             this.login = true;
    //             store.commit("SETID", response.data.id); //responseされたIdをストア内stateのidにセット
    //             store.commit("SETNAME", response.data.name);
    //             store.commit("SETPROFILE", response.data.profile_picture);
    //             alert("アカウントが新規作成しました。" + "\nID : " + store.state.id + "\n" + "PASSWORD : " + this.valuePass + "\n" + store.state.profile);
    //         }
    //         else {
    //             alert("アカウント作成できません。");
    //         }
    //     }).catch(error => {
    //         console.log(error);
    //         alert("エラー起きました。");
    //     });
    // },
    // aa(){
    //   const currentUrl = this.$route.path;
    //   if(currentUrl === '/login'){
    //     this.sign = true;
    //   } else if(currentUrl === '/createaccount') {
    //     this.sign = false;
    //   }

    // },
    logOut() {
      // ログアウト時にセッションストレージから情報を削除する
      sessionStorage.removeItem("id");
      sessionStorage.removeItem("name");
      sessionStorage.removeItem("profile_picture");
      store.commit('SETPAGEBOOLEAN', false);
    }
  },
  components: { 
    LoginView,
    CreateAccount
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  margin-top: 60px;
}

#title a {
  font-family: Comic Sans MS;
  /* 筆記体のフォントを指定 */
  text-decoration: none;
  /* リンクの下線を無効にする */
  color: #090909;
  /* ボタンのデフォルトの文字色を指定 */
}

nav a {

  text-decoration: none;
  /* リンクの下線を無効にする */
  color: #090909;
  /* ボタンのデフォルトの文字色を指定 */
}

nav {
  font-family: Comic Sans MS;
  /* 筆記体のフォントを指定 */
}

.btn_hover {
  background-color: #ffffff;
  /*背景色*/
  -webkit-transition: all 0.3s ease;
  /*ふわっとさせる間隔 Google Chrome、Safari*/
  -moz-transition: all 0.3s ease;
  /*ふわっとさせる間隔 Firefox*/
  -ms-transition: all 0.3s ease;
  /*ふわっとさせる間隔 IE*/
  -o-transition: all 0.3s ease;
  /*ふわっとさせる間隔 Opera*/
  transition: all 0.3s ease;
  /*ふわっとさせる間隔*/
  padding: 10px 0;
  /*文字と背景の余白*/
  width: 500px;
  /*ボタン幅*/
  color: #0c0c0c;
  /*文字の色*/
  text-align: left;
  /*文字を揃える位置*/
  border-radius: 10px;
  /*背景の角丸半径*/
  cursor: pointer;
  /*ホバー時にカーソルの形状をポインターに*/
  /* カーソルを合わせた際にボタンの文字色を変える */
  position: relative;
}

/* マウスオーバーした際の背景 */
.btn_hover:hover {
  background-color: #ebeaea;
  /*背景色*/
}

#change {
  text-align: center;
}

#sub {
  box-sizing: border-box;
  top: 5px;
  height: 100%;
  width: 220px;
  position: fixed;
  overflow: auto;
  background: #ffffff;
  padding: 20px;
}

.photo {
  width: 13%;
  height: auto;
  transition: transform 0.1s ease-in-out;
}

.photo:hover {
  transform: scale(1.2);
  z-index: 1;
}
</style>
<style>
#main {
  box-sizing: border-box;
  margin-left: 180px;
  padding:20px 90px; 
}

.postDetelImg {
  width: 400px;
  height: 400px;
}
</style>