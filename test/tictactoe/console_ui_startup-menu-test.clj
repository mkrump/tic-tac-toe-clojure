;(ns tictactoe.console-ui-test
;  (:require [clojure.test :refer :all]
;            [tictactoe.console-startup-menu :refer :all]
;            [tictactoe.console-ui :as console-ui]))
;
;(def players {1 computer-player 2 human-player})
;
;(deftest choose-player-type-menu-invalid-test
;  (testing "An invalid choice should reprompt the user to choose again"
;    (let [expected-flow
;           (with-out-str
;             (console-ui/clear-screen)
;             (select-player-message 1)
;             (invalid-choice-message)
;             (console-ui/clear-screen)
;             (select-player-message 1))]
;      (is (= expected-flow
;                (with-out-str
;                   (with-in-str "10\n1"
;                          (choose-player-type-menu 1))))))))
;
;(deftest choose-player-type-menu-valid-test
;  (testing "Valid choice should not result in reprompting.
;            A choice of 1 should correspond to a human player
;            A choice of 2 should correspond to a computer player"
;    (let [expected-flow
;           (with-out-str
;             (console-ui/clear-screen)
;             (select-player-message 1))]
;      (is (= expected-flow
;                (with-out-str
;                   (with-in-str "2"
;                          (choose-player-type-menu 1)))))
;      (is (= (type computer-player)
;             (type (with-in-str "1"
;                     (choose-player-type-menu 1)))))
;      (is (= (type human-player)
;             (type (with-in-str "2"
;                     (choose-player-type-menu 1))))))))
;
;
;(deftest choose-player-type-menu-invalid-test
;  (testing "An invalid choice should reprompt the user to choose again"
;    (let [expected-flow
;          (with-out-str
;            (console-ui/clear-screen)
;            (select-player-message 1)
;            (invalid-choice-message)
;            (console-ui/clear-screen)
;            (select-player-message 1))]
;      (is (= expected-flow
;             (with-out-str
;               (with-in-str "10\n1"
;                 (choose-player-type-menu 1))))))))

;(deftest choose-player-marker-menu-valid-test
;  (testing "Valid choice should not result in reprompting.
;            A choice of 1 should correspond to a human player
;            A choice of 2 should correspond to a computer player"
;    (let [expected-flow
;          (with-out-str
;            (console-ui/clear-screen)
;            (choose-marker-type-menu players 1))]
;      (is (= expected-flow
;             (with-out-str
;               (with-in-str "X\n"
;                 (choose-marker-type-menu players 1))))))))

