(ns rich4clojure.easy.problem-096
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Beauty is Symmetry =
;; By 4Clojure user: dbyrne
;; Difficulty: Easy
;; Tags: [trees]
;; 
;; Let us define a binary tree as "symmetric" if the left
;; half of the tree is the mirror image of the right half
;; of the tree. Write a predicate to determine whether or
;; not a given binary tree is symmetric. (see To Tree, or
;; not to Tree for a reminder on the tree representation
;; we're using).

(def __ (fn [t]
          (if (nil? t) true
              ((fn sym [t u]
                 (if (and (nil? t) (nil? u))
                   true
                   (and (= (first t) (first u))
                        (sym (nth t 1) (nth u 2))
                        (sym (nth t 2) (nth u 1)))))
               (nth t 1)
               (nth t 2)
               ))))

(comment
  )

(tests
  (__ '(:a (:b nil nil) (:b nil nil))) := true
  (__ '(:a (:b nil nil) nil)) := false
  (__ '(:a (:b nil nil) (:c nil nil))) := false
  (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]]) :=
   true
  (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]]) :=
   false
  (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] nil]] nil]]) :=
   false)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/74f30ddf0d4ce94f886ccf412d51a099