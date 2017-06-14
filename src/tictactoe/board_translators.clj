(ns tictactoe.board-translators)

(defn ui-mapping [idx] (str (+ idx 1)))

(defn inverse-ui-mapping [idx] (- (Integer/parseInt idx) 1))

;(defn ui-mapping [idx] (str (char (+ idx 97))))
;
;(defn inverse-ui-mapping [char] (- (int char) 97))

(defn get-marker [square-contents player-mapping]
  ((keyword (str square-contents)) player-mapping))

(defn red-marker [marker]
  (str "\033[1;31m" marker "\033[0m"))

(defn green-marker [marker]
  (str "\033[1;32m" marker "\033[0m"))

(defn apply-ui-mapping [board-contents idx player-mapping]
  (let [square-contents (get board-contents idx)]
    (cond (= 0 square-contents) (ui-mapping idx)
          (= -1 square-contents) (red-marker (get-marker square-contents player-mapping))
          (= 1 square-contents) (green-marker (get-marker square-contents player-mapping)))))




