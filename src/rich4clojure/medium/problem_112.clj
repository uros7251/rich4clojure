(ns rich4clojure.medium.problem-112
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Sequs Horribilis =
;; By 4Clojure user: ghoseb
;; Difficulty: Medium
;; Tags: [seqs]
;; 
;; Create a function which takes an integer and a nested
;; collection of integers as arguments. Analyze the
;; elements of the input collection and return a sequence
;; which maintains the nested structure, and which
;; includes all elements starting from the head whose sum
;; is less than or equal to the input integer.

(def __ (fn
          [b s]
          (letfn [(take-while-le
                    [b s]
                    (loop [b' b
                           s' s
                           r []]
                      (if-let [[h & t] (seq s')]
                        (if (seqable? h)
                          (let [[new-b' r'] (take-while-le b' h)]
                            (recur new-b' t (conj r r')))
                          (if (>= b' h)
                            (recur (- b' h) t (conj r h))
                            [b' r]))
                        [b' r])))]
            (second (take-while-le b s)))))

(comment
  (defn lazy-take-while-le
    ([b s] (peek (lazy-take-while-le b s [])))
    ([b s r]
     (->> [b s r]
          (iterate (fn
                     [[b s r]]
                     (when-let [[h & t] (seq s)]
                       (if (seqable? h)
                         (let [[b' _ r'] (lazy-take-while-le b h [])]
                           [b' t (if (empty? r')
                                   r
                                   (conj r r'))])
                         [(- b h) t (if (>= b h)
                                      (conj r h)
                                      r)]))))
          (take-while (fn [[b :as t]]
                        ;(println t) ;; comment out to remove execution trace
                        (and t (>= b 0))))
          (last))))
  (lazy-take-while-le 30 [1 2 [3] 4])
  )

(tests
 (__ 10 [1 2 [3 [4 5] 6] 7]) :=
 '(1 2 (3 (4)))
 (__ 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11]) :=
 '(1 2 (3 (4 (5 (6 (7))))))
 (__ 9 (range)) :=
 '(0 1 2 3)
 (__ 1 [[[[[1]]]]]) :=
 '(((((1)))))
 (__ 0 [1 2 [3 [4 5] 6] 7]) :=
 '()
 (__ 0 [0 0 [0 [0]]]) :=
 '(0 0 (0 (0)))
 (__ 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]]) :=
 '(-10 (1 (2 3 (4)))))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/dfcf8ebb019b5ab679b66d1f812edddf