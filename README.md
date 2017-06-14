# TicTacToe
This repo contain a console version of tic-tac-toe. Its core logic is also relied on by [https://github.com/mkrump/tic-tac-toe-api](https://github.com/mkrump/tic-tac-toe-api), and drives a React tic-tac-toe app [https://github.com/mkrump/tic-tac-toe-react](https://github.com/mkrump/tic-tac-toe-react).

## Prerequisites
- Install [Leiningen](https://leiningen.org/)

- Clone the repo

	```
	git clone https://github.com/mkrump/tic-tac-toe-clojure.git
	```

## Running the tests
```
lein test
```

## Play TicTacToe
```
lein run
```

## Building the project
```
lein uberjar
java -jar DIRECTORY_JAR_FILE/tictactoe-X.X.X-SNAPSHOT-standalone.jar
```
