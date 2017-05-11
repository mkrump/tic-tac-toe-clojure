(ns tictactoe.user-input)

(defn- print-and-flush [msg]
  (print msg)
  (flush))

(defn get-user-move []
  (print-and-flush "Select an open square: ")
  (let [move (read-line)]
    (print-and-flush (str "Moved to: " move))))

