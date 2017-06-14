(ns tictactoe.board
  (:require [clojure.set :as set]
            [clojure.string :as string]))

(defn square-occupied? [board square]
  (let [{board-contents :board-contents} board
        board-square (get board-contents square)]
    (if (nil? board-square)
      nil
      (or (= 1 board-square)
          (= -1 board-square)))))

(defn open-squares [board]
  (keep-indexed #(if (zero? %2) %1) (:board-contents board)))

(defn make-move [board move player]
  (assoc-in board [:board-contents move] player))

(defn generate-board [gridsize]
  {:board-contents (vec (repeat (* gridsize gridsize) 0))
   :gridsize       gridsize})

