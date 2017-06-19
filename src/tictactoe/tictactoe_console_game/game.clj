(ns tictactoe.tictactoe-console-game.game
  (:require [tictactoe.tictactoe-core.board :as board]
            [tictactoe.tictactoe-core.detect-board-state :as detect-board-state]
            [tictactoe.tictactoe-core.computer-minimax-ab-player :as computer-minimax-player]
            [tictactoe.tictactoe-core.ttt-core :as ttt-core]
            [tictactoe.tictactoe-console-game.user-input-validation :as validation]
            [tictactoe.tictactoe-console-game.console-ui :as ui]
            [tictactoe.tictactoe-console-game.board-translators :as board-translators]
            [tictactoe.tictactoe-console-game.human-console-player :as human-console-player]))

(defn- get-current-player [game]
  (let [current-player (get-in game [:game-state :current-player])]
    (get-in game [:players current-player])))

(defn- get-current-player-marker [game]
  (get-in game [:players (get-in game [:game-state :current-player]) :marker]))

(defn- request-player-move [game]
  (let [current-player (get-current-player game)]
    ((:move current-player) (get-in game [:game-state :board]) (get-in game [:game-state :current-player]))))

(defn- generate-player-mapping [players]
  {-1 (get-in players [-1 :marker])
   1  (get-in players [1 :marker])})

(def ^:private human-player
  (partial human-console-player/human-console-player))

(def ^:private computer-player
  (partial computer-minimax-player/computer-minimax-ab-player))

(def ^:private player-types
  {:human-player human-player, :computer-player computer-player})

(defn- create-player [player player-choices]
  (let [player-type (:player-type player)
        player-marker (:marker player)]
    ((player-choices player-type) player-marker)))

(defn- gen-player-map [startup-menu-choices player-types]
  (clojure.set/rename-keys
    (into {} (for [[k v] startup-menu-choices]
               [k (create-player v player-types)]))
    {1 -1 2 1}))


(defn initialize-new-game [players]
  (let [board (board/generate-board 3)
        board-players (gen-player-map players player-types)
        player-symbol-mapping (generate-player-mapping board-players)]
    {:game-state
              {:board board :current-player -1 :is-tie false :winner 0}
     :players board-players}))

(defn game-over? [game-state]
  (detect-board-state/game-over?
    (get-in game-state [:board :board-contents])
    (get-in game-state [:board :gridsize])))

(defn end-game-state [game]
  (let [board-contents (get-in game [:game-state :board :board-contents])
        gridsize (get-in game [:game-state :board :gridsize])
        winner (detect-board-state/winner board-contents gridsize)
        tie (detect-board-state/tie? board-contents gridsize)]
    (cond
      (not= 0 winner) {:winner (get-in game [:players winner :marker])}
      (true? tie) {:tie ""})))

(defn initialize-ui [players initial-game-state]
  (let [{:keys [board current-player is-tie winner]} initial-game-state
        board-players (gen-player-map players player-types)
        player-symbol-mapping (generate-player-mapping board-players)
        ui-board (ui/board->ui board player-symbol-mapping)]
    {:ui-board ui-board :board->ui ui/board->ui :player-symbol-mapping player-symbol-mapping}))

(defn- translate-move-if-needed [move]
  (if (integer? move)
    move
    (ui/ui->board move)))

(defn move-validation [proposed-move game ui]
  (let [ui-board (:ui-board ui)
        board (get-in game [:game-state :board])]
    (->>
      [proposed-move nil]
      (validation/valid-or-error #(validation/valid-console-ui-choice? % ui-board))
      (validation/valid-or-error #(validation/open-square? (translate-move-if-needed %) board)))))

;TODO move to console ui file
(defn ui-get-move [game ui]
  (loop [current-player-marker (get-current-player-marker game)
         _ (ui/render-move-request-msg current-player-marker)
         proposed-move (request-player-move game)]
    (let [validation-results (move-validation proposed-move game ui)]
      (let [[move error] validation-results]
        (if (nil? move)
          (do
            (ui/render-msg error)
            (recur
              current-player-marker
              (ui/render-move-request-msg current-player-marker)
              (request-player-move game)))
          (translate-move-if-needed move))))))

