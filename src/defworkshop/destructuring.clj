;; ## Destructuring, that's like destroying, right?
;;
;; Maybe you've come across languages with pattern matching like ML, Scala or
;; Haskell, maybe you haven't, so you know where this is going but if not, bear
;; with us.
;;
;; So, while programming it might have happened that you ran across a
;; collection datatype that you didn't want to process in whole. Maybe you just
;; wanted a bit of data out of it. Maybe the first element? Or maybe just some
;; key of a map? Something like this? The trivial way would be to use an
;; appropriate function to do this, like `first` or `get` and bind that value
;; to a name, in a `let` form. But what if you wanted to have the first,
;; second, third and fifth element without the forth? Four lines of `let`
;; statements would suck, it's violating DRY big time.
;;
;; Well, fear not, Clojure's got you covered, by providing something called
;; destructuring. The name is pretty descripting, it takes a (data) structure
;; and takes its structure apart for easy access to its insides.
;;
;; This sounds complicated, but really it's not, so how about a simple example?
(ns defworkshop.destructuring
  (:require [clojure.string :refer [join]]
            [workshoplib.tdd :refer […]]))

;; ### Dipping our toes in destructuring
;;
;; Some of you might be familiar with Python, which has a similar concept
;; called *tuple unpacking*. Provided `res` is a tuple (think: vector) you
;; could say `sender, receiver, message = res` and use the variables `sender`,
;; `receiver` and `message` in the code. We'll try to do the same here.
;;
;; In Clojure you can use destructuring both in a `let` form as well as in
;; `defn` (and of course also in `fn`) form. Basically, every time you assign
;; values, you can destructure if you need to.
;;
;; The first exercise is to write a function that takes an vector of three
;; elements and returns the the first and last element as a vector.

(defn ^:not-implemented unpack
  "Write a function which takes a vector containing first, middle
  and last name binds these to their own names and returns a vector of
  the first and last element"
  [_]
  (…))

;; Actually, there are cases where we don't need parts of the unpacked
;; collections. We didn't need the middle element. The convention then is to
;; name every element that we don't want or care about `_`. Yes, that's just an
;; underscore. An underscore is a valid name in Clojure, but in destructuring
;; it means "don't care" by convention.

;; ### Variable length unpacking
;;
;; Suppose we have a vector, where we don't want to unpack the whole thing but
;; just a part of it. This can have various reasons. Maybe we just want to
;; reimplement `first`? In the destructuring form we can use the `&` sign which
;; means: "bind the remaining structure to the name after the &".

;; Here's a simple example for you, we create the numbers 0..9 and assign them
;; to `a`, `b`, `c` and the rest to `rest`. The name `rest` is completely
;; arbitrary, too. You could also call `rest` `stephen` instead.

(let [[a b c & rest] (range 10)] rest)

;; You feel up for this? Alright, how about implementing `first` by yourself?

(defn ^:not-implemented head
  "Implement head using destructuring."
  [_]
  (…))

;; While we're on a roll, have your take on `tail` as well!

(defn ^:not-implemented tail
  "Implement tail using destructuring."
  [_]
  (…))

;; So, we saw that we can destructure the whole data structure. But what if we
;; want to destructure the data structure and keep a reference to the
;; still-structured data. We could of course do it in two steps:

(comment
  (let [our-data-structure ["Scheme" "Clojure" "Common Lisp"]
        [a b c] our-data-structure]
    (str "The middle element of "
         (join ", " our-data-structure)
         " is "
         b)))

;; but we can try to do it in one step, using `:as`:

(comment
  (let [[a b c :as our-data-structure] ["Scheme" "Clojure" "Common Lisp"]]
    (str "The middle element of "
         (join ", " our-data-structure)
         " is "
         b)))

;; Now your job is to write a function that takes a vector of numbers, uses its
;; second element to multiply it with every other and return the result.  As a
;; small hint, you should be using `_`, `&` and `:as` at some point, so we can
;; see all the concepts so far in action. For the multiplication, don't be
;; afraid to use `map`.

(defn ^:not-implemented multiply-second
  [_]
  (…))

;; As for a very last example of vector destructuring, let me tell you that
;; `for` also takes binding forms, so you could destructure in a `for` as well.
;; Here's a multiplication table:

(def pairs
  (for [a (range 1 11)
        b (range 1 11)]
    [a b]))

;; Now, write a function `for` that takes this vector of number pairs and uses
;; a `for` to destructure it and return the multiplied results.

(defn ^:not-implemented multiplication-table
  "Take a vector of number pairs and returns a vector of multiplied results"
  [_]
  (…))

;; So, that was more of the same, you should be proficient now with
;; destructuring vector. In fact, you should be a master of destructuring
;; vectors now! But vectors are not the only structure you can destructure. How
;; about maps?
;;
;; ### Map destructuring
;;
;; Similar to before, you can use a map and the key you want to have to extract
;; it.

(defn ^:not-implemented get-clojure-key
  [_]
  (…))

;; But you see, for every key you extract, you have to specify a binding name
;; (key `:clojure`, binding name `clojure`). This sucks. It's repetitive. Now
;; if there only was a way to use the key name as a binding name automatically.
;; Why of course, there is! You can use `:keys` to specify a vector of keys
;; that you want to get.

(defn ^:not-implemented get-clojure-key
  [_]
  (…))

(comment
  (get-clojure-key {:java "is verbose" :clojure "is awesome"}))

;; Now isn't that charming? Try it now yourself:

(defn ^:not-implemented project
  "Write a function that given a map returns a vector containing
the values associated with keys :k1 :k2 :k3"
  [_]
  (…))

;;; ### Bonus exercises

;; Now, let's do recursive destructuring. Since, you know, you can nest data
;; structures in data structures, you can nest descructuring in destructuring.
;; Yo dawg!
;;
;; First, here's a vector of vectors:

(defn ^:not-implemented recursive-destructuring
  "Write a function which takes a vector of vectors and returns the second"
  [_]
  (…))

;; to get you going, you could call it with a data structure like this:

(comment
  (recursive-destructuring [1 [2 3 4] 5]))

;; Now, try a map of maps. Some hints: first use the normal `:foo`
;; destructuring, then nest the `keys` destructuring.

(defn ^:not-implemented nested-access
  "given the following datastructure, extract [:user :name] and [:user :age] and
return them in a vector."
  [_]
  (…))

;; Here's an example call:
(comment
  (nested-access {:user {:name "Ada" :age "25" :occupation "Programmer"}
                  :builder {:name "Charles" :age 45 :occupation "Inventor"}}))

;; That concludes our session on destructuring. But wait there's even more (and
;; even more exotic). If you're interested check out chapter 3.3 of "Joy of
;; Clojure" (2nd edition) or search the internets.
