(ns tictactoe.main
  (:require [tictactoe.render-board :as rb])
  (:require [tictactoe.board-translators :as bt])
  (:gen-class))

(defn -main []
  (let [board [0 1 2 0 1 1 0 1 1]]
    (->> board
         (bt/numeric-board-translation)
         (rb/render-board)
         (print)))
  (flush))


