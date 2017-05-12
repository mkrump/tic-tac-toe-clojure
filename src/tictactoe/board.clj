(ns tictactoe.board
  (:require [clojure.set :as set]
            [clojure.string :as string]))


(defn- render-row-contents [row]
  (str "  " (string/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (string/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq->string [lz-seq]
  (apply str lz-seq))

(defn- get-board-gridsize [board]
  (int (Math/sqrt (count board))))

(defn square-occupied? [board square]
  (let [board-square (get board square)]
    (or (= 1 board-square)
        (= 2 board-square))))

(defn board->string [board]
  (let [board-size (get-board-gridsize board)]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq->string))))

(defn make-move [board move player]
  (assoc board move player))

(defn generate-board [gridsize]
  (vec (repeat (* gridsize gridsize) 0)))

