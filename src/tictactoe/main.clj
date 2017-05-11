(ns tictactoe.main
  (:require [tictactoe.board :as board])
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.players :as players])
  (:gen-class))

(defn run-game [game]
  (let [{move :move
         player :player
         board :board
         ui->board :ui->board} (validation/validation-loop game)]
    (-> game
        (assoc :board (board/make-move board (ui->board move) player))
        (assoc :player (players/switch-player player)))))

(defn- initial-game []
  (let [board (vec (repeat 9 0))]
     {:board     board
      :ui-board  (ui/board->ui board)
      :ui->board ui/ui->board
      :move      nil
      :player    1}))

(defn -main []
  (loop [i 10 game (initial-game)]
    (ui/render-board (game :board))
    (let [updated-game (run-game game)]
      (Thread/sleep 500)
      (ui/clear-screen)
      (recur (dec i) updated-game))))
