(ns tictactoe.tictactoe-console-game.console-board-rendering-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-console-game.console-board-rendering :refer :all]))

(def player-mapping {1 "X" -1 "O"})

(deftest board->ui-empty-test
  (testing "An empty board square should be mapped to +1 index in ui"
    (is (= "9" (apply-ui-mapping [0 0 1
                                  1 -1 0
                                  0 0 0] 8 player-mapping)))))

(deftest board->ui-occupied-player1-test
  (testing "An occupied board square should be mapped to the players symbol in ui (X=1 O=2)"
    (is (= "X"
           (apply-ui-mapping [0 0 1
                              1 -1 0
                              0 0 0] 2 player-mapping)))))

(deftest board->ui-occupied-player2-test
  (testing "An occupied board square should be mapped to the players symbol in ui (X=1 O=2)"
    (is (= "O"
           (apply-ui-mapping [0 0 1
                              1 -1 0
                              0 0 0] 4 player-mapping)))))

(deftest ui->board-test
  (testing "A ui square should be mapped back to the index of its respective board sqaure"
    (is (= 8 (inverse-ui-mapping "9")))))

(deftest valid-transformation-test
  (testing "Applying the inverse-ui-mapping to ui-mapping should return the original board square"
    (is (= 4 (inverse-ui-mapping (ui-mapping 4))))))

(deftest render-ui-test
  (testing "A vector of length n^2 should return a string nxn ttt board"
    (is (= (str "  X  |     |  O  \n"
                "- - - - - - - - -\n"
                "     |  X  |     \n"
                "- - - - - - - - -\n"
                "  X  |  X  |  X  \n"
                "- - - - - - - - -\n")
           (board->string {:board-contents ["X" " " "O" " " "X" " " "X" "X" "X"] :gridsize 3})))))