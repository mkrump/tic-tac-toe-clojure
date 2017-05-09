(ns tictactoe.board-translators)

(defn numeric-board-translation
  [board & {:keys [player-symbol-mapping]
            :or   {player-symbol-mapping {1 "X" 2 "O"}}}]
  (vec (map-indexed (fn [idx itm] (str (get player-symbol-mapping itm idx))) board)))

(defn inverse-numeric-board-translation
  [board & {:keys [player-symbol-mapping]
            :or   {player-symbol-mapping {1 "X" 2 "O"}}}]
  (let [inverted-player-symbol-mapping (clojure.set/map-invert player-symbol-mapping)]
    (vec (map-indexed (fn [idx itm] (get inverted-player-symbol-mapping itm 0)) board))))

