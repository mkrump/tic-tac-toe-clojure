(ns tictactoe.game
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as board])
  (:require [tictactoe.console-ui :as ui])
  (:require [tictactoe.detect-board-state :as detect-board-state])
  (:require [tictactoe.board-translators :as board-translators])
  (:require [tictactoe.human-console-player :as human-console-player])
  (:require [tictactoe.computer-random-player :as computer-random-player]))



(defn- get-current-player [game]
  (let [current-player (keyword (str (:current-player game)))]
    (get-in game [:players current-player])))

(defn- request-player-move [game]
  (let [current-player (get-current-player game)]
    ((:move current-player) (get-in game [:board :board-contents]))))

(defn- translate-move-if-needed [game move]
  (if (integer? move)
    move
    ((:ui->board game) move)))

(defn switch-player [player]
  (* -1 player))

(defn get-move [game]
  (let [{ui-board :ui-board board
                  :board ui->board
                  :ui->board} game]
    (loop [_ (ui/render-move-request-msg)
           proposed-move (request-player-move game)]
      (let [validation-results
            (->>
              [proposed-move nil]
              (validation/valid-or-error #(validation/valid-console-ui-choice? % ui-board))
              (validation/valid-or-error #(validation/open-square? (translate-move-if-needed game %) board)))]
        (let [[move error] validation-results]
          (if (nil? move)
            (do
              (ui/render-msg error)
              (recur (ui/render-move-request-msg) (request-player-move game)))
            (assoc game :move move)))))))

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


(defn initial-game []
  (let [board (board/generate-board 3)
        ui-board (ui/board->ui board)]
    {:board          board
     :ui-board       ui-board
     :ui->board      ui/ui->board
     :move           nil
     :current-player 1
     :players        {:-1 (human-console-player/human-console-player "X")
                      :1 (computer-random-player/computer-random-player "O")}}))

;(def x (initial-game))

;(get-in x [:players])

(defn end-game-state [game]
  (let [board-contents (get-in game [:board :board-contents])
        gridsize (get-in game [:board :gridsize])
        winner (detect-board-state/winner board-contents gridsize)
        tie (detect-board-state/tie? board-contents gridsize)]
    (cond
      (not= 0 winner) {:winner (get-in game [:players (keyword (str winner)) :marker])}
      (true? tie) {:tie ""})))


