(ns tictactoe.tictactoe-console-game.human-console-player
  (:require [tictactoe.tictactoe-console-game.human-console-player :as ui]))

(defn make-console-move [board current-player]
  (read-line))

(defn human-console-player [marker]
  {:marker marker :move make-console-move})




