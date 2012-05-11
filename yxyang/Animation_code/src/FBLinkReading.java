//Full name: Yingxiang Yang;	
//Email: yxyang@mit.edu
//Stellar username:Yingxiang	
//Recitation Section: r05 T11	
//TA: Enyang (Eric) Huang
//Assignment number: L24
import java.io.*;

public class FBLinkReading implements Serializable {
	private static final long serialVersionUID = 1L;
	int node1,node2;
	
	public FBLinkReading() {}
	
	public FBLinkReading(int a,int b) {
		node1=a;
		node2=b;
	}

	
	
	public String toString() {
		return (node1+ "    \t" + node2);
	}
}