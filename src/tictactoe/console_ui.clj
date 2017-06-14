(ns tictactoe.console-ui
  (:require [tictactoe.board-translators :as board-translators])
  (:require [clojure.string :as string]))

(defn- render-row-contents [row]
  (str "  " (string/join "  |  " row) "  \n"))

(defn- render-row-separator [row]
  (str (string/join " " (repeat (count row) "- - -")) "\n"))

(defn- render-row [row]
  (str (render-row-contents row)
       (render-row-separator row)))

(defn- lazy-seq->string [lz-seq]
  (apply str lz-seq))

(defn board->ui [board player-symbol-mapping]
  (let [{board-contents :board-contents} board
         translated-board (map #(board-translators/apply-ui-mapping board-contents % player-symbol-mapping) (range (count board-contents)))]
    (assoc board :board-contents translated-board)))

(defn ui->board [move] (board-translators/inverse-ui-mapping move))

(defn board->string [board]
  (let [{board :board-contents board-size :gridsize} board]
    (->> board
         (partition board-size)
         (map render-row)
         (lazy-seq->string))))

(defn render-board [board player-symbol-mapping]
  (-> board
       (board->ui player-symbol-mapping)
       (board->string)
       (print))
  (flush))

(defn ui-pause [ms]
  (Thread/sleep ms))

(defn clear-screen []
  (print "\033c")
  (flush))

(defn- print-then-flush [msg]
  (print msg)
  (flush))

(defn render-msg [msg]
  (println msg))

(defn render-move-request-msg [player-marker]
  (print-then-flush
    (str player-marker "'s turn.\n"
         "Select an open square: ")))

(defn render-game-over-msg [end-game-state]
  (if (contains? end-game-state :winner)
    (println (str (end-game-state :winner) "'s Win!"))
    (println (str "Tie game!"))))

(defn redraw-board [board player-symbol-mapping]
  (ui-pause 500)
  (clear-screen)
  (render-board board player-symbol-mapping))
