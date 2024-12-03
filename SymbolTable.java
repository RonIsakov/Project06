import java.util.Hashtable;
public class SymbolTable {

    Hashtable<String, Integer> symbolTable = new Hashtable<>();

        static Hashtable<String, Integer> BasicSymbolTable (Hashtable <String, Integer> symbolTable){
            symbolTable.put("R0", 0);
            symbolTable.put("R1", 1);
            symbolTable.put("R2", 2);
            symbolTable.put("R3", 3);
            symbolTable.put("R4", 4);
            symbolTable.put("R5", 5);
            symbolTable.put("R6", 6);
            symbolTable.put("R7", 7);
            symbolTable.put("R8", 8);
            symbolTable.put("R9", 9);
            symbolTable.put("R10", 10);
            symbolTable.put("R11", 11);
            symbolTable.put("R12", 12);
            symbolTable.put("R13", 13);
            symbolTable.put("R14", 14);
            symbolTable.put("R15", 15);
            symbolTable.put("KBD", 24576);
            symbolTable.put("SCREEN", 16384);
            return symbolTable;
        }
        
    public SymbolTable(){
    this.symbolTable = BasicSymbolTable(symbolTable);
    }

    public void addEntry(String symbol, Integer address){
        this.symbolTable.put(symbol, address);
    }

    public boolean contains(String symbol){
        return this.symbolTable.contains(symbol);
    }

    public Integer getAdress(String symbol){
        return this.getAdress(symbol);
    }
}
