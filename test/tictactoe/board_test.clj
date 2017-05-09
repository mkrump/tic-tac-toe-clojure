(ns tictactoe.board-test
  (:require [clojure.test :refer :all]
            [tictactoe.board :refer :all]))

(deftest get-board-size-test
  (testing "A board vector of length 9 should correspond to a board size of 3"
    (is (= 3 (get-board-gridsize (repeat 9 0))))))

(deftest is-valid-move
  (testing "An open square (value = 0) should be a valid move"
    (is (= true (valid-move? [0 1 2] 0)))))

(deftest is-not-valid-move
  (testing "An occupied square (value != 0) should not be a valid move"
    (is (= false (valid-move? [0 1 2] 1)))))

(deftest out-of-range-move
  (testing "An move that is outside of the board's range should not be a valid move"
    (is (= false (valid-move? [0 1 2] 100)))))

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

