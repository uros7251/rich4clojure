(ns rich4clojure.medium.problem-044
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Rotate Sequence =
;; By 4Clojure user: dbyrne
;; Difficulty: Medium
;; Tags: [seqs]
;; 
;; Write a function which can rotate a sequence in either
;; direction.

(def __ (fn [n s]
          (let [n' (mod n (count s))]
            (concat (drop n' s) (take n' s)))))

(comment
  (conj (seq [1 2 3]) 4)
  (defn my-rotate [n s]
    (loop [n' (mod n (count s))
           s' s
           t []]
      (if (zero? n')
        (concat s' t)
        (recur (dec n') (rest s') (conj t (first s'))))))
  (my-rotate 2 [1 2 3 4 5])
  )

(tests
  (__ 2 [1 2 3 4 5]) := '(3 4 5 1 2)
  (__ -2 [1 2 3 4 5]) := '(4 5 1 2 3)
  (__ 6 [1 2 3 4 5]) := '(2 3 4 5 1)
  (__ 1 '(:a :b :c)) := '(:b :c :a)
  (__ -4 '(:a :b :c)) := '(:c :a :b))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/2a419f3d9e0c22be01179175f6a7d2f0