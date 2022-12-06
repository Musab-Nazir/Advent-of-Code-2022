(ns aoc-2022.day06
  (:require [clojure.string :as str]))

;; Load data
(def input (slurp "./resources/day6_input.edn"))

(def test-input "mjqjpqmgbljsphdztnvjfqwrcgsmlb")

(defn process-buffer
  [distinct-limit s]
  (let [block (atom [])
        total-count (atom 0)]
    (loop [x 0]
      (when (< (count @block) distinct-limit)
        (let [ch (nth s x)
              exists (some #(= ch %) @block)
              sub-vector (when exists
                           (.indexOf @block ch))]
          (when exists
            (reset! block (into [] (subvec @block (inc sub-vector)))))
          (swap! block conj ch))
        (swap! total-count inc)
        (recur (inc x))))
    {:marker (apply str @block)
     :result @total-count}))

(process-buffer 4 input)    ;part1
(process-buffer 14 input)   ;part2
