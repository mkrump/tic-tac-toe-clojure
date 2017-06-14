(ns tictactoe.human-console-player
  (:require [tictactoe.console-ui :as ui]))

(defn make-console-move [board current-player]
  (read-line))

(defn human-console-player [marker]
  {:marker marker :move make-console-move})




