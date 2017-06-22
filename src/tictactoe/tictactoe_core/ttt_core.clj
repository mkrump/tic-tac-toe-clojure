(ns tictactoe.tictactoe-core.ttt-core
  (:require [tictactoe.tictactoe-core.board :as board]
            [tictactoe.tictactoe-core.detect-board-state :as detect-board-state]
            [tictactoe.tictactoe-core.minimax-ab-strategy :as computer-minimax-ab-strategy]))

(def ^:private occupied-response-map
  {nil   {:status :out-of-range :message "Out of range"}
   false {:status :valid-move :message "Success"}
   true  {:status :occupied :message "Square occupied"}})

(defn determine-current-player [board]
  (let [open-squares-count (count (board/open-squares board))]
    (cond
      (even? (:gridsize board)) (if (even? open-squares-count) -1 1)
      (odd? (:gridsize board)) (if (odd? open-squares-count) -1 1))))

(defn initial-game-state [gridsize]
  (let [board (board/generate-board gridsize)]
    {:board          board
     :current-player -1
     :is-tie         false
     :winner         0
     :game-over      false}))

(defn move-status [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        occupied (board/square-occupied? board move)]
    (occupied-response-map occupied)))

(defn update-game-state [game-state move]
  (let [{:keys [board winner is-tie current-player]} game-state
        updated-board (board/make-move board move current-player)
        {:keys [board-contents gridsize]} updated-board
        updated-winner (detect-board-state/winner board-contents gridsize)
        updated-is-tie (detect-board-state/tie? board-contents gridsize)
        updated-current-player (determine-current-player updated-board)
        updated-game-over (detect-board-state/game-over? board-contents gridsize)]
    {:board          updated-board
     :winner         updated-winner
     :is-tie         updated-is-tie
     :current-player updated-current-player
     :game-over      updated-game-over}))

(defn get-computer-move
  ([game-state ui] (get-computer-move game-state))
  ([game-state]
   (let [{:keys [board current-player]} game-state
         move (computer-minimax-ab-strategy/minimax-move board current-player)]
     move)))