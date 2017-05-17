(ns tictactoe.main
  (:require [tictactoe.ui :as ui])
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
      (if (false? (game/game-over? updated-game))
        (do
          (ui/ui-pause 500)
          (ui/clear-screen)
          (ui/render-board (updated-game :board))
          (recur updated-game _))
        (do
          (ui/ui-pause 500)
          (ui/render-game-over-msg (game/end-game-state updated-game)))))))

