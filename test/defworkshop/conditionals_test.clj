(ns defworkshop.conditionals-test
  (:use defworkshop.conditionals)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest number-to-grade-test
  (is (= "A" (number-to-grade 91)))
  (is (= "B" (number-to-grade 81)))
  (is (= "C" (number-to-grade 71)))
  (is (= "D" (number-to-grade 61)))
  (is (= "F" (number-to-grade 51))))

(deftest even-or-odd-test
  (is (= :even (even-or-odd 2)))
  (is (= :odd (even-or-odd 3))))

(deftest day-of-week-test
  (is (= "Friday"
         (day-of-week (java.util.Date. 1379066050639))))
  (is (= "Wednesday"
         (day-of-week (java.util.Date. 1371066050639))))
  (is (= "Saturday"
         (day-of-week (java.util.Date. 1321066050639)))))

(deftest day-of-week-with-hash-lookup-test
  (is (= "Friday"
         (day-of-week-with-hash-lookup (java.util.Date. 1379066050639))))
  (is (= "Wednesday"
         (day-of-week (java.util.Date. 1371066050639))))
  (is (= "Saturday"
         (day-of-week (java.util.Date. 1321066050639)))))

(deftest pos-neg-or-zero-test
  (is (= :positive (pos-neg-or-zero 1)))
  (is (= :positive (pos-neg-or-zero 1231)))
  (is (= :negative (pos-neg-or-zero -1)))
  (is (= :negative (pos-neg-or-zero -1012)))
  (is (= :zero (pos-neg-or-zero 0))))

(deftest get-name-or-empty-test
  (is (= :empty (get-or-default {} :key :empty)))
  (is (= "Alex" (get-or-default {:name "Alex"} :name "Alex"))))

(deftest describe-test
  (is (= :number (describe 1)))
  (is (= :string (describe "1")))
  (is (= :vector (describe [1])))
  (is (= :list (describe '(1))))
  (is (= :map (describe {1 2})))
  (is (= :unknown (describe :keyword))))

(deftest not-a-number-test
  (is (not-a-number? "1")))

(deftest not-a-number-complement-test
  (is (not-a-number-complement "1")))
