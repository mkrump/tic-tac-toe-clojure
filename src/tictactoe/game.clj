(ns tictactoe.game
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as board])
  (:require [tictactoe.console-ui :as ui])
  (:require [tictactoe.detect-board-state :as detect-board-state])
  (:require [tictactoe.board-translators :as board-translators]))

(defn switch-player [player]
  (* -1 player))

(defn get-move [game]
  (let [{ui-board :ui-board board
         :board ui->board
         :ui->board} game]
    (loop [proposed-move (user-input/get-user-move)]
      (let [validation-results
            (->>
              [proposed-move nil]
              (validation/valid-or-error #(validation/valid-ui-choice? % ui-board))
              (validation/valid-or-error #(validation/open-square? (ui->board %) board)))]
          (let [[move error] validation-results]
            (if (nil? move)
              (do
                (ui/render-msg error)
                (recur (user-input/get-user-move)))
              (assoc game :move move)))))))

(defn game-over? [game]
  (detect-board-state/game-over?
    (get-in game [:board :board-contents])
    (get-in game [:board :gridsize])))

(defn update-game [game]
  (let [{board     :board
         ui->board :ui->board
         move      :move
         player    :player} game]
    (-> game
        (assoc :board (board/make-move board move player))
        (assoc :player (switch-player player)))))

(defn initial-game []
  (let [board (board/generate-board 3)
        ui-board (ui/board->ui board)]
    {:board     board
     :ui-board  ui-board
     :ui->board ui/ui->board
     :move      nil
     :player    1
     ui-board   (ui/board->ui board)}))

(defn end-game-state [game]
  (let [board-contents (get-in game [:board :board-contents])
        gridsize       (get-in game [:board :gridsize])
        winner (detect-board-state/winner board-contents gridsize)
        tie (detect-board-state/tie? board-contents gridsize)]
    (cond
      (not= 0 winner) {:winner (board-translators/player-mapping winner)}
      (true? tie) {:tie ""})))


