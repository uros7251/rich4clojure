(ns rich4clojure.easy.problem-039
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Interleave Two Seqs =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [seqs core-functions]
;; 
;; Write a function which takes two sequences and returns
;; the first item from each, then the second item from
;; each, then the third, etc.

(def restricted [interleave])

(def __ (fn [s1 s2]
          (loop [s1' s1 s2' s2 from-first true s []]
            (cond (and from-first (not-empty s1') (not-empty s2'))
                  (recur (rest s1') s2' false (conj s (first s1')))
                  (and (not from-first) (not-empty s2'))
                  (recur s1' (rest s2') true (conj s (first s2')))
                  :else
                  s))))

(comment
  (defn my-interleave [s1 s2]
    (loop [s1' s1 s2' s2 s []]
      (if (or (empty? s1') (empty? s2'))
        s
        (recur (rest s1') (rest s2') (conj s (first s1') (first s2'))))))
  (my-interleave [1 2 3] [4 5 6 7 8])
  )

(tests
  (__ [1 2 3] [:a :b :c]) := '(1 :a 2 :b 3 :c)
  (__ [1 2] [3 4 5 6]) := '(1 3 2 4)
  (__ [1 2 3 4] [5]) := [1 5]
  (__ [30 20] [25 15]) := [30 25 20 15])

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/65d3ee0ffa567e78927bbebbb9d9cc89