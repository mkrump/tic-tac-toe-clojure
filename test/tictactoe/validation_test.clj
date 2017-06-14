(ns tictactoe.validation-test
  (:require [clojure.test :refer :all]
            [tictactoe.user-input-validation :refer :all]))

(deftest is-valid-move
    (testing "An open square (value = 0) should be a valid move"
      (is (= [0 nil] (open-square? 0 {:board-contents [0 1 2]})))))

(deftest is-not-open-square
    (testing "An move that is outside of the board's range should return nill and error message"
      (is (= [nil "Square occupied."] (open-square? 1 {:board-contents [0 1 2]})))))


(deftest valid-ui-choice-test
  (testing "Valid ui choices should return the params and nil error message"
    (is (= ["A" nil] (valid-console-ui-choice? "A" {:board-contents ["A" "B" "C"]})))))

(deftest invalid-ui-choice-test
  (testing "Invalid choices should return nil and error message"
    (is (= [nil "Choice not available."] (valid-console-ui-choice? "R" {:board-contents ["A" "B" "C"]})))))

