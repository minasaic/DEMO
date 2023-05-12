<template>
    <div class="photo-details-wrapper">
        <div class="photo-details-dialog">
            <div class="dialog-content">
                <div class="sua">
                    <img class="suba" :src="getVueCliUrlPost(homeTableObject.image)" alt="post">
                </div>
                <nobr class="qaz">
                    <div class="post-info">
                        <div class="post-top">
                            <a v-if="!showDeleteButton" @click="setStoreUserId" class="qwe">
                                <img v-if="qwerty.profile_picture" id="aaa"
                                    :src="getVueCliUrlProfile(qwerty.profile_picture)" alt="asdf"><b>{{ qwerty.name }}</b>
                            </a>
                            <a v-else @click="goToMyPage" class="qwe">
                                <img v-if="qwerty.profile_picture" id="aaa"
                                    :src="getVueCliUrlProfile(qwerty.profile_picture)" alt="asdf"><b>{{ qwerty.name }}</b>
                            </a>
                            <span class="mypage-caption">&nbsp;{{ homeTableObject.caption }}</span><br>
                            <span>
                                {{ homeTableObject.createdat | formatDate }}
                            </span>
                        </div>
                        <hr>
                        <!-- {{ commentTableObject }} -->
                        <div class="morimori">
                            <div class="comment-section">
                                <ul class="comment-list" v-for="(comment) in commentTableObject" :key="comment.id">
                                    <li class="comment-item">
                                        <a @click="goToUserPage(comment.userid)" class="qwe">
                                            <img id="aaa" :src="getVueCliUrlProfile(comment.profile)" alt="no image">
                                            <b>{{ comment.name }} </b>
                                        </a>
                                        &nbsp;<nobr class="balloon1-left"> {{ comment.comment }}</nobr><br>
                                        <span>
                                            {{ comment.createdat | formatDate }}
                                        </span> 
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                    <div class="btn-section">
                        <div class="comment-form">
                            <div class="heart-and-delete">
                                <nobr class="mypage-likes">
                                    <a v-show="!show" class="heart-button" @click="createLike">
                                        <div class="heart">
                                        </div>
                                    </a>
                                    <a v-show="show" class="heart-button" v-bind:class="{ active: show }"
                                        @click="deleteLike">
                                        <div class="heart">
                                        </div>
                                    </a>
                                    いいね {{ homeTableObject.likes }} 件！
                                </nobr>
                                <a v-if="showDeleteButton" class="delete-btn" @click="deletePost">
                                    <img src="../assets/system/trash.png" height="20px" width="20px" alt="削除">
                                </a>
                            </div>
                            <div class="comment-box">
                                <textarea class="comment-input" v-model="commentText" cols="45" rows="1"></textarea>
                                <nobr class="comment-btn-group">
                                    <a class="chikara" @click="updateComment">送信</a>
                                </nobr>
                            </div>
                        </div>
                    </div>
                </nobr>
                <a class="close-button" @click="$emit('close')">X</a>

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
        },
        show: {
            type: Boolean,
            require: true
        }
    },
    data() {
        return {
            showTextBox: false,
            commentText: '',
            getComments: '',
            JudgementCommentUser: false,
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
                userid: store.state.id, //ストアに保存したid
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
            Service.post('like', {
                postid: this.homeTableObject.id,
                userid: store.state.id
            }).then(response => { //postidを渡す
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
        },
        goToUserPage(userId) {
            alert(userId)
            if (userId == store.state.id) {
                alert("自分のidだ");
                this.goToMyPage();
            } else {
                store.commit('SETUSERID', userId);
                sessionStorage.setItem('user_id', userId);
                alert('setStorUserId  ' + store.state.userId);
                this.$router.push('/userpage')
            }
        },
        deleteLike() {
            Service.post('/deletelikes', {
                postid: this.homeTableObject.id,
                userid: store.state.id
            }).then(response => {
                console.log(response);
                this.$emit('refresh-likes');
            }).catch(error => {
                alert(error);
            })
        }
    },
    filters: {
        formatDate(dateString) {
            // 日付文字列をDateオブジェクトに変換する
            const date = new Date(dateString);

            // 現在の日付を取得する
            const now = new Date();

            // 日付の差をミリ秒単位で計算する
            const diff = now - date;
            
            // 日付の差を日数に変換する
            const dayDiff = Math.floor(diff / (1000 * 60 * 60 * 24));
            const hourDiff = Math.floor(diff / (1000 * 60 * 60));
            const minDiff = Math.floor(diff / (1000 * 60));
            // 日付の差に応じて適切な表記に変換する
            if (dayDiff === 0) {
                // 今日の場合
                if (hourDiff === 0) {
                    // 1時間以内の場合
                    if (minDiff <= 1) {
                        return '1分以内';
                    } else {
                        return `${minDiff}分前`;
                    }
                } else if (hourDiff < 24) {
                    // 1日以内の場合
                    return `${hourDiff}時間前`;
                } else {
                    // 昨日の場合
                    return '昨日';
                }
            } else {
                // それ以外の場合
                return `${dayDiff}日前`;
            }
        }
    }
}
</script>

<style>
#aaa {
    margin-right: 5px;
    /* 画像と名前の間に余白を設ける */
    margin-bottom: -4px;
    /* 画像を少し下げる */
    border-radius: 50%;
    /* 角丸半径を50%にする(=円形にする) */
    width: 20px;
    /* ※縦横を同値に */
    height: 20px;
    /* ※縦横を同値に */
}

.post-top {
    text-align: left;
}

/* .post-top button {
    cursor: pointer;
} */
hr {
    border-color: rgba(255, 255, 255, 0.5);
    /* 線の色を半透明の白色にする */
    border-width: 1px;
    /* 線の太さを1pxにする */
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
    width: 400px;
    height: 455px;
    overflow-y: scroll;
}

/* ハートと削除ボタンの位置 */
.heart-and-delete {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}

/* ボタン本体のスタイル */
.heart-button {
    /* ボタン要素の大きさや色 */
    position: relative;
    display: inline-block;
    background-color: #f2eaea;
    padding: 0.5em 1em;
    border-radius: 10px;
    border: 0px solid transparent;

    /* ボタンの文字の設定 */
    font-family: 'Quicksand', sans-serif;
    font-weight: bold;
    font-size: 10px;
    color: #ed7878;
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
    background-color: #ed7878;
    border-color: #ffffff;
    color: #e3d7d7;
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
        align-items: center;
        justify-content: flex-start;
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
    background: #48c4d4;
    /* 背景色     */
    color: #ffffff;
    /* 文字色     */
    line-height: 1em;
    /* 1行の高さ  */
    transition: .3s;
    /* なめらか変化 */
    border: 2px solid #ffffff;
    /* 枠の指定 */
}

.chikara:hover {
    box-shadow: none;
    /* カーソル時の影消去 */
    color: #33a1d4;
    /* 背景色     */
    background: #ffffff;
    /* 文字色     */
}

a:hover {
    cursor: pointer;
}

/* .qwe {
    text-decoration: none;
    border: 0;
    background-color: #ffffff;
    cursor: pointer;
} */

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
    display: flex;
    /*森上が追加したよーーーーーーーーーーーーーー */
    position: absolute;
    top: 50%;
    left: 50%;
    /*森上がついか*/
    width: 1000px;
    height: 600px;


    transform: translate(-50%, -50%);
    background-color: #fff;
    padding: 0px;
    /*森上が２０を３０にしたよーーー*/
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
    margin-right: 160px;
    /*森上が変更を加えた */
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
    text-align: left;
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

.close-button {
    position: absolute;
    top: 1px;
    right: 10px;
    border: none;
    background-color: transparent;
    font-size: 24px;
    cursor: pointer;
}

/* ハートアイコン */
.heart {
    position: relative;
    display: inline-block;
    width: 18px;
    height: 18px;
    margin-right: 0.3em;
    margin-left: -0.3em;
    margin-top: 3px;
}

.heart:before,
.heart:after {
    position: absolute;
    content: "";
    left: 12.5px;
    top: 0;
    width: 12.5px;
    height: 20px;
    background: rgb(255, 47, 47);
    border-radius: 12.5px 12.5px 0 0;
    transform: rotate(-45deg);
    transform-origin: 0 100%;
}

.heart:after {
    left: 0;
    transform: rotate(45deg);
    transform-origin: 100% 100%;
}

.heart-button:hover .heart:before,
.heart-button:hover .heart:after {
    background-color: rgb(239, 229, 230);
}

/* いいねした時のスタイル */

.heart-button.active {
    background-color: #ed7878;
    border-color: #ffffff;
    color: #e3d7d7;
}

.heart-button.active .heart:before,
.heart-button.active .heart:after {
    background-color: #fff;
}</style>