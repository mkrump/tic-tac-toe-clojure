# Tic-tac-toe
This repo contains a console version of Tic-tac-toe. Its core logic is also relied on by [https://github.com/mkrump/tic-tac-toe-api](https://github.com/mkrump/tic-tac-toe-api), which drives a React tic-tac-toe app [https://github.com/mkrump/tic-tac-toe-react](https://github.com/mkrump/tic-tac-toe-react).

## Requirements
- Install [Leiningen](https://leiningen.org/)

- Clone the repo

	```
	git clone https://github.com/mkrump/tic-tac-toe-clojure.git
	```

## Running the tests
```
lein test
```

## Play Tic-tac-toe
```
lein run
```

## Building the project
```
lein uberjar
java -jar DIRECTORY_JAR_FILE/tictactoe-X.X.X-SNAPSHOT-standalone.jar
```
