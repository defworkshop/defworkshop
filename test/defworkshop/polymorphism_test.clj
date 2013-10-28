(ns defworkshop.polymorphism-test
  (:require [defworkshop.polymorphism :refer :all]
            [clojure.test :refer [testing]]
            [workshoplib.tdd :refer [deftest is]]))

(deftest area-test
  (is (= 452 (Math/round (area (make-circle 12)))))
  (is (= 52 (area (make-rectangle 4 13)))))

(deftest bounded-queue-enqueue-test
  (is (= [1] (enqueue [] 1)))
  (is (= [1 2 3 4 5] (enqueue [1 2 3 4] 5)))
  (is (thrown? RuntimeException (enqueue [1 2 3 4 5 6 7 8 9 10] 11))))

(deftest bounded-queue-dequeue-test
  (is (thrown? RuntimeException (dequeue [])))
  (is (= [1 [2 3 4]] (dequeue [1 2 3 4])))
  (is (= [1 [2 3 4 5 6 7 8 9 10] (dequeue [1 2 3 4 5 6 7 8 9 10])])))

(deftest factorial-test
  (is (= 1 (factorial 0)))
  (is (= 1 (factorial 1)))
  (is (= 6 (factorial 3)))
  (is (= 5040 (factorial 7))))

(deftest serve-test
  (testing "water"
    (is (= {:alcohol 0.0
            :sugar   0.0
            :carbs   0.0} (->map water))))

  (testing "soda"
    (is (= {:alcohol 0.0
            :sugar   0.5
            :carbs   0.1} (->map soda))))

  (testing "beer"
    (is (= {:alcohol 0.05
            :sugar   0.0
            :carbs   0.15}) (->map beer)))

  (testing "wine"
    (is (= {:alcohol 0.15
            :sugar   0.1
            :carbs   0.02}) (->map wine)))

  (testing "vodka"
    (is (= {:alcohol 0.4
            :sugar   0.0
            :carbs   0.0}) (->map vodka))))


(deftest env-configuration-test
  (with-redefs [env-param (fn [name] (if (= name "PORT") 5000 "test"))]
    (is (= 5000 (port (configuration-from-env))))
    (is (= "test" (app-name (configuration-from-env))))))

(deftest map-configuration-test
  (is (= 5000 (-> {:port 5000}
                  configuration-from-map
                  port)))
  (is (= "test" (-> {:app-name "test"}
                    configuration-from-map
                    app-name))))
