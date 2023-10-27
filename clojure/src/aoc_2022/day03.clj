(ns aoc-2022.day03
  (:require [clojure.string :as str]
            [clojure.set :refer [intersection]]))

;; Load data
(def input (str/split-lines (slurp "./resources/day3_input.edn")))

(def groups (partition 3 input))

(defn process-rucksack
  [str-contents]
  (let [[first-compart second-compart] (partition (/ (count str-contents) 2) str-contents)
        s1 (into #{} first-compart)
        s2 (into #{} second-compart)]
    (first (intersection s1 s2))))

(defn item-score [item]
  (if (<= (int \a) (int item) (int \z))
    (+ 1  (- (int item) (int \a)))
    (+ 27 (- (int item) (int \A)))))

(def scores (reduce (fn [acc r]
                      (let [item (process-rucksack r)
                            item-priority (item-score item)]
                        (conj acc item-priority))) [] input))

(defn process-group 
  [group] 
  (let [s1 (reduce (fn [acc chr] 
                   (conj acc chr)) #{} (first group))
        s2 (reduce (fn [acc chr] 
                   (conj acc chr)) #{} (second group))
        s3 (reduce (fn [acc chr] 
                   (conj acc chr)) #{} (nth group 2))]
    (first (intersection s1 s2 s3))))

(process-group (first groups))

(def scores2 (reduce (fn [acc g]
                      (let [item (process-group g)
                            item-priority (item-score item)]
                        (conj acc item-priority))) [] groups))

(reduce + scores)

(reduce + scores2)
