(ns rich4clojure.medium.problem-077
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Anagram Finder =
;; By 4Clojure user: dbyrne
;; Difficulty: Medium
;; 
;; Write a function which finds all the anagrams in a
;; vector of words. A word x is an anagram of word y if
;; all the letters in x can be rearranged in a different
;; order to form y. Your function should return a set of
;; sets, where each sub-set is a group of words which are
;; anagrams of each other. Each sub-set should have at
;; least two words. Words without any anagrams should not
;; be included in the result.

(def __ (fn [words]
          (->> words
               (group-by frequencies)
               (remove (fn [[_ v]] (= 1 (count v))))
               (map (fn [[_ v]] (into #{} v)))
               (into #{}))))


(comment
)

(tests
  (__ ["meat" "mat" "team" "mate" "eat"]) :=
   #{#{"meat" "team" "mate"}}
  (__ ["veer" "lake" "item" "kale" "mite" "ever"]) :=
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/0ffdaf0cd713a4df991004e0fccf19ac