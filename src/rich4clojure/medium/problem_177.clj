(ns rich4clojure.medium.problem-177
  (:require [hyperfiddle.rcf :refer [tests]]))

;; = Balancing Brackets =
;; By 4Clojure user: daowen
;; Difficulty: Medium
;; Tags: [parsing]
;; 
;; When parsing a snippet of code it's often a good idea
;; to do a sanity check to see if all the brackets match
;; up. Write a function that takes in a string and returns
;; truthy if all square [] round () and curly {} brackets
;; are properly paired and legally nested, or returns
;; falsey otherwise.

(def __ (fn [s]
            (loop [[h & t] (filter (set "[](){}") s) stack []]
              (if h
                (if (some (partial = (str (peek stack) h))
                          ["[]" "()" "{}"])
                  (recur t (pop stack))
                  (recur t (conj stack h)))
                (empty? stack)))))

(comment
  (defn sanity-check [s]
    (loop [[h & t] (filter (set "[](){}") s) stack []]
      (if h
        (if (some (partial = (str (peek stack) h))
                  ["[]" "()" "{}"])
          (recur t (pop stack))
          (recur t (conj stack h)))
        (empty? stack))))
  (sanity-check "class Test {
                      public static void main(String[] args) {
                        System.out.println(\"Hello world.\");
                      }
                    }")
  )

(tests
 (__ "This string has no brackets.") :=
 (__ "class Test {
      public static void main(String[] args) {
        System.out.println(\"Hello world.\");
      }
    }") :=
 (__ "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))") := true
 (__ "(start, end]") :=
 (__ "())") :=
 (__ "[ { ] } ") :=
 (__ "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))") :=
 (__ "[") :=)

;; Share your solution, and/or check how others did it:
;; https://gist.github.com/6b8d50ee0811042bdc646dc9060037e8