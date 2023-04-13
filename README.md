# DEMOプロジェクトについて
フロントでAPIリクエスト
↓
APIがDBからデータを取得
↓
レスポンス情報をフロントに表示

を再現するために作成しました。
このプロジェクトを起動するためには「インストール手順.md」に記載の
各ツールをインストールしておく必要があります。
以下、各プロジェクトの説明です。

## backend-api
springbootのAPIプロジェクトです。
controllerはリクエストの受付、
seviceはロジックの記述、
repositoryはリポジトリ定義
という構成です。

## frontend
vue.jsのプロジェクトです。
axiosを使用してのAPI実行ができます。

## flyway
DBのマイグレーションプロジェクトです。
DOCではDBへのCREATE,INSERT,DELETEはflywayを
使用してバージョン管理できるようにしています。

# プロジェクトの起動方法
##　起動順
1.mysql
2.flyway
3.springboot
4.vue

## 起動コマンド
### mysql
1.docker-compose.ymlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
docker compose up doc-db
,,,

### flyway
1.flywayプロジェクトのpom.xmlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
mvn flyway:clean
mvn flyway:migrate
,,,

### springboot
1.backend-apiプロジェクトのpom.xmlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
mvn spring-boot:run
,,,

### vue
1.frontendプロジェクトのpackage.jsonがあるディレクトリに移動
2.以下のコマンドを実行
,,,
npm install(初回のみ)
npm run serve
,,,