(ns tictactoe.console-startup-menu
  (:require [tictactoe.human-console-player :as human-console-player]
            [tictactoe.computer-minimax-player :as computer-minimax-player]
            [tictactoe.console-ui :as console-ui]))

(def ^:private human-player
  (partial human-console-player/human-console-player))

(def ^:private computer-player
  (partial computer-minimax-player/computer-minimax-player))

(defn- upcase-letter? [s]
  (not (nil? (re-find #"^[A-Z]$" s))))

(defn- create-player [players player-number marker]
  ((players player-number) marker))

(defn- sanitize-input [s]
  (-> s
      (clojure.string/upper-case)
      (clojure.string/trim)))

(defn- marker-already-chosen? [marker-choice opponent-marker]
  (= marker-choice opponent-marker))

(defn invalid-choice-message []
  (println "Not a Valid choice. Please choose again.")
  (console-ui/ui-pause 1000)
  (console-ui/clear-screen))

(defn marker-already-chosen-message []
  (println "Marker already chosen. Please choose another marker.")
  (console-ui/ui-pause 1000)
  (console-ui/clear-screen))

(defn select-player-message [player-number]
  (println
    (str "Select Player " player-number "'s player type from the list below:\n"
         "1. Human\n"
         "2. Computer\n")))

(defn choose-marker-message [player-number]
  (println
    (str "Select a letter to use as "
         "Player " player-number "'s"
         " marker:")))

(defn get-opponent-marker [players player-number]
  (let [opponent (mod (inc player-number) 2)]
    (get-in players [opponent :marker])))

(defn choose-player-type-menu [player-number]
  (console-ui/clear-screen)
  (select-player-message player-number)
  (let [input (read-line)]
    (cond
      (= "1" input) human-player
      (= "2" input) computer-player
      :else
      (do
        (invalid-choice-message)
        (choose-player-type-menu player-number)))))

(defn choose-marker-type-menu [players player-number]
  (console-ui/clear-screen)
  (let [player (players player-number)
        oppenent-marker (get-opponent-marker players player-number)]
    (choose-marker-message player-number)
    (let [marker-choice (sanitize-input (read-line))]
      (cond
        (marker-already-chosen? marker-choice oppenent-marker)
        (do
          (marker-already-chosen-message)
          (recur players player-number))
        (upcase-letter? marker-choice)
        (create-player players player-number marker-choice)
        :else
        (do
          (invalid-choice-message)
          (recur players player-number))))))

(defn run-startup-menus []
  (as-> {} players
        (assoc players 1 (choose-player-type-menu 1))
        (assoc players 1 (choose-marker-type-menu players 1))
        (assoc players 2 (choose-player-type-menu 2))
        (assoc players 2 (choose-marker-type-menu players 2))))

