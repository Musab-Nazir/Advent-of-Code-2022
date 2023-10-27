import strutils, algorithm 

let filePath = "../data/input1"
let inputRaw = readFile(filePath)
var processed: seq[int] = @[]
var acc = 0

for item in splitLines(inputRaw):
  if item == "":
    processed.add(acc)
    acc = 0
  else:
    let intVal = parseInt(item)
    acc += intVal

let sortedList = processed.sorted(SortOrder.Descending)
let sum = (sortedList[0] + sortedList[1] + sortedList[2])

echo "Most calories carried by an elf: ", sortedList[0]
echo "Most calories carried by top 3 elves: ", sum
