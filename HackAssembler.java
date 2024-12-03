import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HackAssembler {
    public static void main(String[] args) {
        File hackFile = new File(args[0]);
        Parser parse = new Parser(hackFile);

        try
        {
            while(parse.hasMoreLines()){
                parse.advance();
                String instructionType = parse.InstructionType();
                
                if(instructionType.equals("C_INSTRUCTION")){
                    
                }

            }
        }
        catch (IOException e) {
            // Handle the exception
            System.out.println("An error occurred while trying to read the file.");
            e.printStackTrace(); 
        }     


    }
}
