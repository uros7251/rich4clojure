(ns rich4clojure.medium.problem-070
  (:require [hyperfiddle.rcf :refer [tests]]
            [clojure.string]))

;; = Word Sorting =
;; By 4Clojure user: fotland
;; Difficulty: Medium
;; Tags: [sorting]
;; 
;; Write a function that splits a sentence up into a
;; sorted list of words. Capitalization should not affect
;; sort order and punctuation should be ignored.

(def __ (fn [s] 
          (sort-by clojure.string/lower-case
                   (re-seq #"\w+" s))))
(comment
  (re-seq #"\w+" "Have a")
  )

(tests
  (__  "Have a nice day.") :=
   ["a" "day" "Have" "nice"]
  (__  "Clojure is a fun language!") :=
   ["a" "Clojure" "fun" "is" "language"]
  (__  "Fools fall for foolish follies.") :=
   ["fall" "follies" "foolish" "Fools" "for"])

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/ede095348d09c012bb17854de2c26690