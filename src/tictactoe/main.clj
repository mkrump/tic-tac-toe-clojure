(ns tictactoe.main
  (:require [tictactoe.console-ui :as ui])
  (:require [tictactoe.game :as game])
  (:gen-class))


(defn -main []
  (ui/clear-screen)
  (loop [game (game/initial-game)
         _ (ui/render-board (game :board))]
    (let [updated-game
          (-> game
              (game/get-move)
              (game/update-game))]
      (ui/redraw-board (updated-game :board))
      (if (false? (game/game-over? updated-game))
        (do
          (recur updated-game _))
        (do
          (ui/render-game-over-msg (game/end-game-state updated-game)))))))

