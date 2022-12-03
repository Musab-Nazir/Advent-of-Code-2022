(ns aoc-2022.day01
  (:require [clojure.string :as str]))

;; Load data
(def calories-input (str/split (slurp "./resources/day1_input.edn") #"\n\n"))

(def elf-groups-str (mapv str/split-lines calories-input))

(def elf-totals (reduce (fn [acc elf-calories]
                          (let [elf-calories-int (mapv #(Integer/parseInt %) elf-calories)]
                            (conj acc (reduce + elf-calories-int)))) [] elf-groups-str))

(apply max elf-totals)
(apply + (take-last 3 (sort elf-totals)))
