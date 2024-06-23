(ns rich4clojure.easy.problem-020
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Penultimate Element =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [seqs]
;; 
;; Write a function which returns the second to last
;; element from a sequence.

(def __ (fn
          [coll]
          (nth coll (- (count coll) 2))))

(comment
  (defn second-last [coll]
    (second (into () coll)))
  (second-last (list 1 2 3 4 5))
  )

(tests
  (__ (list 1 2 3 4 5)) := 4
  (__ ["a" "b" "c"]) := "b"
  (__ [[1 2] [3 4]]) := [1 2])

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/bb564e188dc4d73aa37b714b64003dfe