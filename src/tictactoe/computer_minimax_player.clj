(ns tictactoe.computer-minimax-player
  (:require [tictactoe.board :as board])
  (:require [tictactoe.detect-board-state :as detect-board-state]))

(defn score [board player depth]
  (let [winner (detect-board-state/winner (:board-contents board) (:gridsize board))]
    (if (not= 0 winner)
      (/ (* player winner) depth)
      0)))

(defn next-board-seq [board player]
  (let [open-squares (board/open-squares board)]
    (map #(board/make-move board % player) open-squares)))

(defn minimax-move-score [board player depth]
  (if (detect-board-state/game-over? (:board-contents board) (:gridsize board))
    (score board player depth)
    (let [board-seq (next-board-seq board player)]
      (reduce max
        (map #(- (minimax-move-score % (- player) (inc depth))) board-seq)))))

(defn- minimax-score-moves [board current-player]
  (pmap #(vector % (- (minimax-move-score (board/make-move board %1 %2) (* -1 %2) 1)))
     (board/open-squares board) (iterate identity current-player)))

(defn minimax-move [board current-player]
  ((apply max-key #(get % 1) (minimax-score-moves board current-player)) 0))

(defn computer-minimax-player [marker]
  {:marker marker :move minimax-move})

