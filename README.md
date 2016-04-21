# siregeja
A Simple Report Generator for Java

Copyright (C) 2015  Chuck Runge
Lombard, IL.
CGRunge001@GMail.com

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 

SIREGEJA

Siregeja provides utility methods for simple text reports.  It is intended as a supplement for sidacoja (data conversion for tabular data), but it contains the Row and Cell classes needed to create a RowCache.  Any program that builds a RowCache can run a text report.  

Standard report features (headings, footings, control breaks and totals) are supported.  Three of each are available.  In most cases, if a value is not provided, the users can provide it and pass it in.

RELEASE NOTES
0.0.1 - This is the first working model.
- Added SQL example, and sidacoja example.
- Added JUnit test classes
- Converted to Maven and added POM file.

INTRODUCTION

A RowCache is the conttainer for a list of rows.  A Row is a container for a list of cells.  A Cell contains everything we know about a cell (name, value, type, sequence).  This is the object input to the report generator.

For the most basic report, simply create a generator object, and call execRpt with a RowCache object.

	 Generator generator = new Generator(4,16,132); //nbr cols, data length, linelength
	 generator.execRpt(cache);

There are three parameters.  The first is the number of columns, the 2nd is the width of a column, and the third is the length of a line.  These numbers are used to neatly spread the columns across the report.

PROCESSING 

Headings are optional strings supported by getters and setters.  The value supplied is centered and printed after every page break.  If there are particular values you'd like to see, just provide them in a heading.  Three levels of headings are provided.	 
		 
	 generator.setHeading1(sql);
	 Calendar cal = Calendar.getInstance();
	 String dfString = DateFormat.getDateInstance().format(cal.getTime());
	 generator.setHeading2(dfString);
	 generator.setHeading3(" ");
	 
Control breaks are used to print subtotals.  The expectation is that these are numeric columns with a data type of "Integer".  The control break is activated when a cell with a matching label changes value.  The conreol breaks have to be in sync with the sort order of the data.  Printed subtotals are comma delimited.

	 generator.setControlBreak1(DESIG);
	 generator.setControlBreak2(TERM);
	 generator.setControlBreak3(null);
	 
Footings are activated by submitting a value, but totals are printed automatically when control breaks are activated. 

	 generator.setFooting1("*");
	 generator.setFooting2("**");
	 generator.setFooting3("End of Report");
	 