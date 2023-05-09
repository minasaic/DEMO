<template>
    <div class="photo-details-wrapper">
        <div class="photo-details-dialog">
            <div class="dialog-content">
                <div class="sua">
                    <img class="suba" :src="getVueCliUrlPost(homeTableObject.image)" alt="post">
                </div>
                <nobr class="qaz">
                    <div class="post-info">
                        <button v-if="!showDeleteButton" @click="setStoreUserId">
                            <img v-if="qwerty.profile_picture" id="aaa" :src="getVueCliUrlProfile(qwerty.profile_picture)"
                                alt="asdf">{{ qwerty.name }}
                        </button>
                        <button v-else @click="goToMyPage">
                            <img v-if="qwerty.profile_picture" id="aaa" :src="getVueCliUrlProfile(qwerty.profile_picture)"
                                alt="asdf">{{ qwerty.name }}
                        </button>
                        <span class="mypage-caption">{{ homeTableObject.caption }}</span>
                        <!-- {{ commentTableObject }} -->
                        <div class="morimori">
                            <div class="comment-section">
                                <ul class="comment-list" v-for="(comment) in commentTableObject" :key="comment.id">
                                    <li class="comment-item">
                                        <img id="aaa" :src="getVueCliUrlProfile(comment.profile)" alt="no image">
                                        {{ comment.name }}<nobr class="balloon1-left">{{ comment.comment }}</nobr>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                    <div class="btn-section">
                        <div class="comment-form">
                            <!-- <nobr class="mypage-likes">いいね!{{ homeTableObject.likes }}件</nobr> -->
                            <!-- <button class="like-btn" @click="createLike">いいね</button> -->
                            <div class="comment-box">
                                <textarea class="comment-input" v-model="commentText" cols="39" rows="1"></textarea>
                                <nobr class="comment-btn-group">
                                    <button class="chikara" @click="updateComment">送信</button>
                                </nobr>
                            </div>
                            <div class="mypage-likes"><button class="heart-button" @click="createLike">いいね</button>{{
                                homeTableObject.likes }}件
                            </div>
                            <button v-if="showDeleteButton" class="delete-btn" @click="deletePost">削除する</button>

                        </div>
                    </div>
                </nobr>
                <button class="close-button" @click="$emit('close')">X</button>

            </div>
        </div>
    </div>
</template>
<script>
import { Service } from '@/service/service';
import store from '@/store';
export default {
    name: 'HomeSearchComponent',
    props: {
        homeTableObject: {
            type: Object,
            require: true
        },
        commentTableObject: {
            type: Array,
            require: true
        },
        qwerty: {
            type: Object,
            require: true
        },
        showDeleteButton: {
            type: Boolean,
            require: true
        }
    },
    data() {
        return {
            showTextBox: false,
            commentText: '',
            getComments: '',
            likeCount: 0,
        }
    },
    computed: {
        getVueCliUrlPost() {
            return (imgUrl) => {
                return require('../assets/post/' + imgUrl);
            }
        },
        getVueCliUrlProfile() {
            return (imgUrl) => {
                return require('../assets/profile/' + imgUrl);
            }
        },
    },
    methods: {
        setStoreUserId() {
            store.commit('SETUSERID', this.homeTableObject.userid);
            sessionStorage.setItem('user_id', this.homeTableObject.userid);
            alert('setStorUserId  ' + store.state.userId);
            this.$router.push('/userpage')
        },
        showTextarea() {
            this.showText = true
            Service.post('getcom', this.postId //commentテーブルのpost_id
            ).then(response => {
                console.log(response)
                this.getComments = response.data;
            }).catch(error => {
                alert(error)
            })
        },
        updateComment() {
            Service.post('comment', {
                user_id: store.state.id, //ストアに保存したid
                postid: this.homeTableObject.id,
                comment: this.commentText
            }).then(response => {
                console.log(response);
                this.commentText = '';
                //コメントがアップデート成功時に、コメント一覧を更新する
                this.$emit('refresh-comment');
            }).catch(error => {
                alert(error);
            })
        },
        createLike() {
            Service.post('like', this.homeTableObject.id).then(response => { //postidを渡す
                console.log(response);
                this.$emit('refresh-likes'); // 最新のデータがreturnする
            }).catch(error => {
                alert(error)
            })
        },
        deletePost() {
            Service.post('deletepost', this.homeTableObject.id).then(response => {
                console.log(response);
                alert('削除しました。')
            }).catch(error => {
                alert(error)
            })
        },
        goToMyPage() {
            this.$router.push('/mypage');
        }
    }
}
</script>

<style>
#aaa {
    border-radius: 50%;
    /* 角丸半径を50%にする(=円形にする) */
    width: 20px;
    /* ※縦横を同値に */
    height: 20px;
    /* ※縦横を同値に */
}

.balloon1-left {
    position: relative;
    display: inline-block;
    margin: 1.5em 0 1.5em 15px;
    padding: 7px 10px;
    min-width: 120px;
    max-width: 100%;
    color: #0c0b0b;
    font-size: 16px;
    background: #bbf3a7;
}

.balloon1-left:before {
    content: "";
    position: absolute;
    top: 50%;
    left: -30px;
    margin-top: -15px;
    border: 15px solid transparent;
    border-right: 15px solid #bbf3a7;
}

.balloon1-left p {
    margin: 0;
    padding: 0;
}

.suba {
    margin-right: auto;
    width: 100%;
    height: 100%;
    max-width: 600px;
    display: block;
    border-radius: 5px;
}

.morimori {
    width: 350px;
    height: 500px;
    overflow-y: scroll;
}

/* ボタン本体のスタイル */
.heart-button {
    /* ボタン要素の大きさや色 */
    position: relative;
    display: inline-block;
    background-color: #ed7878;
    padding: 0.5em 1em;
    border-radius: 10px;
    border: 0px solid transparent;

    /* ボタンの文字の設定 */
    font-family: 'Quicksand', sans-serif;
    font-weight: bold;
    font-size: 10px;
    color: #f4efef;
    letter-spacing: 0.3em;

    /* その他必要なスタイル */
    transition: all 0.3s ease;
    cursor: pointer;
    text-decoration: none;
}

/* ハートの共通スタイル */
.heart-button::before,
.heart-button::after {
    content: "";
    background-size: contain;
    background-repeat: no-repeat;
    width: 20px;
    height: 20px;
    position: absolute;
    opacity: 0;
}

/* 左上のハートのスタイル */
.heart-button::before {
    background-image: url('heart.svg');
    top: 0;
    left: -12px;
}

/* 右下のハートのスタイル */
.heart-button::after {
    background-image: url('heart-reverse.svg');
    bottom: -6px;
    right: -14px;
}

/* ========= ホバー時のスタイル ======== */

/* ボタンホバー時のスタイル */
.heart-button:hover {
    background-color: #f6f0f0;
    border-color: #ef4b53;
    color: #ef4b53;
}

/* ボタンホバー時の左上のハートのスタイル */
.heart-button:hover::before {
    animation: heart 1.5s infinite ease-out;
}

/* ボタンホバー時の右下のハートのスタイル */
.heart-button:hover::after {
    animation: heart 1.5s 0.2s infinite ease-out;
}

/* ========= アニメーションの設定 ======== */
@keyframes heart {
    0% {
        transform: translateY(0) scale(1);
        opacity: 0;
    }

    25% {
        opacity: 1;
    }

    75% {
        opacity: 1;
    }

    100% {
        transform: translateY(-20px) scale(1.4);
        opacity: 0;
    }
}

/* ========= SPのスタイル ======== */
@media screen and (max-width: 768px) {

    /* ボタン本体のスタイル */
    .heart-button {
        background-color: #fff;
        border-color: #ef4b53;
        color: #ef4b53;
    }

    /* 左上のハートのスタイル */
    .heart-button::before {
        animation: heart 1.5s infinite ease-out;
    }

    /* 右下のハートのスタイル */
    .heart-button::after {
        animation: heart 1.5s 0.2s infinite ease-out;
    }
}

.chikara {
    display: inline-block;
    border-radius: 5%;
    /* 角丸       */
    font-size: 12pt;
    /* 文字サイズ */
    text-align: center;
    /* 文字位置   */
    cursor: pointer;
    /* カーソル   */
    padding: 6px 8px;
    /* 余白       */
    background: #48d448;
    /* 背景色     */
    color: #ffffff;
    /* 文字色     */
    line-height: 1em;
    /* 1行の高さ  */
    transition: .3s;
    /* なめらか変化 */
    border: 2px solid #66ff66;
    /* 枠の指定 */
}

.chikara:hover {
    box-shadow: none;
    /* カーソル時の影消去 */
    color: #66ff66;
    /* 背景色     */
    background: #ffffff;
    /* 文字色     */
}

.qwe {
    text-decoration: none;
}

/* ポップアップ */
.photo-details-dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

  background-color: rgba(156, 156, 156, 0.5);
  z-index: 100;
}

.dialog-content {
   display: flex; /*森上が追加したよーーーーーーーーーーーーーー */
  position: absolute;
  top: 50%;
  left: 50%;
  /*森上がついか*/
  width: 1000px;
  height: 600px;
  

  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 30px; /*森上が２０を３０にしたよーーー*/ 
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.mypage-img {
  width: 100%;
  max-width: 600px;
  height: auto;
  display: block;
  margin: 0 auto;
  border-radius: 5px;
}

.mypage-caption {
  font-size: 18px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
}

.mypage-likes {
  /* margin-bottom: 10px; */
   margin-right: 160px; /*森上が変更を加えた */
}

.comment-list {
  list-style: none;
  padding: 0;
  margin-right: 0%;
  margin-top: 0px;
  height: 50px;
}

.comment-item {
  font-size: 14px;
  line-height: 1.5;
  margin-bottom: 5px;
}

.comment-form {
  margin-top: 10px;
}

.comment-box {
  margin-top: 0px;
  display: flex;
  /* flex-direction: column; */
}

.comment-input {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 5px;
  font-size: 14px;
  resize: none;
  margin-bottom: 10px;
}

.comment-btn-group {
  /* display: flex;森上がやりました*/
  justify-content: space-between;
}

.confirm-btn,
.cancel-btn {
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 14px;
  cursor: pointer;
}

.confirm-btn {
  background-color: #3f51b5;
  color: #fff;
}

.cancel-btn {
  background-color: #fff;
  color: #3f51b5;
  border: 1px solid #3f51b5;
}

.btn-section {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}

.like-btn,
.delete-btn {
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 14px;
  cursor: pointer;
}

.like-btn {
  background-color: #ecc9e0;
  color: #fff;
}

.delete-btn {
  background-color: #fff;
  color: #3f51b5;
  border: 1px solid #3f51b5;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  border: none;
  background-color: transparent;
  font-size: 24px; 
  cursor: pointer;
}
</style>