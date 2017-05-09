(ns tictactoe.ui
  (:require [tictactoe.board-translators :as bm]
            [tictactoe.board :as b]))

(defn board->ui [board]
  (map #(bm/apply-ui-mapping board %) (range (count board))))

(defn ui->board [move] (bm/inverse-ui-mapping move))

(defn render-board [board]
  (->> board
       (board->ui)
       (b/board->string)
       (print))
  (flush))

(defn clear-screen []
  (print "\033c")
  (flush))

