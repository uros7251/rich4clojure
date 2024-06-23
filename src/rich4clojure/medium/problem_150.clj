(ns rich4clojure.medium.problem-150
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Palindromic Numbers =
;; By 4Clojure user: maximental
;; Difficulty: Medium
;; Tags: [seqs math]
;; 
;; A palindromic number is a number that is the same when
;; written forwards or backwards (e.g., 3, 99, 14341).
;; 
;; 
;; Write a function which takes an integer n, as its only
;; argument, and returns an increasing lazy sequence of
;; all palindromic numbers that are not less than n.
;; 
;; 
;; The most simple solution will exceed the time limit!

(def __ (fn [n]
          (letfn [(pow [x n]
                    (reduce * 1 (repeat n x)))
                  (digits [n] ;; returns list of digits of n starting with most significant digit
                    (if (zero? n)
                      '(0)
                      (loop [n' n ds ()]
                        (if (zero? n')
                          ds
                          (recur (quot n' 10) (conj ds (rem n' 10)))))))
                  (from-digits [ds]
                    (loop [[h & t] ds n 0]
                      (if h
                        (recur t (+ h (* n 10)))
                        n)))
                  (halve [n] ;; halves the number into two parts and returns them alongside number of digits: for example, (halve 432) = [43 2 3].
                    (let [ds (digits n)
                          cnt (count ds)
                          [h1 h2] (split-at (quot (inc cnt) 2) ds)]
                      [(from-digits h1) (from-digits (reverse h2)) cnt]))
                  (gen-palindrom [first-half cnt] ;; generates palindrom from first part of the number and the resulting number of digits: for example, (gen-palindrom 43 3) = 434
                    (loop [n (if (odd? cnt)
                               (quot first-half 10)
                               first-half)
                           p first-half
                           m (quot cnt 2)]
                      (if (zero? m)
                        p
                        (recur (quot n 10)
                               (+ (rem n 10) (* p 10))
                               (dec m)))))
                  (inc-first-half [first-half cnt] ;; increments palindrom seed (note careful overflow handling)
                    (let [n (inc first-half)
                          next-pow-of-ten (pow 10 (quot (inc cnt) 2))]
                      (if (= n next-pow-of-ten)
                        [(if (odd? cnt)
                           (quot n 10)
                           n) (inc cnt)]
                        [n cnt])))
                  (lazy-palindrome [first-half cnt]
                    (lazy-seq
                     (cons (gen-palindrom first-half cnt)
                           (apply lazy-palindrome (inc-first-half first-half cnt)))))]
            (let [[first-half second-half cnt] (halve n)
                  x (if (odd? cnt) (quot first-half 10) first-half)]
              (lazy-palindrome (if (< x second-half)
                                 (inc first-half) ; this doesn't risk overflow mishandling because that happens only when x are all 9s, but then x can't be smaller than second half 
                                 first-half)
                               cnt)))))

(comment)

(tests
 (take 26 (__ 0)) :=
 [0 1 2 3 4 5 6 7 8 9
  11 22 33 44 55 66 77 88 99
  101 111 121 131 141 151 161]
 (take 16 (__ 162)) :=
 [171 181 191 202
  212 222 232 242
  252 262 272 282
  292 303 313 323]
 (take 6 (__ 1234550000)) :=
 [1234554321 1234664321 1234774321
  1234884321 1234994321 1235005321]
 (first (__ (* 111111111 111111111))) :=
 (* 111111111 111111111)
 (set (take 199 (__ 0))) :=
 (set (map #(first (__ %)) (range 0 10000)))
 true :=
 (apply < (take 6666 (__ 9999999)))
 (nth (__ 0) 10101) :=
 9102019)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/a8b9c97438851e16448c3133cfbb2c29