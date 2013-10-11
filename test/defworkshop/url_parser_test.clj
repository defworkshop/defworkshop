(ns defworkshop.url-parser-test
  (:require [defworkshop.url-parser :refer :all]
            [workshoplib.tdd :refer [deftest is]]))

(deftest named-argument-test
  (is (named-argument? ":asd"))
  (is (not (named-argument? "asd"))))

(deftest get-parts-test
  (is (= ["first" "second" "third"]) (get-parts "/first/second/third")))

(deftest uri-matches?-test
  (is (uri-matches? "/people" "/people"))
  (is (uri-matches? "/people/1" "/people/:id"))
  (is (not (uri-matches? "/people/1" "/people")))
  (is (uri-matches? "/people/1" "/people/1"))
  (is (not (uri-matches? "/people/1/2" "/people/:id"))))

(deftest extract-arguments-test
  (is (= {:id "person_id"} (extract-arguments "/people/person_id" "/people/:id")))
  (is (= {} (extract-arguments "/people" "/people"))))

(deftest dispatch-test
  (let [route-map (partition 2 ["/people" #(assoc % :route :index)
                                "/people/:id" #(assoc % :route :show)
                                "/people/:id/edit"#(assoc % :route :edit)])]
    (is (= {:route :index} (dispatch "/people" route-map)))
    (is (= {:route :show :id "123"} (dispatch "/people/123" route-map)))
    (is (= {:route :edit :id "123"} (dispatch "/people/123/edit" route-map)))))
