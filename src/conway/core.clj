(ns conway.core)

(defn live?
  [n]
  (zero? (rand-int n)))

(defn create-map
  [n f]
  (->> (for [i (range n)
             j (range n)]
         [[i j] (f n)])
       (into {})))


(defn neighbours
  [grid x y]
  (for [i (range -1 1)
        j (range -1 1)
        :when (not (and (zero? i) (zero? j)))]
    (grid [(+ x i) (+ y j)])))

(defn count-neighbours [neighbours]
  (count (filter identity neighbours)))

(def alive-next-step?
  (comp boolean #{2 3}))

(defn step
  [grid]
  (->> (for [[x y] grid]
         [[x y] (->> (neighbours grid x y)
                     count-neighbours
                     alive-next-step?)])
       (into {})))


