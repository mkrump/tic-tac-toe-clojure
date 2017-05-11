(ns tictactoe.main
  (:require [tictactoe.board :as board])
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.user-input-validation :as validation])
  (:gen-class))


(defn make-move [board move player]
  (assoc board move player))

(defn switch-player [player]
  (+ 1 (mod player 2)))

(defn -main []
  (let [board (vec (repeat 9 0))]
    (loop [i 5
           params {:board     board
                   :ui-board  (ui/board->ui board)
                   :ui->board ui/ui->board
                   :move      nil
                   :player 1}]
      (ui/render-board (params :board))
      (let [
            validated-params (validation/validation-loop params)

            board (validated-params :board)
            ui->board (validated-params :ui->board)
            move (validated-params :move)
            player (validated-params :player)

            updated-board (make-move board (ui->board move) player)
            updated-player (switch-player player)
            updated-params (assoc validated-params :player updated-player :board updated-board)]
        (Thread/sleep 1000)
        (ui/clear-screen)
        (recur (dec i) updated-params)))))


(-main)
;(def b (repeat 9 0))
;(def params
;       {:board     b
;          :ui-board  (ui/board->ui b)
;          :ui->board ui/ui->board
;          :move      nil})
;
;((params :ui->board) "1")
;(assoc params :board (make-move (new-params :board) (new-params :ui->board (new-params :move)) 1))
