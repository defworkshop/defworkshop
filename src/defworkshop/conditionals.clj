;; # Conditionals
;;
;; Conditionals are control structures that make certain blocks of code executed or omitted from
;; execution under certain conditions. For exmaple, with `if` block, you only want the truthy block
;; to be evaluated, and for `when` you only want an entire expression to be evaluated only if
;; condition is true.
(ns defworkshop.conditionals
  (:require [workshoplib.tdd :refer […]]))

;;
;; To implement this task, you have to understand:
;;
;;    * what `if` control structure does
;;    * how to use `=` function for equality check
;;

(defn ^:not-implemented even-or-odd
  "Write a function that checks wether number is even or odd and returns `:even` if it's even and `odd`
   otherwise.

   You can use `mod` function to find modulo, `=` to check for equality and `if` conditional."
  [number]
  (…))

(comment
  (= :even (even-or-odd 2)))

;;
;; To implement this task, you have to understand:
;;
;;    * how to make a method call on the Java Object
;;    * `case` control structure
;;

(defn ^:not-implemented day-of-week
  "Using `case` contrcol structure and `.getDay` method of `java.util.Date` class to return which day
   of week certain date is. For example 12 October 2013 is \"Saturday\"."
  [^java.util.Date date]
  (…))

(comment
  (= "Friday"
     (day-of-week (java.util.Date. 1379066050639))))

(def days {1 "Monday"
           2 "Tuesday"
           3 "Wednesday"
           4 "Thursday"
           5 "Friday"
           6 "Saturday"
           7 "Sunday"})

;;
;; Quite often, `case` conditional can be replaced with a hash lookup.
;;
;; To implement this task, you have to understand:
;;
;;    * how hash-lookup using `get` works
;;

(defn ^:not-implemented day-of-week-with-hash-lookup
  "Having `days` hash, write a function that finds a particular day by given number (1-7)."
  [date]
  (…))

;;
;; To implement this task, you have to understand:
;;
;;    * how nubmer comparisons `<` and `>` work
;;    * how `cond` works
;;    * that only `false` and `nil` are considered falsy in Clojure, anything besides them can
;;      account for truthy expression
;;
(defn ^:not-implemented pos-neg-or-zero
  "Using `cond` (or nested ifs), write a function that checks wether number is `:positive`, `:negative`
   or `:zero`, and returns corresponding symbol."
  [n]
  (…))

(defn ^:not-implemented number-to-grade
  "Given US grading system, convert a given number `n` (0-100) to grade:

   * \"A\" is 90-100,
   * \"B\" is 80-89
   * \"C\" is 70-79
   * \"D\" is 60-69
   * \"F\" is anything under 60"
  [n]
  (…))

;;
;; To implement this task, you have to understand:
;;
;;    * how to use hash lookup using `get`
;;    * `if-let` control structure
;;

(defn ^:not-implemented get-or-default
  "Write a function that would retrieve a key from map or return a default value, without using defaults in get, but with if-let"
  [map key default]
  (…))


;; You can use `number?` function to check something is a number or no
;;
;;     (number? 1)
;;     ;; => true
;;
;;     (number? "1")
;;     ;; => false
;;
;; In order to check wether something is sequential or no, you can use `sequential?`:
;;
;;     (sequential? [1 2 3])
;;     ;; => true
;;
;;     (sequential? 1)
;;     ;; => false
;;
;; Same with strings:
;;
;;     (string? 1)
;;     ;; => true
;;
;;     (string? "1")
;;     ;; => false
;;
;; Same with maps:
;;
;;     (map? {})
;;     ;; => true
;;
;;     (map? [])
;;     ;; => false
;;
;; And vectors:
;;
;;     (vector? [])
;;     ;; => true
;;     (vector? {})
;;     ;; => false
;;
;; And lists:
;;
;;     (list '())
;;     ;; => true
;;     (list? [])
;;     ;; => false

;;
;; To implement this task, you have to understand:
;;
;;    * how `cond` operator works
;;    * `number?` / `string?` etc predicates / helper functions
;;

(defn ^:not-implemented describe
  "Using the above-mentioned predicates, write a `describe` function that figures out wether the given item
   is.
   If it's a number, return `:number`,
             string         `:string`,
             list           `:list`,
             vector         `:vector`,
             map            `:map`,
   Otherwise, return `:unknown`."
  [item]
  (…))

(defn ^:not-implemented not-a-number?
  "Write a funciton that returns true if given item is not a number, and false otherwise.

   To implement is, try `not` out."
  [item]
  (…))

;; Now, try doing same thing by using `complement` function
(def ^:not-implemented not-a-number-complement …)
