(ns tictactoe.board-test
  (:require [clojure.test :refer :all]
            [tictactoe.board :refer :all]))

(deftest square-occupied-player1-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= true (square-occupied? {:board-contents [1 0 1]} 0)))))

(deftest square-occupied-player2-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= true (square-occupied? {:board-contents [1 -1 1]} 1)))))

(deftest square-vacant-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= false (square-occupied? {:board-contents [1 0 1]} 1)))))

(deftest make-move-test
  (testing "New array should be returned with appropriate player marker"
    (is (= {:board-contents [0 1 0]} (make-move {:board-contents [0 0 0]} 1 1)))))

(deftest new-3x3-board-test
  (testing "A new board should consist of a vector of zeros length n^2"
    (let [{board :board-contents} (generate-board 3)]
      (is (and (= 9 (count board)) (every? zero? board))))))

(deftest new-4x4-board-test
  (testing "A new board should consist of a vector of zeros length n^2"
    (let [{board :board-contents} (generate-board 4)]
      (is (and (= 16 (count board)) (every? zero? board))))))

