
import java.io.*;

public class FBNodeReading implements Serializable {
	static final long serialVersionUID = 1L;
	int namedef;
	String name;
	int byear;
	int degree;

	
	public FBNodeReading() {}
	
	public FBNodeReading(int a, String b, int c, int d) {
		namedef=a;
		name=b;
		byear=c;
		degree=d;
	}

	public String toString() {
		return (namedef+ "    \t" + name + "    \t" + byear+"    \t"+degree);
	}
}
