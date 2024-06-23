(ns rich4clojure.easy.problem-026
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Fibonacci Sequence =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [Fibonacci seqs]
;; 
;; Write a function which returns the first X fibonacci
;; numbers.

(def __ (fn 
          [x]
         (take x (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1])))))

(comment
  (defn concat_fib
    [coll]
    (cond (empty? coll) [1]
          (= 1 (count coll)) [1 1]
          :else (conj coll (+ (last coll) (last (butlast coll))))))
  (defn fib
    [n]
    (loop [k 0 coll []]
      (if (= k n)
        coll
        (recur (inc k) (concat_fib coll)))))
  (fib 10) ;=> [1 1 2 3 5 8 13 21 34 55]
  )

(tests
  (__ 3) := '(1 1 2)
  (__ 6) := '(1 1 2 3 5 8)
  (__ 8) := '(1 1 2 3 5 8 13 21))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/87153a8e55b56058703e5bca6f8ba62a