(ns tictactoe.validation-test
  (:require [clojure.test :refer :all]
            [tictactoe.user-input-validation :refer :all]))

(deftest is-valid-move
  (let [params {:board-contents [0 1 2]
                :move 0
                :ui-board nil
                :ui->board #(identity %)}]
    (testing "An open square (value = 0) should be a valid move"
      (is (= [params nil] (open-square? params))))))

(deftest is-not-open-square
  (let [params {:board {:board-contents [0 1 2]}
                :move 1
                :ui->board #(identity %)}]
    (testing "An move that is outside of the board's range should return nill and error message"
      (is (= [nil "Square occupied."] (open-square? params))))))


(deftest valid-ui-choice-test
  (let [params {:move "A"
                :ui-board {:board-contents ["A" "B" "C"]}}]
    (testing "Valid ui choices should return the params and nil error message"
      (is (= [params nil] (valid-ui-choice? params))))))

(deftest invalid-ui-choice-test
  (let [params {:move "R"
                :ui-board {:board-contents ["A" "B" "C"]}}]
    (testing "Invalid choices should return nil and error message"
      (is (= [nil "Choice not available."] (valid-ui-choice? params))))))

(deftest valid-move-test
  (let [params {:move "B"
                :ui-board {:board-contents ["A" "B" "C"]}
                :ui->board (fn [idx] 1)}]
    (testing "Chains together valid-ui-choice and open-square"
      (is (= [params nil] (valid-move? params))))))

(deftest invalid-square-occupied
  (let [params {:board {:board-contents [0 1 0]}
                :move "B"
                :ui-board {:board-contents ["A" "B" "C"]}
                :ui->board (fn [idx] 1)}]
    (testing "Chains together valid-ui-choice and open-square"
      (is (= [nil "Square occupied."]
             (valid-move? params))))))

(deftest invalid-not-ui-choice
  (let [params {:board {:board-contents [0 1 0]}
                :move "Z"
                :ui-board {:board-contents ["A" "B" "C"]}
                :ui->board (fn [idx] 0)}]
    (testing "Chains together valid-ui-choice and open-square"
      (is (= [nil "Choice not available."] (valid-move? params))))))
