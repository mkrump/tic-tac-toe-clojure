(ns tictactoe.user-input)

(defn- print-then-flush [msg]
  (print msg)
  (flush))

(defn get-user-move []
  (print-then-flush "Select an open square: ")
  (read-line))

