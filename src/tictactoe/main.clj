(ns tictactoe.main
  (:require [tictactoe.console-ui :as ui])
  (:require [tictactoe.game :as game])
  (:gen-class))

(defn -main []
  (ui/clear-screen)
  (loop [game (game/initialize-new-game)
         _ (ui/render-board (game :board) (game :player-symbol-mapping))]
    (let [updated-game
          (-> game
              (game/get-move)
              (game/update-game))]
      (ui/redraw-board (updated-game :board) (updated-game :player-symbol-mapping))
      (if (false? (game/game-over? updated-game))
        (recur updated-game _)
        (ui/render-game-over-msg (game/end-game-state updated-game)))))
  (shutdown-agents))


