(ns tictactoe.detect-board-state)

(defn- line [a m]
  (for [x (iterate inc 0)] (+ a (* x m))))

(defn- generate-row-indices [gridsize row]
  (for [row [row] col (range 0 gridsize)] (+ (* row gridsize) col)))

(defn- generate-col-indices [gridsize col]
  (for [col [col] row (range 0 gridsize)] (+ (* row gridsize) col)))

(defn- up-diagonal [gridsize]
  (take gridsize (line (* gridsize (- gridsize 1)) (- 1 gridsize))))

(defn- down-diagonal [gridsize]
  (take gridsize (line 0 (+ 1 gridsize))))

(defn- board-rows [gridsize]
  (map #(generate-row-indices gridsize %) (range gridsize)))

(defn- board-columns [gridsize]
  (map #(generate-col-indices gridsize %) (range gridsize)))

(defn- board-diags [gridsize]
  (list (up-diagonal gridsize) (down-diagonal gridsize)))

(defn- winning-positions [gridsize]
  (concat
    (board-diags gridsize)
    (board-rows gridsize)
    (board-columns gridsize)))

(defn- select-values [board ks]
  (reduce #(conj %1 (nth board %2)) [] ks))

(defn- sum [v] (reduce + v))

(defn winner [board gridsize]
  (loop [board board winning-positions (winning-positions gridsize)]
     (let [board-values (select-values board (first winning-positions))]
        (cond
          (empty? board-values) 0
          (= gridsize (sum board-values)) 1
          (= (- gridsize) (sum board-values)) -1
          :else (recur board (rest winning-positions))))))

(defn- board-full? [board]
  (every? (complement zero?) board))

(defn tie? [board gridsize]
  (and (board-full? board)
       (= 0 (winner board gridsize))))

(defn- winner? [board gridsize]
  (not= 0 (winner board gridsize)))

(defn game-over? [board gridsize]
  (if (or
        (winner? board gridsize)
        (tie? board gridsize))
      true
      false))




