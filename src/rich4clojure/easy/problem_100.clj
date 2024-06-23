(ns rich4clojure.easy.problem-100
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Least Common Multiple =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [math]
;; 
;; Write a function which calculates the least common
;; multiple. Your function should accept a variable number
;; of positive integers or ratios.

(def __ (fn
          ([a] a)
          ([a b]
             (let [n (* a b)
                   d ((fn [a b]
                        (cond (zero? a) b
                              (> a b) (recur b a)
                              :else (recur (rem b a) a))) a b)]
               (/ n d)))
          ([a b & c] (apply __ (__ a b) c))))

(comment 
  (rem 2/5 5/16)
  )

(tests
  (__ 2 3) := 6
  (__ 5 3 7) := 105
  (__ 1/3 2/5) := 2
  (__ 3/4 1/6) := 3/2
  (__ 7 5/7 2 3/5) := 210)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/f9876ddb08600e7c90e1647e14efeeae