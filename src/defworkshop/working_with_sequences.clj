(ns defworkshop.working-with-sequences
  (:require [workshoplib.tdd :refer […]]))

(defn ^:not-implemented split-head-and-tail
  "Split head an tail of sequence by using `first` and `next` functions"
  [list]
  (…))

(defn ^:not-implemented get-nth
  "Now let's write a function that retrieves nth element from the sequence. For that, you can use `nth`"
  [seq n]
  (…))

(defn ^:not-implemented conj-to-list
  "Conj is an operator that adds element to the list. In case with lists, it
   appends items in a reverse order."
  [list item]
  (…))

(defn ^:not-implemented conj-to-vector
  "Conj operator with vector appends to tail of vector"
  [vector item]
  (…))

(defn ^:not-implemented remove-from-set
  "Remove an element from a set using `disj`"
  [s to-remove]
  (…))

(defn ^:not-implemented rev-concat
  "given two sequences `s1` and `s2`, concatenate them such that the resulting sequence starts with the last element of `s2` and ends with the first element of `s1`. Hint: there's a function called `reverse` that might be of help."
  [s1 s2]
  (…))

(defn ^:not-implemented interleave-seqs
  "given two sequences `s1` and `s2`, interleave them, starting with the shorter sequence."
  [s1 s2]
  (…))

(defn ^:not-implemented split-head-and-tail-destructuring
  "Split head and tail of sequence by destructuring the input. Here, instead of `list` (see split-head-and-tail above), use [[head & tail]] which will assign the first element to `head` and
   the rest of sequence (denoted by &) to the tail."
  [list]
  (…))

(defn ^:not-implemented contains-index
  "`contains` is a somewhat confusing function. Usually people expect (contains? [1 2 3] 3) to return
   true, but it returns false, since it checks wether the sequence contains element with `key` (index, in
   case with vectors."
  [coll index]
  (…))

(defn ^:not-implemented real-contains
  "But if you use `some` instead of `contains` it will produce a desirable result. Let's implement a real
   contains with `some` and equality predicate"
  [coll item]
  (…))

(defn ^:not-implemented double-all
  "Double each item in sequence by using `map`."
  [l]
  (…))

(defn ^:not-implemented add-5-to-all
  "Add 5 to each item in an array by using `map` and an anonymous function"
  [l]
  (…))

(defn ^:not-implemented add-5-to-all-with-for
  [l]
  (…))

(defn ^:not-implemented print-combinations
  "given two sequences `s1` and `s2`, print each combination of their elements where the elements aren't equal as a vector."
  [s1 s2]
  (…))

(defn ^:not-implemented split-to-pairs
  "Given a sequence, split it to pairs.

   You can use `partition` function for that."
  [seq]
  (…))

(defn ^:not-implemented moving-average
  "given a sequence of numbers, calculate the moving average using `partition`."
  [nums window-size]
  (…))

(defn ^:not-implemented sum
  "Calculate a sum of numbers in a list by using `reduce`"
  [list]
  (…))

(defn ^:not-implemented even-only
  "Filter even items from the list by using `filter` and `even?`"
  [l]
  (…))

(defn ^:not-implemented odd-only
  "Filter out all the even items from the list, using `remove` (opposite of `filter`) and
   `even?`"
  [l]
  (…))

(defn ^:not-implemented sum-of-squares-of-even-numbers
  "This excercise will help you to understand threading operator in Clojure"
  [coll]
  (…))

(defn ^:not-implemented get-part-of-vector
  "In order to get part of the vector, you can use `subvec`."
  ([vec start]
     (…))
  ([vec start end]
     (…)))

(defn ^:not-implemented sort-sequence
  "You often need to have a sequence sorted, for that you can use `sort` function"
  [seq]
  (…))

(defn ^:not-implemented sort-maps
  "If you have a sequence of maps that has a structure [{:name \"Alex\" :age 25} {:name \"Maxi\" :age 26}],
   you can use `sort-by` to sort these maps by :age key"
  [seq]
  (…))

(defn ^:not-implemented interpolate-words
  "Given a string of format \"Hello %name%, you're %age% years old!\", and map {:name \"Alex\" :age 25},
   write an interpolation function that would replace %name% with \"Alex\" and %age% with 25."
  [string m]
  (…))

(defn ^:not-implemented repeat-times
  "For the given `number`, return a sequence where this number is repeated `n` times"
  [number n]
  (…))

(defn ^:not-implemented read-and-summarize-contents
  "Given a filename of the file that holds numbers (newline-separated), write a function that parses numbers in file,
   summarizes them and returns. You can use `read-string` to parse the particular number."
  [filename]
  (…))

(defn ^:not-implemented reduce-assoc
  "In this function, use reduce to get a hash map from vector of pairs.

   This example illustrates how to use destructuring"
  [list]
  (…))

(defn ^:not-implemented stringify-map
  "Given an array of name and age, return a string that contains name and age in format `<name>: <age>`

   For that, use destructuring for keys and values and `str` to compose a new string."
  [m]
  (…))

(defn ^:not-implemented select-keysp
  "given a map `m`, return a map containing only the key-value pairs that satisfy `pred`."
  [m pred]
  (…))

(defn ^:not-implemented invert-map+
  "given a map, invert the mapping as previously. this time, however we will deal with duplicates in a defined way: for duplicate values in the original map, the inverted map should contain a mapping from value to a set of keys."
  [m]
  (…))
