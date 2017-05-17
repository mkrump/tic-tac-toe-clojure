(ns tictactoe.computer-random-player)

(defn random-move [board]
  (rand-nth (keep-indexed #(if (zero? %2) %1) board)))

(defn computer-random-player [marker]
  {:marker marker :move random-move})
