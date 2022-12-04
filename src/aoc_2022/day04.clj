(ns aoc-2022.day04
  (:require [clojure.string :as str]))

;; Load data
(def input (str/split-lines (slurp "./resources/day4_input.edn")))

(defn is-subset?
  [s]
  (let [[r1 r2] (str/split s #",")
        [start1 end1] (str/split r1 #"-")
        [start2 end2] (str/split r2 #"-")]
    (or (and (<= (Integer/parseInt start1) (Integer/parseInt start2))
             (>= (Integer/parseInt end1) (Integer/parseInt end2)))

        (and (<= (Integer/parseInt start2) (Integer/parseInt start1))
             (>= (Integer/parseInt end2) (Integer/parseInt end1))))))

(defn does-overlap?
  [s]
  (let [[r1 r2] (str/split s #",")
        [start1 end1] (str/split r1 #"-")
        [start2 end2] (str/split r2 #"-")]
    (and (<= (Integer/parseInt start2) (Integer/parseInt end1))
         (<= (Integer/parseInt start1) (Integer/parseInt end2)))))

(def test-data ["2-4,6-8"
                "2-3,4-5"
                "5-7,7-9"
                "2-8,3-7"
                "6-6,4-6"
                "2-6,4-8"
                "5-7,1-3"])

(def test-res (mapv is-subset? test-data))

(def test-res2 (mapv does-overlap? test-data))

(def results (mapv is-subset? input))

(def results2 (mapv does-overlap? input))

(count (filterv true? results))

(count (filterv true? results2))
