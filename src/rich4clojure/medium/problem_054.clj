(ns rich4clojure.medium.problem-054
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Partition a Sequence =
;; By 4Clojure user: dbyrne
;; Difficulty: Medium
;; Tags: [seqs core-functions]
;; 
;; Write a function which returns a sequence of lists of x
;; items each. Lists of less than x items should not be
;; returned.

(def restricted [partition partition-all])

(def __ (fn [n s]
          (loop [res [] s' s]
            (if (< (count s') n)
              res
              (let [[h t] (split-at n s')] ; more efficient than separate take and drop
                (recur (conj res h) t))))))

(comment
  (defn my-partition [n s]
    (lazy-seq
     (if (< (count s) n)
       nil
       (let [[h t] (split-at n s)]
         (cons h (my-partition n t))))))
  (nth (my-partition 2 (range 6000)) 50)
  (nth (__ 2 (range 6000)) 50)
  )

(tests
  (__ 3 (range 9)) := '((0 1 2) (3 4 5) (6 7 8))
  (__ 2 (range 8)) := '((0 1) (2 3) (4 5) (6 7))
  (__ 3 (range 8)) := '((0 1 2) (3 4 5)))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/1da137c7927d083dfbb4db1686a3e3cf