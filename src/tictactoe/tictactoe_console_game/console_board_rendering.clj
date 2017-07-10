(ns tictactoe.tictactoe-console-game.console-board-rendering
  (:require [clojure.string :as string]))

(defn ui-mapping [idx] (str (inc idx)))

(defn inverse-ui-mapping [idx] (dec (Integer/parseInt idx)))

(defn get-marker [square-contents player-mapping]
  (player-mapping square-contents))

(defn apply-ui-mapping [board-contents idx player-mapping]
  (let [square-contents (get board-contents idx)]
    (cond (zero? square-contents) (ui-mapping idx)
          (= -1 square-contents) (get-marker square-contents player-mapping)
          (= 1 square-contents) (get-marker square-contents player-mapping))))

(defn- render-row-contents [row]
  (str "  " (string/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (string/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq->string [lz-seq]
  (string/join lz-seq))

(defn board->ui [board player-symbol-mapping]
  (let [{board-contents :board-contents} board
        board-indices (range (count board-contents))
        translated-board (map #(apply-ui-mapping board-contents % player-symbol-mapping) board-indices)]
    (assoc board :board-contents translated-board)))

(defn ui->board [move] (inverse-ui-mapping move))

(defn board->string [board]
  (let [{board :board-contents board-size :gridsize} board]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq->string))))