(ns tictactoe.main
  (:require [tictactoe.tictactoe-console-game.console-ui :as console-ui]
            [tictactoe.tictactoe-console-game.console-startup-menu :as startup-menu]
            [tictactoe.tictactoe-console-game.game :as game]
            [tictactoe.tictactoe-core.ttt-core :as ttt-core])
  (:gen-class))

(defn -main []
  (console-ui/clear-screen)
  (let [players (startup-menu/startup-menu)
        initial-game (game/initialize-new-game players)
        initial-game-state (:game-state initial-game)
        ui (game/initialize-ui players initial-game-state)]
    (console-ui/render-board (:board initial-game-state) (:player-symbol-mapping ui))
    (loop [game initial-game]
      (game/player-move-prompt game)
      (let [move (game/make-move game ui)
            updated-game-state (ttt-core/update-game-state (:game-state game) move)
            updated-game (assoc game :game-state updated-game-state)]
        (console-ui/redraw-board (:board updated-game-state) (:player-symbol-mapping ui))
        (if (true? (:game-over updated-game-state))
          (console-ui/render-game-over-msg (game/end-game-state updated-game))
          (recur updated-game))))
    (shutdown-agents)))
