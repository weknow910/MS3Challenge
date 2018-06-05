Huy Ngo
MS3 Skills Challenge

---INSTRUCTIONS---
To compile and execute the application, run the commands:
```
	javac MS3Challenge.java
	java -classpath ".;sqlite-jdbc-3.7.2.jar" MS3Challenge
```

It will prompt you to enter a file name.  Make sure the file is in the same directory as the program.

------------------

The project was to implement a simple Java application that parses .CSV files and inserts
valid data entries into a SQLite In-Memory database.  The task was fairly straightforward, but
did not come without difficulties.

	First, I misinterpreted the instuctions and began coding the majority of the program in Python.
After realizing this, I transitioned into Java, and imported my Python implementation to Java.
Having worked with SQLite before, I found developing the update statements to be an easy task.
However, creating and establishing a connection with the driver to the local database proved to be
the most time consuming task, as I have only remotely accessed databases before.  I was required to install
SQLite and establish a path to the project, and it required a fair amount of trial and error before working.

	As for the actual parsing algorithm, it was quite simple; I used a Buffered Reader to read each line
of the CSV file, and if the line was a valid entry, I split each attribute into a list of Strings and inserted
them into the database via query.  If the row was invalid, it would be written to the "Bad Data" CSV file.  A
count of the specified statistics was tallied while iterating through the algorithm, which is efficient in terms
of time and space; It only iterates through the data file once (O(N) time), and it does not store any large data
structures other than the database.

Included is a sample Bad-data file and Log file for a successful execution, as well as the initial Python file
I began working on.


