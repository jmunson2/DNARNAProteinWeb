package dna;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The ConvertDNAServlet class carries out methods from the ConvertDNA class to convert a coding DNA sequence (CDS) strand (5' to 3' direction) to 
 * its reverse complementary, RNA, and protein strand. 
 * 
 * A message is provided to the user depending on the validity of the provided DNA strand.
 * 
 * Example of form input: ATGAAATGA
 * Example of output: 
 *  Message: Success!
 *  Original DNA: ATGAAATGA
 *  RNA: AUGAAAUGA
 *  Complementary DNA: TCATTTCAT
 *  Protein: MK
 * 
 * @author jmunson2
 */
public class ConvertDNAServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String formInput;
        String origDNA;
        String mRNA;
        String protein;
        String complementaryDNA;
        String message;
        formInput = request.getParameter("dnaJS");
        
        ConvertDNA cd = new ConvertDNA(formInput);
        origDNA = cd.getDNAString();    
        if (!cd.invalidMessage()) {     
            mRNA = cd.getRNA();
            protein = cd.getProtein();
            complementaryDNA = cd.getDNAReverseComplement();
        } else {
            origDNA = "invalid";
            complementaryDNA = mRNA = protein = "could not determine";
        }
        message = cd.getMessage();
        
        // output is displayed on the same page as the input
        out.write("<script src='js/dnaconverter.js'></script>");
        out.write("<link rel='stylesheet' href='css/dnaconverter.css' type='text/css'/>");
        out.write("<h2>" + message + "</h2>");
        out.write("<h2>Click each section header below to show and hide each sequence, which are each in the 5' to 3' direction.</h2>");
        out.write("<div id='section1'>");
        out.write("<p id='p_origDNA' class='p_seq'>Original DNA <div id = 'div_origDNA' class='div_seq'>" + origDNA + "</div></p>");
        out.write("<p id='p_mRNA' class='p_seq'>mRNA <div id = 'div_mRNA' class='div_seq'>" + mRNA + "</div></p>");
        out.write("</div>");
        out.write("<div id='section2'>");
        out.write("<p id='p_compDNA' class='p_seq'>Complementary DNA <div id = 'div_compDNA' class='div_seq'>" + complementaryDNA + "</div></p>");
        out.write("<p id='p_protein' class='p_seq'>Protein <div id = 'div_protein' class='div_seq'>" + protein + "</div></p>");
        out.write("</div>");
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}