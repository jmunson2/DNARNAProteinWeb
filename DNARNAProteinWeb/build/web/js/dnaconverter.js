/* 
 * Javascript used to display results on the same page as the input form (with AJAX), among other things.
 * 
 * Example of form input: ATGAAATGA
 * Example of output: 
 *  Message: Success!
 *  Original DNA: ATGAAATGA
 *  RNA: AUGAAAUGA
 *  Complementary DNA: TCATTTCAT
 *  Protein: MK
 *  
 *  @author jmunson2
 */

var exampleStrand = ">gb|BC085096.1|:251-1408 Mus musculus cDNA clone MGC:102640 IMAGE:30731739, complete cds\n\
ATGAGTGTGTCTCTGCTAAATATGCCACAGGGTTTGTTATCCTTCAAGGACGTGGCCTTGGACTTCTCAC \
TGGAGGAATGGGAATGTCTCAGTTTTGCTCAGAGGTCATTGTACATGGATGTGATGTTGGAGAATTACAA \
CAATCTGTTGTTTGTGGAAAATCATTGCATACATGGAAAGTATGAGAAGGTCATGGATCAAGACAGCCAG \
TACATTGTTCTTGAACACATGAATATTCAAGAGAAGTCTTCTAAATGGAACAAGCTTAGCAATGTGATTC \
TAGAATCCTCCCATTATACACCTCACAAATGTACTGAATGGGACAAATGCTTTTCCCAAAAATCCCATCT \
TAATTTTCATCAGAAAATTCATGCAGGAGAGAAATCTTACAAATGCAGTGAATGCGACAAATGCTTTACC \
GAAAAAGGCAGTCTCAGAATTCATCAGAGAATTCATACAGGAGAGAAACCTTACAAATGTAGCGAATGTG \
ACAAATGCTTTACTGGAAAAGGCAATCTGAGAATTCATCAGAGAATTCATACAGGAGAGAAACCTTACAA \
ATGCAGTGAATGTGACAAATGCTTTACCAAACAATCCAATCTTAGTATTCATCAGAGAATTCATACAGGA \
GAGAAACCTTACAAATGCAGTGAATGTGACAAATGCTTTACCCAAGAATCCTATCTTAGTATTCATCAGA \
GAATTCATACAGGAGAGAAACCTTACAAATGCAGTGAATGTGGCAAATGCTTTACTGAAAAAAGCAGTCT \
GAGAATTCATCAGAGAATTCATACAGGAGAGAAACCTTACAAATGCAGTGAATGTGACAAATGCTTTACC \
CAACAATCCCATCTTAGTATTCATCAGAGAATTCATACAGGAGAGAAACCTTACAAATGCAGTGAATGTG \
ACAAATGCTTTACCCAACAATCCAATCTTAGTATTCATCAGAGAATTCATACAGGAGAGAAACCTTACAA \
ATGCACTGAATGTGGCAAATGCTTTACTGAAAAAAGCAGTCTGAGAATTCATCAGAGAATTCATACAGGA \
GAGAAACCTTACAAATGCAGTGAATGTGGCAAATGCTTTACTGAAAAAAAGCAGTCTGAGAATTCATCAG \
AGAATTCATACAGGAGAGAAACCTTACAAATGCAATGA";

function addText() {
        // display example strand to the user
	document.DNAConverterForm.dna.value = exampleStrand;
}

function hideAndSeek(sequence) {
    // show or hide each sequence by clicking on the section header
    $('#div_origDNA, #div_compDNA, #div_mRNA, #div_protein').hide();
    $('#p_'+ sequence).click(function() {
        $('#div_'+ sequence).slideToggle();
    });  
}

function submitText() {
    // grab form input and push it to the servlet for processing
    var dnaJS = document.DNAConverterForm.dna.value;
    $.ajax({
        type: 'POST',
        data: {
            dnaJS: dnaJS
        },
        url: 'ConvertDNAServlet',
        // display results
        success: function(result){
            $('#result').html(result);
        },
        error: function(){
            alert('failure');
        }
    });
}

$(document).ready(function() {
    // display the results obtained from the servlet
    hideAndSeek('origDNA');
    hideAndSeek('compDNA');
    hideAndSeek('mRNA');
    hideAndSeek('protein');
    $( "#example" ).click(function() {
        addText();
    });
    $( "#reset" ).click(function() {
        // refresh the page
        location.reload();
    });
    $( "#submit" ).click(function() {
        submitText();
    });
});