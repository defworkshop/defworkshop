(ns defworkshop.working-with-maps-test
  (:use defworkshop.working-with-maps)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest get-name-or-empty-test
  (is (= :empty (get-name-or-empty {})))
  (is (= "Alex" (get-name-or-empty {:name "Alex"}))))

(deftest retrieve-city-test
  (is (= "Munich" (retrieve-city {:name "Alex"
                                  :address {:city "Munich"
                                            :street "Saulingstr"}})))
  (is (= nil (retrieve-city {}))))

(deftest get-by-path-test
  (is (= "Alex"
         (get-by-path {:person {:name {:first-name "Alex"}}}))))

(deftest add-name-test
  (is (= {:name "Alex"
          :some :value}
         (add-name {:some :value} "Alex")))
  (is (= {:name "Alex"}
         (add-name {} "Alex"))))

(deftest add-age-test
  (is (= {:age 25
          :some :value}
         (add-age {:some :value} 25)))
  (is (= {:age 25}
         (add-age {} 25))))

(deftest add-name-and-age-age-test
  (is (= {:name "Alex"
          :age 25
          :some :value}
         (add-name-and-age {:some :value} "Alex" 25)))
  (is (= {:name "Alex"
          :age 25}
         (add-name-and-age {} "Alex" 25))))

(deftest remove-name-test
  (is (= {:some :value}
         (remove-name {:name "Alex" :some :value})))
  (is (= {}
         (remove-name {:name "Alex"}))))

(deftest remove-age-test
  (is (= {:some :value}
         (remove-age {:age 25 :some :value})))
  (is (= {}
         (remove-age {:age 25}))))

(deftest remove-name-and-age-test
  (is (= {:some :value}
         (remove-name-and-age {:name "Alex" :age 25 :some :value})))
  (is (= {}
         (remove-name-and-age {:name "Alex" :age 25}))))

(deftest map-keys-test
  (is (= (set '(:a :b :c))
         (set (map-keys {:a 1 :b 2 :c 3})))))

(deftest map-values-test
  (is (= (set '(1 2 3))
         (set (map-values {:a 1 :b 2 :c 3})))))

(deftest map-select-test
  (is (= {:a :b :c :d}
         (map-select {:a :b :c :d :e :f} [:a :c]))))

(deftest map-from-keys-and-vals-test
  (is (= {:key1 :value1
          :key2 :value2
          :key3 :value3}
         (map-from-keys-and-vals [:key1 :key2 :key3]
                                 [:value1 :value2 :value3]))))

(deftest kv-for-key-test
  (is (= [:a :b]
         (kv-for-key {:a :b :c :d} :a)))
  (is (= [:a :c]
         (kv-for-key {:a :c :c :d} :a))))

(deftest map-merge-test
  (is (= {:a :b :c :d}
         (map-merge {:a :b} {:c :d})))
  (is (= {:a :b :c :d :e :f}
         (map-merge {:a :b :c :d} {:e :f :c :f}))))

(deftest merge-max-test
  (is (= {:a 3 :b 5 :c 4}
         (merge-max {:a 1 :b 5} {:a 3 :b 2 :c 4})))
  (is (= {:f 4 :e 6 :a 3}
         (merge-max {:a 2 :e 6} {:f 4 :e -1 :a 3}))))

(deftest contains-key-test
  (is (contains? {:a :b} :a))
  (is (not (contains? {:a :b} :b))))

(deftest invert-map-test
  (is (= {:b :a :d :c}
         (invert-map {:a :b :c :d})))
  (is (some (partial = (invert-map {:a :b :d :c :f :c}))
            [{:b :a :c :f}
             {:b :a :c :d}])))
