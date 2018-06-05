import csv
import sqlite3
import time

timestamp = str(time.time())
conn = sqlite3.connect(":memory:")
c = conn.cursor()
c.execute('''CREATE TABLE myTable (A,B,C,D,E,F,G,H,I,J)''')

numRecords = 0
numSuccess = 0
numFailed = 0
with open('ms3Interview.csv', encoding="utf8") as f:
    with open("bad-data-" + timestamp + ".csv", "w", newline='', encoding="utf8") as badDataFile:
        reader = csv.reader(f, delimiter=',', quotechar='"')
        writer = csv.writer(badDataFile)
        writer.writerow(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'])
        for x in reader:
            if x:
                if not x[0] or not x[1] or not x[2] or not x[3] or not x[4] or not x[5] or not x[6] or not x[7] or not x[8] or not x[9]:
                    numFailed += 1
                    writer.writerow(x)
                else:
                    numSuccess += 1
                    query = '''INSERT INTO myTable VALUES ()'''
                numRecords += 1
    badDataFile.close()
    logFile = open("log.txt", "w")
    logFile.write("Records received: " + str(numRecords) + "\n")
    logFile.write("Records successful: " + str(numSuccess) + "\n")
    logFile.write("Records failed: " + str(numFailed))
    logFile.close()

conn.commit()
conn.close()

