(ns tictactoe.main
  (:require
    [tictactoe.tictactoe-console-game.console-ui :as ui]
    [tictactoe.tictactoe-console-game.console-startup-menu :as startup-menu]
    [tictactoe.tictactoe-console-game.game :as game]
    [tictactoe.tictactoe-core.ttt-core :as ttt-core])
  (:gen-class))

;(def player-choices {1 {:player-type :human-player, :marker "X"}, 2 {:player-type :computer-player, :marker "O"}})
(defn -main []
  (ui/clear-screen)
  (let [player-choices (startup-menu/startup-menu)
        game (game/initialize-new-game player-choices)
        console-ui (game/initialize-ui player-choices (:game-state game))]
    (ui/render-board (get-in game [:game-state :board]) (console-ui :player-symbol-mapping))
    (loop [game game]
      (let [move (game/ui-get-move game console-ui)
            updated-game-state (ttt-core/update-game-state (:game-state game) move)
            updated-game (assoc game :game-state updated-game-state)]
        (ui/redraw-board (get-in updated-game [:game-state :board]) (console-ui :player-symbol-mapping))
        (if (false? (game/game-over? (:game-state updated-game)))
          (recur updated-game)
          (ui/render-game-over-msg (game/end-game-state updated-game)))))
    (shutdown-agents)))

