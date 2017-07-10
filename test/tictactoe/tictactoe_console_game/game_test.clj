(ns tictactoe.tictactoe-console-game.game-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [tictactoe.tictactoe-console-game.console-board-rendering :as console-board-rendering]
            [tictactoe.tictactoe-console-game.game :refer :all]
            [tictactoe.tictactoe-console-game.console-ui :as console-ui]
            [tictactoe.tictactoe-core.ttt-core :as ttt-core]))

(deftest initialize-new-game-test
  (let [players {-1 "X" 1 "O"}
        game (initialize-new-game players)]
    (is (= (contains? game :game-state)))
    (is (= (contains? game :players)))))

(deftest initialize-ui-test
  (let [players {-1 "X" 1 "O"}
        ui (initialize-ui players (ttt-core/initial-game-state 3))]
    (is (= (contains? ui :board-ui)))
    (is (= (contains? ui :player-symbol-mapping)))))

(deftest end-game-state-player1-wins-test
  (let [game {:game-state
                       {:board     {:board-contents [1 1 1 0 0 0 0 0 0] :gridsize 3}
                        :winner    1
                        :is-tie    false
                        :game-over true}
              :players {1 {:marker "X"} -1 {:marker "O"}}}]
    (testing "If player 1 wins"
      (is (= {:winner "X"} (end-game-state game))))))

(deftest end-game-state-player2-wins-test
  (let [game {:game-state
                       {:board     {:board-contents [-1 -1 -1 0 0 0 0 0 0] :gridsize 3}
                        :winner    -1
                        :is-tie    false
                        :game-over true}
              :players {1 {:marker "X"} -1 {:marker "O"}}}]
    (testing "If player 2 wins"
      (is (= {:winner "O"} (end-game-state game))))))

(deftest end-game-state-tie-test
  (let [game {:game-state
                       {:board     {:board-contents [1 1 -1 -1 -1 1 1 1 -1] :gridsize 3}
                        :winner    0
                        :is-tie    true
                        :game-over true}
              :players {1 {:marker "X"} -1 {:marker "O"}}}]
    (testing "If tie"
      (is (= {:tie ""}
             (end-game-state game))))))

(defn return-valid [game-state ui] 1)
(deftest get-user-input-valid-test
  (let [players {-1 {:marker "X" :move return-valid}
                 1  {:marker "O" :move return-valid}}
        game (initialize-new-game players)
        ui (initialize-ui players game)]
    (testing "Valid move returns move"
      (let [move (make-move game ui)]
        (is (= 1 move))))))

;Returns 10, 0, ... to simulate bad then good user input
(def values (atom 20))
(defn list-of-values [game ui]
  (swap! values (fn [current-value] (- current-value 10))))
(deftest get-user-input-invalid-test
  (testing "Invalid Valid move returns move")
  (let [players {-1 {:marker "X" :move list-of-values}
                 1  {:marker "O" :move list-of-values}}
        game (initialize-new-game players)
        ui (initialize-ui players game)
        msg (with-out-str (make-move game ui))]
    (is (= msg "Out of range\n"))))