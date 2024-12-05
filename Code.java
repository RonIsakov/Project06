import java.util.Hashtable;

public class Code {
	private Hashtable<String, String> destTable;
	private Hashtable<String, String> compTable;
	private Hashtable<String, String> jumpTable;
	
	public Code() {
		this.jumpTable = new Hashtable<String, String>();
		this.writejumpTable();
		this.compTable = new Hashtable<String, String>();
		this.writeCompTable();
		this.destTable = new Hashtable<String, String>();
		this.writeDestTable();
	}
	
	private void writejumpTable() {
		this.jumpTable.put("NULL", "000");
		this.jumpTable.put("JGT", "001");
		this.jumpTable.put("JEQ", "010");
		this.jumpTable.put("JGE", "011");
		this.jumpTable.put("JLT", "100");
		this.jumpTable.put("JNE", "101");
		this.jumpTable.put("JLE", "110");
		this.jumpTable.put("JMP", "111");
	}
	
	private void writeCompTable() {
		this.compTable.put("0", "0101010");
		this.compTable.put("1", "0111111");
		this.compTable.put("-1", "0111010");
		this.compTable.put("D", "0001100");
		this.compTable.put("A", "0110000");
		this.compTable.put("M", "1110000");
		this.compTable.put("!D", "0001101");
		this.compTable.put("!A", "0110001");
		this.compTable.put("!M", "1110001");
		this.compTable.put("-D", "0001111");
		this.compTable.put("-A", "0110011");
		this.compTable.put("-M", "1110011");
		this.compTable.put("D+1", "0011111");
		this.compTable.put("A+1", "0110111");
		this.compTable.put("M+1", "1110111");
		this.compTable.put("D-1", "0001110");
		this.compTable.put("A-1", "0110010");
		this.compTable.put("M-1", "1110010");
		this.compTable.put("D+A", "0000010");
		this.compTable.put("D+M", "1000010");
		this.compTable.put("D-A", "0010011");
		this.compTable.put("D-M", "1010011");
		this.compTable.put("A-D", "0000111");
		this.compTable.put("M-D", "1000111");
		this.compTable.put("D&A", "0000000");
		this.compTable.put("D&M", "1000000");
		this.compTable.put("D|A", "0010101");
		this.compTable.put("D|M", "1010101");
	}
	
	private void writeDestTable() {
		this.destTable.put("NULL", "000");
		this.destTable.put("M", "001");
		this.destTable.put("D", "010");
		this.destTable.put("MD", "011");
		this.destTable.put("A", "100");
		this.destTable.put("AM", "101");
		this.destTable.put("AD", "110");
		this.destTable.put("AMD", "111");
	
    }

    public String dest(String destAssembly){
        return this.destTable.get(destAssembly);
    }

    public String comp(String destAssembly){
        return this.compTable.get(destAssembly);
    }

    public String jump(String destAssembly) {
		if (destAssembly == null || destAssembly.isEmpty()) {
			return jumpTable.get("NULL"); // Use "NULL" as the default key for no jump
		}
		return jumpTable.get(destAssembly);
	}
	
}
