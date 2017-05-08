(ns tictactoe.play
  (require tictactoe.render-board))

(defn -main []
  (print
    (tictactoe.render-board/render-board
           [0 1 2
            0 1 1
            1 1 1])))

