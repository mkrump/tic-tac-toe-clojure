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




