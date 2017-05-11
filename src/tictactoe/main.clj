(ns tictactoe.main
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.user-input-validation :as validation])
  (:gen-class))

(def board [0 1 2 0 1 1 0 1 1])

(defn -main []
  (loop [i 5]
    (ui/render-board board)
    (let [params {:board board
                   :ui-board (ui/board->ui board)
                   :ui->board ui/ui->board
                   :move nil}]
         [params (validation/validation-loop params)])
    (Thread/sleep 2000)
    (ui/clear-screen)
    (recur (dec i))))

