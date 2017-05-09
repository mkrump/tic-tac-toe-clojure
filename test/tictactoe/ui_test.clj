(ns tictactoe.ui-test
  (:require [clojure.test :refer :all]
    [tictactoe.ui :refer :all]))

(deftest render-board-test
  (testing "render-board should print ui's version of tictactoe board to console"
    (is (= (str "  X  |  2  |  O  \n"
                "- - - - - - - - -\n"
                "  4  |  5  |  6  \n"
                "- - - - - - - - -\n"
                "  7  |  8  |  X  \n"
                "- - - - - - - - -\n")
           (with-out-str (render-board [1 0 2 0 0 0 0 0 1]))))))

(deftest clear-screen-test
  (testing "print ANSI clear screen escape code '\033c' "
    (is (= "\033c" (with-out-str (clear-screen))))))

