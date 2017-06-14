(ns tictactoe.minimax-time
  (:require [tictactoe.computer-minimax-player])
  ;(:require [tictactoe.computer-minimax-player-ab])
  (:require [tictactoe.board]))

(def b (tictactoe.board/generate-board 3))
;(def cp-ab (tictactoe.computer-minimax-player-ab/computer-minimax-player "X"))
(def cp (tictactoe.computer-minimax-player/computer-minimax-player "X"))
;(println "AB" (time ((:move cp-ab) b 1)))
(println "Normal" (time ((:move cp) b 1)))

;(load-file "src/tictactoe/minimax_time.clj")