(ns tictactoe.tictactoe-console-game.game-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [tictactoe.tictactoe-console-game.board-translators :as board-translators]
            [tictactoe.tictactoe-console-game.game :refer :all]))

(deftest end-game-state-player1-wins-test
  (let [game {:game-state {:board {:board-contents [1 1 1 0 0 0 0 0 0] :gridsize 3}}
              :players {1 {:marker "X"} -1 {:marker "O"}}}]
    (testing "If player 1 wins"
      (is (= {:winner "X"} (end-game-state game))))))

(deftest end-game-state-player2-wins-test
  (let [game {:game-state {:board {:board-contents [-1 -1 -1 0 0 0 0 0 0] :gridsize 3}}
              :players {1 {:marker "X"} -1 {:marker "O"}}}]
    (testing "If player 2 wins"
      (is (= {:winner "O"} (end-game-state game))))))

(deftest end-game-state-tie-test
  (let [game {:game-state
              {:board
               {:board-contents [1 1 -1 -1 -1 1 1 1 -1] :gridsize 3}}}]
    (testing "If tie"
      (is (= {:tie ""}
             (end-game-state game))))))

;TODO still sorting this function
;(deftest get-user-input-test
;  (let [game {:current-player 1
;              :players        {1  (tictactoe.tictactoe-console-game.human-console-player/human-console-player "X")
;                               -1 (tictactoe.tictactoe-console-game.human-console-player/human-console-player "O")}
;              :board          {:board-contents [0 1]}}
;        ui {:ui->board {"A" 0 "B" 1}
;            :ui-board  {:board-contents ["A" "B"]}}]
;
;    (testing "Valid move returns game map reflecting move"
;      (with-out-str
;        (let [move (with-in-str "A\n" (ui-get-move game ui))]
;          (is (= 0 move)))))))

    ;(testing "A choice that is not part of UI should
    ;          let user know choice is not valid and then re-prompt until correct"
    ;  (is (= true
    ;         (string/includes?
    ;           (with-out-str
    ;             (with-in-str "1\nA\n" (ui-get-move game ui)))
    ;           "Choice not available."))))
    ;(testing "A choice of a square that is already taken should
    ;          let user know choice is not valid and then re-prompt until correct"
    ;  (is (= true
    ;         (string/includes?
    ;           (with-out-str
    ;             (with-in-str "B\nA\n" (ui-get-move game ui)))
    ;           "Square occupied."))))))
