(ns tictactoe.console-ui-test
  (:require [clojure.test :refer :all]
            [tictactoe.console-ui :refer :all]))

(deftest render-ui-test
  (testing "A vector of length n^2 should return a string nxn ttt board"
    (is (= (str "  2  |     |  2  \n"
                "- - - - - - - - -\n"
                "     |  2  |     \n"
                "- - - - - - - - -\n"
                "  1  |  2  |  1  \n"
                "- - - - - - - - -\n")
           (board->string {:board-contents ["2" " " "2" " " "2" " " "1" "2" "1"] :gridsize 3})))))


(deftest render-board-default-player-mapping-test
  (testing "If no player mapping supplied Xs and Os will be used for player symbols"
    (is (= (str "  X  |     |  O  \n"
                "- - - - - - - - -\n"
                "     |  X  |     \n"
                "- - - - - - - - -\n"
                "  X  |  X  |  X  \n"
                "- - - - - - - - -\n")
           (board->string {:board-contents ["X" " " "O" " " "X" " " "X" "X" "X"] :gridsize 3})))))

(deftest render-board-test
  (testing "render-board should print ui's version of tictactoe board to console"
    (is (= (str "  X  |  2  |  O  \n"
                "- - - - - - - - -\n"
                "  4  |  5  |  6  \n"
                "- - - - - - - - -\n"
                "  7  |  8  |  X  \n"
                "- - - - - - - - -\n")
           (with-out-str
             (render-board {:board-contents [1 0 -1 0 0 0 0 0 1] :gridsize 3}))))))

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