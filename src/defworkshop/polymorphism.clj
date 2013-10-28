(ns defworkshop.polymorphism
  (:require [workshoplib.tdd :refer […]]))

;;
;; ## Shapes
;;

(defmulti area :shape)

;; Reactangle area is calculated by multiplying height by width
(defmethod area :rectangle
  [rectangle]
  (…))

;; Circule radius is calculated by Pi * r^2
;; You can use Pi from `Math/PI`
(defmethod area :circle
  [c]
  (…))

(defn make-rectangle
  "Factory method for rectangle"
  [width height]
  {:shape :rectangle
   :width width
   :height height})

(defn make-circle
  "Factory function for circle"
  [radius] {:shape :circle
            :radius radius})






;; ## Bounded queue
;;
(def queue-limit 10)

(defn queue-state [queue & args]
  "Queue-state determines current queue state. If the queue is empty, it returns `::empty`, if the queue
   is full (has reached the `queue-limit`), it returns `::full`.

   `queue-state` is a dispatch function for our enqueue/dequeue multimethods."
  (cond
   (empty? queue) ::empty
   (= (count queue) queue-limit) ::full
   :else ::some))

;; Enqueue multimethod
(defmulti enqueue queue-state)

;; If the queue is `::full`, we should throw a Runtime exception saying that queue is full
(defmethod enqueue ::full [_ _]
  (…))

;; If the queue is not full, we should return a new queue with added element
(defmethod enqueue :default [queue element]
  (…))

;; Dequeue multimethod
(defmulti dequeue queue-state)

;; If the queue is `::empty`, we should throw a RuntimeException saying that queue is empty
(defmethod dequeue ::empty [_]
  (…))

;; Otherwise, we should dequeue element from it, by returning an array of the head (`first`)
;; element and the `rest` of elements.
(defmethod dequeue :default [queue]
  [(first queue) (…)])




;; ## Factorial

;; Implementing factorial using multimethods Note that factorial-like function
;; is best implemented using `recur` for enable tail-call optimization to avoid
;; stack overflow error. This is a only a demonstration of clojure's multimethod

;; identity form returns the same value passed
(defmulti factorial …)

(defmethod factorial 0 [_] (…))

(defmethod factorial :default
  [num]
  (…))

(comment
  (factorial 0)
  ;;=> 1
  (factorial 1)
  ;;=> 1
  (factorial 3)
  ;;=> 6
  (factorial 7)
  ;;=> 5040
  )






;; ## Beverages
;; Having a `Beverage` protocol, implement a function `serve` that describes a
;; beverage by answering how much alcohol, sugar and carbs it's gotten.
(defprotocol Beverage
  (alcohol [beverage])
  (sugar   [beverage])
  (carbs   [beverage])
  (->map   [beverage]))

(defn ^:not-implemented serve [alcohol sugar carbs]
  …)

(def water (serve 0.0 0.0 0.0))
(def soda  (serve 0.0 0.5 0.1))
(def beer  (serve 0.05 0.0 0.15))
(def wine  (serve 0.15 0.1 0.02))
(def vodka (serve 0.4 0.0 0.0))








;; ## Configuration
(defn env-param
  "Returns a named parameter from system ENV."
  [name]
  (System/getenv name))

(defprotocol Configuration
  (port [this])
  (app-name [this]))

(defn ^:not-implemented configuration-from-env
  "Returns a version of `Configuration` that looks up `port` and `app-name` from ENV by using
   `env-param` (\"PORT\" for port and \"APP_NAME\" for app-name)"
  []
  (reify Configuration
    (port [_] (env-param "PORT"))
    (app-name [_] (env-param "APP_NAME"))))

(defn ^:not-implemented configuration-from-map [configuration]
  "Returns a version of `Confirmation` that looks up `port` and `app-name` from the given
   `configuration` hash."
  (reify Configuration
    (port [_] (:port configuration))
    (app-name [_] (:app-name configuration))))
