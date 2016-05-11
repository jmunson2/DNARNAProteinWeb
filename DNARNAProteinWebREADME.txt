* ABOUT *

The DNARNAProteinWeb NetBeans web project converts a coding DNA sequence (CDS) strand (5' to 3' direction) to its reverse complementary, RNA, and protein strand (each in the 5' to 3' direction). A message is provided to the user depending on the validity of the provided DNA strand. The input form for the CDS strand and the results are displayed on the same web page.

Web page: http://convertdna-jmunson2.rhcloud.com/DNARNAProteinWeb/

* MAIN CONTENTS OF DNARNAProteinWeb *

- DNARNAProteinWeb_README.txt : this file
- css/
   dnaconverter.css : CSS for index.html and HTML results (see ConvertDNAServlet.java)
- index.html : HTML file with input form 
- js/ 
   dnaconverter.js : JavaScript file used to display results on the same page as the input form (with AJAX), among other things
- java/
   ConvertDNA.java : The ConvertDNA class provides methods to convert a coding DNA sequence (CDS) strand (5' to 3' direction) to its reverse complementary, RNA, and protein strand.
   ConvertDNAServlet.java :  The ConvertDNAServlet class carries out methods from the ConvertDNA class to convert a CDS strand (5' to 3' direction) to its reverse complementary, RNA, and protein strand. 

* EXAMPLE DATA *

Example of valid form input: ATGAAATGA

Example of output: 

Message: Success!
Original DNA: ATGAAATGA
RNA: AUGAAAUGA
Complementary DNA: TCATTTCAT
Protein: MK

Example of invalid form input: S

Example of output: 

Message: Sorry, but a DNA CDS length of 1 is not divisible by 3. Also, a DNA strand can only contain A, C, G, and/or T.
Original DNA: S
RNA: could not determine
Complementary DNA: could not determine
Protein: could not determine

* DETAILED USAGE *

For the DNA to be converted successfully, it must only contain A, C, G, and/or T, and its length must be divisible by 3.    

* REQUIREMENTS *

The NetBeans Java IDE was used to execute this project. Version 8.0.2 of NetBeans was used to create it.

* AUTHOR *

jmunson2 (Jocelyn Munson) 

2016
