(ns tictactoe.main
  (:require
            [tictactoe.tictactoe-console-game.console-ui :as ui]
            [tictactoe.tictactoe-console-game.console-startup-menu :as startup-menu]
            [tictactoe.tictactoe-console-game.game :as game])
  (:gen-class))

(defn -main []
  (ui/clear-screen)
  (let [player-choices (startup-menu/startup-menu)
        game (game/initialize-new-game player-choices)]
    (ui/render-board (game :board) (game :player-symbol-mapping))
    (loop [game game]
      (let [updated-game
            (-> game
                (game/get-move)
                (game/update-game))]
        (ui/redraw-board (updated-game :board) (updated-game :player-symbol-mapping))
        (if (false? (game/game-over? updated-game))
          (recur updated-game)
          (ui/render-game-over-msg (game/end-game-state updated-game)))))
    (shutdown-agents)))


