(ns defworkshop.java-interop-test
  (:use defworkshop.java-interop)
  (:require [workshoplib.tdd :refer [deftest is]])
  (:import [java.util Date ArrayList HashSet]))

(deftest get-constant-value-test
  (is (= Integer/MAX_VALUE
         (get-constant-value))))

(deftest static-method-call-test
  (is (= (System/getProperties)
         (static-method-call))))

(deftest mk-date-test
  (let [d  (Date.)
        d2 (mk-date (.getYear d) (.getMonth d)  (.getDate d)
                    (.getHours d) (.getMinutes d) (.getSeconds d))]
    (is (= (.getYear d)    (.getYear d2)))
    (is (= (.getMonth d)   (.getMonth d2)))
    (is (= (.getDate d)    (.getDate d2)))
    (is (= (.getHours d)   (.getHours d2)))
    (is (= (.getMinutes d) (.getMinutes d2)))
    (is (= (.getSeconds d) (.getSeconds d2)))))

(deftest str-to-upper-test
  (is (= "STRING." (str-to-upper "string.")))
  (is (= "STRING." (str-to-upper "STRING."))))

(deftest str-to-upper-char-test
  (is (= \S (str-to-upper-char "STRING.")))
  (is (= \S (str-to-upper-char "string."))))

(deftest thread-info-test
  (let [t        (Thread.)
        expected (format "%s-%s:%s,%s" (.getId t) (.getName t) (.isAlive t) (.isInterrupted t))]
    (is (= expected
           (thread-info t)))))

(deftest add-elements!-test
  (let [array-list (ArrayList. [1 2 3])
        hash-set (HashSet. [:a :b :c])]
    (is (= hash-set
           (add-elements! (HashSet.) :a :b :c)))
    (is (= array-list
           (add-elements! (ArrayList.) 1 2 3)))))

(deftest lexicographical-comparator-test
  (let [cmp (lexicographical-comparator)
        sort-with-cmp (fn [coll]
                        (let [array (to-array coll)]
                          (java.util.Arrays/sort array cmp)
                          (seq array)))]
    (is (= (range 5) (sort-with-cmp (reverse (range 5)))))
    (is (= [:a :b :c] (sort-with-cmp [:b :c :a])))
    (is (= ["hello" "world"] (sort-with-cmp ["world" "hello"])))
    (is (= [100 20 3 40] (sort-with-cmp [20 3 40 100])))))

;; gen-class needs a namespace in which it is used to be compiled. Otherwise
;; classes will not be generated.
(compile 'defworkshop.java-interop)

(deftest shout-test
  (let [shout (shout "clojure")]
    (is (not= "CLOJURE"
              shout))
    (is (= "CLOJURE"
           (str shout)))))

(deftest greeter-test
  (let [greeter (greeter)]
    (is (= "Hello, World!"
           (.greet greeter "World")))))

(deftest myseq-test
  (let [my-seq #(my-seq %)]
    (doseq [coll [[1 2 3]
                  '(\a \b \c)
                  ()]]
      (is (not= (seq coll) (my-seq coll)))
      (is (= (seq coll) (seq (my-seq coll)))))))

(deftest immutable-queue-test
  (let [my-queue #(immutable-queue %)]
    (let [q (my-queue [])]
      (is (nil? (.peek q)))
      (is (thrown? RuntimeException (.poll q)))
      (is (thrown? RuntimeException (.remove q)))
      (is (thrown? java.util.NoSuchElementException (.element q))))
    (doseq [coll [[1] [3 2]]]
      (let [q (my-queue coll)]
        (is (= (.peek q) (first coll)))
        (is (= (.element q) (first coll)))))))

(deftest mutable-queue-test
  (let [my-mut-queue #(mutable-queue %)]
    (let [q (my-mut-queue [1 2])]
      (is (= 1 (.peek q)))
      (is (= 1 (.element q)))
      (is (= 1 (.poll q)))
      (is (= 2 (.peek q)))
      (is (= 2 (.element q)))
      (is (= 2 (.remove q)))
      (is (nil? (.peek q)))
      (is (nil? (.poll q)))
      (is (thrown? java.util.NoSuchElementException (.remove q)))
      (is (thrown? java.util.NoSuchElementException (.element q))))))

;; Notice that you can use -main as an entry point from the console!
;;
;;    lein run -m defworkshop.java-interop x y z

(deftest main-test
  (is (= "(:a :b :c)\n"
         (with-out-str
           (-main :c :b :a)))))
