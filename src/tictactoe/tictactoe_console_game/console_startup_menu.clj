(ns tictactoe.tictactoe-console-game.console-startup-menu
  (:require [tictactoe.tictactoe-console-game.console-ui :as console-ui]
            [tictactoe.tictactoe-core.ttt-core :as ttt-core]))

(defn- upcase-letter? [s]
  (not (nil? (re-find #"^[A-Z]$" s))))

(defn- sanitize-input [s]
  (-> s
      (clojure.string/upper-case)
      (clojure.string/trim)))

(defn- marker-already-chosen? [marker-choice opponent-marker]
  (= marker-choice opponent-marker))

(defn invalid-choice-message []
  (console-ui/render-msg "Not a Valid choice. Please choose again.")
  (console-ui/ui-pause 1000)
  (console-ui/clear-screen))

(defn marker-already-chosen-message []
  (console-ui/render-msg "Marker already chosen. Please choose another marker.")
  (console-ui/ui-pause 1000)
  (console-ui/clear-screen))

(defn select-player-message [player-number]
  (console-ui/render-msg
    (str "Select Player " player-number "'s player type from the list below:\n"
         "1. Human\n"
         "2. Computer\n")))

(defn choose-marker-message [player-number]
  (console-ui/render-msg
    (str "Select a letter to use as "
         "Player " player-number "'s"
         " marker:")))

(defn get-opponent-marker [players player-number]
  (let [opponent (inc (mod player-number 2))]
    (get-in players [opponent :marker])))

(defn choose-player-type-menu [player-number]
  (console-ui/clear-screen)
  (select-player-message player-number)
  (let [input (read-line)]
    (cond
      (= "1" input) :human-player
      (= "2" input) :computer-player
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
        marker-choice
        :else
        (do
          (invalid-choice-message)
          (recur players player-number))))))

(defn run-startup-menus []
  (as-> {} players
        (assoc-in players [1 :player-type] (choose-player-type-menu 1))
        (assoc-in players [1 :marker] (choose-marker-type-menu players 1))
        (assoc-in players [2 :player-type] (choose-player-type-menu 2))
        (assoc-in players [2 :marker] (choose-marker-type-menu players 2))))

(defn- human-player [marker]
  {:marker marker :move console-ui/get-console-move})

(defn- computer-player [marker]
  {:marker marker :move ttt-core/get-computer-move})

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

(defn startup-menu []
  (let [player-choices (run-startup-menus)]
    (console-ui/clear-screen)
    (gen-player-map player-choices player-types)))