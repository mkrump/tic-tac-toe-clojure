(ns tictactoe.ui
  (:require [tictactoe.board-translators :as board-translators]
            [tictactoe.board :as board]))

(defn board->ui [board]
  (map #(board-translators/apply-ui-mapping board %) (range (count board))))

(defn ui->board [move] (board-translators/inverse-ui-mapping move))

;TODO Move board->string to ui shouldn't be part of board.
(defn render-board [board]
  (->> board
       (board->ui)
       (board/board->string)
       (print))
  (flush))

(defn clear-screen []
  (print "\033c")
  (flush))

