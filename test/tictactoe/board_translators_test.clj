(ns tictactoe.board-translators-test
  (:require [clojure.test :refer :all]
            [tictactoe.board-translators :refer :all]))

(deftest board-int->board-string-test
  (testing "A Board should map its elements to strings 1=X 2=O and 0=board index"
           (is (= ["0" "X" "O"
                   "3" "4" "5"
                   "X" "O" "8"]
                  (numeric-board-translation [0 1 2
                                              0 0 0
                                              1 2 0])))))

(deftest board-string->board-int-test
  (testing "A Board should map its strings represention to ints X=1 O=2 and board index=0"
           (is ( = [0 1 2
                    0 0 0
                    1 2 0]
                   (inverse-numeric-board-translation ["0" "X" "O"
                                                       "3" "4" "5"
                                                       "X" "O" "8"])))))


(deftest board-string->board-int-test
  (testing "Applying the inverse to the tranformed board should return the origin board"
    (let [test-board [0 1 2 0 0 0 1 2 0]]
      (is ( = test-board (inverse-numeric-board-translation
                           (numeric-board-translation test-board)))))))
