(ns defworkshop.polymorphism-test
  (:require [defworkshop.polymorphism :refer :all]
            [clojure.test :refer [testing]]
            [workshoplib.tdd :refer [deftest is]]))

(deftest message-logging-test
  (is (= "Host 1 initiating conversation with host 2"
         (log {:type :init :from 1 :to 2})))
  (is (= "Host 3 to host 4: hello"
         (log {:type :message :from 3 :to 4 :message "hello"})))
  (is (= "Host 5 sending image.jpg to host 6"
         (log {:type :file :from 5 :to 6 :file {:name "image.jpg" :payload ""}})))
  (is (= "Host 7 closed the conversation with host 8"
         (log {:type :end :from 7 :to 8}))))

(deftest bounded-queue-enqueue-test
  (is (= [1] (enqueue [] 1)))
  (is (= [1 2 3 4 5] (enqueue [1 2 3 4] 5)))
  (is (thrown? RuntimeException (enqueue [1 2 3 4 5 6 7 8 9 10] 11))))

(deftest bounded-queue-dequeue-test
  (is (thrown? RuntimeException (dequeue [])))
  (is (= [1 [2 3 4]] (dequeue [1 2 3 4])))
  (is (= [1 [2 3 4 5 6 7 8 9 10] (dequeue [1 2 3 4 5 6 7 8 9 10])])))

(deftest user-routes-test
  (is (= "Listing all users" (route {:uri "/users" :method :get})))
  (is (= "Creating user" (route {:uri "/users" :method :post})))
  (is (= "Updating user 1" (route {:uri "/users" :method :put :params {:id 1}})))
  (is (= "Deleting user 1" (route {:uri "/users" :method :delete :params {:id 1}}))))

(deftest item-routes-test
  (is (= "Listing all items" (route {:uri "/items" :method :get})))
  (is (= "Creating item" (route {:uri "/items" :method :post})))
  (is (= "Updating item 1" (route {:uri "/items" :method :put :params {:id 1}})))
  (is (= "Deleting item 1" (route {:uri "/items" :method :delete :params {:id 1}}))))

(deftest not-found-routes-test
  (is (= "Not found" (route {:route "/test" :method :get}))))

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
