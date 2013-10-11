;; ## Let the FN begin!
;;
;; Basic building block of Clojure Program is a Function, and in this section we'll cover basic
;; function operations.
(ns defworkshop.simple-functions
  (:require [workshoplib.tdd :refer […]]))

;; ## Clojure basics
;;
;; First, let's figure out a couple of things about Clojure and explain how we're going to handle
;; some things here. Every new task will be started as a function in format of:
;;
;;     (defn task-name
;;        [...]
;;        <here you'll have to write your code>
;;        )
;;
;; We've added the `^:incomplete` hint in order to avoid noise in the unit tests, so that the functions
;; that you haven't yet implemented wouldn't pop out like errors when you're trying to verify currently
;; written ones.


;; Let's first check out how things are defined in Clojure. Following code block will be surrounded by `comment`,
;; which acts like a comment for any syntactically correct code block (symbols and constants aren't checked though,
;; only  proper amount of parantheses.

;;
;; First, let's go through the language primitives (once again).
;;
;; Here's what the string looks like:
;;
;;     "this is a string"
;;
;; Here's an integer:
;;
;;      1
;;
;; Here's a vector
;;
;;      [1 2 3]
;;
;; You can also write it as
;;
;;      [1, 2, 3]
;;
;; if you feel like writing all these commas, they're optional
;;
;; Here's a hash:
;;
;;      {:key "value" :key2 "value2"}
;;
;; This is a keyword:
;;
;;     :key
;;
;; This is a symbol:
;;
;;     'key
;;
;; What you would generally write as 1 + 1 in Clojure is represented like
;;
;;     (+ 1 1)
;;
;; this is called Prefix Notation, it means that operator or function call
;; is always on the first place.
;;
;; In order to define a "variable" in Clojure, you use `def`:
;;
;;     (def a 1)
;;
;; defines `a` as `1`
;;
;; In order to define a function, you should use `defn`:
;;
;;     (defn decrement
;;      [number]
;;      (- number 1))
;;
;; If you want to write a so-called "anonymous function", you can use `def` and `fn`
;; definition, `defn` is mostly a shortcut for combining these two:
;;
;;      (def (fn [number] (- number 1)))
;;
;; If you want to get documentation for certain funciton, you can run:
;;
;;      (clojure.repl/doc defn)
;;
;; in your repl, this will give you infromatione on any function. Since we're going to work
;; with many new functions, this will be particularly useful for you.
;;
;; In case you want to refer to the source of function or macro, you can always use:
;;
;;      (clojure.repl/source defn)
;;
;; Although since most of the Clojure standard library is not as simple as it seems to be, this
;; function is mostly useful for cases when you're working with user libraries
;;
;; In order to suppress evalutation, you can use `quote`:
;;
;;      (quote (println "Hey there!"))
;;
;; You can use `eval` to execute it further down the line:
;;
;;      (eval (quote (println "Hey there!")))
;;
;; And your favorite debugger tool:
;;
;;      (println 123)
;;
;; prints a line with `123`, you can pass arbitrary amount of arguments to `println`
;;
;; ## Simple Functions
;;

(defn ^:not-implemented double-number
  "Write a function that returns a doubled number

      f(x) = x + x;"
  [x]
  (…))

(comment
  (= 4 (double-number 2)))

(defn ^:not-implemented square
  "Write a function returns a square of a number"
  [x]
  (…))

(comment
  (= 16 (square 4)))

(defn ^:not-implemented cube
  "Write a function that returns a cube of a number"
  [x]
  (…))

(comment
  (= 64 (cube 4)))

(defn ^:not-implemented sum-of-numbers
  "Write a multi-arity function for `sum-of-numbers`, for 2, 3 and 4 arguments"
  ([a b]
     (…)))

(comment
  (= 64 (cube 4)))

(defn ^:not-implemented sum-of-numbers-in-vector
  "You can destructure arguments from vector by showing the shape of vector,
   for example vector `[1 2]` destructured to `[a b]` will assign `1` to `a` and `2` to `b`:

        [1 2]
        [a b]"
  [_]
  (…))

(comment
  (+ 7 (sum-of-numbers-in-vector [3 4])))

(defn ^:not-implemented get-second
  "You can use & to indicate that the `rest` of elements should go to the argument,
   for example, destructuring `[1 2 3 4]` to `[a b & c]` will assign `1` to `a`, `2` to `b` and
   `(3, 4)` to `c`:

        [1  2    3 4]
        [a  b  &  c ]
      "
  [_]
  (…))

(comment
  (= 2 (get-second [1 2 3])))
(defn ^:not-implemented first-first
  "You can deep-destructure things, so `[[1 2] 3]` can be represented as `[[a b] c]`, and
   `a` will hold `1`, `b` will hold `2`, `c` will hold `3`.

        [[1 2] 3]
        [[a b] c]"
  [_]
  (…))

(comment
  (= 1 (first-first [[1 2] 2])))

(defn ^:not-implemented get-from-map-as-function
  "Maps can also be called as functions, for example, `({:a :b} :a)` will return `:b`"
  [m key]
  (…))

(comment
  (= :b (get-from-map-as-function {:a :b} :a)))

(defn ^:not-implemented get-from-map-key-as-function
  "Keys can also be called as function, for example, `(:a {:a :b})` will also return `b`"
  [m key]
  (…))

(comment
  (= :b (get-from-map-key-as-function :a {:a :b})))

(defn ^:not-implemented call-twice
  "Write a function that receives another function (`f`) as argument and calls it with
   given `parameter`, twice"
  [f parameter]
  (…))

(comment
  (call-twice (fn [i] (println i)))
  (call-twice println))

(defn ^:not-implemented composition-string-plus
  "In Clojure, people don't compose objects that much, they mostly compose functions.
   For example, if you want to obtain a sum of numbers and then convert it to string, you
   can use `comp` to compose a new function out of `+` and `str` functions. Try it:"
  [a1 a2]
  (…))

(comment
  (= "4" (composition-string-plus 2 2)))

(defn ^:not-implemented max-from-sequence
  "Sometimes you get all the arguments as a sequence, but still want to make a call to function
   in a \"normal\" way, for example, having a vector of thee numbers: `[1 2 3]` you want to find
   largest one of them, and calling

        (max [1 2 3])

   won't help you, since `max` wants to receive flat arguments, like that:

        (max 1 2 3).

   In this case, you want to use `apply` function that receives a function and vector of arguments,
   and applies them to function."
  [seq]
  (…))

(comment
  (= 5 (max-from-sequence [4 3 2 5 1 3 2])))

(defn ^:not-implemented read-file-contents
  "In order to read contents of the file, you can use `slurp` helper function. Now, write a function
   that reads file contents, prints them out and then returns them.

   You can use `let` to capture contents of file into variable and `println` to print it out.

   For testing purposes, you can use a file that contains numbers from 1 to 5 each one on the new line,
   it's located under \"resources/numbers\""
  [filename]
  (…))

(comment
  (read-file-contents "resources/numbers"))

(def ^:not-implemented ten-times
  "In functional programming, you will often hear about `partial application`. This means that you're taking
   a function, wrapping it into the closure and returning a new, anonymous function that will call the first
   function with given argument. For example,

     (def add-two (partial + 2))

   Will generate a function that, when called with `argument`, will execute (+ 2 argument)

   In the same spirit, let's write `ten-times` function that will return 50 for 5, and 20 for 2."
  …)

(comment
  (= 20 (ten-times 2)))

(defn ^:not-implemented my-partial
  "Now, let's try writing our own partial function replacement, that will take a function and a parameter, and
   return another, anonymous function that will make a call with enclosed value"
  [f param]
  (…))

(comment
  (= 4 ((my-partial + 2) 2)))

;;
;; ## Anonymous function
;;

(def ^:not-implemented cube-anonymous …)

(comment
  (= 8 (cube-anonymous 2)))
