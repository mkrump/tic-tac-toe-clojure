(ns tictactoe.main
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.user-input :as user-input])
  (:gen-class))

(defn -main []
  (let [board [0 1 2 0 1 1 0 1 1]]
    (loop [i 5]
      (ui/render-board board)
      (user-input/get-user-move)
      (Thread/sleep 2000)
      (ui/clear-screen)
      (recur (dec i)))))



