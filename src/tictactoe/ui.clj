(ns tictactoe.ui
  (:require [tictactoe.board-translators :as board-translators])
  (:require [clojure.string :as string]))

(defn- render-row-contents [row]
  (str "  " (string/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (string/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq->string [lz-seq]
  (apply str lz-seq))

(defn board->ui [board]
  (let [{board-contents :board-contents} board]
    (assoc board :board-contents (map #(board-translators/apply-ui-mapping board-contents %) (range (count board-contents))))))

(defn ui->board [move] (board-translators/inverse-ui-mapping move))

(defn board->string [board]
  (let [{board :board-contents board-size :gridsize} board]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq->string))))

(defn render-board [board]
  (->> board
       (board->ui)
       (board->string)
       (print))
  (flush))

(defn clear-screen []
  (print "\033c")
  (flush))

