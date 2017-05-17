(ns tictactoe.human-console-player
  (:require [tictactoe.console-ui :as ui]))

(defn make-console-move [board]
  (read-line))

(defn human-console-player [marker]
  {:marker marker :move make-console-move})

(defn random-move [board]
  (rand-nth (keep-indexed #(if (zero? %2) %1) board)))

(defn computer-random-player [marker]
  {:marker marker :move random-move})


