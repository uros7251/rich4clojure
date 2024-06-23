(ns rich4clojure.easy.problem-128
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Recognize Playing Cards =
;; By 4Clojure user: amalloy
;; Difficulty: Easy
;; Tags: [strings game]
;; 
;; A standard American deck of playing cards has four
;; suits - spades, hearts, diamonds, and clubs - and
;; thirteen cards in each suit. Two is the lowest rank,
;; followed by other integers up to ten; then the jack,
;; queen, king, and ace.
;; 
;; 
;; It's convenient for humans to represent these cards as
;; suit/rank pairs, such as H5 or DQ: the heart five and
;; diamond queen respectively. But these forms are not
;; convenient for programmers, so to write a card game you
;; need some way to parse an input string into meaningful
;; components. For purposes of determining rank, we will
;; define the cards to be valued from 0 (the two) to 12
;; (the ace)
;; 
;; 
;; Write a function which converts (for example) the
;; string "SJ" into a map of {:suit :spade, :rank 9}. A
;; ten will always be represented with the single
;; character "T", rather than the two characters "10".

(defn suit [s]
  (cond (= s \S) :spade
        (= s \H) :heart
        (= s \D) :diamond
        (= s \C) :club
        :else :invalid))

(defn rank [r]
  (let [ascii (- (int r) (int \2))]
    (cond (and (>= ascii 0) (<= ascii 7)) ascii
          (= r \T) 8
          (= r \J) 9
          (= r \Q) 10
          (= r \K) 11
          (= r \A) 12
          :else -1)))

(def __ (fn [card]
          {:suit (suit (first card)) :rank (rank (second card))}))

(comment
  (defn my-card-conv [card]
    (let [suits (into {} (map hash-map "SHDC" [:spade :heart :diamond :club]))
          ranks (into {} (map hash-map "23456789TJQKA" (range 13)))
          s (first card)
          r (second card)]
      {:suit (suits s) :rank (ranks r)})
  )
  
  (map my-card-conv ["DQ" "H5" "CA" "S9"])
  )

(tests
  {:suit :diamond :rank 10} := (__ "DQ")
  {:suit :heart :rank 3} := (__ "H5")
  {:suit :club :rank 12} := (__ "CA")
  (range 13) := (map (comp :rank __ str)
                   '[S2 S3 S4 S5 S6 S7
                     S8 S9 ST SJ SQ SK SA]))

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/462630c19a4463c7a67c4af684c9f71e