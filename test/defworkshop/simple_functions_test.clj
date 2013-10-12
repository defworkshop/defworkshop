(ns defworkshop.simple-functions-test
  (:use defworkshop.simple-functions)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest double-number-test
  (is (= 4 (double-number 2)))
  (is (= 6 (double-number 3)))
  (is (= 8 (double-number 4))))

(deftest square-test
  (is (= 4 (square 2)))
  (is (= 9 (square 3)))
  (is (= 16 (square 4))))

(deftest cube-test
  (is (= 8 (cube 2)))
  (is (= 27 (cube 3)))
  (is (= 64 (cube 4))))

(deftest sum-of-numbers-test
  (is (= 3 (sum-of-numbers 1 2)))
  (is (= 6 (sum-of-numbers 1 2 3)))
  (is (= 10 (sum-of-numbers 1 2 3 4))))

(deftest sum-of-numbers-in-vector-test
  (is (= 7 (sum-of-numbers-in-vector [3 4])))
  (is (= 3 (sum-of-numbers-in-vector [1 2]))))

(deftest get-second-test
  (is (= 2 (get-second [1 2 3]))))

(deftest first-first-test
  (is (= 1 (first-first [[1]])))
  (is (= 1 (first-first [[1] 2])))
  (is (= 1 (first-first [[1 2] 2]))))

(deftest get-from-map-as-function-test
  (is (= :b (get-from-map-as-function {:a :b} :a))))

(deftest get-from-map-key-as-function-test
  (is (= :b (get-from-map-key-as-function :a {:a :b}))))

(deftest call-twice-test
  (let [count (atom 0)]
    (call-twice (fn [i] (swap! count #(+ i %))) 1)
    (is (= 2 @count))))

(deftest composition-string-plus-test
  (is (= "7" (composition-string-plus 5 2)))
  (is (= "4" (composition-string-plus 2 2))))

(deftest max-from-sequence-test
  (is (= 12 (max-from-sequence [4 3 2 1 12 3 2])))
  (is (= 5 (max-from-sequence [4 3 2 5 1 3 2]))))

(deftest read-file-contents-test
  (is (= "1\n2\n3\n4\n5\n" (read-file-contents "resources/numbers"))))

(deftest ten-times-test
  (is (= 20 (ten-times 2)))
  (is (= 70 (ten-times 7))))

(deftest my-partial-test
  (is (= 4 ((my-partial + 2) 2))))

(deftest cube-anonymous-test
  (is (= 8 (cube-anonymous 2)))
  (is (= 27 (cube-anonymous 3)))
  (is (= 64 (cube-anonymous 4))))
