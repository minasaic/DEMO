<template>
  <div id="app">
    <div v-if="$store.state.pageBoolean">
      <div id="sub">
        <router-link to="/">
          <img src="./assets/system/mainlogo.png" alt="LOGO" width="200" height="50">
        </router-link>
        <nav>
          <br>
          <br>
          <router-link to="/"><span class="btn_hover"><img class="photo" src="./assets/system/home.png" alt="LOGO">
              ホーム</span></router-link>
          <br>
          <br>
          <router-link to="/search"><span class="btn_hover"><img class="photo" src="./assets/system/search.png" alt="LOGO">
              検索</span></router-link>
          <br>
          <br>
          <router-link to="/create"><span class="btn_hover"><img class="photo" src="./assets/system/plus.png" alt="LOGO">
              作成</span></router-link>
          <br>
          <br>
          <router-link to="/likespage"><span class="btn_hover"><img class="photo" src="./assets/system/profile.png" alt="LOGO">
              いいね一覧
            </span>
          </router-link>
          <br>
          <br>
          <router-link to="/mypage">
            <span class="btn_hover">
              <img v-if="getVueCliUrl() !== undefined" class="photo" :src="getVueCliUrl()"
                alt="LOGO">
                <img v-else class="photo" src="./assets/system/profile.png" alt="LOGO">
              プロフィール
            </span>
          </router-link>
          <br><br><br>
          <div>
            <a @click="toggleMenu" class="btn_hover"><img class="photo" src="./assets/system/menu.png" alt="LOGO"> その他</a>
            <ul v-if="isOpen" class="menu">
              <!-- メニューアイテムのリストを表示 -->
              <li><a class="btn_hover" @click="showModal = true">
                  設定
                </a>
                <OptionModalView v-if="showModal" :title="modalTitle" @close="showModal = false"
                  @save="showModal = false">
                </OptionModalView>
              </li>
              <li><a class="btn_hover" @click="logOut">ログアウト</a></li>
              <!-- 必要な項目を追加 -->
            </ul>
          </div>
        </nav>
      </div>
      <div id="change">
        <router-view />
      </div>
    </div>

    <div v-else>
      <div v-if="$store.state.signSigu">
        <LoginView />
      </div>
      <div v-else>
        <CreateAccount />
      </div>


    </div>
  </div>
</template>

<script>
// import { Service } from "@/service/service"
import store from "./store"
import CreateAccount from "./views/CreateAccount.vue"
import LoginView from "./views/LoginView.vue"
import OptionModalView from "./components/OptionModalView.vue"
export default {
  name: "App",
  created() {
    //セッションストレージから情報を読み込む
    const id = sessionStorage.getItem("id");
    const name = sessionStorage.getItem("name");
    const profilePicture = sessionStorage.getItem("profile_picture");
    const pageBoolean = sessionStorage.getItem("page_boolean");
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
      isOpen: false, // メニューバーが開いているかどうかの状態を管理
      showModal: false,
      modalTitle: 'アカウント情報変更'
    };
  },
  methods: {
    toggleMenu() {
      this.isOpen = !this.isOpen; // メニューバーの状態を切り替え
    },
    logOut() {
      // ログアウト時にセッションストレージから情報を削除する
      sessionStorage.removeItem("id");
      sessionStorage.removeItem("name");
      sessionStorage.removeItem("profile_picture");
      store.commit('SETPAGEBOOLEAN', false);
    },
    getVueCliUrl() {
      if (store.state.profile !== null) {
        return require('./assets/profile/' + store.state.profile);
      } 
    },
  },
  components: {
    LoginView,
    CreateAccount,
    OptionModalView
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
  display: inline-block;
  justify-content: center;
  align-items: center;

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
  /* width: 130px; */
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
  transform: scale(1.2);
  z-index: 1;
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
  width: 25px;
  height: 25%;
  margin-top: -10px;
  /* transition: transform 0.1s ease-in-out; */
}
</style>
<style>
#main {
  box-sizing: border-box;
  margin-left: 180px;
  padding: 20px 90px;
}

.postDetelImg {
  width: 400px;
  height: 400px;
}

.menu {
  /* メニューバーのスタイルを指定 */
  /* 例えば、背景色や文字色、ポジションなどを設定 */
}
</style>