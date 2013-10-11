(ns defworkshop.working-with-sequences-test
  (:use defworkshop.working-with-sequences)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest split-head-and-tail-test
  (is (= [1 '(2 3)] (split-head-and-tail [1 2 3]))))

(deftest get-nth-test
  (is (= 2 (get-nth [1 2 3] 1)))
  (is (= 3 (get-nth [1 2 3] 2))))

(deftest conj-to-list-test
  (is (= '(2 1) (conj-to-list '(1) 2)))
  (is (= '(3 1 2) (conj-to-list '(1 2) 3)))
  (is (= '(4 1 2 3) (conj-to-list '(1 2 3) 4))))

(deftest conj-to-vector-test
  (is (= [1 2] (conj-to-vector [1] 2)))
  (is (= [1 2 3] (conj-to-vector [1 2] 3)))
  (is (= [1 2 3 4] (conj-to-vector [1 2 3] 4))))

(deftest remove-from-set-test
  (is (= #{1 3}
         (remove-from-set #{1 2 3} 2))))

(deftest rev-concat-test
  (is (= [:d :c :b :a]
         (rev-concat [:a :b] [:c :d])))
  (is (= [1 2 3 4]
         (rev-concat [4 3] [2 1]))))

(deftest interleave-seqs-test
  (is (= [1 :a 2 :b]
         (interleave-seqs [1 2] [:a :b :c])))
  (is (= [:a 1 :b 2]
         (interleave-seqs [:a :b] [1 2])))
  (is (= [1 :a 2 :b]
         (interleave-seqs [:a :b :c] [1 2]))))

(deftest split-head-and-tail-destructuring-test
  (is (= [1 '(2 3)] (split-head-and-tail-destructuring [1 2 3]))))

(deftest contains-index-test
  (is (not (contains-index [1 2 3] 3)))
  (is (contains-index [1 2 3] 2)))

(deftest real-contains-test
  (is (real-contains [5 6 7] 5))
  (is (not (real-contains [5 6 7] 2))))

(deftest double-all-test
  (is (= '(2 4 6 8) (double-all [1 2 3 4]))))

(deftest add-5-to-all-test
  (is (= '(6 7 8 9) (add-5-to-all [1 2 3 4]))))

(deftest add-5-to-all-for-test
  (is (= '(6 7 8 9) (add-5-to-all-with-for [1 2 3 4]))))

(deftest print-combinations-test
  (is (= "[0 1]\n[1 0]\n"
         (with-out-str (print-combinations (range 2) (range 2)))))
  (is (= "[0 1]\n[0 2]\n[1 0]\n[1 2]\n[2 0]\n[2 1]\n"
         (with-out-str (print-combinations (range 3) (range 3))))))

(deftest split-to-pairs-test
  (is (= '((1 2) (3 4)) (split-to-pairs [1 2 3 4]))))

(deftest moving-average-test
  (let [nums [1.0 2.0 3.0 4.0]]
    (is (= [1.5 2.5 3.5]
           (moving-average nums 2)))
    (is (= [2.0 3.0]
           (moving-average nums 3)))))

(deftest sum-test
  (is (= 6 (sum [1 2 3]))))

(deftest even-only-test
  (is (= '(2 4) (even-only [1 2 3 4]))))

(deftest odd-only-test
  (is (= '(1 3) (odd-only [1 2 3 4]))))

(deftest sum-of-squares-of-even-numbers-test
  (is (= 20 (sum-of-squares-of-even-numbers [1 2 3 4])))
  (is (= 52 (sum-of-squares-of-even-numbers [3 4 5 6]))))

(deftest get-part-of-vector-test
  (is (= [1 2] (get-part-of-vector [0 0 1 2] 2)))
  (is (= [3 4] (get-part-of-vector [1 2 3 4] 2)))
  (is (= [2 3] (get-part-of-vector [1 2 3 4] 1 3))))

(deftest sort-sequence-test
  (is (= [1 2 3] (sort-sequence [3 2 1])))
  (is (= ["a" "b" "c"] (sort-sequence ["c" "b" "a"]))))

(deftest sort-maps-test
  (is (= [{:name "Alex" :age 19} {:name "Maxi" :age 20}]
         (sort-maps [{:name "Maxi" :age 20} {:name "Alex" :age 19}]))))

(deftest interpolate-words-test
  (is (= "Hello Alex, you're 25 years old!"
         (interpolate-words "Hello %name%, you're %age% years old!"
                            {:name "Alex" :age 25}))))

(deftest repeat-times-test
  (is (= `(1 1 1 1 1) (repeat-times 1 5))))

(deftest read-and-summarize-contents-test
  (is (= 15 (read-and-summarize-contents "resources/numbers"))))

(deftest reduce-assoc-test
  (is (= {:key1 :value1
          :key2 :value2
          :key3 :value3}
         (reduce-assoc [:key1 :value1
                        :key2 :value2
                        :key3 :value3]))))

(deftest stringify-map-test
  (is (= ["Max: 26" "Alex: 25"]
         (stringify-map {"Alex" 25
                         "Max" 26}))))

(deftest select-keysp-test
  (is (= {:alex 25}
         (select-keysp {:alex 25 :max 26}
                       #(= \a (first (name %)))))))

(deftest invert-map+-test
  ;; trivial case
  (is (= {:b :a :d :c}
         (invert-map+ {:a :b :c :d})))
  ;; invert results in value set
  (is (= {:b :a :c #{:d :f}}
         (invert-map+ {:a :b :d :c :f :c})))
  (is (= {:b #{:a :c} :c #{:d :e :f}}
         (invert-map+ {:a :b :c :b :d :c :e :c :f :c})))
  ;; invert from value set
  (is (= {:a :b :d :c :f :c}
         (invert-map+ {:b :a :c #{:d :f}})))
  ;; inversion is symmetric
  (let [m {:a :b :d :c :f :c}]
    (is (= m
           (-> m
               invert-map+
               invert-map+)))))
