# 2IP90 CBL: Snake

Written by:
- Tymur Tykva (2275201)
- Borislav Grebanarov (2109832)

## Setup Instructions

There are no special setup instructions for the project: simply compile the project and run `Main.java`.

## Gameplay Instructions

Controls:
- `Arrow Keys`: to move the snake
- `P`: to pause the game
- `R`: to restart the game

Features to test:
- Tutorial showcasing the keybindings and apple.
  - Dismissable by any button press.
- Pause menu appears, and all buttons work on it.
- Game over menu appears, and all buttons work on it.
  - Appropriate death-specific death message appears.
- Pause button in the top panel works.
- Score updates upon apple consumption.
- Direction input queueing works.
- Apples:
    - Eating a red/yellow apple increases the snake's length by one.
    - Eating a red/yellow apple increases the score by one.
    - Eating a black apple ends the game.
    - Eating a yellow apple reverses the snake.
    - Black apples randomly turn into either a red/yellow apple.
    - Apples spawn in their predetermined spawning patterns:

Score Breakpoints | 0<sup>*</sup> | 6 | 12
--- | --- | --- | ---
Red Apples | 1 | 1 | 0.5
Black Apples | 0 | 1 | 1
Yellow Apples | 0 | 0 | 0.5

<sup>*</sup> Also applied when the amount of available spaces is insufficient.

- Collision:
  - Snake self-collision results in the game ending.
  - Snake colliding with the border results in the game ending.
  - Snake colliding with an apple triggers the appropriate behavior.
