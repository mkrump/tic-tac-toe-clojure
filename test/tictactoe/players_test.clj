(ns tictactoe.players-test
  (:require [clojure.test :refer :all]
            [tictactoe.players :refer :all]))

(deftest square-occupied-player1-test
  (testing "A switching player 1 results in player 2"
    (is (= 2 (switch-player 1)))))

(deftest square-occupied-player2-test
  (testing "A switching player 2 results in player 1"
    (is (= 1 (switch-player 2)))))

