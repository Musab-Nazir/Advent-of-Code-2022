(ns aoc-2022.day02
  (:require [clojure.string :as str]))

;; Load data
(def input (str/split-lines (slurp "./resources/day2_input.edn")))

(def opponent-key {"A" :rock
                   "B" :paper
                   "C" :scissors})

(def player-key {"X" :rock
                 "Y" :paper
                 "Z" :scissors})

(def player-key-2 {"X" :lose
                   "Y" :draw
                   "Z" :win})

(def shape-score {:rock 1
                  :paper 2
                  :scissors 3})

(defn get-round-score
  [op pl]
  (cond
      ;; draw
    (= op pl) 3

      ;; win
    (and (= op :rock) (= pl :paper)) 6
    (and (= op :paper) (= pl :scissors)) 6
    (and (= op :scissors) (= pl :rock)) 6

   ;; loss 
    (and (= op :scissors) (= pl :paper)) 0
    (and (= op :rock) (= pl :scissors)) 0
    (and (= op :paper) (= pl :rock)) 0))

(defn get-total-round-score
  [round-str]
  (let [[opponent player] (str/split round-str #" ")
        op (get opponent-key opponent)
        pl (get player-key player)]
    (+ (shape-score pl) (get-round-score op pl))))

(defn get-total-round-score-2
  [round-str]
  (let [[opponent player] (str/split round-str #" ")
        op (get opponent-key opponent)
        pl (get player-key player)]
    (+ (shape-score pl) (get-round-score op pl))))

(def total-score (reduce + (mapv get-total-round-score input)))
