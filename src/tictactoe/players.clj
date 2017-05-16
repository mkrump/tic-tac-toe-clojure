(ns tictactoe.players)

(defn switch-player [player]
  (+ 1 (mod player 2)))
