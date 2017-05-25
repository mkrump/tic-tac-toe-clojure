(ns tictactoe.human-player-console-test
  (:require [clojure.test :refer :all]
            [tictactoe.human-console-player :refer :all]))

(deftest human-player-move-test
  (let [player (human-console-player "X")]
    (testing "A human player can enter a move via the console"
      (is (= "1" (with-in-str "1\n" ((:move player) [])))))))

(deftest human-player-symbol-test
  (let [player (human-console-player "X")]
    (testing "A human player's marker matches one used in constructor"
      (is (= "X" (:marker player))))))
