(ns tictactoe.game
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.board :as board])
  (:require [tictactoe.console-ui :as ui])
  (:require [tictactoe.detect-board-state :as detect-board-state])
  (:require [tictactoe.board-translators :as board-translators])
  (:require [tictactoe.human-console-player :as human-console-player])
  (:require [tictactoe.computer-random-player :as computer-random-player])
  (:require [tictactoe.computer-minimax-player-blah :as computer-minimax-player]))

(defn- ?->keyword [value]
  (keyword (str value)))

(defn- get-current-player [game]
  (let [current-player (?->keyword (:current-player game))]
    (get-in game [:players current-player])))

(defn- request-player-move [game]
  (let [current-player (get-current-player game)]
    ((:move current-player) (:board game) (:current-player game))))

(defn- translate-move-if-needed [game move]
  (if (integer? move)
    move
    ((:ui->board game) move)))

(defn switch-player [player]
  (* -1 player))

(defn- generate-player-mapping [players]
  {:-1 (get-in players [:-1 :marker])
    :1 (get-in players [:1 :marker])})

(defn initialize-new-game []
  (let [board (board/generate-board 3)
        players {:1  (human-console-player/human-console-player "X")
                 :-1 (computer-minimax-player/computer-minimax-player "O")}
        player-symbol-mapping (generate-player-mapping players)]

    {:board          board
     :ui->board      ui/ui->board
     :move           nil
     :current-player 1
     :players        players
     :player-symbol-mapping player-symbol-mapping
     :ui-board       (ui/board->ui board player-symbol-mapping)}))

(defn game-over? [game]
  (detect-board-state/game-over?
    (get-in game [:board :board-contents])
    (get-in game [:board :gridsize])))

(defn update-game [game]
  (let [{board          :board
         ui->board      :ui->board
         move           :move
         current-player :current-player} game]
    (-> game
        (assoc :board (board/make-move board move current-player))
        (assoc :current-player (switch-player current-player)))))

(defn end-game-state [game]
  (let [board-contents (get-in game [:board :board-contents])
        gridsize (get-in game [:board :gridsize])
        winner (detect-board-state/winner board-contents gridsize)
        tie (detect-board-state/tie? board-contents gridsize)]
    (cond
      (not= 0 winner) {:winner (get-in game [:players (?->keyword winner) :marker])}
      (true? tie) {:tie ""})))


(defn move-validation [proposed-move game]
  (let [{ui-board :ui-board board
                  :board ui->board
                  :ui->board} game]
    (->>
      [proposed-move nil]
      (validation/valid-or-error #(validation/valid-console-ui-choice? % ui-board))
      (validation/valid-or-error #(validation/open-square? (translate-move-if-needed game %) board)))))


(defn get-move [game]
  (loop [_ (ui/render-move-request-msg)
         proposed-move (request-player-move game)]
    (let [validation-results (move-validation proposed-move game)]
      (let [[move error] validation-results]
        (if (nil? move)
          (do
            (ui/render-msg error)
            (recur (ui/render-move-request-msg) (request-player-move game)))
          (assoc game :move move))))))


