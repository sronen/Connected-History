import org.ubiety.ubigraph.UbigraphClient;
import java.io.*;
public class Examle3 {

	public static void main(String[] args) {
		UbigraphClient graph = new UbigraphClient();
		graph.clear();
		try{
			FileReader fin=new FileReader("Node.txt");
			BufferedReader in=new BufferedReader(fin);
			FBNodeReading[] nodeList=readNode(in);
			in.close();

			FileReader fin1=new FileReader("Link.txt");
			BufferedReader in1=new BufferedReader(fin1);
			FBLinkReading[] linkList=readLink(in1);
			in1.close();


			int N = 18281;//max namedef
			int[] vertices = new int[N];
			graph.setVertexStyleAttribute(0, "shape", "sphere");
			graph.setVertexStyleAttribute(0, "color", "#ff0000");
			graph.setVertexStyleAttribute(0, "size", "0.6");
			//smallRed=graph.newVertexStyle(shape="sphere",color="#ff0000",size="0.2");
			for (int i=0; i < N; i++){
				int num=nodeList[i].namedef;
				vertices[num-1] = graph.newVertex();
				//graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
				//graph.setVertexAttribute(vertices[num-1], "fontsize", "1");
				graph.setVertexAttribute(vertices[num-1], "shapedetail", "1");
			}

			for (int i=0; i < 56705; i++){
				graph.newEdge(vertices[linkList[i].node1-1], vertices[linkList[i].node2-1]);
			}
		}catch(IOException e){System.out.println(e);}

	}
	public static FBNodeReading[] readNode(BufferedReader in) throws IOException {
		int n= Integer.parseInt(in.readLine());
		FBNodeReading[] sArr= new FBNodeReading[n];
		for (int i=0; i < n; i++) {
			sArr[i]= new FBNodeReading();
			String str = in.readLine();
			String [] parts = str.split(",");
			sArr[i].namedef=Integer.parseInt(parts[0]);
			sArr[i].name=parts[1];
			sArr[i].byear=Integer.parseInt(parts[2]);
			sArr[i].degree=Integer.parseInt(parts[3]);	
			System.out.println(sArr[i].namedef);
		}
		return sArr;
	}
	public static FBLinkReading[] readLink(BufferedReader in) throws IOException {
		int n= Integer.parseInt(in.readLine());
		FBLinkReading[] sArr= new FBLinkReading[n];
		for (int i=0; i < n; i++) {
			sArr[i]= new FBLinkReading();
			String str = in.readLine();
			String [] parts = str.split(",");
			sArr[i].node1=Integer.parseInt(parts[0]);
			sArr[i].node2=Integer.parseInt(parts[1]);	
		}
		return sArr;
	}
}