(ns tictactoe.render-board)

(defn- cell-empty? [cell] (= 0 cell))

(defn- render-empty-cell [] " ")

(defn- lookup-player-symbol [key player-symbol-mapping]
  (if (not (cell-empty? key))
    (get player-symbol-mapping key key)
    (render-empty-cell)))

(defn render-cell
  [cell & {:keys [player-symbol-mapping]
           :or {player-symbol-mapping {1 "1" 2 "2"}}}]
  (str "  " (lookup-player-symbol cell player-symbol-mapping) "  "))


(defn render-row-contents [row]
  (str (clojure.string/join "|" (map render-cell row)) "\n"))

(defn render-row-separator [row]
  (str (clojure.string/join " " (repeat (count row) "- - -")) "\n"))

(defn get-board-size [board]
  (int (Math/sqrt (count board))))

(defn render-row [row]
  (str (render-row-contents row) (render-row-separator row)))

(defn lazy-seq-to-string [lz-seq] (apply str lz-seq))

(defn render-board [board]
  (lazy-seq-to-string
    (map render-row
         (partition (get-board-size board) board))))
