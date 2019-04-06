program: compile run

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin Scheme $(arg1) $(arg2)

git:
	git add -A
	git commit -m "[AUTO]"
	git push

clean:
	rm ./bin/*.class