(ns tictactoe.computer-minimax-player
  (:require [tictactoe.board :as board])
  (:require [tictactoe.detect-board-state :as detect-board-state]))

(defn- score-function [board gridsize player depth]
  (let [winner (detect-board-state/winner board gridsize)]
    (if (not= 0 winner)
      (/ (* player winner) depth)
      0)))

(defn- minimax-move-score [board current-player depth]
  (loop [candidate-moves (board/open-squares board)
         best-score (Double/NEGATIVE_INFINITY)
         depth 1]
      (if (true? (detect-board-state/game-over? (:board-contents board) (:gridsize board)))
        (score-function (:board-contents board) (:gridsize board) current-player depth)
        (if (seq candidate-moves)
          (do
            (let [move (first candidate-moves)
                  updated-board (board/make-move board move current-player)
                  score (* -1 (minimax-move-score updated-board (* -1 current-player) 1))]
              (if (> score best-score)
                (recur (rest candidate-moves) score (inc depth))
                (recur (rest candidate-moves) best-score (inc depth)))))
          best-score))))

(defn- minimax-score-moves [board current-player]
  (pmap #(vector % (* -1 (minimax-move-score (board/make-move board %1 %2) (* -1 %2) 1)))
       (board/open-squares board) (iterate identity current-player)))

(defn- minimax-move [board current-player]
  ((apply max-key #(get % 1) (minimax-score-moves board current-player)) 0))

(defn computer-minimax-player [marker]
  {:marker marker :move minimax-move})

