(ns tictactoe.board-translators)

(defn ui-mapping [idx] (str (+ idx 1)))
(defn inverse-ui-mapping [idx] (- (Integer/parseInt idx) 1))
(defn ui-mapping-letters [idx] (str (char (+ idx 97))))
(defn inverse-ui-mapping-letters [char] (- (int (.charAt char 0)) 97))

(defn apply-ui-mapping [board-contents idx player-mapping]
  (let [square-contents (get board-contents idx)]
    (if (= 0 square-contents)
      (ui-mapping idx)
      ((keyword (str square-contents)) player-mapping))))



