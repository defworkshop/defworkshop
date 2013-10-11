(ns defworkshop.concurrency-test
  (:use defworkshop.concurrency)
  (:require [workshoplib.tdd :refer [deftest is]]))

(deftest make-bank-account-test
  (is (= 5 @(make-bank-account 5))))

(deftest deposite-test
  (let [account (make-bank-account 5)]
    (deposite account 5)
    (is (= 10 @account))))

(deftest withdraw-test
  (let [account (make-bank-account 5)]
    (withdraw account 5)
    (is (= 0 @account))))

(deftest get-balance-test
  (let [account (make-bank-account 5)]
    (is (= 5 (get-balance account)))))

(deftest tumbling-window-test
  (let [res    (atom nil)
        window (tumbling-window 3 #(reset! res %))]
    (window 1)
    (window 2)
    (window 3)
    (is (= [1 2 3] @res))

    (window 4)
    (window 5)
    (window 6)
    (is (= [4 5 6] @res))))
