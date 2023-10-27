(ns aoc-2022.day05
  (:require [clojure.string :as str]))

;; Load data
(def input (str/split (slurp "./resources/day5_input.edn") #"\n\n"))

(def procedure (str/split-lines (second input)))

(def init-state {:1 ["S" "Z" "P" "D" "L" "B" "F" "C"]
                 :2 ["N" "V" "G" "P" "H" "W" "B"]
                 :3 ["F" "W" "B" "J" "G"]
                 :4 ["G" "J" "N" "F" "L" "W" "C" "S"]
                 :5 ["W" "J" "L" "T" "P" "M" "S" "H"]
                 :6 ["B" "C" "W" "G" "F" "S"]
                 :7 ["H" "T" "P" "M" "Q" "B" "W"]
                 :8 ["F" "S" "W" "T"]
                 :9 ["N" "C" "R"]})

(defn process-step
  [state step-str]
  (let [[cnt from to] (re-seq #"\d+" step-str)
        cnt-int (Integer/parseInt cnt)
        atom-state (atom state)]

    (loop [x 0]
      (when (< x cnt-int)
        (let [from-vec (@atom-state (keyword from))
              to-vec (@atom-state (keyword to))]
          (swap! atom-state assoc (keyword to) (conj to-vec (peek from-vec)))
          (swap! atom-state assoc (keyword from) (pop from-vec))
          (recur (inc x)))))

    @atom-state))

(defn process-step-v2
  [state step-str]
  (let [[cnt from to] (re-seq #"\d+" step-str)
        cnt-int (Integer/parseInt cnt)
        atom-state (atom state)
        from-vec (@atom-state (keyword from))
        to-vec (@atom-state (keyword to))]

    (swap! atom-state assoc (keyword to) (into [] (concat to-vec (take-last cnt-int from-vec))))
    (swap! atom-state assoc (keyword from) (into [] (drop-last cnt-int from-vec)))

    @atom-state))

(def part1-final-state (reduce process-step init-state procedure))

(def part2-final-state (reduce process-step-v2 init-state procedure))

(defn print-final-order
  [state]
  (let [key-list (map str (range 1 10))
        rr (reduce (fn [acc k]
                     (concat acc (peek (state (keyword k))))) "" key-list)]
    (apply str rr)))

(print-final-order part1-final-state)

(print-final-order part2-final-state)
