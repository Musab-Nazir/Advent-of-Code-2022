(ns aoc-2022.day07
  (:require [clojure.string :as str]))

;; Load data
(def input (str/split-lines (slurp "./resources/day7_input.edn")))

(def test-input (str/split-lines "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"))

(defn process
  [cmd-list])
