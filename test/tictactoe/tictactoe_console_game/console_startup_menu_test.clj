(ns tictactoe.tictactoe-console-game.console-startup-menu-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe-console-game.console-startup-menu :refer :all]
            [tictactoe.tictactoe-console-game.console-ui :as console-ui]))

(def players {1 :human-player 2 :computer-player})

(deftest choose-player-type-menu-valid-test
  (testing "Valid choice should not result in reprompting.
            A choice of 1 should correspond to a human player
            A choice of 2 should correspond to a computer player"
    (let [expected-flow
           (with-out-str
             (console-ui/clear-screen)
             (select-player-message 1))]
      (is (= expected-flow
                (with-out-str
                   (with-in-str "2"
                          (choose-player-type-menu 1)))))
      (with-out-str
        (let [choice (with-in-str "1" (choose-player-type-menu 1))]
          (is (= :human-player choice))))

      (with-out-str
        (let [choice (with-in-str "2" (choose-player-type-menu 1))]
          (is (= :computer-player choice)))))))

(deftest choose-player-type-menu-invalid-test
  (testing "An invalid choice should reprompt the user to choose again"
    (let [expected-flow
           (with-out-str
             (console-ui/clear-screen)
             (select-player-message 1)
             (invalid-choice-message)
             (console-ui/clear-screen)
             (select-player-message 1))]
      (is (= expected-flow
                (with-out-str
                   (with-in-str "10\n1\n"
                          (choose-player-type-menu 1))))))))
;
(deftest choose-player-marker-menu-valid-test
  (testing "A Valid choice should not result in reprompting."
    (let [expected-flow
          (with-out-str
            (console-ui/clear-screen)
            (choose-marker-message 1))]
      (is (= expected-flow
             (with-out-str
               (with-in-str "X\n"
                 (choose-marker-type-menu players 1))))))))

(deftest choose-player-marker-menu-invalid-test
  (testing "A Valid choice should not result in reprompting."
    (let [expected-flow
          (with-out-str
            (console-ui/clear-screen)
            (choose-marker-message 1)
            (invalid-choice-message)
            (console-ui/clear-screen)
            (choose-marker-message 1))]
      (is (= expected-flow
             (with-out-str
               (with-in-str "?\nX\n"
                 (choose-marker-type-menu players 1))))))))

(deftest choose-player-marker-menu-marker-already-chosen-test
  (testing "If another player has already chosen a marker"
    (let [expected-flow
          (with-out-str
            (console-ui/clear-screen)
            (choose-marker-message 2)
            (marker-already-chosen-message)
            (console-ui/clear-screen)
            (choose-marker-message 2))]
      (is (= expected-flow
             (with-out-str
               (with-in-str "X\nO\n"
                 (choose-marker-type-menu
                   {1 {:player-type :human-player, :marker "X"}, 2 {:player-type :computer-player}} 2))))))))

(deftest run-startup-menus-test
  (testing "If another player has already chosen a marker"
    (with-out-str
      (let [valid-inputs "1\nX\n2\nO\n"
            output (with-in-str valid-inputs (run-startup-menus))]
        (is (= '(1 2) (keys output)))
        (is (= "X" (get-in output [1 :marker])))
        (is (= :human-player (get-in output [1 :player-type])))
        (is (= "O" (get-in output [2 :marker])))
        (is (= :computer-player (get-in output [2 :player-type])))))))

(deftest startup-menu-test
  (testing "If another player has already chosen a marker"
    (with-out-str
      (let [valid-inputs "1\nX\n2\nO\n"
            output (with-in-str valid-inputs (startup-menu))]
        (is (= '(-1 1) (keys output)))
        (is (= "X" (get-in output [-1 :marker])))
        (is (= (contains? (output -1)  :move)))
        (is (= "O" (get-in output [1 :marker])))
        (is (= (contains? (output 1)  :move)))))))

