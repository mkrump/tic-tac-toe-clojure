(ns tictactoe.get-user-input-test)

(defn get-user-move []
  (println "Select an open square: ")
  (let [move (read-line)] (println move)))

