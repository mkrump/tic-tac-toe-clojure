(ns tictactoe.tictactoe-core.ttt-core-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-core.ttt-core :refer :all]))

(deftest switch-player-player1-test
  (testing "Initial game state is set correctly"
    (let [expected-initial-game-state
          {:board          {:board-contents [0 0 0 0 0 0 0 0 0] :gridsize 3}
           :current-player -1
           :is-tie         false
           :winner         0
           :game-over      false}]
      (is (= expected-initial-game-state (initial-game-state 3))))))

(def current-game-state
  {:board          {:board-contents [0 1 -1 0 0 0 0 0 0] :gridsize 3}
   :current-player -1
   :is-tie         false
   :winner         0
   :game-over      false})


(deftest current-player-turn-empty-test
  (testing "A correct current player turn is derived from a board"
    (let [board {:board-contents [0 0 0 0 0 0 0 0 0] :gridsize 3}]
      (is (= -1 (determine-current-player board))))))

(deftest current-player-turn-non-empty-test
  (testing "A correct current player turn is derived from a board"
    (let [board {:board-contents [-1 1 -1 0 0 0 0 0 0] :gridsize 3}]
      (is (= 1 (determine-current-player board))))))

(deftest current-player-turn-4x4-empty-test
  (testing "A correct current player turn is derived from a board"
    (let [board {:board-contents [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] :gridsize 4}]
      (is (= -1 (determine-current-player board))))))

(deftest current-player-turn-4x4-non-empty-test
  (testing "A correct current player turn is derived from a board"
    (let [board {:board-contents [-1 0 0 0 1 0 0 -1 0 0 0 0 0 0 0 0] :gridsize 4}]
      (is (= 1 (determine-current-player board))))))

(deftest square-occupied-test
  (testing "An occupied square should return :occupied"
    (is (= {:status :occupied, :message "Square occupied"}
           (move-status current-game-state 1)))))

(deftest square-out-of-range
  (testing "An out of range square should return :out-of-range"
    (is (= {:status :out-of-range, :message "Out of range"}
           (move-status current-game-state 1000)))))

(deftest valid-move
  (testing "An open square should return :valid-move"
    (is (= {:status :valid-move, :message "Success"}
           (move-status current-game-state 0)))))

(deftest update-game-state-no-win-test
  (testing "Next game-state is returned"
    (let
      [current-game-state
       {:board          {:board-contents [0 1 -1 0 0 0 0 0 0] :gridsize 3}
        :current-player -1
        :is-tie         false
        :winner         0
        :game-over      false}
       expected-game-state
       {:board          {:board-contents [-1 1 -1 0 0 0 0 0 0] :gridsize 3}
        :current-player 1
        :is-tie         false
        :winner         0
        :game-over      false}]
      (is (= expected-game-state (update-game-state current-game-state 0))))))

(deftest update-game-state-win-test
  (testing "Next game-state is returned"
    (let
      [current-game-state
       {:board          {:board-contents [1 0 -1 1 -1 -1 0 0 0] :gridsize 3}
        :current-player 1
        :is-tie         false
        :winner         0
        :game-over      false}
       expected-game-state
       {:board          {:board-contents [1 0 -1 1 -1 -1 1 0 0] :gridsize 3}
        :current-player -1
        :is-tie         false
        :winner         1
        :game-over      true}]
      (is (= expected-game-state (update-game-state current-game-state 6))))))

(deftest computer-move-test
  (testing "For a given game state computer move is returned"
    (let
      [current-game-state
       {:board          {:board-contents [-1 0 0 0 0 0 0 0 0] :gridsize 3}
        :current-player 1
        :is-tie         false
        :winner         0
        :game-over      false}]
      (is (= 4 (get-computer-move current-game-state))))))