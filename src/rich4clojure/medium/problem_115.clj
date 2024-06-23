(ns rich4clojure.medium.problem-115
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = The Balance of N =
;; By 4Clojure user: amcnamara
;; Difficulty: Medium
;; Tags: [math]
;; 
;; A balanced number is one whose component digits have
;; the same sum on the left and right halves of the
;; number. Write a function which accepts an integer n,
;; and returns true iff n is balanced.

(def __ (fn [n]
          (letfn [(digits [n]
                    (->> [n 0]
                         (iterate (fn [[n]] [(quot n 10) (rem n 10)]))
                         (drop 1)
                         (take-while #(some pos-int? %))
                         (map second)))]
            (let [ds (digits n)
                  d-cnt (count ds)
                  [first-half second-half] (split-at (quot d-cnt 2) ds)]
              (= (reduce + first-half)
                 (reduce + (if (odd? d-cnt)
                             (drop 1 second-half)
                             second-half)))))))

(comment
  )

(tests
 true := (__ 11)
 true := (__ 121)
 false := (__ 123)
 true := (__ 0)
 false := (__ 88099)
 true := (__ 89098)
 true := (__ 89089)
 (take 20 (filter __ (range))) :=
 [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/e4305e689ab891bd802f6292e01c1ad8