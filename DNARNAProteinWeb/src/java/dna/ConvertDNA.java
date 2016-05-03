package dna;

import java.util.HashMap;

/**
 * The ConvertDNA class provides methods to convert a coding DNA sequence (CDS) strand (5' to 3' direction) to 
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
public class ConvertDNA {
        private final String DNAString;
        private String message;
        private String protein;
        private String RNA;
        private String complement;
        private String reverseComplement;
        private String output;

        /**
         * Creates instance of ConvertDNA class.
         * 
         * @param DNAString
         */
	public ConvertDNA(String DNAString) {
                // remove header line from input, if necessary. 
                // remove all whitespace from input
                // convert DNA to uppercase, in order for the dictionary to correctly function
		this.DNAString = DNAString.replaceAll(">?+.*\n", "")
                        .replaceAll("\\s+","").toUpperCase();
	}
        
        // dictionary of codons and their corresponding amino acid
        // source: https://en.wikipedia.org/wiki/DNA_codon_table
	private static final HashMap<String, String> DNAToProteinMap;
	static
	{
		DNAToProteinMap = new HashMap<>();
                // phenylalanine
		DNAToProteinMap.put("TTT", "F");
		DNAToProteinMap.put("TTC", "F");
		DNAToProteinMap.put("TTA", "F");
		DNAToProteinMap.put("TTG", "F"); 
                // leucine
		DNAToProteinMap.put("CTT", "L");
		DNAToProteinMap.put("CTC", "L");
		DNAToProteinMap.put("CTA", "L");
		DNAToProteinMap.put("CTG", "L"); 
                // isoleucine
		DNAToProteinMap.put("ATT", "I");
		DNAToProteinMap.put("ATC", "I");
		DNAToProteinMap.put("ATA", "I");
                // methionine (start codon)
		DNAToProteinMap.put("ATG", "M");
                // valine
                DNAToProteinMap.put("GTT", "V");
                DNAToProteinMap.put("GTC", "V");
                DNAToProteinMap.put("GTA", "V");
                DNAToProteinMap.put("GTG", "V"); 
                // serine
                DNAToProteinMap.put("TCT", "S");
                DNAToProteinMap.put("TCC", "S");
                DNAToProteinMap.put("TCA", "S");
                DNAToProteinMap.put("TCG", "S"); 
                // proline
                DNAToProteinMap.put("CCT", "P"); 
                DNAToProteinMap.put("CCC", "P");
                DNAToProteinMap.put("CCA", "P");
                DNAToProteinMap.put("CCG", "P");
                // threonine
                DNAToProteinMap.put("ACT", "T");
                DNAToProteinMap.put("ACC", "T");
                DNAToProteinMap.put("ACA", "T");
                DNAToProteinMap.put("ACG", "T");
                // alanine
                DNAToProteinMap.put("GCT", "A");
                DNAToProteinMap.put("GCC", "A");
                DNAToProteinMap.put("GCA", "A");
                DNAToProteinMap.put("GCG", "A");
                // tyrosine
                DNAToProteinMap.put("TAC", "Y");
                DNAToProteinMap.put("TAT", "Y");
                // histidine
                DNAToProteinMap.put("CAT", "H");
		DNAToProteinMap.put("CAC", "H");
                // glutamine
                DNAToProteinMap.put("CAA", "Q");
                DNAToProteinMap.put("CAG", "Q");
                // asparagine
                DNAToProteinMap.put("AAT", "N");
                DNAToProteinMap.put("AAC", "N");
                // lysine
                DNAToProteinMap.put("AAA", "K");
                DNAToProteinMap.put("AAG", "K");
                // aspartic acid
                DNAToProteinMap.put("GAT", "D");
                DNAToProteinMap.put("GAC", "D");
                // glutamic acid
		DNAToProteinMap.put("GAA", "E");
                DNAToProteinMap.put("GAG", "E");
                // cysteine
                DNAToProteinMap.put("TGT", "C");
                DNAToProteinMap.put("TGC", "C");
                // stop codons
                DNAToProteinMap.put("TGA", "");
                DNAToProteinMap.put("TAA", ""); 
                DNAToProteinMap.put("TAG", "");
                // tryptophan
                DNAToProteinMap.put("TGG", "W");
                // arginine
                DNAToProteinMap.put("CGT", "R");
                DNAToProteinMap.put("CGA", "R");
                DNAToProteinMap.put("CGC", "R");
                DNAToProteinMap.put("CGG", "R");
                DNAToProteinMap.put("AGA", "R");
                DNAToProteinMap.put("AGG", "R");
                // serine
                DNAToProteinMap.put("AGT", "S");
                DNAToProteinMap.put("AGC", "S");
                // glycine
                DNAToProteinMap.put("GGT", "G");
                DNAToProteinMap.put("GGA", "G");
                DNAToProteinMap.put("GGC", "G");
                DNAToProteinMap.put("GGG", "G");
	}
        
        /**
         * Converts codons to an amino acid chain (protein).
         *
         * @return protein
         */
        public String getProtein() {
		protein = "";
		String codon;
		String aminoAcid;
		
		for (int i = 0; i <= DNAString.length() - 3; i = i + 3) {
			// key
			codon = DNAString.substring(i, i + 3);  //[0,3)
			// get key's value
			aminoAcid = DNAToProteinMap.get(codon);
			
			protein += aminoAcid;
		}
		
		return protein;	
	}
	
        /**
         * Converts DNA to RNA.
         *
         * @return RNA
         */
        public String getRNA() {
		RNA =  DNAString.replace('T','U');
		return RNA;
	 }
	
        /**
         * Obtains complementary DNA strand from original DNA strand (in 5' to 3' direction).
         *
         * @return reverseComplement
         */
        public String getDNAReverseComplement() {
		 complement = "";
		 StringBuilder sb = new StringBuilder();
		 for (int i = 0; i < DNAString.length(); i++) {
			 if (DNAString.charAt(i) == 'C') {
				 complement += "G";
			 } else if (DNAString.charAt(i) == 'G') {
				 complement += "C";
			 } else if (DNAString.charAt(i) == 'A') {
				 complement += "T";
			 } else if (DNAString.charAt(i) == 'T') {
				 complement += "A";
			 }
		 }
		 
                 // reverse the string so that it's in the 5' to 3' direction
		 reverseComplement = sb.append(complement).reverse().toString();
                 
                 return reverseComplement;
	 }
        
         /**
          * Validates DNA length; DNA passed in must be CDS.
          *
          * @return true if divisible by 3, false if not divisible by 3
          */
	 private boolean validateDNALength() {
             return DNAString.length() % 3 == 0;
	 }
	 
         /**
          * Validates DNA content; DNA passed in must have A, G, C, T only.
          *
          * @return true if divisible by 3, false if not divisible by 3
          */
	 private boolean validateDNAContent() {
            /* [ATGC]+ match a single character present in ATGC 
             a single character in the list ATGC literally (case sensitive) 
             between one and unlimited times, as many times as possible, 
             giving back as needed [greedy] */ 
		return DNAString.matches("[ATGC]+");
	 }

	/**
	 * @return the DNAString
	 */
	public String getDNAString() {
		return DNAString;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
        
       /**
         * Provides the user with a message based on the validity of the input.
         *
         * @return true if invalid message needed, false if not
         */
	protected boolean invalidMessage() {
		if (!validateDNALength() && !validateDNAContent()) {
			message = "Sorry, but a DNA CDS length of " + DNAString.length() + 
                                " is not divisible by 3. Also, a DNA strand can only contain A, C, G, and/or T.";
			return true;
		}
		else if (validateDNALength() && !validateDNAContent()) {
			message = "Sorry, a DNA strand can only contain A, C, G, and/or T.";
			return true;
		}
		else if (!validateDNALength() && validateDNAContent()) {
			message = "Sorry, but a DNA CDS length of " + DNAString.length() + 
                                " is not divisible by 3.";
			return true;
		}
		else {
			message = "Success!";
			return false;
		}
	}
        
        /**
         * Display results in a String.
         *
         * @return output
         */
        public String displayOutput() {
            if (invalidMessage()) {
                output = "Original DNA strand: " + getDNAString() + "\n" + getMessage();
            }
            else {
                output = "Original DNA strand: " + getDNAString() 
			+ "\nReverse complement of DNA strand: " + getDNAReverseComplement() 
			+ "\nmRNA strand: " + getRNA() + "\nProtein: " + getProtein();
            }
            return output;
        }
}