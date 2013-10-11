(ns defworkshop.macros-test
  (:use defworkshop.macros)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest call-test
  (is (= 2 (call inc 1)))
  (is (= 6 (call + 1 2 3))))

(deftest my-when-test
  (is (= 3 (my-when true 1 2 3)))

  (let [res (atom [])]
    (my-when true
             (swap! res conj 1)
             (swap! res conj 2)
             (swap! res conj 3))
    (is (= [1 2 3] @res)))

  (let [res (atom [])]
    (my-when false
             (swap! res conj 1)
             (swap! res conj 2)
             (swap! res conj 3))
    (is (= [] @res))))

(deftest with-debug-test
  (is (= "hello\n"
         (with-out-str
           (= 4 (with-debug
                  (+ 1 1)
                  (debug "hello")
                  (+ 2 2)))))))

(deftest my-threading-test
  (is (= 4 (my-threading 1 inc inc inc)))
  (is (= 7 (my-threading 1 (+ 2) (+ 2) (+ 2)))))

(deftest with-latch-test
  (with-latch 5
    (is (= 5 (.getCount latch)))
    (.countDown latch)
    (.countDown latch)
    (.countDown latch)
    (.countDown latch)
    (.countDown latch)
    (is (= 0 (.getCount latch)))))

(deftest my-let-test
  (my-let [a 1
           b 2]
          (is (= 3 (+ a b)))))

(deftest my-or-test
  (let [calls (atom 0)]
    (is
     (or (do (swap! calls inc) false)
         (do (swap! calls inc) true)
         (do (swap! calls inc) true)
         (do (swap! calls inc) true)))
    (is (= 2 @calls))))
