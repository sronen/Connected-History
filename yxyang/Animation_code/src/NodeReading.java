
import java.io.*;

public class NodeReading implements Serializable {
	static final long serialVersionUID = 1L;
	int namedef;
	String name;
	int byear;
	int dyear;
	int fyear;

	
	public NodeReading() {}
	
	public NodeReading(int a, String b, int c, int d,int e) {
		namedef=a;
		name=b;
		byear=c;
		dyear=d;
		fyear=e;
	}

	public String toString() {
		return (namedef+ "    \t" + name + "    \t" + byear+"    \t"+dyear+"    \t"+fyear);
	}
}
