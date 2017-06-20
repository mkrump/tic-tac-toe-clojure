(ns tictactoe.tictactoe-core.minimax-ab-strategy-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-core.minimax-ab-strategy :refer :all]))

(deftest minimax-strategy-test-1
  (let [board {:board-contents
                         [1 0 0
                          0 0 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board -1)]
    (testing "If first player takes a corner any square other than the center is a loss"
      (is (= 4 move)))))

(deftest minimax-strategy-test-2
  (let [board {:board-contents
                         [0 0 1
                          0 0 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board -1)]
    (testing "If first player takes a corner any square other than the center is a loss"
      (is (= 4 move)))))

(deftest minimax-strategy-test-3
  (let [board {:board-contents
                         [0 0 0
                          0 0 0
                          0 0 -1]
               :gridsize 3}
        move (minimax-move board 1)]
    (testing "If first player takes a corner any square other than the center is a loss"
      (is (= 4 move)))))

(deftest minimax-strategy-test-4
  (let [board {:board-contents
                         [0 0 0
                          0 0 0
                          -1 0 0]
               :gridsize 3}
        move (minimax-move board 1)]
    (testing "If first player takes a corner any square other than the center is a loss"
      (is (= 4 move)))))

(deftest minimax-strategy-easy-win-test-1
  (let [board {:board-contents
                         [1 1 0
                          -1 -1 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board 1)]
    (testing "If win in one is available the computer should take that move"
      (is (= 2 move)))))

(deftest minimax-strategy-easy-win-test-2
  (let [board {:board-contents
                         [1 -1 0
                          -1 1 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board 1)]
    (testing "If win in one is available the computer should take that move"
      (is (= 8 move)))))

(deftest minimax-strategy-win-test-3
  (let [board {:board-contents
                         [-1 -1 0
                          1 1 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board -1)]
    (testing "If win in one is available the computer should take that move"
      (is (= 2 move)))))

(deftest minimax-strategy-block-win-test
  (let [board {:board-contents
                         [1 -1 0
                          -1 1 0
                          0 0 0]
               :gridsize 3}
        move (minimax-move board -1)]
    (testing "If win in one is available the computer should block that move"
      (is (= 8 move)))))