(ns rich4clojure.medium.problem-075
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Euler's Totient Function =
;; By 4Clojure user: dbyrne
;; Difficulty: Medium
;; 
;; Two numbers are coprime if their greatest common
;; divisor equals 1. Euler's totient function f(x) is
;; defined as the number of positive integers less than x
;; which are coprime to x. The special case f(1) equals 1.
;; Write a function which calculates Euler's totient
;; function.

(def __ (fn [n]
          (letfn [(gcd [a b]
                    (if (zero? b)
                      a
                      (recur b (rem a b))))]
            (->> (range 1 (inc n))
                 (filter #(= (gcd n %) 1))
                 (count)))))

(comment
  (defn factorize [n]
    (loop [factorization {} p 2 n' n]
      (cond (= n' 1) factorization
            (zero? (rem n' p)) (recur (update factorization p
                                              #(if (nil? %)
                                                 1
                                                 (inc %))
                                              )
                                      p
                                      (quot n' p))
            :else (recur factorization (inc p) n'))))
  (defn phi [n]
    (->> (factorize n)
         (map (comp #(- 1 (/ %)) key))
         (reduce *)
         (* n)))
  
  (factorize 1254)
  (phi 99)
  )
(tests
 (__ 1) := 1
 (__ 10) := (count '(1 3 7 9)) 4
 (__ 40) := 16
 (__ 99) := 60)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/da5e5c50f14f015708f967e20b450874