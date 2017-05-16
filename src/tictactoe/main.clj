(ns tictactoe.main
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.game :as game])
  (:gen-class))


(defn -main []
  (ui/clear-screen)
  (loop [game (game/initial-game)]
    (when (false? (game/game-over? game))
      (ui/render-board (game :board))
      (let [updated-game
            (-> game
                (game/get-move)
                (game/validate-move)
                (game/update-game))]
        (Thread/sleep 500)
        (ui/clear-screen)
        (recur updated-game))))
  (println "Game Over"))

(-main)