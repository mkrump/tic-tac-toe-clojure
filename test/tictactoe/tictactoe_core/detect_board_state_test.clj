(ns tictactoe.tictactoe-core.detect-board-state-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-core.detect-board-state :refer :all]))

(deftest no-win-test
  (let [board  [-1 1 -1
                0 -1 1
                1 0 1]]
    (is (= 0 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= false (game-over? board 3)))))

(deftest row-one-win-test
  (let [board [1 1 1
               0 0 0
               0 0 0]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest row-two-win-test
  (let [board [0 0 0
               1 1 1
               0 0 0]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest row-three-win-test
  (let [board [ 0  0  0
                0  0  0
               -1 -1 -1]]
    (is (= -1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest up-diag-win-test
  (let [board [ 0  0  -1
                0  -1  0
               -1  -1 -1]]
    (is (= -1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest down-diag-win-test
  (let [board [ 1  0  1
                0  1  0
               -1  -1 1]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest col-one-win-test
  (let [board [ 1  0  1
                1  1  0
                1 -1 1]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest col-two-win-test
  (let [board [1 1 -1
               -1 1 0
                1 1 1]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest col-three-win-test
  (let [board [ 1 0 1
               -1 0 1
                1 1 1]]
    (is (= 1 (winner board 3)))
    (is (= false (tie? board 3)))
    (is (= true (game-over? board 3)))))

(deftest tie-test
  (let [board [ 1 -1 1
                1 -1 1
               -1  1 -1]]
    (is (= 0 (winner board 3)))
    (is (= true (tie? board 3)))
    (is (= true (game-over? board 3)))))

