import strutils, tables

let filePath = "../data/input2"
let inputRaw = readFile(filePath).splitLines()

type Move = enum 
  Rock = 1 
  Paper 
  Scissors

type Result = enum 
  Win
  Lose
  Draw

func inputToMove(input: string): Move =
   case input:
   of "A": Rock
   of "B": Paper
   of "C": Scissors
   of "X": Rock
   of "Y": Paper
   of "Z": Scissors
   else: raise newException(ValueError, input & " is not a valid value")  

func losesTo(a: Move): Move =
    if a == Scissors: 
      Rock 
    else: 
      succ a

func winsAgainst(a: Move): Move =
    if a == Rock: 
      Scissors 
    else: 
      pred a
    
func processRound(opponentMove: Move, playerMove: Move): Result = 
  var res = Draw

  if playerMove == losesTo(opponentMove):
    res = Win
  elif opponentMove == losesTo(playerMove):
    res = Lose
  elif opponentMove == playerMove:
    res = Draw

  return res

  
var acc = 0
var acc2 = 0
let resultPoints = {Win: 6, Lose: 0, Draw: 3}.toTable

for round in inputRaw:
  let moves = round.split(" ")
  let opp = inputToMove(moves[0])
  let player = inputToMove(moves[1])
  let res = processRound(opp, player)
  let res2 = case moves[1]
              of "X": Lose
              of "Y": Draw
              of "Z": Win
              else: raise newException(ValueError, moves[1] & " is not valid")
  
  let player2 = case res2
                of Draw: opp
                of Win: losesTo(opp)
                of Lose: winsAgainst(opp)
  
  let scoreP1 = resultPoints[res] + ord(player)
  let scoreP2 = resultPoints[res2] + ord(player2)
  acc += scoreP1
  acc2 += scoreP2

echo "Total score P1: ", acc
echo "Total score P2: ", acc2
