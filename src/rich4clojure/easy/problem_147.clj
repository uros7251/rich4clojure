(ns rich4clojure.easy.problem-147
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Pascal's Trapezoid =
;; By 4Clojure user: narvius
;; Difficulty: Easy
;; Tags: [seqs]
;; 
;; Write a function that, for any given input vector of
;; numbers, returns an infinite lazy sequence of vectors,
;; where each next one is constructed from the previous
;; following the rules used in Pascal's Triangle. For
;; example, for [3 1 2], the next row is [3 4 3 2].
;; 
;; Beware of arithmetic overflow! In clojure (since
;; version 1.3 in 2011), if you use an arithmetic operator
;; like + and the result is too large to fit into a 64-bit
;; integer, an exception is thrown. You can use +' to
;; indicate that you would rather overflow into Clojure's
;; slower, arbitrary-precision bigint.

(def __ (fn
          ([row] (lazy-seq (cons row (__ row :next))))
          ([row _]
           (lazy-seq
            (let [next-row (map #(apply +' %) (partition 2 1 (conj (into [0] row) 0)))]
              (cons next-row (__ next-row :next)))))))

(comment 
  (defn blogscot [coll]
    (lazy-seq
      (cons coll
            (blogscot (let [middle (map #(apply +' %) (partition 2 1 coll))]
                           (concat [(first coll)] middle [(last coll)]))))))
  (time (nth (nth (blogscot [1]) 1000) 50))
  (time (nth (nth (__ [1]) 1000) 50))
)
(tests
  (second (__ [2 3 2])) := [2 5 5 2]
  (take 5 (__ [1])) := [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]]
  (take 2 (__ [3 1 2])) := [[3 1 2] [3 4 3 2]]
  (take 100 (__ [2 4 2])) := (rest (take 101 (__ [2 2]))))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/9dcf23444c51883c4318d69efcd5e9f7