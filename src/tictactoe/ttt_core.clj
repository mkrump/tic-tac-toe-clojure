(ns tictactoe.ttt-core
  (:require [tictactoe.board :as board]
            [tictactoe.game :as game]
            [tictactoe.detect-board-state :as detect-board-state]
            [tictactoe.computer-minimax-ab-player :as computer-minimax-ab-player]))

;TODO Add tests
(def occupied-response-map
  {nil :out-of-range false :valid-move true :occupied})

(defn square-occupied [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        occupied (board/square-occupied? board move)]
    (occupied-response-map occupied)))

(defn update-game-state [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        updated-board (board/make-move board move current-player)
        updated-winner (detect-board-state/winner (:board-contents updated-board) (:gridsize updated-board))
        updated-is-tie (detect-board-state/tie? (:board-contents updated-board) (:gridsize updated-board))
        updated-current-player (game/switch-player current-player)]
    {:board          updated-board
     :winner         updated-winner
     :is-tie         updated-is-tie
     :current-player updated-current-player}))

(defn make-computer-move [game-state]
  (let [{:keys [board winner is-tie current-player]} game-state
        move (computer-minimax-ab-player/minimax-move board current-player)
        updated-game-state (update-game-state game-state move)]
    updated-game-state))

