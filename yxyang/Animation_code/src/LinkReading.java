//Full name: Yingxiang Yang;	
//Email: yxyang@mit.edu
//Stellar username:Yingxiang	
//Recitation Section: r05 T11	
//TA: Enyang (Eric) Huang
//Assignment number: L24
import java.io.*;

public class LinkReading implements Serializable {
	private static final long serialVersionUID = 1L;
	int node1,node2,let1,let2;
	
	public LinkReading() {}
	
	public LinkReading(int a,int b,int c,int d) {
		node1=a;
		node2=b;
		let1=c;
		let2=d;
	}

	
	
	public String toString() {
		return (node1+ "    \t" + node2 + "    \t" + let1+"    \t"+let2);
	}
}