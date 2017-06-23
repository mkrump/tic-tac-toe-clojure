(defproject org.clojars.mkrump/tictactoe "0.1.1"
  :description "TicTacToe"
  :license {:name "MIT"}
  :url "https://github.com/mkrump/tic-tac-toe-clojure"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot tictactoe.main
  :profiles {:uberjar {:aot :all}}
  :repositories [["snapshots"
                  {:url "https://clojars.org/repo/"
                   :username [:gpg :env/cj_username]
                   :password [:gpg :env/cj_password]}]])
