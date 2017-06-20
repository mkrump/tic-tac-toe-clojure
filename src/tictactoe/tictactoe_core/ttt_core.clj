(ns tictactoe.tictactoe-core.ttt-core
  (:require [tictactoe.tictactoe-core.board :as board]
            [tictactoe.tictactoe-core.detect-board-state :as detect-board-state]
            [tictactoe.tictactoe-core.computer-minimax-ab-player :as computer-minimax-ab-player]))

(defn switch-player [player]
  (* -1 player))

(def occupied-response-map
  {nil   {:status :out-of-range :message "Out of range"}
   false {:status :valid-move :message "Success"}
   true  {:status :occupied :message "Square occupied"}})

(defn move-status [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        occupied (board/square-occupied? board move)]
    (occupied-response-map occupied)))

(defn update-game-state [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        updated-board (board/make-move board move current-player)
        updated-winner (detect-board-state/winner (:board-contents updated-board) (:gridsize updated-board))
        updated-is-tie (detect-board-state/tie? (:board-contents updated-board) (:gridsize updated-board))
        updated-current-player (switch-player current-player)]
    {:board          updated-board
     :winner         updated-winner
     :is-tie         updated-is-tie
     :current-player updated-current-player}))

(defn get-computer-move [game-state]
  (let [{:keys [board winner is-tie current-player]} game-state
        move (computer-minimax-ab-player/minimax-move board current-player)]
    move))


