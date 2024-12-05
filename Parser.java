

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Parser cunstructor checking if file exists if not throws exeption.
public class Parser  {
    private BufferedReader reader;
    private String currentInstruction;
    private String nextInstruction;

    public Parser(File hackSourceFile) throws IOException {
        if (hackSourceFile == null ){
            throw new IllegalArgumentException("the hack code file is empty!");
        }
        if(!hackSourceFile.exists()){
            throw new IllegalArgumentException("the hack code file doesnt exist!");  
        }
        this.reader = new BufferedReader(new FileReader(hackSourceFile));
        this.currentInstruction = null;
        this.nextInstruction = null;
    }


    //checks if the string is a comment
    public boolean isLineComment(String line){
        if(line.startsWith("//") ){
            return true;
        }
        else{
            return false;
        }
    }
// Found "//", return the part after it // No "//" found, return the original string
public String trimUntilComment(String line) {
    if (line == null) {
        return ""; // Return an empty string if line is null
    }
    int commentIndex = line.indexOf("//");
    if (commentIndex != -1) {  
        return line.substring(0, commentIndex).trim();
    }
    return line.trim(); // If no comment, just trim whitespace
}

    //checks if there is more work to do
    public boolean hasMoreLines() throws IOException {
        // Read the next line
        this.nextInstruction = reader.readLine();
    
        // Skip blank lines and comment lines
        while (this.nextInstruction != null && 
               (isLineComment(this.nextInstruction.trim()) || this.nextInstruction.trim().isEmpty())) {
            this.nextInstruction = reader.readLine(); // Read the next line
        }
    
        // If we reach here, either there are no more lines, or nextInstruction is valid
        return this.nextInstruction != null;
    }
    
    
    
        
    

    //gets the next instruction and makes it the current instruction 
    //if there is a valid next instruction return true else false
    public boolean advance() throws IOException {
        if (this.nextInstruction != null || this.hasMoreLines()) {
            this.currentInstruction = this.nextInstruction.trim();
            this.nextInstruction = null; // Clear `nextInstruction` for the next call
            return true;
        }
        return false;
    }
    


    //returns the type of the cuurent instruction as a constant
    public String InstructionType() throws IOException{
        if(currentInstruction.startsWith("@")){
            return "A_INSTRUCTION";
        }
        if(currentInstruction.startsWith("(")){
            return "L_INSTRUCTION";
        }
        return "C_INSTRUCTION";
    }


    public String symbol() throws IOException{
        if(InstructionType().equals("A_INSTRUCTION")){
            return currentInstruction.substring(1);
        } 
        else if(InstructionType().equals("L_INSTRUCTION")){
            return currentInstruction.substring(1, currentInstruction.length() - 1);
        }
        else{
            throw new IllegalArgumentException("not an A or an L instruction!");
        }
    }

    //Returns the instructions jump feild
    public String jump() throws IOException{
            if (currentInstruction.contains(";")) {
                 String[] split = currentInstruction.split(";",2);
                 return split[1].trim();
            }
            return null;
    }


    //Returns the instructions comp feild
    public String comp() throws IOException {
        if (currentInstruction.contains("=")) {
            // Split on "=" to get the part after the "="
            String[] split = currentInstruction.split("=", 2);
            // Check if the part after "=" contains ";"
            if (split[1].contains(";")) {
                // Split on ";" to get the comp part before ";"
                return split[1].split(";")[0].trim();
            } else {
                // No ";" after "=", return the part after "="
                return split[1].trim();
            }
        } else if (currentInstruction.contains(";")) {
            // If there's no "=", the whole comp is before ";"
            return currentInstruction.split(";")[0].trim();
        }
        throw new IllegalArgumentException("Malformed C_INSTRUCTION: " + currentInstruction);
    }
    


    //Returns the instructions dest feild
    public String dest() throws IOException{
        if (currentInstruction.contains("=")){
            String[] split = currentInstruction.split("=", 2);
            return split [0].trim();
        }
        return null;
        } 
        
    }

