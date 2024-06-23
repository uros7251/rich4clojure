(ns rich4clojure.easy.problem-045
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Intro to Iterate =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [seqs]
;; 
;; The iterate function can be used to produce an infinite
;; lazy sequence.

(def __ (loop [x 1 s [] n 5]
          (if (zero? n)
            s
            (recur (+ 3 x) (conj s x) (dec n)))))

(comment
  
  )

(tests
  __ := (take 5 (iterate #(+ 3 %) 1)))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/f1da413cb1110daf97898dbf73113894