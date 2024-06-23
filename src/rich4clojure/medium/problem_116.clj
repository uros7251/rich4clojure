(ns rich4clojure.medium.problem-116
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Prime Sandwich =
;; By 4Clojure user: amcnamara
;; Difficulty: Medium
;; Tags: [math]
;; 
;; A balanced prime is a prime number which is also the
;; mean of the primes directly before and after it in the
;; sequence of valid primes. Create a function which takes
;; an integer n, and returns true iff it is a balanced
;; prime.

(def __ (fn [n]
          (letfn [(is-prime [n]
                   (if (< n 2)
                     false
                     (->> (range 2 (inc (int (Math/sqrt n))))
                          (some #(= (rem n %) 0))
                          (not))))]
            (and (> n 2) (is-prime n)
                 (let [prev-prime (->> (dec n)
                                       (iterate dec)
                                       (filter is-prime)
                                       (first))
                       next-prime (->> (inc n)
                                       (iterate inc)
                                       (filter is-prime)
                                       (first))]
                   (= n (/ (+ prev-prime next-prime) 2)))))))

(comment
  )

(comment
  ;; = nth - Example 1 = 
  
  ; Note that nth uses zero-based indexing, so that
  ;   (first my-seq) <=> (nth my-seq 0)
  (def my-seq ["a" "b" "c" "d"])
  (nth my-seq 0)
  ; => "a"
  (nth my-seq 1)
  ; => "b"
  (nth [] 0)
  ; => IndexOutOfBoundsException ...
  (nth [] 0 "nothing found")
  ; => "nothing found"
  (nth [0 1 2] 77 1337)
  ; => 1337
  ;; See also:
  clojure.core/first
  clojure.core/second
  clojure.core/nthnext
  clojure.core/get
  clojure.core/take-nth
  clojure.core/nthrest
  clojure.core/rand-nth
  :rcf)



(comment
  ;; = filter - Example 1 = 
  
  (filter even? (range 10))
  ;;=> (0 2 4 6 8)
  
  (filter (fn [x]
            (= (count x) 1))
          ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])
  ;;=> ("a" "b" "n" "f" "q")
  
  (filter #(= (count %) 1)
          ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])
  ;;=> ("a" "b" "n" "f" "q")
  
  ; When coll is a map, pred is called with key/value pairs.
  (filter #(> (second %) 100)
          {:a 1
           :b 2
           :c 101
           :d 102
           :e -1})
  ;;=> ([:c 101] [:d 102])
  
  (into {} *1)
  ;;=> {:c 101, :d 102}
  
  ;; See also:
  clojure.core/remove
  clojure.core/keep
  clojure.core/filterv
  clojure.core/group-by
  :rcf)



(tests
 false := (__ 4)
 true := (__ 563)
 1103 := (nth (filter __ (range)) 15))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/286f071c259fb6861c10beb7411bde48