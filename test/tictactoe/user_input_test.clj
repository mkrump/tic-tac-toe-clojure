(ns tictactoe.user-input-test
  (:require [clojure.test :refer :all]
            [tictactoe.user-input :refer :all]))

(deftest get-user-input-test
  (testing "Test user input captured"
    (is (= (str "Select an open square: "
                "Moved to: 3")
           (with-out-str
             (with-in-str "3" (get-user-move)))))))



