(ns tictactoe.board
  (:require [clojure.set :as set]
            [clojure.string :as s]))

(defn- render-row-contents [row]
  (str "  " (s/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (s/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq->string [lz-seq]
  (apply str lz-seq))

(defn get-board-gridsize [board]
  (int (Math/sqrt (count board))))

(defn valid-move? [board, move]
  (= 0 (get board move)))

(defn board->string [board]
  (let [board-size (get-board-gridsize board)]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq->string))))

