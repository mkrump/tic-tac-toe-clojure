(ns tictactoe.board-rendering-test
  (:require [clojure.test :refer :all]
            [tictactoe.render-board :refer :all]))

(deftest render-cell-not-empty-contents-test
  (testing "A cell with a value not equal to zero should render its contents"
    (is (= "  1  " (render-cell 1)))))

(deftest render-cell-empty-contents-test
  (testing "A cell with a value of zero should render as an empty space"
    (is (= "     " (render-cell 0)))))

(deftest render-cell-not-empty-with-mapping-contents-test
  (testing "A cell with a value not equal to zero should render its mapped value"
    (is (= "  X  " (render-cell 1 :player-symbol-mapping {1 "X" 2 "O"})))))

(deftest render-cell-empty-with-mapping-contents-test
  (testing "A cell with a value not equal to zero should render its mapped value"
    (is (= "     " (render-cell 0 :player-symbol-mapping {1 "X" 2 "O"})))))

(deftest render-row-contents-test
  (testing "A row of a board should return a string representation of the row"
    (is (= "  1  |  1  |  1  \n"
           (render-row-contents [1 1 1])))))

(deftest render-row-separator-test
  (testing "A row of a board should return a string representation of the separator row"
    (is (= "- - - - - - - - -\n"
           (render-row-separator [1 1 1])))))

(deftest get-board-size-test
  (testing "A board vector of length 9 should correspond to a board size of 3"
    (is (= 3 (get-board-size (repeat 9 0))))))

(deftest render-row-test
  (testing "A row of a board should return a string of its contents and a separator"
    (is (= " 1 | 1 | 1 \n___ ___ ___ \n") (render-row [1 0 1]))))

(deftest render-board-test
  (testing "A vector of length n^2 should return a string nxn ttt board"
    (is (= "  1  |     |  1  \n- - - - - - - - -\n     |  1  |     \n- - - - - - - - -\n  1  |  1  |  1  \n- - - - - - - - -\n"
           (render-board [1 0 1 0 1 0 1 1 1])))))



