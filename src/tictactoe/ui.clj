(ns tictactoe.ui
  (:require [tictactoe.board :as rb])
  (:require [tictactoe.board-translators :as bt]))

(defn render-board [board]
  (->> board
       (bt/numeric-board-translation)
       (rb/board->string)
       (print))
  (flush))

(defn clear-screen []
  (print "\033c")
  (flush))
