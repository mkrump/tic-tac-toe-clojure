(defproject tictactoe "0.1.0"
  :description "TicTacToe"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot tictactoe.main
  :profiles {:uberjar {:aot :all}})
