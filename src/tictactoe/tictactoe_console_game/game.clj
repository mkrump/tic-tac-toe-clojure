(ns tictactoe.tictactoe-console-game.game
  (:require [tictactoe.tictactoe-core.ttt-core :as ttt-core]
            [tictactoe.tictactoe-console-game.console-board-rendering :as console-board-rendering]
            [tictactoe.tictactoe-console-game.console-ui :as console-ui]))

(defn- get-current-player [game]
  (let [current-player (get-in game [:game-state :current-player])]
    (get-in game [:players current-player])))

(defn- get-current-player-marker [game]
  (let [current-player (get-current-player game)]
    (:marker current-player)))

(defn- request-player-move [game ui]
  (let [current-player (get-current-player game)
        game-state (:game-state game)]
    ((:move current-player) game-state ui)))

(defn- red-marker [marker]
  (str "\033[1;31m" marker "\033[0m"))

(defn- green-marker [marker]
  (str "\033[1;32m" marker "\033[0m"))

(defn- generate-player-mapping [players]
  {-1 (red-marker (get-in players [-1 :marker]))
   1  (green-marker (get-in players [1 :marker]))})

(defn initialize-new-game [players]
  (let [initial-game-state (ttt-core/initial-game-state 3)]
    {:game-state initial-game-state :players players}))

(defn end-game-state [game]
  (let [game-state (:game-state game)
        {:keys [winner is-tie]} game-state
        winner-marker (get-in game [:players winner :marker])]
    (cond
      (not= 0 winner) {:winner winner-marker}
      (true? is-tie) {:tie ""})))

(defn initialize-ui [players initial-game-state]
  (let [{:keys [board current-player is-tie winner]} initial-game-state
        player-symbol-mapping (generate-player-mapping players)
        ui-board (console-board-rendering/board->ui board player-symbol-mapping)]
    {:ui-board ui-board :player-symbol-mapping player-symbol-mapping}))

(defn player-move-prompt [game]
  (let [current-player-marker (get-current-player-marker game)]
    (console-ui/render-move-request-msg current-player-marker)))

(defn make-move [game ui]
  (loop [proposed-move (request-player-move game ui)]
    (let [move-status (ttt-core/move-status (:game-state game) proposed-move)]
      (if (= (:status move-status) :valid-move)
        proposed-move
        (do
          (console-ui/render-msg (:message move-status))
          (recur
            (request-player-move game ui)))))))