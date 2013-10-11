(ns defworkshop.polymorphism
  (:require [workshoplib.tdd :refer […]]))

;;
;; ## Message logging
;;
;; Let's implement multimethod-based logging system, that will match on the message itself,
;; and figure out which logger to use.
;;
;; Messages themselves will look pretty much like:
;;
;;        {:type :init :from 1 :to 2}
;;
;; We will determine type of the message (and corresponding logger) based on `:type` key of the
;; message.

(defmulti log (fn [_] (…)))

;; If the type is `:init`, use string format of
;;
;;      "Host <:from> initiating conversation with host <:to>".
;;
;; Payload example:
;;
;;      {:type :init :from 1 :to 2}
;;
(defmethod log :init [{from :from to :to}]
  …)

;; If the type is `:message`, use string format of
;;
;;      "Host <:from> to host <:to>: <:message>"
;;
;; Payload example:
;;
;;      {:type :message :from 3 :to 4 :message "hello"}
;;
(defmethod log :message [_]
  (…))

;; If the type is `:file`, use format of
;;
;;      "Host <:from> sending <:filename> to host <:to>"
;;
;; Payload example:
;;
;;      {:type :file :from 5 :to 6 :file {:name "image.jpg" :payload ""}}
;;
(defmethod log :file [_]
  (…))

;; If the type is `:end`, use format of
;;
;;      "Host <from> closed the conversation with host <to>"
;;
;; Payload example:
;;
;;       {:type :end :from 7 :to 8}
;;
(defmethod log :end [_]
  (…))

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

;; ## Router
;; Router matches a given route and calls a corresponding handler, depending on which method
;; is called.

;; Route multimethod extracts
(defmulti route (fn [_] (…)))
;; Alternative implementation of the same method would look like
;;
;;     (defmulti route (fn [request]
;;                        [(:uri request) (:method request)]))
;;
;; So it basically returns an array of `:uri` and `:method` from the given request


;; When the we match `["/users" :get]`, return:
;;
;;    "Listing all users"
;;
(defmethod route ["/users" :get] [_]
  "Listing all users")

;; When the we match `["/users" :post]`, return
;;
;;     "Creating user"
;;
(defmethod route ["/users" :post] [_]
  "Creating user")

;; When the we match `["/users" :put]`, return
;;
;;     "Updating user <id>"
;;
(defmethod route ["/users" :put] [{{id :id} :params}]
  (str "Updating user " id))

;; When the we match `["/users" :delete]`, return
;;
;;     "Deleting user <:id>"
;;
(defmethod route ["/users" :delete] [{{id :id} :params}]
  (str "Deleting user " id))

;; When the we match `["/items" :get]`, return
;;
;;     "Listing all items"
;;
(defmethod route ["/items" :get] [_]
  "Listing all items")

;; When the we match `["/items" :post]`, return
;;
;;     "Creating user"
;;
(defmethod route ["/items" :post] [_]
  "Creating item")

;; When the we match `["/items" :put]`, return
;;
;;      "Updating item <:id>"
;;
(defmethod route ["/items" :put] [{{id :id} :params}]
  (str "Updating item " id))

;; When the we match `["/users" :post]`, return
;;
;;     "Deleting item <:id>"
;;
(defmethod route ["/items" :delete] [{{id :id} :params}]
  (str "Deleting item " id))

;; Otherwise, just return
;;
;;     "Not found"
;;
(defmethod route :default [_]
  "Not found")

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
