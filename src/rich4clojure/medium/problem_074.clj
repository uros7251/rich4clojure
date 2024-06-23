(ns rich4clojure.medium.problem-074
  (:require [hyperfiddle.rcf :refer [tests]]
            [clojure.string :as str]))

;; = Filter Perfect Squares =
;; By 4Clojure user: dbyrne
;; Difficulty: Medium
;; 
;; Given a string of comma separated integers, write a
;; function which returns a new comma separated string
;; that only contains the numbers which are perfect
;; squares.

(def __ (fn [s]
          (letfn [(square? [x]
                    (let [sqrt-x (Math/sqrt x)]
                      (= (Math/floor sqrt-x) sqrt-x)))]
            (->> (str/split s #",")
                 (map parse-long)
                 (filter square?)
                 (map str)
                 (str/join ","))
            )))

(comment
  
  )

(tests
  (__ "4,5,6,7,8,9") := "4,9"
  (__ "15,16,25,36,37") := "16,25,36")

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/b90fcac09b35c74a07228dea603ddc73