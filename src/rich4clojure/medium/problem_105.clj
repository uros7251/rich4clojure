(ns rich4clojure.medium.problem-105
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Identify keys and values =
;; By 4Clojure user: amalloy
;; Difficulty: Medium
;; Tags: [maps seqs]
;; 
;; Given an input sequence of keywords and numbers, create
;; a map such that each key in the map is a keyword, and
;; the value is a sequence of all the numbers (if any)
;; between it and the next keyword in the sequence.

(def __ (fn [s]
            (loop [k nil v [] m {} s' s]
              (if-let [[f & r] s']
                (if (keyword? f)
                  (recur f [] (assoc m k v) r)
                  (recur k (conj v f) m r))
                (dissoc (if k
                          (assoc m k v)
                          m)
                        nil)))))

(comment
  (defn my-key-group [s]
    (loop [s' s
           acc {}]
      (if-let [[head & tail] (seq s')]
        (let [[vals others] (split-with (complement keyword?) tail)]
          (recur others (assoc acc head (vec vals))))
        acc
        )))
  (my-key-group [])
  )

(tests
  {} := (__ [])
  {:a [1]} := (__ [:a 1])
  {:a [1], :b [2]} := (__ [:a 1, :b 2])
  {:a [1 2 3], :b [], :c [4]} := (__ [:a 1 2 3 :b :c 4]))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/da9a4c4197dc581cb9635fe8358bc62d