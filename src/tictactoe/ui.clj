(ns tictactoe.ui
  (:require [tictactoe.board :as rb]
            [tictactoe.board-translators :as bt]
            [tictactoe.board :as b]))

(defn render-board [board]
  (->> board
       (bt/numeric-board-translation)
       (rb/board->string)
       (print))
  (flush))

(defn valid-ui-choice? [ui-board choice]
  (some? ((set ui-board) choice)))

(defn- identity-mapping [board]
  (map str board))

;TODO Left off here. Wasn't sure how to handle validation
;TODO because had to combine with mapping function and wasn't
;TODO if should be passing around
(defn valid-input? [board choice &
                    {:keys [board-elements->ui-elements]
                     :or {board-elements->ui-elements identity-mapping}}]
  (cond
    (valid-ui-choice? (board-elements->ui-elements board) choice)
    (b/valid-move? board (Integer/parseInt choice))
    :else false))

(defn clear-screen []
  (print "\033c")
  (flush))
