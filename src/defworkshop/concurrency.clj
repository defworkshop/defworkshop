(ns defworkshop.concurrency
  (:import [java.util.concurrent ExecutorService Executors CountDownLatch TimeUnit])
  (:require [workshoplib.core :refer [try-password]]
            [workshoplib.tdd :refer […]]
            clojure.string))

(alter-var-root #'*out* (constantly *out*))

;; # Bank account
;;
;; First, let's write a function that constructs a bank account, which is basically an atom.
;;

(defn ^:not-implemented make-bank-account
  "Write a function that constructs a bank account out of given start amount.
   Use atoms to hold the amount."
  [start-amount]
  (…))

(defn ^:not-implemented deposite
  "Write a function that receives an `account` as a first argument and adds money
   to the balance by using `swap!` and `+` function.

   Don't forget to check if amount to deposite is positive ;)"
  [account amount-to-deposite]
  (…))

(defn ^:not-implemented withdraw
  "Write a function that receives an `account` as a first argument and withdraws money
   from the balance by using `swap!` and `-` function.

   Don't forget to check if there're sufficient funds on balance, throw `RuntimeException`
   otherwise."
  [account amount-to-withdraw]
  (…))

(defn ^:not-implemented transfer
  "Now write a transfer function that will transfer an amount from one bank account to another."
  [account1 account2 amount]
  (…))

(defn ^:not-implemented get-balance
  "Write a function that returns current amount of money on the balance by using dereferecing."
  [account]
  (…))


;; # Tumbling Window
;;

(defn ^:not-implemented tumbling-window
  "A tumbling window. Tumbling windows (here) have a fixed a-priori known size.

   The idea is simple: using the `let-over-lambda` concept, write a function that has some state
   in closure (vector within the atom).

   Return the function that will add the value it's called with to the buffer, after that check
   wether the buffer is already full (`count` of `@buffer` equals `size`). If it is, fun an `emit-fn`,
   and reset the buffer back to empty vector.

   That's pretty much what the tumbling window looks like:

        t0       t1          t2         (emit)
      +---+  +---+---+  +---+---+---+                 -...-...-...-
      | 1 |  | 1 | 2 |  | 1 | 2 | 3 |    <[1 2 3]>    : 1 : 2 : 3 :
      +---+  +---+---+  +---+---+---+                 -...-...-...-

        t3       t4          t5         (emit)
      +---+  +---+---+  +---+---+---+                 -...-...-...-
      | 4 |  | 4 | 5 |  | 4 | 5 | 6 |    <[4 5 6]>    : 4 : 5 : 6 :
      +---+  +---+---+  +---+---+---+                 -...-...-...-
"
  [size emit-fn]
  (…)
  (let [buffer (atom [])]
    (fn [next-value]
      (…))))

;; # Password Cracker
;;
;; Here, you're completely on your own. We only can tell you where to take the passwords:
;;
;;    http://downloads.skullsecurity.org/passwords/hotmail.txt.bz2
;;
;; Brutforce the password from the directory, url is http://clojureworkshop.com/crack_password putting password in
;; form params, here's an example code:
;;
;;     (http/post (check-password-url)
;;                {:form-params      {:password password}
;;                 :throw-exceptions false})
;;
;; It will respond with 200OK if you submit a password correctly.
;;
;; You can use `workshoplib.core/try-password`, which returns you true when password is correct and false when password
;; is incorrect.
;;
;; Use `ExecutorService` to generate more threads!

(defonce threads-count 32)
(defonce executors (Executors/newFixedThreadPool threads-count))

(defn ^:not-implemented crack-password
  "You can get the dictionary from here: https://dl.dropboxusercontent.com/u/2516311/dictionary.txt"
  []
  (…))

;; # Word Count
;;
;; Write a word count from files,

(defn count-words
  "Write a function that receives a text from the file, breaks it to words and performs a word count.

   For that you can use `clojure.string/split`"
  [text]
  (count (clojure.string/split text #"\ ")))

(defn ^:not-implemented word-count
  "Complete the function by putting word counts by filename into results atom.

   You can find download example files here:
     https://dl.dropboxusercontent.com/u/2516311/WordCount/1.txt
     https://dl.dropboxusercontent.com/u/2516311/WordCount/2.txt
     https://dl.dropboxusercontent.com/u/2516311/WordCount/3.txt
     https://dl.dropboxusercontent.com/u/2516311/WordCount/4.txt
     https://dl.dropboxusercontent.com/u/2516311/WordCount/5.txt
     https://dl.dropboxusercontent.com/u/2516311/WordCount/6.txt"
  [filenames]
  (…))


(defn calculate-pi
  "Calculates Pi using the approximation

        4 * (1 - 1/3 + 1/5 - 1/7 + ...).

   In order to be fair and compliant to the parallel version, we decided to take number of
   iteration by multiplying thread count to precision."
  [precision]
  (let [iterations  (* threads-count precision)
        odd-numbers (filter odd? (iterate inc 1))]
    (* 4.0
       (apply + (take iterations (map / (cycle [1.0 -1.0]) odd-numbers))))))



(defn ^:not-implemented chunks
  "Returns chunks for parallel Pi version. Just give it a step, it will return end number for each chunk."
  [step]
  ((fn rchunks [num]
     (cons num (lazy-seq (rchunks (+ num step)))))
   step))

(defn ^:not-implemented calculate-pi-parallel
  "Parallelize `pi` calculation.

   Split work on calculation of the approximation

         (1 - 1/3 + 1/5 - 1/7 + ...)

   For that, take a `threads-count` number of `chunks`, and submit each one to the tasks to the `executors`.

   For each chunk calculate proximity.

   Use `CountDownLatch` to make sure every thread has finished it's work."
  [iterations]
  (…))
