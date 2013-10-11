(ns defworkshop.destructuring-test
  (:use defworkshop.destructuring)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest test-project
  (is (= [:a :b :c]
         (project {:k1 :a :k2 :b :k3 :c :k4 :x})))
  (is (= [:a :b nil]
         (project {:k1 :a :k2 :b :k4 :c}))))

(deftest test-head
  (is (= :a
         (head [:a :b :c])))
  (is (nil? (head []))))

(deftest test-tail
  (is (= [:b :c]
         (tail [:a :b :c])))
  (is (nil? (tail [:a]))))


(deftest test-nested-access
  (is (= ["Maxi" 33]
         (nested-access {:user {:name "Maxi" :age 33 :id 12}})))
  (is (= [nil 33]
         (nested-access {:user {:age 33 :id 12}}))))

(deftest unpack-test
  (is (= [1 3] (unpack [1 2 3])))
  (is (= ["first" "last"] (unpack ["first" "middle" "last"]))))

(deftest head-test
  (is (= 1 (head [1 2 3 4])))
  (is (= "first" (head ["first" 2 3 4]))))

(deftest tail-test
  (is (= '(2 3) (tail [1 2 3])))
  (is (= '("second" "third") (tail ["first" "second" "third"]))))

(deftest multiply-second-test
  (is (= '(2 4 6) (multiply-second [1 2 3])))
  (is (= '(3 9 9) (multiply-second [1 3 3]))))

(deftest multiplication-table-test
  (is (= [2 12] (multiplication-table [[1 2] [3 4]])))
  (is (= [25 36] (multiplication-table [[5 5] [6 6]]))))

(deftest get-clojure-key-test
  (is (= nil (get-clojure-key {})))
  (is (= 1 (get-clojure-key {:clojure 1 :b 2})))
  (is (= :clj (get-clojure-key {:clojure :clj :b 2}))))

(deftest project-test
  (is (= [1 2 3] (project {:k1 1 :k2 2 :k3 3})))
  (is (= [:first :second :third] (project {:k1 :first :k2 :second :k3 :third}))))

(deftest recursive-destructuring-test
  (is (= 3 (recursive-destructuring [1 [2 3 4] 5])))
  (is (= "yo" (recursive-destructuring [1 [2 "yo" 4] 5]))))

(deftest nested-access-test
  (is (= ["Ada" "25"] (nested-access {:user {:name "Ada" :age "25" :occupation "Programmer"}})))
  (is (= ["Alex" "26"] (nested-access {:user {:name "Alex" :age "26" :occupation "Programmer"}})))
  (is (= [nil nil] (nested-access {}))))
