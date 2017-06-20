(ns tictactoe.tictactoe-core.minimax-ab-strategy
  (:require [tictactoe.tictactoe-core.board :as board]
            [tictactoe.tictactoe-core.detect-board-state :as detect-board-state]))

(declare ab-pruning minimax-ab-move-score)

(defn- score-function [board player depth]
  (let [winner (detect-board-state/winner (:board-contents board) (:gridsize board))]
    (if (not= 0 winner)
      (/ (* player winner) depth)
      0)))

(defn- next-board-seq [board player]
  (let [open-squares (board/open-squares board)]
    (map #(board/make-move board % player) open-squares)))

(defn- ab-pruning [[player depth alpha beta best-score] board]
  (let [move-score (- (minimax-ab-move-score board (- player) (inc depth) (- beta) (- alpha)))
        updated-alpha (max alpha move-score)
        updated-best-score (max best-score move-score)]
    (if (>= updated-alpha beta)
      ;Short circuit reduce when alpha >= beta
      (reduced [player depth alpha beta updated-best-score])
      [player depth updated-alpha beta updated-best-score])))

(defn- minimax-ab-move-score [board player depth alpha beta]
  (let [best-score (Double/NEGATIVE_INFINITY)
        board-seq (next-board-seq board player)]
    (if (detect-board-state/game-over? (:board-contents board) (:gridsize board))
      (score-function board player depth)
      (get
        (reduce ab-pruning [player depth alpha beta best-score] board-seq)
        4))))

(defn- minimax-score-moves [board player]
  (map #(vector %
                (- (minimax-ab-move-score (board/make-move board % player) (- player) 1 (Double/NEGATIVE_INFINITY) (Double/POSITIVE_INFINITY))))
       (board/open-squares board)))

(defn minimax-move [board current-player]
  (let [moves (minimax-score-moves board current-player)
        max-score ((apply max-key #(get % 1) moves) 1)
        best-moves (filter #(= max-score (get % 1)) moves)]
    ((rand-nth best-moves) 0)))


