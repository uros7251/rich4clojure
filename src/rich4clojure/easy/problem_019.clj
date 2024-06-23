(ns rich4clojure.easy.problem-019
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Last Element =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [seqs core-functions]
;; 
;; Write a function which returns the last element in a
;; sequence.

(def restricted [last])

(def __ (fn 
          [s]
          (if-let [s' (next s)]
            (recur s')
            (first s))))

(comment
  (defn __ [s] 
    (nth s (- (count s) 1)))
    
    (__ [1 2 3 4 5]) ;=> 5
  :rcf)

(tests
 (__ [1 2 3 4 5]) := 5
 (__ '(5 4 3)) := 3
 (__ ["b" "c" "d"]) := "d")

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/374c499f3dad0203503b7dae16bf86f4