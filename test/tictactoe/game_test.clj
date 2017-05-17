(ns tictactoe.game-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [tictactoe.board-translators :as board-translators]
            [tictactoe.game :refer :all]))

(deftest square-occupied-player1-test
  (testing "A switching player 1 results in player 2"
    (is (= -1 (switch-player 1)))))

(deftest square-occupied-player2-test
  (testing "A switching player 2 results in player 1"
    (is (= 1 (switch-player -1)))))

(deftest player1-wins-test
  (let [game {:board
                {:board-contents [1 1 1
                                  0 0 0
                                  0 0 0]
                 :gridsize 3}
              :players {:1 {:marker "X"}
                        :-1 {:marker "O"}}}]
       (testing "If player 1 wins"
         (is (= {:winner "X"}
                (end-game-state game))))))

(deftest player2-wins-test
  (let [game {:board
              {:board-contents [-1 -1 -1
                                 0 0 0
                                 0 0 0]
               :gridsize 3}
              :players {:1 {:marker "X"}
                        :-1 {:marker "O"}}}]
    (testing "If player 2 wins"
      (is (= {:winner "O"}
             (end-game-state game))))))

(deftest tie-test
  (let [game {:board
              {:board-contents [1 1 -1
                                -1 -1 1
                                1 1 -1]
               :gridsize 3}}]
    (testing "If tie"
      (is (= {:tie ""}
             (end-game-state game))))))

(deftest get-user-input-test
  (let [game {:ui->board {"A" 0 "B" 1}
              :ui-board {:board-contents ["A" "B"]}
              :current-player 1
              :players {:1  (tictactoe.human-console-player/human-console-player "X")
                        :-1 (tictactoe.human-console-player/human-console-player "O")}
              :board {:board-contents [0 1]}}]
    (testing "Valid move"
      (is (= (assoc game :move 0)
             (with-in-str "A\n" (get-move game)))))
    (testing "A choice that is not part of UI should
              let user know choice is not valid and then re-prompt until correct"
       (is (= true
             (string/includes?
               (with-out-str
                 (with-in-str "1\nA\n" (get-move game)))
               "Choice not available."))))
    (testing "A choice of a square that is already taken should
              let user know choice is not valid and then re-prompt until correct"
       (is (= true
             (string/includes?
               (with-out-str
                 (with-in-str "B\nA\n" (get-move game)))
               "Square occupied."))))))

