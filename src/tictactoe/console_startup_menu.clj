(ns tictactoe.console-startup-menu
  (:require [tictactoe.human-console-player :as human-console-player]
            [tictactoe.computer-minimax-player :as computer-minimax-player]
            [tictactoe.console-ui :as console-ui]))

(def dflt-human-player
  (human-console-player/human-console-player "X"))

(def dflt-computer-player
  (computer-minimax-player/computer-minimax-player "O"))

;TODO need to check for duplicate markers.
;(assoc dflt-human-player :marker "J")

(defn bold-text [s]
  (str "\033[1m" s "\033[0m"))

(defn choose-game-type [player-number]
  (println
    (str "Select player " player-number "'s player type from the list below:\n"
         "1. Human\n"
         "2. Computer\n"))
  (let [input (read-line)]
    (cond
      (= "1" input) dflt-computer-player
      (= "2" input) dflt-human-player
      :else
      (do
        (println "Not a Valid choice. Please choose again.")
        (console-ui/ui-pause 1000)
        (console-ui/clear-screen)
        (choose-game-type player-number)))))

(defn choose-marker-type [player]
  (println
      (str "The default marker for this player is " (bold-text (:marker player)) " \n"
           "press " (bold-text "ENTER") " to use the default marker or enter \n"
           "another letter if you prefer a different marker.\n"))
  (let [input (clojure.string/upper-case (read-line))]
    (cond
      (not (nil? (re-find #"^[A-Z]$" input)))
      (assoc player :marker (clojure.string/trim input))
      (= "\n" input) player
      :else
        (do
          (println "Not a Valid choice. Please choose again.")
          (console-ui/ui-pause 1000)
          (console-ui/clear-screen)
          (choose-marker-type player)))))

(choose-marker-type dflt-human-player)

;(load-file "src/tictactoe/console_startup_menu.clj")
;(defn initialize-new-game [players]
;  (let [board (board/generate-board 3)
;        players {:1  (human-console-player/human-console-player "X")
;                 :-1 (computer-minimax-player/computer-minimax-player "O")}
;        player-symbol-mapping (generate-player-mapping players)]
;
;    {:board          board
;     :ui->board      ui/ui->board
;     :move           nil
;     :current-player 1
;     :players        players
;     :player-symbol-mapping player-symbol-mapping
;     :ui-board       (ui/board->ui board player-symbol-mapping)}))