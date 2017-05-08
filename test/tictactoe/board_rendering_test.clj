(ns tictactoe.board-rendering-test
  (:require [clojure.test :refer :all]
            [tictactoe.render-board :refer :all]))

(deftest get-board-size-test
  (testing "A board vector of length 9 should correspond to a board size of 3"
    (is (= 3 (get-board-size (repeat 9 0))))))

(deftest render-board-test
  (testing "A vector of length n^2 should return a string nxn ttt board"
    (is (= (str "  2  |     |  2  \n"
                "- - - - - - - - -\n"
                "     |  2  |     \n"
                "- - - - - - - - -\n"
                "  1  |  2  |  1  \n"
                "- - - - - - - - -\n")
           (render-board [2 0 2 0 2 0 1 2 1]
                         :player-symbol-mapping {1 "1" 2 "2"})))))

(deftest render-board-default-player-mapping-test
  (testing "If no player mapping supplied Xs and Os will be used for player symbols"
    (is (= (str "  X  |     |  O  \n"
                "- - - - - - - - -\n"
                "     |  X  |     \n"
                "- - - - - - - - -\n"
                "  X  |  X  |  X  \n"
                "- - - - - - - - -\n")
           (render-board [1 0 2 0 1 0 1 1 1])))))

