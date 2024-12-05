import java.io.*;

public class HackAssembler {
    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        File inputFile = new File(args[0]);
        File outputFile = new File(inputFile.getParent(), inputFile.getName().replace(".asm", ".hack"));

        try {
            // initialize components
            SymbolTable symbolTable = new SymbolTable();
            Code code = new Code();

            // first pass: build symbol table with labels
            firstPass(inputFile, symbolTable);

            // second pass: generate binary code
            secondPass(inputFile, outputFile, symbolTable, code);

        } catch (IOException e) {
            System.out.println("Error during assembly: " + e.getMessage());
        }
    }

    private static void firstPass(File inputFile, SymbolTable symbolTable) throws IOException {
        Parser parser = new Parser(inputFile);
        int instructionAddress = 0;

        while (parser.hasMoreLines()) {
            parser.advance();
            String instructionType = parser.InstructionType();

            if (instructionType.equals("L_INSTRUCTION")) {
                String label = parser.symbol();
                symbolTable.addEntry(label, instructionAddress);
            } else {
                instructionAddress++;
            }
        }
    }

    private static void secondPass(File inputFile, File outputFile, SymbolTable symbolTable, Code code) throws IOException {
        Parser parser = new Parser(inputFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        int variableAddress = 16;

        while (parser.hasMoreLines()) {
            parser.advance();
            String instructionType = parser.InstructionType();

            if (instructionType.equals("A_INSTRUCTION")) {
                String symbol = parser.symbol();
                int address;

                if (symbol.matches("\\d+")) { // numeric address
                    address = Integer.parseInt(symbol);
                } else {
                    // else: symbol address
                    if (!symbolTable.contains(symbol)) {
                        symbolTable.addEntry(symbol, variableAddress++);
                    }
                    address = symbolTable.getAddress(symbol);
                }

                writer.write(String.format("0%15s", Integer.toBinaryString(address)).replace(' ', '0'));
                writer.newLine();
            } else if (instructionType.equals("C_INSTRUCTION")) {
                String dest = code.dest(parser.dest());
                String comp = code.comp(parser.comp());
                String jump = code.jump(parser.jump());

                // Default to "null" if any field is missing
                if (dest == null) dest = "000";
                if (comp == null) comp = "0000000";
                if (jump == null) jump = "000";

                writer.write("111" + comp + dest + jump);
                writer.newLine();
            }
        }

        writer.close();
    }
}
