(ns tictactoe.board-translators-test
  (:require [clojure.test :refer :all]
            [tictactoe.board-translators :refer :all]))

(deftest board->ui-empty-test
  (testing "An empty board square should be mapped to +1 index in ui"
    (is (= "1" (apply-ui-mapping [0 0 1
                                  1 2 0
                                  0 0 0] 0)))))
(deftest board->ui-empty-test
  (testing "An empty board square should be mapped to +1 index in ui"
    (is (= "9" (apply-ui-mapping [0 0 1
                                  1 2 0
                                  0 0 0] 8)))))

(deftest board->ui-occupied-player1-test
  (testing "An occupied board square should be mapped to the players symbol in ui (X=1 O=2)"
    (is (= "X" (apply-ui-mapping [0 0 1
                                  1 2 0
                                  0 0 0] 2)))))

(deftest board->ui-occupied-player2-test
  (testing "An occupied board square should be mapped to the players symbol in ui (X=1 O=2)"
    (is (= "O" (apply-ui-mapping [0 0 1
                                  1 2 0
                                  0 0 0] 4)))))

(deftest ui->board
  (testing "A ui square should be mapped back to the index of its respective board sqaure"
    (is (= 8 (inverse-ui-mapping "9")))))


(deftest valid-transformation-test
  (testing "Applying the inverse-ui-mapping to ui-mapping should return the original board square"
    (is (= 4 (inverse-ui-mapping (ui-mapping 4))))))
