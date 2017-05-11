(ns tictactoe.main
  (:require [tictactoe.board :as board])
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.players :as players])
  (:gen-class))

(defn run-game [game]
  (-> game
      (validation/validation-loop)
      (assoc :move (board/make-move :board (:ui->board :move) :player))
      (assoc :board (board/make-move :board (:ui->board :move) :player))
      (assoc :play (players/switch-player :player))))

(defn -main []
  (let [board (vec (repeat 9 0))]
    (loop [i 10
           game {:board     board
                 :ui-board  (ui/board->ui board)
                 :ui->board ui/ui->board
                 :move      nil
                 :player    1}]
      (ui/render-board (game :board))
      (let [
            validated-params (validation/validation-loop game)
            board (validated-params :board)
            ui->board (validated-params :ui->board)
            move (validated-params :move)
            player (validated-params :player)
            [updated-board _] (board/make-move board (ui->board move) player)
            updated-player (players/switch-player player)
            updated-params (assoc validated-params :player updated-player :board updated-board)]

        (Thread/sleep 500)
        (ui/clear-screen)
        (recur (dec i) updated-params)))))


