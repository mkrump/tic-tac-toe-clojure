(ns tictactoe.computer-minimax-player-blah
  (:require [tictactoe.board :as board])
  (:require [tictactoe.detect-board-state :as detect-board-state]))

(defn- score-function [board gridsize player depth]
  (let [winner (detect-board-state/winner board gridsize)
        tie (detect-board-state/tie? board gridsize)]
    (cond
      (= (* winner player) 1) (/ 1 depth)
      (= (* winner player) -1) (/ -1 depth)
      (true? tie) 0
      :else nil)))

(defn- minimax-score [board player depth]
  (if (true? (detect-board-state/game-over? (:board-contents board) (:gridsize board)))
    (score-function (:board-contents board) (:gridsize board) player depth)
    (let [best-score (Double/NEGATIVE_INFINITY)
          candidate-move (first (board/open-squares board))
          updated-board (board/make-move board candidate-move player)
          score (* -1 (minimax-score updated-board (* -1 player) (inc depth)))]
      (if (> score best-score)
          score
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
          (if (>= score best-score)
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
                 [1 0 0
                  0 0 0
                  0 0 0]
                 :gridsize 3})

(minimax-move test-board 1)
(map #(minimax-move2 test-board -1 %) (board/open-squares test-board))

;;(map #([ %]) (board/open-squares test-board))

;(minimax-score test-board 1)

;(score-function (test-board :board-contents) (test-board :gridsize) 1)