(ns rich4clojure.easy.problem-135
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Infix Calculator =
;; By 4Clojure user: fdaoud
;; Difficulty: Easy
;; Tags: [higher-order-functions math]
;; 
;; Your friend Joe is always whining about Lisps using the
;; prefix notation for math. Show him how you could easily
;; write a function that does math using the infix
;; notation. Is your favorite language that flexible, Joe?
;; 
;; Write a function that accepts a variable length
;; mathematical expression consisting of numbers and the
;; operations +, -, *, and /. Assume a simple calculator
;; that does not do precedence and instead just calculates
;; left to right.

(def __ (fn
          ([a]
           (assert (number? a) "Expected number in the last position")
           a)
          ([a & rest']
           (assert (fn? (first rest')) "Expected operator in an even position")
           (let [f (first rest') b (second rest') rest'' (rest (rest rest'))]
             (apply __ (f a b) rest'')))))

(comment
  (defn my-calc [x & xs]
    (reduce (fn [acc, [f x]] (f acc x)) x (partition 2 xs)))
  (my-calc 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9)
  )

(tests
  7 :=  (__ 2 + 5)
  42 := (__ 38 + 48 - 2 / 2)
  8 :=  (__ 10 / 2 - 1 * 2)
  72 := (__ 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/3dddcaa33e0bba7b9de89b463d778e55