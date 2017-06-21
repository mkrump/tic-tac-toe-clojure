(ns tictactoe.tictactoe-console-game.console-ui-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [tictactoe.tictactoe-console-game.console-ui :refer :all]
            [tictactoe.tictactoe-console-game.console-board-rendering :as console-board-rendering]
            [clojure.string :as string]))

(deftest render-board-test
  (testing "render-board should print ui's version of tictactoe board to console"
    (is (= (str "  X  |  2  |  O  \n"
                "- - - - - - - - -\n"
                "  4  |  5  |  6  \n"
                "- - - - - - - - -\n"
                "  7  |  8  |  X  \n"
                "- - - - - - - - -\n")
           (with-out-str
             (render-board {:board-contents [1 0 -1 0 0 0 0 0 1] :gridsize 3} {1 "X" -1 "O"}))))))

(deftest clear-screen-test
  (testing "print ANSI clear screen escape code '\033c' "
    (is (= "\033c" (with-out-str (clear-screen))))))

(deftest render-msg-test
  (testing "An abitrary msg should be rendered to the console"
    (is (= "Hello\n" (with-out-str (render-msg "Hello"))))))

(deftest render-game-over-msg-win-test
  (testing "A game with a winner should render a winning message"
    (is (= "X's Win!\n" (with-out-str (render-game-over-msg {:winner "X"}))))))

(deftest render-game-over-msg-tie-test
  (testing "If game ends in tie should display tie msg"
    (is (= "Tie game!\n" (with-out-str (render-game-over-msg {:tie ""}))))))

(deftest valid-ui-choice-test
  (testing "Valid ui choices should return the params and nil error message"
    (is (= {:status :valid-move, :message "Success"}
           (valid-console-ui-choice? "A" {:board-contents ["A" "B" "C"]})))))

(deftest invalid-ui-choice-test
  (testing "Invalid choices should return nil and error message"
    (is (= {:status :choice-not-available :message "Choice not available."}
           (valid-console-ui-choice? "R" {:board-contents ["A" "B" "C"]})))))

(deftest get-console-input
  (testing "Valid input should return move"
    (with-out-str
      (let [valid-inputs "1\n"
            output (with-in-str valid-inputs (get-console-move {} {:ui-board {:board-contents ["1" "2"]}}))]
        (is (= output 0))))))

(deftest get-console-input
  (testing "Bad input should re-prompt user"
    (let [inputs "1000\n1\n"
          output
          (with-out-str
            (with-in-str inputs (get-console-move {} {:ui-board {:board-contents ["1" "2"]}})))]
      (is (= true (string/includes? output "Choice not available."))))))

(deftest get-console-input
  (testing "Bad input should re-prompt user"
    (with-out-str
      (let [valid-inputs "1000\n1\n"
            output (with-in-str valid-inputs (get-console-move {} {:ui-board {:board-contents ["1" "2"]}}))]
        (is (= output 0))))))