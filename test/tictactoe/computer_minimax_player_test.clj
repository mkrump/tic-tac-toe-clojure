(ns tictactoe.computer-minimax-player-test
  (:require [clojure.test :refer :all]
            [tictactoe.computer-minimax-player :refer :all]))

(deftest minimax-player-move-test-1
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [1 0 0
                0 0 0
                0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board -1)]
       (testing "If first player takes a corner any square other than the center is a loss"
         (is (= 4 move)))))

(deftest minimax-player-move-test-2
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [0 0 1
                0 0 0
                0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board -1)]
       (testing "If first player takes a corner any square other than the center is a loss"
         (is (= 4 move)))))

(deftest minimax-player-move-test-3
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [0 0 0
                0 0 0
                0 0 -1]
               :gridsize 3}
        move   ((:move minimax-player) board 1)]
       (testing "If first player takes a corner any square other than the center is a loss"
         (is (= 4 move)))))

(deftest minimax-player-move-test-4
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [0 0 0
                0 0 0
                -1 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board 1)]
       (testing "If first player takes a corner any square other than the center is a loss"
         (is (= 4 move)))))

(deftest minimax-player-easy-win-test-1
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [1 1 0
                -1 -1 0
                0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board 1)]
       (testing "If win in one is available the computer should take that move"
         (is (= 2 move)))))

(deftest minimax-player-easy-win-test-2
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [1 -1 0
                -1 1 0
                0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board 1)]
       (testing "If win in one is available the computer should take that move"
         (is (= 8 move)))))

(deftest minimax-player-win-test-3
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
                         [-1 -1 0
                          1 1 0
                          0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board -1)]
    (testing "If win in one is available the computer should take that move"
      (is (= 2 move)))))

(deftest minimax-player-block-win-test
  (let [minimax-player (computer-minimax-player "X")
        board {:board-contents
               [1 -1 0
                -1 1 0
                0 0 0]
               :gridsize 3}
        move   ((:move minimax-player) board -1)]
       (testing "If win in one is available the computer should block that move"
         (is (= 8 move)))))


