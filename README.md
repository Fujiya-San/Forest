# Forest
# Last Edit 12/05/2019

# Collaboration
# (°▽°) Ryo Tanonaka  <-%%%%->  _:(´ཀ`」 ∠): ƪ(˘⌣˘)ʃ 
``` bash
# Satoshi Tahara
# Fumiya Odahara
# Kazuki Fujiyama
# Shunsuke Hatanaka
```


# GitHubの使用方法

``` bash
# 基本編集操法

# ファイルダウンロード
他の人がファイルを変更した物を自身に反映する
git pull origin master

# ファイルアップロード
自身が変更したファイルを他の人に共有する。
git add . (git add ファイル名)
git commit -m "バージョン名"
git push origin ブランチ名(基本自身の苗字のローマ字)

# プルリクエストを作成
GitHubサイト上の「pull request」よりプルリクエストを作成する。

# masterに統合する
git push origin master

#〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜#

# 初期設定方法

# はじめにやること
新しいフォルダを作成する場合は以下の作業は無視

# フォルダを空っぽにする。
rm *

もし.DS_Storeや、すでに.gitなどが存在する場合は以下も実行
rm -rf　.DS_Store　.gitにてs削除を行う。
.gitだけであれば「 sift + command + . 」にてFinder上で隠しファイルを表示するショートカットでも行える。

# gitの初期化
git init
git remote add origin リポジトリURL.git
今回のプロジェクトの場合はこうである
git remote add origin https://github.com/Fujiya-San/Forest.git

git pull origin master

これにてリポジトリ内のファイルを共有できる。

#〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜#

# branchの作成
# 現在のブランチを確認する
git branch

使用例: workの場合
$ git branch
  master
* work


# 新たなブランチを作成する
git branch ブランチ名

使用例 work -> master
Switched to branch 'master'
Your branch is up to date with 'origin/master'.


# ブランチの移動
git checkout 移動するブランチ名

使用例 master -> Fujiya
Branch 'Fujiya' set up to track remote branch 'Fujiya' from 'origin'.
Switched to a new branch 'Fujiya'

# ブランチの作成と移動を同時に行う
git checkout -b ブランチ名

使用例 master -> make work -> work
Switched to a new branch 'work'
```

# 普段の編集手順
``` bash
基本的には自身のブランチから出ることはないです。
はじめに自身のブランチに入ったらそこから以下の手順にて行うことができます。

# 1. ダウンロード
git pull origin master

# 2. 編集
各パソコンにて編集

# 3. ローカルリポジトリに追加
git add . (git add ファイル名)

# 4. メッセージを追加(バージョン書き込み)
git commit -m "バージョン"

# 5. 自身のリポジトリにアップロード
git push origin ブランチ名

# 6. GitHubサイトにてプルリクエストを作成する
サイトにて「new pull request」から作成 or Pull Requestから作成

# 7. masterブランチに追加する
git push origin master

# 補足
「手順7」に関して
masterへのマージ(結合)はサイト上でも行うことができます。
自身のブランチから「New pull rquest」を選択
次に比較画面が表示されるので下に行き「Create pull request」を選択
少し待ち、「Merge pull request」を選択
最後に「Confirm merge」を選択するとmasterに統合される。
```

# aliasについての解説
``` bash
# aliasとは
資料にも書いてあるが基本的には関連付けというものであり、コマンドの組み合わせを他の名前で定義するなどのことが行える。

例: osのバージョン確認コマンドsw_vers -> osに関連付け
alias os='sw_vers'

コマンドライン上では以下のように表示される。
fujiyamakazuki@Comand Line Number : 150:~$ os
ProductName:	Mac OS X
ProductVersion:	10.14.4
BuildVersion:	18E226


aliasの設定例
8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8<

alias h='history'
alias h2='history 20'
alias ll='ls -l'
alias monitor='open -a "Activity Monitor.app"'
alias moodle='open -a "Google Chrome.app" https://cclms.kyoto-su.ac.jp/'
alias music='open -a iTunes.app'
alias now='pwd'
alias end='. ~/.bash_profile'
alias start='vi ~/.bash_profile'

8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8< 8<

上記のように打ち込むと「os」と打ち込むとsw_versが起動する。
※ ただしターミナルを変えたり、終了したりすると使えなくなる。

#〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜#

# aliasの半永久化
先ほどの状態ではaliasを打ち込んだターミナル以外のターミナルや終了してしまうので半永久化する手順を書いておく。

# 1. 「~/.bash_profile」をviエディタで開く
vi ~/.bash_profile 

# 2. 「i」を押して「INSERTモード(編集モード)」にする。
普通のエディタとは異なりクリックなどでは移動できないので矢印キーにて移動する。

# 3. 「esc」を押してINSERTモードを終了する

# 4. 保存して終了する。
「sift + q」を押し終了キーの入力を開始する。

以下のバーが現れる
Entering Ex mode.  Type "visual" to go to Normal mode.
:
「:」の後ろに以下のいずれかを打ち込む
保存して終了する -> wq
保存するが終了しない -> w
保存せず終了する　-> q

# 5. 変更した内容をロードする
「. ~/.bash_profile」と打ち込みロードする。
以降、aliasは半永久的に使用することができる。

#〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜#

# aliasの削除
aliasにて設定したコマンドは「unalias」にて解除することができる

例: 先ほどのosコマンドを解除する
unalias os
これ以降、関連付けたコマンドは解除される。
※ ただし、半永久化している場合は新たなターミナルを開く、「. ~/.bash_profile」にてロードすると使用できる。

解除後は以下のようになる
fujiyamakazuki@Comand Line Number : 157:~$ os
-bash: os: command not found

# 補足
今回はviエディタを使用して編集したが、任意のエディタを選択して編集することも可能である。
※ デフォルトであるならばテキストエディタになる。
```

# 最後に
``` bash
今回述べたことに関してわからないことがあればグループラインなどで行ってください
答えれることは答えます。

# P.S
あまり日本語が得意ではないので、文章がおかしいところがあるかもしれませんがご了承ください。
```

# 参考文献
``` bash
# viエディタ編集
viエディタの使い方
https://prev.net-newbie.com/linux/commands/vi.html

# その他の設定の仕方
青木教授のホームページより参照
http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/SoftwareVersions/index-j.html

# viエディタの練習におすすめ
java用ビルドツール「ApachAnt」の設定
方法については以下を参照
http://bluetree.kyoto-su.ac.jp/repositories/AP/Java/ApacheAnt/index.html
※ 学内からのアクセスのみ
```

