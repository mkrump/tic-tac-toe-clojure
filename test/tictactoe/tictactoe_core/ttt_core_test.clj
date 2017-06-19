(ns tictactoe.tictactoe-core.ttt-core-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-core.ttt-core :refer :all]))

(deftest switch-player-player1-test
  (testing "A switching player 1 results in player 2"
    (is (= -1 (switch-player 1)))))

(deftest switch-player-player2-test
  (testing "A switching player 2 results in player 1"
    (is (= 1 (switch-player -1)))))

(def initial-game-state
  {:board          {:board-contents [0 1 -1 0 0 0 0 0 0] :gridsize 3}
   :current-player -1 :is-tie false :winner 0})

(deftest square-occupied-test
  (testing "An occupied square should return :occupied"
    (is (= :occupied (move-status initial-game-state 1)))))

(deftest square-out-of-range
  (testing "An out of range square should return :out-of-range"
    (is (= :out-of-range (move-status initial-game-state 1000)))))

(deftest valid-move
  (testing "An open square should return :valid-move"
    (is (= :valid-move (move-status initial-game-state 0)))))

(deftest update-game-state-invalid-test
  (testing "Next game-state is returned"
    (let
      [initial-game-state
       {:board
        {:board-contents [0 1 -1 0 0 0 0 0 0] :gridsize 3}
        :current-player -1 :is-tie false :winner 0}
       expected-game-state
       {:board          {:board-contents [-1 1 -1 0 0 0 0 0 0] :gridsize 3}
        :current-player 1 :is-tie false :winner 0}]
      (is (= expected-game-state (update-game-state initial-game-state 0))))))

(deftest update-game-state-invalid-test
  (testing "Next game-state is returned"
    (let
      [initial-game-state
       {:board          {:board-contents [0 1 1 0 0 0 0 0 0] :gridsize 3}
        :current-player 1 :is-tie false :winner 0}
       expected-game-state
       {:board          {:board-contents [1 1 1 0 0 0 0 0 0] :gridsize 3}
        :current-player -1 :is-tie false :winner 1}]
      (is (= expected-game-state (update-game-state initial-game-state 0))))))

(deftest computer-move-test
  (testing "Next game-state is returned"
    (let
      [initial-game-state
       {:board          {:board-contents [-1 0 0 0 0 0 0 0 0] :gridsize 3}
        :current-player 1 :is-tie false :winner 0}
       expected-game-state
       {:board          {:board-contents [-1 0 0 0 1 0 0 0 0] :gridsize 3}
        :current-player -1 :is-tie false :winner 0}]
      (is (= expected-game-state (make-computer-move initial-game-state))))))
