# MajongScoreSystem
This is a very simple (and probably incredibly poorly tested!) program which goal is to automate a process of counting Majong scores.


## How to use the application to count the scores.
### Step One - Setup.
- When you launch the application you should type a name of a file that either matches a filename of one of the previous
games files, or it should be a totally new file. Either way, make sure that you include `.txt` format after the filename.
Here is an example: `game-03-20-2022.txt`. The format must be `.txt` 

Next setup step only applies if you are setting a brand-new game.
- Next you should select players' names in the following direction:
  1. East
  2. South
  3. West
  4. North

## Step Two - Recording the Scores.
 - After you finish a round you will need to press `Enter` after seeing this message:
 `When you finish the round, please press Enter`.
 - Then write a number (from 1 to 4) to indicate which player won the round.
 - Enter the combinations for each player in the order that is shown on the screen.
>#### _How to Enter combinations:_
>[Here](https://github.com/adtimokhin/MajongScoreSystem/blob/master/src/main/java/com/adtimokhin/ScoreCounter.java) you will find a copy of a game file that stores all possible combination shortcuts. Here is a copy of these shortcuts:
```properties
    # Open three cards that are the same but are not 1s or 9s.
    OPEN_THREE_NOT_SPECIAL_IN_A_ROW_SYMBOL = OTA
    OPEN_THREE_ONE_OR_NINE_IN_A_ROW_SYMBOL = OT1
    OPEN_THREE_DRAGONS_SYMBOL = OTD
    OPEN_THREE_WINDS_SYMBOL = OTW


    OPEN_FOUR_NOT_SPECIAL_IN_A_ROW_SYMBOL = OFA
    OPEN_FOUR_ONE_OR_NINE_IN_A_ROW_SYMBOL = OF1
    OPEN_FOUR_DRAGONS_SYMBOL = OFD
    OPEN_FOUR_WINDS_SYMBOL = OFW

    CLOSE_THREE_NOT_SPECIAL_IN_A_ROW_SYMBOL = CTA
    CLOSE_THREE_ONE_OR_NINE_IN_A_ROW_SYMBOL = CT1
    CLOSE_THREE_DRAGONS_SYMBOL = CTD
    CLOSE_THREE_WINDS_SYMBOL = CTW

    CLOSE_FOUR_NOT_SPECIAL_IN_A_ROW_SYMBOL = CFA
    CLOSE_FOUR_ONE_OR_NINE_IN_A_ROW_SYMBOL = CF1
    CLOSE_FOUR_DRAGONS_SYMBOL = CFD
    CLOSE_FOUR_WINDS_SYMBOL = CFW

    # write this command when a player has at least one sequence of numbers (like 1,2,3):
    SEQUENCE_NOT_SPEACIAL_SYMBOL = S

    PAIR_DRAGONS_SYMBOL = PD
    PAIR_OWN_WINDS_SYMBOL = OW
    PAIR_ANY_WINDS_SYMBOL = PW

    # This symbol stops entering combinations for a current player:
    STOP_SYMBOL = - 

```
> #### Important! The system only understands the commands (ie shortcuts) from the above list. 
> After selecting a type of combination that you want to select for a given player, enter it into the command line as it
> appears in the shortcut. Some combinations (like open combination of 3 dragons, or a combination of 2s) will ask you to answer certain questions to award you required doubles.
> 
> #### Important! Enter all combinations a player has. You can type the same combination command into the system for a single player. I encourage to do so if: 
>
>1. Player has multiple occurrences of a given combination
>2. The system did not ask how many combinations like this you have.
> 
> The system will ask to enter any additional doubles that a winning player deserves. If none are required type 0 
> (most commonly you will type 0 when you are asked).


- When you do this operation for all four players the system will display the calculated scores for all players.


## What's Next?
Well, enjoy the game! When you are done playing for the day, save the combinations for the final round you've played and
close the program. Make a note of a filename that you used for that game so that you can load the game later.
