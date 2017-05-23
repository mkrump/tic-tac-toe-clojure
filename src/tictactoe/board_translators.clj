(ns tictactoe.board-translators)

(defn ui-mapping [idx] (str (+ idx 1)))

(defn inverse-ui-mapping [idx] (- (Integer/parseInt idx) 1))

(defn player-mapping [itm]
  (get {1 "X" -1 "O"} itm))

(defn apply-ui-mapping [board-contents idx]
  (let [square-contents (get board-contents idx)]
    (if (= 0 square-contents)
      (ui-mapping idx)
      (player-mapping square-contents))))


