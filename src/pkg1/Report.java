package pkg1;

//Sql.java -- sample program to read a database
//Configure the database for ODBC access using Start->Settings->Control Panel->ODBC32
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rptGenerator.Cell;
import rptGenerator.Generator;
import rptGenerator.Row;
import rptGenerator.RowCache;

public class Report {
	 static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	 static final String DB_URL = "jdbc:sqlserver://CHUCK-PC\\SQLSERVERADVSP2;database=chuckDB;integratedSecurity=true;";

	 //  Database credentials
	 static final String USER = "Chuck-PC\\Chuck"; //DriverManager.getConnection(connectionUrl,"Chuck-PC\\Chuck","");
	 static final String PASS = "";

public static void main(String[] args)
{
 // attempt to connect to the ODBC database
 //String db = "myDatabase"; // ODBC database name
 //System.out.println("Attempting to open database ...");
 Connection conn = null;
 Statement stmt = null;
 try
 {
	    Class.forName(JDBC_DRIVER);

	    //STEP 3: Open a connection
	    //System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    stmt = conn.createStatement();
 }
 catch (Exception ex)
 {
	System.out.println(ex.getMessage()); 
   // if not successful, quit
   System.out.println("Cannot open database -- make sure connection is configured properly.");
   System.exit(1);
 }

 /* create a table
 String cr8 = "create table courses (desig varchar(12),term varchar(12),units integer,grade varchar(1))";
 System.out.println("Executing " + cr8);
 try
 {
   stmt.executeUpdate(cr8);
 }
 catch (Exception ex)
 {
   // error executing SQL statement
   System.out.println("Error: " + ex);
 }
*/
 // create an INSERT statement
 String sql = "INSERT INTO courses (desig, term, units, grade) VALUES ('COMSC-265', 'FA2001', 4, 'A')";
 //System.out.println("Executing " + sql);
 //try
 //{
 //  stmt.executeUpdate(sql);
 //}
 //catch (Exception ex)
 //{
 //  // error executing SQL statement
 //  System.out.println("Error: " + ex);
 //}

 // create another SQL statement
 sql = "SELECT desig,term,units,grade FROM courses order by desig, term";
 ResultSet rs = null;
 try
 {
   rs = stmt.executeQuery(sql);
 }
 catch (Exception ex)
 {
   // error executing SQL statement
   System.out.println("Error: " + ex);
 }

 final String STRING = "String";
 final String INTEGER = "Integer";
 final String DESIG = "Desig";
 final String TERM = "Term";
 final String UNITS = "Units";
 final String GRADE = "Grade";
 RowCache cache = new RowCache();
 List<Row> rowList = new ArrayList<Row>();

 // show records (skip for INSERT, DELETE, or UPDATE)
 try {
    while (rs.next()) {
    	
    	Row row = new Row();
    	List<Cell> cellList = new ArrayList<Cell>();

     	String desig = rs.getString(1); // read 1st column as text
    	Cell cell0 = loadCell(STRING,DESIG,0,desig);
     	cellList.add(cell0);
     	
     	String term = rs.getString(2); // read 2nd column as text
    	Cell cell1 = loadCell(STRING,TERM,1,term);
     	cellList.add(cell1);
     	
     	int units = rs.getInt(3); // read 3rd column as int
    	Cell cell2 = loadCell(INTEGER,UNITS,2,Integer.toString(units));
     	cellList.add(cell2);
  	
     	String grade = rs.getString(4); // read 4th column as text
    	Cell cell3 = loadCell(STRING,GRADE,3,grade);
  		cell3.setLabel(GRADE);
  		cellList.add(cell3);

  		row.setList(cellList);
     	rowList.add(row);
    }
 	cache.setList(rowList);
 }
 catch (Exception ex)
 {
   // error executing SQL statement
   System.out.println("Error: " + ex);
 }

 // close database
 try
 {
   conn.close();
 }
 catch (Exception ex){}

 try {
	 
	 Generator generator = new Generator(4,10,120); //nbr cols, data length, linelength
	 generator.setHeading1(sql);
	 Calendar cal = Calendar.getInstance();
	 String dfString = DateFormat.getDateInstance().format(cal.getTime());
	 generator.setHeading2(dfString);
	 generator.setHeading3(" ");
	 generator.setControlBreak1(DESIG);
	 generator.setControlBreak2(TERM);
	 generator.setControlBreak3(null);
	 generator.setFooting1("*");
	 generator.setFooting2("**");
	 generator.setFooting3("End of Report");
	 generator.execRpt(cache);
	 
 } catch(Exception rex) {
	 System.out.println(rex.getMessage());
	 rex.printStackTrace();
 }
 }
/*
 * loadCell is a utility method to create and load a standard cell object
 */
static public Cell loadCell(String dataType, String label, int number, String value) {
	Cell cell = new Cell();
 	cell.setDataType(dataType);
 	cell.setLabel(label);
 	cell.setNumber(number);
 	cell.setValue(value);
 	return cell;
}
 
}
