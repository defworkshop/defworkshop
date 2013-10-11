(ns defworkshop.let-over-lambda-test
  (:use defworkshop.let-over-lambda)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest fn-in-fn-test
  (is (= 42 ((fn-in-fn)))))

(deftest apply-apply-test
  (is (= 42 (apply-apply (fn [] (fn [] 42))))))

(deftest scoped-fn-in-fn-test
  (is (= 42 ((scoped-fn-in-fn)))))

(deftest multiply-by-test
  (let [f (multiply-by 2)]
    (is (= 4 (f 2)))
    (is (= 8 (f 4)))))

(deftest make-counter-test
  (let [f (make-counter)]
    (is (= 1 (f)))
    (is (= 2 (f)))
    (is (= 3 (f)))))

(deftest curried-assoc-test
  (is (= {:language "Clojure"} (((curried-assoc {}) :language) "Clojure")))
  (is (= {:something "Else"} (((curried-assoc {}) :something) "Else")))
  )
