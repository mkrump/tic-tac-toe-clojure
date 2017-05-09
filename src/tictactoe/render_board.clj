(ns tictactoe.render-board
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [tictactoe.board-translators :as bt]))

(defn- render-row-contents [row]
  (str "  " (s/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (s/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq-to-string [lz-seq] (apply str lz-seq))

(defn get-board-size [board]
  (int (Math/sqrt (count board))))

(defn render-board [board]
  (let [board-size (get-board-size board)]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq-to-string))))


