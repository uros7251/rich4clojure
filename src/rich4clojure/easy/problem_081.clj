(ns rich4clojure.easy.problem-081
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Set Intersection =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [set-theory]
;; 
;; Write a function which returns the intersection of two
;; sets. The intersection is the sub-set of items that
;; each set has in common.

(def restricted ['interestion])

(def __ (fn [a b]
          (reduce #(if (a %2)
                     (conj %1 %2)
                     %1)
                  #{}
                  b)))

(comment
  
  )

(tests
  (__ #{0 1 2 3} #{2 3 4 5}) := #{2 3}
  (__ #{0 1 2} #{3 4 5}) := #{}
  (__ #{:a :b :c :d} #{:c :e :a :f :d}) := #{:a :c :d})

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/a51be488815f09d5d8e68e93d16f61e8