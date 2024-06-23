(ns rich4clojure.medium.problem-086
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Happy numbers =
;; By 4Clojure user: daviddavis
;; Difficulty: Medium
;; Tags: [math]
;; 
;; Happy numbers are positive integers that follow a
;; particular formula: take each individual digit, square
;; it, and then sum the squares to get a new number.
;; Repeat with the new number and eventually, you might
;; get to a number whose squared sum is 1. This is a happy
;; number. An unhappy number (or sad number) is one that
;; loops endlessly. Write a function that determines if a
;; number is happy or not.

(def __ (fn [n]
          (letfn [(lazy-digits
                    [n]
                    (when (pos-int? n)
                      (lazy-seq
                       (cons (rem n 10) (lazy-digits (quot n 10))))))
                  (digits-squared
                    [n]
                    (reduce #(+ %1 (* %2 %2)) 0 (lazy-digits n)))]
            (loop [n' n visited #{}]
              (if (contains? visited n')
                false
                (let [ds (digits-squared n')]
                  (if (= ds 1)
                    true
                    (recur ds (conj visited n')))))))))

(comment
)

(tests
 (__ 7) := true
 (__ 986543210) := true
 (__ 2) := false
 (__ 3) := false)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/b921948244dc0417fc716fe31cecb359