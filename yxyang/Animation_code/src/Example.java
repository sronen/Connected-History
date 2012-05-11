import org.ubiety.ubigraph.UbigraphClient;
import java.io.*;
public class Example {

	public static void main(String[] args) {
		UbigraphClient graph = new UbigraphClient();
		graph.clear();
		try{
			FileReader fin=new FileReader("NodeForPlot.txt");
			BufferedReader in=new BufferedReader(fin);
			NodeReading[] nodeList=readNode(in);
			in.close();

			FileReader fin1=new FileReader("LinkForPlot.txt");
			BufferedReader in1=new BufferedReader(fin1);
			LinkReading[] linkList=readLink(in1);
			in1.close();
			for (int i=0;i<linkList.length;i++){
				System.out.println(linkList[i]);
			}


			int N = 12069;//max namedef
			int[] vertices = new int[N];
			graph.setVertexStyleAttribute(0, "shape", "sphere");
			graph.setVertexStyleAttribute(0, "color", "#ff0000");
			graph.setVertexStyleAttribute(0, "size", "0.6");
			//smallRed=graph.newVertexStyle(shape="sphere",color="#ff0000",size="0.2");
			for (int i=0; i < 2004; i++){
				int num=nodeList[i].namedef;
				vertices[num-1] = graph.newVertex();
				//graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
				graph.setVertexAttribute(vertices[num-1], "fontsize", "1");
			}

			for (int i=0; i < 2230; i++){
				graph.newEdge(vertices[linkList[i].node1-1], vertices[linkList[i].node2-1]);
			}
	}catch(IOException e){System.out.println(e);}

}
public static NodeReading[] readNode(BufferedReader in) throws IOException {
	int n= Integer.parseInt(in.readLine());
	NodeReading[] sArr= new NodeReading[n];
	for (int i=0; i < n; i++) {
		sArr[i]= new NodeReading();
		String str = in.readLine();
		String [] parts = str.split(",");
		sArr[i].namedef=Integer.parseInt(parts[0]);
		sArr[i].name=parts[1];
		sArr[i].byear=Integer.parseInt(parts[2]);
		sArr[i].dyear=Integer.parseInt(parts[3]);
		sArr[i].fyear=Integer.parseInt(parts[4]);		
	}
	return sArr;
}
public static LinkReading[] readLink(BufferedReader in) throws IOException {
	int n= Integer.parseInt(in.readLine());
	LinkReading[] sArr= new LinkReading[n];
	for (int i=0; i < n; i++) {
		sArr[i]= new LinkReading();
		String str = in.readLine();
		String [] parts = str.split(",");
		sArr[i].node1=Integer.parseInt(parts[0]);
		sArr[i].node2=Integer.parseInt(parts[1]);
		sArr[i].let1=Integer.parseInt(parts[2]);
		sArr[i].let2=Integer.parseInt(parts[3]);		
	}
	return sArr;
}
}