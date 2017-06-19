(ns tictactoe.tictactoe-console-game.user-input-validation
  (:require [tictactoe.tictactoe-core.board :as board]))

(defn open-square? [move board]
    (if (board/square-occupied? board move)
      [nil "Square occupied."]
      [move nil]))

(defn valid-console-ui-choice? [move ui-board]
    (if (or (integer? move)
            (contains? (set (:board-contents ui-board)) move))
      [move nil]
      [nil "Choice not available."]))

(defn valid-or-error [validator [value error]]
  (if (nil? error)
    (validator value)
    [nil error]))




