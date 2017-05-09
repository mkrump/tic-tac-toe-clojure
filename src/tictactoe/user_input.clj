(ns tictactoe.user-input)

(defn- print-then-flush [msg]
  (print msg)
  (flush))

(defn get-user-move []
  (print-then-flush "Select an open square: ")
  (let [move (read-line)]
    (print-then-flush (str "Moved to: " move))))



