(ns rich4clojure.easy.problem-030
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Compress a Sequence =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [seqs]
;; 
;; Write a function which removes consecutive duplicates
;; from a sequence.

(def __ (fn [coll]
          (reduce (fn [acc x]
                    (if (or (empty? acc) (not= x (last acc)))
                      (conj acc x)
                      acc))
                  []
                  coll))
  )

(comment
  (defn rem-dupl
     [s]
     (loop [s' s, r [], c nil]
       (cond (empty? s')
             r
             (or (nil? c) (not= c (first s')))
             (recur (rest s') (conj r (first s')) (first s'))
             :else
             (recur (rest s') r c))))
  (conj [5] 4)
  (rem-dupl [1 1 2 3 3 2 2 3])
)

(tests
  (apply str (__ "Leeeeeerrroyyy")) := "Leroy"
  (__ [1 1 2 3 3 2 2 3]) := '(1 2 3 2 3)
  (__ [[1 2] [1 2] [3 4] [1 2]]) := '([1 2] [3 4] [1 2]))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/92a4bd13aaa6bffb80d7724de2c8e64d