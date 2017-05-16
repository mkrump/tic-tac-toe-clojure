(ns tictactoe.user-input-validation
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as board]))

(defn open-square? [move board]
    (if (board/square-occupied? board move)
      [nil "Square occupied."]
      [move nil]))

(defn valid-ui-choice? [move ui-board]
    (if (contains? (set (:board-contents ui-board)) move)
      [move nil]
      [nil "Choice not available."]))

(defn valid-or-error [validator [value error]]
  (if (nil? error)
    (validator value)
    [nil error]))

;(defn valid-move? [game]
;  (->> (valid-ui-choice? game)
;       (valid-or-error open-square?)))
;
;(defn validator [game]
;  (let  [[result err] (valid-move? game)]
;      (if (nil? result)
;        (do
;          (println err)
;          result)
;        result)))
;



