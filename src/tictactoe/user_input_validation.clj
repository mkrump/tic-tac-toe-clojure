(ns tictactoe.user-input-validation
  (:require [tictactoe.user-input :as user-input])
  (:require [tictactoe.board :as b]))

(defn open-square? [params]
  (let [{:keys [board move ui-board ui->board]} params
         translated-move (ui->board move)]
    (if (b/square-occupied? board translated-move)
      [nil "Square occupied."] [params nil])))

(defn valid-ui-choice? [params]
  (let [{:keys [board move ui-board ui->board]} params]
    (if (contains? (set ui-board) move)
      [params nil]
      [nil "Choice not available."])))

;TODO Based on suggestions here
;TODO wasn't sure if better route
;https://adambard.com/blog/acceptable-error-handling-in-clojure/
(defn apply-or-error [f [val err]]
  (if (nil? err)
    (f val)
    [nil err]))

(defn valid-move? [params]
  (->> (valid-ui-choice? params)
       (apply-or-error open-square?)))

(defn validation-loop [params]
  (let  [params (assoc params :move (user-input/get-user-move))
         [result err] (valid-move? params)]
      (if (nil? result)
        (do
          (println err)
          (recur params))
        (do
          result))))





