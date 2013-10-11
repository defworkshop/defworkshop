(ns defworkshop.java-interop
  (:require [workshoplib.tdd :refer […]]))

;; # Basic Java operations
;;
;; ## Obtaining Constant Values
;;

(defn ^:not-implemented get-constant-value
  "Write a function that returns Integer.MAX_VALUE"
  []
  (…))

;; ## Static calls
;;
;; It's also possible to call Java static methods from Clojure.
;;
(defn ^:not-implemented static-method-call
  "Write a function that returns the result of System.getProperties()"
  []
  (…))

;; # Constructors
;;
;; You can also call the constructor for Java class by calling it with `.`:
;;
;;     (Date. ...)
;;
(defn ^:not-implemented mk-date
  "Given year (offset 1900), month, day, hour minute and second, construct a new
   java.util.Date object."
  [year month day hour minute second]
  (…))

;; # Method calls
;;
(defn ^:not-implemented str-to-upper
  "Given a String s, return an uppercase version."
  [s]
  (…))

(defn ^:not-implemented str-to-upper-with-typehint
  "Add a typehint to the previous function now. `s` can only be ^java.lang.String"
  [^java.lang.String s]
  (…))

(defn ^:not-implemented str-to-upper-char
  "given a String s, convert it to uppercase and return the first character."
  [s]
  (…))

(defn ^:not-implemented thread-info
  "given a Thread t, produce a string description as follows: '<id>-<name>:<alive>,<interrupted>'
hint: use bean to access thread information."
  [^Thread t]
  (…))

(defn ^:not-implemented add-elements!
  "given a java.util.Collection l, add the elements e1 e2 e3 and return the
  modified collection. hint: use the doto macro."
  [l e1 e2 e3]
  (…))

;; # Implemeting Interfaces and Abstract Classes
;;
(defn ^:not-implemented lexicographical-comparator
  "Implement interface `java.util.Comparator`, that would make a lexicographical comparison
   of given elements.

   You can conert any object to string by using `str` function.
   In order to implement an interface or an abstract class, use `reify`"
  []
  (…))

;; # Generating Java Classes
;;
;; ## Simple Java Class generation
;;
;; You can also generate Java classes from Clojure code. For thay you can use `gen-class`
;;
;; Implement a simple Java class, Java version of which would look like that:
;;
;;        package workshop_tasks_implemented.java_interop;
;;
;;        public class Shout {
;;
;;          public String state;
;;
;;          public Shout(String str) {
;;            this.state = str.toUpperCase();
;;          }
;;
;;          public String toString() {
;;            return this.state;
;;          }
;;        }
;;
;; `gen-class` receives attributes, such as:
;;
;;   * `:constructors` maps the arguments of the class' constructor to the arguments of the super
;;      class' constructor. This is used to determine which constructor is supposed to be called.
;;   * `:state` deﬁnes a method which will return the object's state.
;;   * `:init` deﬁnes the name of the initialiser. This is a function which has to return a vector.
;;      The first element is again a vector of arguments to the super class constructor. In our case
;;      this is just the empty vector. The second element is the object's state.
;;   * `:name` fully qualified name of the class (together with package).
;;

(comment
  (gen-class
   :name workshop_tasks_implemented.java_interop.Shout
   :prefix Shout-
   :state …
   :init …
   :constructors …))

(defn- Shout-toString
  [this]
  (…))

(defn- Shout-init
  [s]
  [nil (…)])

(defn ^:not-implemented shout
  "Implement a factory function for `Shout` class, that would call a constructor of the class."
  [str]
  (…))

;; Implement a `Greeter` class, whose Java implementation would look something like that:
;;
;;        package workshop_tasks_implemented.java_interop;
;;
;;        public class Greeter {
;;
;;          public String state;
;;
;;          public Greeter() {
;;          }
;;
;;          public String greet(String s) {
;;            return "Hello, " + s;
;;          }
;;        }
;;
;; Which means that it's default constructor is empty, and it has one method, `greet` which
;; receives `String` and returns that string concatenated with `Hello`.
;;

(comment
  (gen-class
   :name workshop_tasks_implemented.java_interop.Greeter
   :prefix Greeter-
   :methods …))

(defn- Greeter-greet
  [this s]
  (…))

(defn ^:not-implemented greeter
  "Make a factory function that would create an instance of `Greeter` class."
  []
  (…))

;; You can also implement interfaces using `gen-class`, for that you should use `:implements`
;; attribute, for example:
;;
;;      :implements [clojure.lang.Seqable]
;;

(comment
  (gen-class
   :name workshop_tasks_implemented.java_interop.MySeq
   :prefix MySeq-
   :state …
   :init …
   :constructors …
   :implements [clojure.lang.Seqable]))

(defn- MySeq-seq
  [this]
  (…))

(defn- MySeq-init
  ([xs]

   [nil (…)]))

(defn ^:not-implemented my-seq
  [coll]
  (…))

;; Implement a `ImmutableQueue` class, that receives `clojure.lang.Sequable`
;; as a constructor, and implements`java.util.Queue`.
;;
;;

(comment
  (gen-class
   :name workshop_tasks_implemented.java_interop.ImmutableQueue
   :prefix Queue-
   :state …
   :init …
   :constructors …
   :implements [java.util.Queue]))

(defn- Queue-init
  ([xs]
   [nil (…)]))

(defn- Queue-peek
  "Implement a `peek` function for queue that gets first item from the `state`
   of the queue."
  [this]
  (…))

(defn- Queue-element
  "Implement `element` method for queue, that returns head element from the queue and throws
   `java.util.NoSuchElementException`."
  [this]
  (…))

(defn- Queue-poll
  [this]
  (throw (RuntimeException. "Can't poll from ImmutableQueue")))

(defn- Queue-remove
  [this]
  (throw (RuntimeException. "Can't remove from ImmutableQueue")))

(defn ^:not-implemented immutable-queue
  [^clojure.lang.Seqable els]
  (…))

;; Implement a `MutableQueue`, backed by the `ref`
;;
(comment
  (gen-class
   :name workshop_tasks_implemented.java_interop.MutableQueue
   :prefix MQueue-
   :state …
   :init …
   :constructors …
   :implements [java.util.Queue]))

(defn- MQueue-init
  ([xs]
   [nil (…)]))

(defn- MQueue-peek
  "Implement `peek` from the queue, that returns the head element from the queue.

   You can use `deref` to dereference a `ref`."
  [this]
  (…))

(defn- MQueue-poll
  "Implement `poll` for the queue, that gets first element from the `state`, and
   removes it from it.

   You can use `commute` to mutate the ref, just remember you should do it within `dosync` block."
  [this]
  (…))

(defn- MQueue-remove
  "Implement `remove` that removes the head element from the queue, and throws `java.util.NoSuchElementException`
   if the `state` is already empty.

   You can reuse `poll` implementation here."
  [this]
  (…))

(defn- MQueue-element
  "Implement `element` for the queue, that gets head element from the queue and throws `java.util.NoSuchElementException`
   if the `state` is already empty.

   You can reuse `peek` implementation here, too."
  [this]
  (…))

(defn ^:not-implemented mutable-queue
  [^clojure.lang.Seqable els]
  (…))

(defn ^:not-implemented -main
  [& args]
  (…))
