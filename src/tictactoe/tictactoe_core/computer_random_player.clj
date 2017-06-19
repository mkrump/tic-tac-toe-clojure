(ns tictactoe.computer-random-player
  (:require [tictactoe.tictactoe-core.board :as board]))

(defn random-move [board current-player]
  (rand-nth (board/open-squares board)))

(defn computer-random-player [marker]
  {:marker marker :move random-move})
