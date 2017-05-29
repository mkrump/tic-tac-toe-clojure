(ns tictactoe.computer-minimax-ab-player
  (:require [tictactoe.board :as board])
  (:require [tictactoe.detect-board-state :as detect-board-state]))
(declare ab-pruning minimax-ab-move-score)

(defn- score-function [board player depth]
  (let [winner (detect-board-state/winner (:board-contents board) (:gridsize board))]
    (if (not= 0 winner)
      (/ (* player winner) depth)
      0)))

(defn next-board-seq [board player]
  (let [open-squares (board/open-squares board)]
    (map #(board/make-move board % player) open-squares)))

(defn ab-pruning [[player depth alpha beta best-score] node]
    (let [node-score (- (minimax-ab-move-score node (- player) (inc depth) (- beta) (- alpha)))
          updated-alpha (max alpha node-score)
          updated-best-score (max best-score node-score)]
      (if (>= updated-alpha beta)
        (reduced [player depth alpha beta updated-best-score])
        [player depth updated-alpha beta updated-best-score])))

(defn minimax-ab-move-score [board player depth alpha beta]
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
  ((apply max-key #(get % 1) (minimax-score-moves board current-player)) 0))

(defn computer-minimax-ab-player [marker]
  {:marker marker :move minimax-move})

;Short circuit reduce w/ reduced
;;https://stackoverflow.com/questions/7491360/how-do-you-return-from-a-function-early-in-clojure
