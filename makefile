program: clean compile run

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin EightPuzzleSearchAgent

git:
	git add -A
	git commit -m "[AUTO]"
	git push

clean:
	rm ./bin/*.class