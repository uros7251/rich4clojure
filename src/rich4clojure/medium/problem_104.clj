(ns rich4clojure.medium.problem-104
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Write Roman Numerals =
;; By 4Clojure user: 0x89
;; Difficulty: Medium
;; Tags: [strings math]
;; 
;; This is the inverse of Problem 92, but much easier.
;; Given an integer smaller than 4000, return the
;; corresponding roman numeral in uppercase, adhering to
;; the subtractive principle .

(def __ (fn [n]
            (let [patterns [[:zero]
                            [:one]
                            [:one :one]
                            [:one :one :one]
                            [:one :five]
                            [:five]
                            [:five :one]
                            [:five :one :one]
                            [:five :one :one :one]
                            [:one :ten]]
                  to-letters [{:zero "" :one "I" :five "V" :ten "X"}
                              {:zero "" :one "X" :five "L" :ten "C"}
                              {:zero "" :one "C" :five "D" :ten "M"}
                              {:zero "" :one "M"}]
                  digits (fn [n]
                           (loop [n' n d []]
                             (if (zero? n')
                               d
                               (recur (quot n' 10) (conj d (rem n' 10))))))
                  ds (take 4 (digits n))]
              (->> ds
                   (map #(get patterns %))
                   (map (fn [n coll]
                          (let [dict (get to-letters n)]
                            (map #(% dict) coll))) (range 4))
                   ((comp flatten reverse))
                   (apply str)))))

(comment
  (__ 43545)
  )

(tests
  "I" := (__ 1)
  "XXX" := (__ 30)
  "IV" := (__ 4)
  "CXL" := (__ 140)
  "DCCCXXVII" := (__ 827)
  "MMMCMXCIX" := (__ 3999)
  "XLVIII" := (__ 48))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/99336a4cfdbb80b30d640f4b51134a42