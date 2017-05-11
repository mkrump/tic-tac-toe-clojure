(ns tictactoe.board-test
  (:require [clojure.test :refer :all]
            [tictactoe.board :refer :all]))

(deftest render-board-test
  (testing "A vector of length n^2 should return a string nxn ttt board"
    (is (= (str "  2  |     |  2  \n"
                "- - - - - - - - -\n"
                "     |  2  |     \n"
                "- - - - - - - - -\n"
                "  1  |  2  |  1  \n"
                "- - - - - - - - -\n")
           (board->string ["2" " " "2" " " "2" " " "1" "2" "1"])))))


(deftest render-board-default-player-mapping-test
  (testing "If no player mapping supplied Xs and Os will be used for player symbols"
    (is (= (str "  X  |     |  O  \n"
                "- - - - - - - - -\n"
                "     |  X  |     \n"
                "- - - - - - - - -\n"
                "  X  |  X  |  X  \n"
                "- - - - - - - - -\n")
           (board->string ["X" " " "O" " " "X" " " "X" "X" "X"])))))


(deftest square-occupied-player1-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= true (square-occupied? [1 0 1] 0)))))

(deftest square-occupied-player2-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= true (square-occupied? [1 2 1] 1)))))

(deftest square-vacant-test
  (testing "Is a square is occupied (value of 1 or 2) should return true"
    (is (= false (square-occupied? [1 0 1] 1)))))

(deftest make-move-test
  (testing "New array should be returned with appropriate player marker"
    (is (= [[0 1 0] (make-move [0 0 0] 1 1)]))))


