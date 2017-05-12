(ns tictactoe.user-input-validation
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as board]))

(defn open-square? [game]
  (let [{board :board move :move ui->board :ui->board} game
         translated-move (ui->board move)]
    (if (board/square-occupied? board translated-move)
      [nil "Square occupied."]
      [game nil])))

(defn valid-ui-choice? [game]
  (let [{move :move ui-board :ui-board} game]
    (if (contains? (set ui-board) move)
      [game nil]
      [nil "Choice not available."])))

(defn apply-or-error [f [val err]]
  (if (nil? err)
    (f val)
    [nil err]))

(defn valid-move? [params]
  (->> (valid-ui-choice? params)
       (apply-or-error open-square?)))

(defn validator [params]
  (let  [[result err] (valid-move? params)]
      (if (nil? result)
        (do
          (println err)
          result)
        result)))




