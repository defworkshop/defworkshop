(ns defworkshop.macros
  (:require [workshoplib.tdd :refer […]]))

;;
;; Macros are often used as a tool for changing execution order, modifying source code in runtime
;; and for building so called "Control Structures". Another reason why macros are useful is the way
;; you can transform code to fit it into the problem domain. In other words, for building DSLs.
;;
;; Macros have four concepts that "usual" code doesn't (or at least same concept has different application
;; when written in macros).
;;
;;   * Quote (`'`)
;;   * Syntax quote (`)
;;   * Unquote (`~`)
;;   * Unquote splicing (`~@`)
;;
;; `Quote` supresses evaluation of the form that follows it. In other words, instead of being treated
;; as an invocation, it will be treated as a list.
;;
;; `Unquote` then is how parts of the template are forced to be evaluated (act similarly to variables in
;; templates in templating languages).
;;
;; The unquote-splicing operator (`~@`) is a convenient way to concatenate several forms into a single one.
;;

;; Call is a simplest macro you can implement. It takes just a symbol (function name) and set of params
;; and generates a call to the function.
;;
;; ## Code Generation
;;
(defmacro ^:not-implemented call
  "As simple as macro can get.
   Write a macro that receives a symbol `sym` and param `x`, and turns it to the function call."
  [sym & params]
  ;; …
  )

;; When is a simple control structure you can implement in terms of macro. It utilizes bootstrapped `if`
;; internally and makes it possible to write multi-line expressions.
;;
;; ## Control Structures
;;

(defmacro ^:not-implemented my-when
  "Think of `when` in terms of macro for a second. In essence, what we need to do is to write a
   macro that would return us (if (test) (do <body>)). There're several ways of implementing it, check them out."
  [test & body]
  ;; …
  )

;; Unless macro simply negates `if` conditions

(defmacro ^:not-implemented unless
  "Similar to if but negates the condition"
  [condition & forms]
  ;; …
  )

;;
;; ## Context-depending evaluation
;;
;; You can also use macros for writing little debugging helpers
;;


;; Dynamic bindings are used together with `binding` blocks, where you specify temporary value
;; for some variable
(def ^:dynamic *DEBUG*)

(defmacro ^:not-implemented with-debug
  "Write a macro that will set *DEBUG* to true, by using `binding`, and splice-unqote the body, so that body would
   be executed in the context of binding."
  [& body]
  ;; …
  )

(defn ^:not-implemented debug
  [& all]
  ;; …
  )

;;
;; ## DSL / Code Simplification
;;
;; Threading eases chained function calls. So if you want to increment an integer and then convert result
;; to the string, you can simply do
;;
;;     (my-threading 1 inc str)
;;

(defmacro ^:not-implemented my-threading
  "Threading macro is quite simple: it takes first argument, and makes following substitution:

     (my-threading 1 inc str)

     (str (inc 1))

   And when you pass a list (like a function call, for example):

     (my-threading 1 (+ 2) str)

     (str (+ 1 2))

   Just fill out the blanks (second fn implementation is missing)"
  ([x] x)

  ([x form]
     ;; …
     )

  ([x form & more]
     ;; …
     ))

;;
;; ## Injecting concerns
;;
;; With countdown latch
;;

;;
;; When you're working with some concept, such as Countdown Latch a lot, it's useful to have a little macro
;; that makes it available for entire scope of your `with-latch` function.
;;

(defmacro ^:not-implemented with-latch
  "Write a macro that executes `body`, makes `latch` binding available within complete macro and
   waits for latch after the body"
  [countdown-from & body]
  ;; …
  )

;; Let is one of the first macros people learn to write in Lisps, it generates a function and makes context
;; bindings available for the function.

;; ## Helper methods
;;
(defmacro ^:not-implemented my-let
  "One of the ways to implement let is to create a temporary function scope that will have required
   bindings, and call this function with params that represent arguments.

   For example, if you have bindings like `[a 1 b 2]`, you'll take `a` and `b` as variable names (bindings),
   and `1` and `2` as values, that you'll call function with.

   This means that our let will expand:

        (let [a 1
              b 2]
          (println (+ a b)))

   To:

        ((fn [a b]
            (println (+ a b)))
          1 2)
  "
  [bindings & body]
  ;; …
  )


;;
;; Another interesting part of how you can apply macros is the way so called `gensyms` are
;; handled.
;;
;; Because you're writing code that's going to be expanded in a foregin and unknown context,
;; you want to make sure that by using your own names you're not hiding someone else's variables;
;; that's why we're using so called gensyms, which generate (guaranteed) unique names for all
;; variables. So whenever you add a postfix of `#` to your variable, it's replaced with auto-generated
;; symbol, such as `G__19378` during macroexpansion time.
;;

;; ## Recursive macroexpansion
;;
(defmacro ^:not-implemented my-or
  "Try implementing `or` knowing about gensyms. "
  ([] nil)
  ([x] x)
  ([x & next]
     ;; …
     ))

;;
;; Macros can be also used to cache some results (think of it, you can even do I/O operations
;; during macro-expansion time), so it's not a problem at all to make your macro pre-calculate
;; something for you, fetch some result and make them available for execution. Combining this
;; concept with `let-over-lambda` may yield some very interesting results.
