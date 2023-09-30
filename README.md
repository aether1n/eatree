# Eatree - A Timberman Remake

Eatree is an arcade-style game in which the player controls the bear named Oak. In the 
center there’s a large tree that can have branches on it. The player is tasked with navigating 
Oak to eat the woods from the tree, while at the same time must evade the branches that 
are coming from above.


## How to play

The main goal of this game is to achieve highest score possible. Use A key or left arrow key 
to go left, D key or right arrow key to go right. Avoid hitting the branches but keep your 
eating pace so that you don’t run out of time!


## Installation

To run this application, clone this repository. Open your desired IDE 
and open the src folder. Run the Main method inside the eatree package. (Optional: 
Change the directory in writeSaveData() method to the directory of your scores.txt file to 
save best score).


## Execution in JShell

The model code of this game is also runnable in JShell. To execute the game, the following 
steps are necessary:
1. Open Command Prompt (Win+R > type cmd)
2. Navigate to the directory of the game by using change directory (cd)
3. Open JShell using the command below to import external libraries to JShell.
```cmd
jshell –class-path out\production\eatree
```
4. Import the model class of Eatree by using
```cmd
import eatree.model.EatreeModel
```
5. Create a new instance of the model class using
```cmd
var game = new EatreeModel()
```
6. The game will now successfully be created. To play, use the function
```cmd
move(“left”)
```
or
```cmd
move(“right”)
```
7. If you lose, a game over text will show up alongside the score achieved.


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.


## Resources

Some of the game’s algorithm are referenced from [OpenProcessing](https://openprocessing.org/sketch/731769/)

Writing and loading game scores from [Stack Overflow](https://stackoverflow.com/questions/37217078/savinggame-scores)

Timer thread referenced from [a video on YouTube](https://www.youtube.com/watch?v=HZTlQ6rSf_c)

Chew Sound Effect by 666HeroHero from [Pixabay](https://pixabay.com/sound-effects/chew21768/)

Branch image from [Pixel Art Maker](http://pixelartmaker.com/art/808fd4e033e7145)

Bear image from [Pinterest](https://www.pinterest.com/pin/340795896806958203/) (I turned it into a gif)

Wood image from [Pixil Art](https://www.pixilart.com/art/oak-wood-log-4f7e90163ba6436) (I edited it again)


## License

[MIT](https://choosealicense.com/licenses/mit/)
