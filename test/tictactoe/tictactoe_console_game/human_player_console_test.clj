(ns tictactoe.tictactoe-console-game.human-player-console-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-console-game.human-console-player :refer :all]))

(deftest human-player-move-test
  (let [player (human-console-player "X")]
    (testing "A human player can enter a move via the console"
      (is (= "1" (with-in-str "1\n" ((:move player) [] 1)))))))

(deftest human-player-symbol-test
  (let [player (human-console-player "X")]
    (testing "A human player's marker matches one used in constructor"
      (is (= "X" (:marker player))))))