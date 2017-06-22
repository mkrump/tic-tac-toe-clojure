(ns tictactoe.tictactoe-console-game.console-ui
  (:require [tictactoe.tictactoe-console-game.console-board-rendering :as console-board-rendering]
            [clojure.string :as string]))

(defn- print-then-flush [msg]
  (print msg)
  (flush))

(defn ui-pause [ms]
  (Thread/sleep ms))

(defn clear-screen []
  (print "\033c")
  (flush))

(defn render-msg [msg]
  (println msg))

(defn render-move-request-msg [player-marker]
  (print-then-flush
    (str player-marker "'s turn.\n")))

(defn valid-console-ui-choice? [move ui-board]
  (if (contains? (set (:board-contents ui-board)) move)
    {:status :valid-move :message "Success"}
    {:status :choice-not-available :message "Choice not available."}))

(defn get-console-move [game-state ui]
  (let [{:keys [ui-board]} ui]
    (print-then-flush "Select an open square: ")
    (let [move (read-line)]
      (let [{:keys [status message]} (valid-console-ui-choice? move ui-board)]
        (if (= status :valid-move)
          (console-board-rendering/ui->board move)
          (do
            (println message)
            (recur game-state ui)))))))

(defn render-game-over-msg [end-game-state]
  (if (contains? end-game-state :winner)
    (println (str (end-game-state :winner) "'s Win!"))
    (println (str "Tie game!"))))

(defn render-board [board player-symbol-mapping]
  (-> board
      (console-board-rendering/board->ui player-symbol-mapping)
      (console-board-rendering/board->string)
      (print))
  (flush))

(defn redraw-board [board player-symbol-mapping]
  (ui-pause 500)
  (clear-screen)
  (render-board board player-symbol-mapping))