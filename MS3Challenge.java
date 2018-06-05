//Huy Ngo
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.*;
import java.io.BufferedReader;
import java.util.Scanner;

public class MS3Challenge {

	//Simple Java application that parses .csv files with ten attributes, inserts it into an in-memory
	//SQLite Database, and eliminates any invalid entries.
	public static void main(String[] args) {
		try {
			//Integers to log statistics
			int numRecords = 0, numSuccess = 0, numFailed = 0;
			String line;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			//Establish a connection to the database location - in this case the local cache
			Class.forName("org.sqlite.JDBC");
			Connection conn = null;
			Statement stmt = null;
			conn = DriverManager.getConnection("jdbc:sqlite::memory:");
			System.out.println("Opened database successfully.");
			
			//Create a table in the DB with ten attributes
			stmt = conn.createStatement();
			String sql = "CREATE TABLE myTable (A TEXT, B TEXT, C TEXT, D TEXT, E TEXT, F TEXT, G TEXT, H TEXT, I TEXT, J TEXT)";
			stmt.executeUpdate(sql);
			System.out.println("Table created Successfully.");
			
			//Retrieve input file
			Scanner scan = new Scanner(System.in);
			System.out.print("Please enter the file name: ");
			String input = scan.next();
			
			File inFile = null;
			if (!input.isEmpty())
				inFile = new File(input);
			else
		    {
		       System.err.println("Invalid file name.");
		       System.exit(0);
		    }
			
			//Code for file parser, initializes a new file for bad data
			FileReader fileReader = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fileReader);
			PrintWriter writer = new PrintWriter("bad-data-" + timestamp.getTime() + ".csv", "UTF-8");
			
			//Add headers from input file to the bad data file
			line = br.readLine();
			writer.println(line);
			
			//iterate through the entire file
	    	while((line = br.readLine()) != null)
	    	{
	    		//split each attribute and store it into a temporary list of strings
	    		String[] currentLine = line.split(",");
	    		//if any of the attributes are empty, then the whole row is invalid and will not be added to the DB
	    		if(currentLine[0].isEmpty() || currentLine[1].isEmpty() || currentLine[2].isEmpty() || currentLine[3].isEmpty()
	    				 || currentLine[4].isEmpty() || currentLine[5].isEmpty() || currentLine[6].isEmpty() || currentLine[7].isEmpty()
	    				 || currentLine[8].isEmpty() || currentLine[9].isEmpty())
	    		{
	    			writer.println(line);	//the row is added to the bad data file
	    			numFailed++;
	    		}   		
	    		//otherwise, add the row to the database with an insert statement
	    		//column five is put in double quotes because all entries contain commas
	    		else
	    		{
	    			sql = "INSERT INTO myTable VALUES ('"+ currentLine[0] + "','" + currentLine[1]
	    					+ "','" + currentLine[2] + "','" + currentLine[3]
	    					+ "','\"" + currentLine[4] + "\"','" + currentLine[5]
	    					+ "','" + currentLine[6] + "','" + currentLine[7]
	    					+ "','" + currentLine[8] + "','" + currentLine[9] + "')";
	    			stmt.executeUpdate(sql);
	    			numSuccess++;
	    		}
	    		numRecords++;
	    	}
	    	scan.close();
	        br.close();
			writer.close();
			stmt.close();
			conn.close();
			
			System.out.println("Records received: " + numRecords);
			System.out.println("Records successful: " + numSuccess);
			System.out.println("Records failed: " + numFailed);
			
			//record the statistics and write it to a log file
			writer = new PrintWriter("log.txt", "UTF-8");
			writer.append("Records received: " + numRecords);
			writer.append("\n");
			writer.append("Records successful: " + numSuccess);
			writer.append("\n");
			writer.append("Records failed: " + numFailed);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
