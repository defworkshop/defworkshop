(ns defworkshop.state-to-recursion-test
  (:use defworkshop.state-to-recursion)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest reverse-coll-test
  (is (= '(4 3 2 1) (reverse-coll [1 2 3 4]))))

(deftest recursive-sum-test
  (is (= 10 (recursive-sum [1 2 3 4])))
  (is (= 1 (recursive-sum [1])))
  (is (= 0 (recursive-sum []))))

(deftest recursive-sum-tc-test
  (is (= 10 (recursive-sum-tc [1 2 3 4])))
  (is (= 1 (recursive-sum-tc [1]))))

(deftest my-reduce-test
  (is (= 10 (my-reduce + (range 5))))
  (is (= '(4 3 2 1 0)
         (my-reduce conj '() (range 5))))
  (is (nil? (my-reduce + []))))

(deftest max-from-list-test
  (is (= 8 (max-from-list [8 4 2 2 4 4 5])))
  (is (= nil (max-from-list [])))
  (is (= 2 (max-from-list [2 2 2 2])))
  (is (= 2 (max-from-list [2]))))

(deftest max-from-list-tc-test
  (is (= 8 (max-from-list-tc [8 4 2 2 4 4 5])))
  (is (= 2 (max-from-list-tc [2 2 2 2])))
  (is (= 2 (max-from-list-tc [2]))))

(deftest loop-recur-sum-test
  (is (= 10 (loop-recur-sum [1 2 3 4])))
  (is (= 1 (loop-recur-sum [1])))
  (is (= 0 (loop-recur-sum []))))

(deftest map-from-keys-and-vals-test
  (is (= {:key1 :value1
          :key2 :value2
          :key3 :value3}
         (map-from-keys-and-vals [:key1 :key2 :key3]
                                 [:value1 :value2 :value3]))))

(deftest parantheses-balanced?-test
  (is (parentheses-balanced? "()()"))
  (is (parentheses-balanced? "a(b)c(d)e"))
  (is (not (parentheses-balanced? ")()()")))
  (is (parentheses-balanced? "((()))"))
  (is (not (parentheses-balanced? "(ab(()cef)"))))
