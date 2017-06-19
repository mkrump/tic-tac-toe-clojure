(ns tictactoe.tictactoe-console-game.board-translators)

(defn ui-mapping [idx] (str (+ idx 1)))
(defn inverse-ui-mapping [idx] (- (Integer/parseInt idx) 1))

(defn ui-mapping-letters [idx] (str (char (+ idx 97))))
(defn inverse-ui-mapping-letters [char] (- (int (.charAt char 0)) 97))

(defn get-marker [square-contents player-mapping]
  (player-mapping square-contents))

(defn red-marker [marker]
  (str "\033[1;31m" marker "\033[0m"))

(defn green-marker [marker]
  (str "\033[1;32m" marker "\033[0m"))

(defn apply-ui-mapping [board-contents idx player-mapping]
  (let [square-contents (get board-contents idx)]
    (cond (= 0 square-contents) (ui-mapping idx)
          (= -1 square-contents) (red-marker (get-marker square-contents player-mapping))
          (= 1 square-contents) (green-marker (get-marker square-contents player-mapping)))))




