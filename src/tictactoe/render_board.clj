(ns tictactoe.render-board)

(defn- cell-empty? [cell] (= 0 cell))

(defn- render-empty-cell [] " ")

(defn- render-cell [key player-symbol-mapping]
  (str "  "
    (if (not (cell-empty? key))
      (get player-symbol-mapping key key)
      (render-empty-cell))
    "  "))

(defn- render-row-contents [row player-symbol-mapping]
  (str (clojure.string/join "|" (map #(render-cell %1 player-symbol-mapping) row)) "\n"))

(defn- render-row-separator [row]
  (str (clojure.string/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row player-symbol-mapping]
  (str (render-row-contents row player-symbol-mapping)
       (render-row-separator row)))

(defn- lazy-seq-to-string [lz-seq] (apply str lz-seq))

(defn get-board-size [board]
  (int (Math/sqrt (count board))))

(defn render-board
  [board & {:keys [player-symbol-mapping]
            :or   {player-symbol-mapping {1 "X" 2 "O"}}}]
  (lazy-seq-to-string
    (map #(render-row %1 player-symbol-mapping)
         (partition (get-board-size board) board))))
