(ns tictactoe.computer-minimax-player-blah
  (:require [tictactoe.board :as board])
  (:require [tictactoe.detect-board-state :as detect-board-state]))

(defn- score-function [board gridsize player depth]
  (let [winner (detect-board-state/winner board gridsize)]
    (if (not= 0 winner)
      (/ (* player winner) depth)
      0)))


(defn minimax-score [board current-player depth]
  (loop [candidate-moves (board/open-squares board)
         best-score (Double/NEGATIVE_INFINITY)
         depth 1]
      (if (true? (detect-board-state/game-over? (:board-contents board) (:gridsize board)))
        (score-function (:board-contents board) (:gridsize board) current-player depth)
        (if (seq candidate-moves)
          (do
            (let [move (first candidate-moves)
                  updated-board (board/make-move board move current-player)
                  score (* -1 (minimax-score updated-board (* -1 current-player) 1))]
              (if (> score best-score)
                (recur (rest candidate-moves) score (inc depth))
                (recur (rest candidate-moves) best-score (inc depth)))))
          best-score))))


(defn minimax-move [board current-player]
  (loop [candidate-moves (board/open-squares board)
         best-score (Double/NEGATIVE_INFINITY)
         best-move nil]
    (if (seq candidate-moves)
      (do
        (let [move (first candidate-moves)
              updated-board (board/make-move board move current-player)
              score (* -1 (minimax-score updated-board (* -1 current-player) 1))]
          (if (> score best-score)
            (recur (rest candidate-moves) score move)
            (recur (rest candidate-moves) best-score best-move))))
      best-move)))

(defn computer-minimax-player [marker]
  {:marker marker :move minimax-move})

(defn minimax-move2 [board player move]
  (let [updated-board (board/make-move board move player)
        score (* -1 (minimax-score updated-board (* -1 player) 1))]
       score))

;;(defn computer-minimax-player [marker]
;;  {:marker marker})
(def test-board {:board-contents
                 [0  -1 1
                  0 -1 1
                  1 1 -1]
                 :gridsize 3})

(def test-board2 {:board-contents
                  [1  0 0
                   0  0 0
                   0  0 0]
                  :gridsize 3})


;(map #(minimax-move2 test-board -1 %) (board/open-squares test-board))
(minimax-score (board/make-move test-board 1 1) (* -1 1) 1)
(minimax-score (board/make-move test-board 0 1) (* -1 1) 1)

(def test-player -1)
(map #(vector % (* -1 (minimax-score (board/make-move test-board %1 %2) (* -1 %2) 1)))
     (board/open-squares test-board) (iterate identity test-player))

;(minimax-score #(board/make-move test-board % -1) (board/open-squares test-board))

;(board/open-squares test-board)

;;Losing to 3 0 5 when human to move first
;;(map #([ %]) (board/open-squares test-board))

;(minimax-score test-board 1)

;(score-function (test-board :board-contents) (test-board :gridsize) 1)