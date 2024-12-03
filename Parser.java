
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Parser cunstructor checking if file exists if not throws exeption.
public class Parser  {
    private File hackSourcefile;
    private String currentInstruction;

    public Parser(File hackSourcefile) {
        if (hackSourcefile == null || !hackSourcefile.exists()){
            throw new IllegalArgumentException("the hack code file is empty!");
        }
        this.hackSourcefile = hackSourcefile;
        this.currentInstruction = currentInstruction;
    }

    
    //checks if there is more work to do
    public boolean hasMoreLines() throws IOException {
    String currLine;
    BufferedReader reader = new BufferedReader(new FileReader(hackSourcefile));
    currLine =  reader.readLine();
    reader.close();
        if(currLine.length() >= 1){
            return true;
        }
        else{
            return false;
        }
    }

    //gets  the next instruction and makes it the current instruction 
    public void advance() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(hackSourcefile));
        this.currentInstruction = reader.readLine();
        reader.close();
    }


    //returns the type of the cuurent instruction as a constant
    public String InstructionType() throws IOException{
        if((currentInstruction.charAt(0) == '@') ){
            return "A_INSTRUCTION";
        }
        
        if(currentInstruction.charAt(0) == '('){
            return "L_INSTRUCTION";
        }

        return "C_INSTRUCTION";
    }


    public String symbol() throws IOException{
        if(InstructionType().equals("A_INSTRUCTION")){
            return currentInstruction.substring(1, currentInstruction.length() - 1);
        } 
        else if(InstructionType().equals("L_INSTRUCTION")){
            return currentInstruction.substring(1, currentInstruction.length() - 2);
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
    public String comp() throws IOException{

        if(currentInstruction.contains("=")){
        String[] split = currentInstruction.split("=", 2);
            if(split[1].contains(";")){
                String[] split2 = currentInstruction.split(";", 2);
                return split2[0].trim(); 
            }
            else{
                 return split[1].trim();
            }
        }
        return null;
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

