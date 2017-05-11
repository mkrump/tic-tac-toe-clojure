(ns tictactoe.ui
  (:require [tictactoe.board :as board])
  (:require [tictactoe.board-translators :as board-translators]))

(defn render-board [board]
  (->> board
       (board-translators/numeric-board-translation)
       (board/board->string)
       (print))
  (flush))

(defn clear-screen []
  (print "\033c")
  (flush))
