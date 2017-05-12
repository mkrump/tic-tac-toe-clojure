(ns tictactoe.main
  (:require [tictactoe.board :as board])
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.players :as players])
  (:gen-class))

(defn get-move [game]
  (let [updated-game
        (-> game
            (assoc :move (user-input/get-user-move))
            (#(assoc % :move (:move (validation/validator %)))))]
    (if (nil? (:move updated-game))
      (recur game)
      updated-game)))

(defn run-game [game]
  (let [{board     :board
         ui->board :ui->board
         move      :move
         player    :player} game]
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
  (loop [game (initial-game)]
    (ui/render-board (game :board))
    (let [updated-game
          (-> game
              (get-move)
              (run-game))]
      (Thread/sleep 500)
      (ui/clear-screen)
      (recur updated-game))))

