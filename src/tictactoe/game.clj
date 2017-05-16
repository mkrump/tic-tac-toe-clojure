(ns tictactoe.game
  (:require [tictactoe.user-input-validation :as validation])
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as board])
  (:require [tictactoe.ui :as ui])
  (:require [tictactoe.detect-board-state :as detect-board-state]))

(defn switch-player [player]
  (* -1 player))

(defn get-move [game]
  (assoc game :move (user-input/get-user-move)))

(defn validate-move [game]
  (let [[move error]
        (->> (validation/valid-ui-choice? #(game :move) (game :ui-board))
             (validation/valid-or-error #(validation/open-square? (game :move) (game :board))))]
    (if (nil? move)
      (get-move game)
      (assoc game :move move))))

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
