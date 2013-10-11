;; ## The one who flew the cuckoos nest: Let over Lambda
;;
;; (or how I stopped worrying and loved the scope)
;;
;; (or just LOL)
;;
;; This time around we will look at passing functions. "Passing functions as
;; arguments", you say, "didn't we do that all the time already?" And you'd be
;; right, just this time we'll go deeper. Have you seen Inception? The dream in
;; a dream in a dream? We'll do it with functions.
;;
;; But first, you need to know what a scope is. A functions scope is basically
;; everything a function can see, every binding name it can access. If you
;; write `(fn [] a)` and `a` resolves to something instead of crashing, it
;; means it is in scope.
;;
;; There are two kinds of scopes:
;;
;; * lexical scopes: your function sees what the programmer sees while writing
;;   the function. Your language doez dis!
;; * dynamic scope: your function sees what's in your call stack. Elisp doez
;;   dis. Elisp is also kinda weird like that. Clojure can do dynamic scoping
;;   too; sometimes it's useful.
;;
;; We'll only worry about lexical scope here, if you want to know about dynamic
;; scope, grab an instructor and ask him :-)
(ns defworkshop.let-over-lambda
  (:require [workshoplib.tdd :refer […]]))

;; ### A function in a function
;;
;; To avoid confusion, we'll start with a simple function that returns a
;; function. A FunctionFactory if you like!

(defn ^:not-implemented fn-in-fn
  "Write a function that returns a function that returns 42"
  []
  (…))

;; Now call it and get 42:

(comment
  (let [res ((fn-in-fn))]
    (str "fn-in-fn results in " res)))

;; So, write a function that takes a fn-in-fn as argument and returns it's
;; value. Sorry if this is getting repetitive, but it's better if you get the
;; exercise and avoid the confusion now than later.

(defn ^:not-implemented apply-apply
  "Write a function that takes a function and calls the function, and then
  calls the resulting function"
  [f]
  (…))

;; Having this, it should work:

(comment
  (apply-apply fn-in-fn))

;; ### Moving values into scope
;;
;; Previously, fn-in-fn just returned 42, which was of course it its scope. Now
;; what happens if you move 42 up one level? Let's put it in the outer
;; functions scope.

(defn ^:not-implemented scoped-fn-in-fn
  "Write a function that `let`s `42` to `fourtytwo` and returns a function
  which returns `fourtytwo`"
  []
  (…))

;; So, `let` opens a scope, and the `fn` is in a scope. See if it makes a
;; difference:

(comment
  (apply-apply scoped-fn-in-fn))

;; If you got 42 as result, you did everything right.

;; ### Now for something useful!
;;
;; We saw how the scoping works, as every function can see all the variables
;; that are defined "around" it. Now, how is this useful? A very common
;; approach is to take an existing function, and preconfigure it.
;;

(defn ^:not-implemented multiply-by
  [n]
  …)

;; So, we could build a function that just increments things:

(def increment …)

(comment
  (increment 1)
  (increment 42))

;; How is this useful? It might be useful, consider some examples

(comment
  (map increment [1 2 3 4 5 6 7])
  (take 10 (iterate increment 42))
  (take 10 (iterate (multiply-by 23) 42)))

;; You would agree that this is more concise than writing a whole new function
;; everytime you want to specialize something, I hope. Notice, I'm not saying
;; that there is never a place for anonymous functions, just sometimes it might
;; be cleaner, especially if the function is needed more than once in your
;; code.
;;
;; This increment function by the way seems to be useful enough for Clojure to
;; provide a built-in function that does just increment: `inc`.
;;
;; ### Wrapping private data
;;
;; With the scoping mechanism we can also create private data, since the values
;; bound in a function are private unless they are returned in some way. So we
;; can wrap some data in a function and return accessor functions for this
;; data. A simple, classic example is a counter.
;;
;; A small tip, if you don't know about refs, you can use (dosync (alter ref
;; function)) to do the equivalent of ref = function(ref) from other languages.
;; More information on changing values in the recursion chapter.

(defn ^:not-implemented make-counter
  "Returns a function that increments the internal state everytime it is
  called"
  []
  (…))

; some code to try what we built
(def ^:not-implemented my-counter …)

(comment
  (my-counter))

;; As you can see, count is never exported, so it cannot be messed with, while
;; it can still be incremented and accessed using the accessor function that
;; was returned. And we saw `inc` in action. If you think this was easy peasy,
;; feel free to extend `make-counter` by adding maybe an starting value, or
;; returning the value of the counter before incrementing or whatever you
;; fancy.
;;
;; ### Bonus exercise: Let over Lambda in ML
;;
;; In languages like OCaml, every function has *exactly* one argument. If a
;; function takes more than one argument, it returns a function that has the
;; first argument in scope and one argument less. If there are zero arguments
;; left, it will get executed.
;;
;; Think of how to write a function that does this in Clojure and how to call
;; such a function. Implement `assoc` in this way: `assoc` takes a hashmap as
;; first argument, a `:key` as second argument and a value as third.

(defn ^:not-implemented curried-assoc
  "Write a version of assoc that consists of functions returning functions.
  You may call assoc inside the function."
  [h]
  (…))

(comment
  (((curried-assoc {}) :language) "Clojure"))
