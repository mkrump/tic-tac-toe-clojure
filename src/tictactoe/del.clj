(defn rolls [] (vec (repeat 21 0)))

(defn roll [rolls roll pins] (assoc rolls roll pins))


(defn get-n-rolls [rolls roll n] (subvec rolls roll (+ roll n)))


(defn roll-aggregator [number-of-rolls]

  (partial (fn [rolls roll]
             (reduce + (get-n-rolls rolls roll number-of-rolls)))))
(defn spare? [rolls roll] (= 10 ((roll-aggregator 2) rolls roll)))
(defn spare-bonus [rolls roll] ((roll-aggregator 3) rolls roll))
(defn strike? [rolls roll] (= 10 ((roll-aggregator 1) rolls roll)))
(defn strike-bonus [rolls roll] ((roll-aggregator 3) rolls roll))
(defn no-bonus [rolls roll] ((roll-aggregator 2) rolls roll))







(defn score


  ([rolls] (score rolls 0 0 0))


  ([rolls frame roll current-score]


   (if (< frame 10)


     (cond (strike? rolls roll)


           (recur rolls (inc frame) (+ 1 roll) (+ current-score (strike-bonus rolls roll)))


           (spare? rolls roll)


           (recur rolls (inc frame) (+ 2 roll) (+ current-score (spare-bonus rolls roll)))


           :else


           (recur rolls (inc frame) (+ 2 roll) (+ current-score (no-bonus rolls roll))))


     current-score)))
```The second week, I practiced the kata using someone else’s solution http:// www.jneander.com / writing / bowling-game-kata-in-clojure /, which I’ve reposted below. A decent amount of the two solutions are similar, but at the same to the score functions look very different. The functional version is very declarative and almost reads like a sentence, “ I think the part that was decidedly not was the `to-frames `function. The recursive call to `to-frames `combined with dropping the already processed rolls feels really elegant, very function, and was something that I wouldn’t have thought to do. Obviously the score function itself has been greatly simplified too.

```(ns bowling.core)








(defn sum [rolls] (reduce + rolls))






(defn spare? [rolls]


  (= 10 (sum (take 2 rolls))))






(defn strike? [rolls]


  (= 10 (sum (take 1 rolls))))






(defn rolls-for-frame [rolls]


  (if (or (spare? rolls)


          (strike? rolls))


    (take 3 rolls)


    (take 2 rolls)))






(defn rest-rolls [rolls]


  (if (strike? rolls)


    (drop 1 rolls)


    (drop 2 rolls)))






(defn to-frames [rolls]


  (lazy-seq


    (cons (rolls-for-frame rolls)


          (to-frames (rest-rolls rolls)))))






(defn score [rolls]


  (reduce + (flatten (take 10 (to-frames rolls)))))

