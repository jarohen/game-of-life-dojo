(ns conway.core
  (:require [clojure.string :as s]))

(defn live?
  [n]
  (zero? (rand-int n)))

(defn create-grid
  ([n] (create-grid n live?))
  
  ([n f]
     (->> (for [i (range n)
                j (range n)]
            [[i j] (f n)])
          (into {}))))


(defn neighbours
  [grid x y]
  (for [i (range -1 2)
        j (range -1 2)
        :when (not (and (zero? i) (zero? j)))]
    (grid [(+ x i) (+ y j)])))

(defn count-neighbours [neighbours]
  (count (filter identity neighbours)))

(defn alive-next-step? [neighbours-count alive-already?]
  (-> [neighbours-count alive-already?]
      #{[2 true] [3 true] [3 false]}
      boolean))

(defn step
  [grid]
  (->> (for [[[x y] alive-already?] grid]
         [[x y] (-> (neighbours grid x y)
                    count-neighbours
                    (alive-next-step? alive-already?))])
       (into {})))

(defn render-cell [cell-live?]
  (case cell-live?
    true "*"
    false "-"))

(defn pprint-grid [grid size]
  (->> (for [x (range size)]
         (->> (for [y (range size)]
                (render-cell (grid [x y])))
              s/join))
       (s/join "\n")))

(defn play-game [grid]
  (iterate step grid))
