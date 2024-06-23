(ns rich4clojure.medium.problem-108
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Lazy Searching =
;; By 4Clojure user: amalloy
;; Difficulty: Medium
;; Tags: [seqs sorting]
;; 
;; Given any number of sequences, each sorted from
;; smallest to largest, find the smallest single number
;; which appears in all of the sequences. The sequences
;; may be infinite, so be careful to search lazily.

(def __ (fn [& ss]
          (when (seq ss)
            (let [vs (into [] ss) n (count vs)]
              (loop [i 1 curr (ffirst vs) cnt 1 vs' (assoc vs 0 (rest (first vs)))]
                (if (= cnt n)
                  curr
                  (let [v (nth vs' i) v-head (first v) vs-new (assoc vs' i (rest v))]
                    (cond (< v-head curr) (recur i curr cnt vs-new)
                          (> v-head curr) (recur (rem (inc i) n) v-head 1 vs-new)
                          :else (recur (rem (inc i) n) curr (inc cnt) vs-new)))))))))

(comment
  )

(tests
 3 := (__ [3 4 5])
 4 := (__ [1 2 3 4 5 6 7] [0.5 3/2 4 19])
 7 := (__ (range) (range 0 100 7/6) [2 3 5 7 11 13])
 64 := (__ (map #(* % % %) (range)) ;; perfect cubes
           (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
           (iterate inc 20)))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/4a0485cfb64ebc165afeffc2406b5669