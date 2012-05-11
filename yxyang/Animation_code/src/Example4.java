import org.ubiety.ubigraph.UbigraphClient;
import java.io.*;
public class Example4 {

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
			int linkN=56705;
			int[] vertices = new int[N];
			graph.setVertexStyleAttribute(0, "shape", "sphere");
			graph.setVertexStyleAttribute(0, "color", "#ff0000");
			graph.setVertexStyleAttribute(0, "size", "0.6");
			int[] edges=new int[linkN];
			int[] edgesB=new int[linkN];
			int[] edgesD=new int[linkN];
			for (int i=0;i<linkN;i++){
				System.out.println(linkList[i].node1+" "+linkList[i].node2);
				edgesB[i]=Math.max(nodeList[linkList[i].node1-1].byear, nodeList[linkList[i].node2-1].byear);
				edgesD[i]=Math.max(nodeList[linkList[i].node1-1].byear+50, nodeList[linkList[i].node2-1].byear+50);
			}
			//smallRed=graph.newVertexStyle(shape="sphere",color="#ff0000",size="0.2");
			try {
				Thread.sleep(5000);
				for (int j=340;j<2100;j++){
					if (j<500){
						Thread.sleep(50);
					}
					else if(j<1600){
						Thread.sleep(130);
					}
					else if (j<1780){
						Thread.sleep(600);
					}
					else {
						Thread.sleep(1200);
					}
					for (int i=0; i < N; i++){
						if (nodeList[i].byear==j){
							int num=nodeList[i].namedef;
							vertices[num-1] = graph.newVertex();
							if (nodeList[i].degree<8){
								graph.setVertexAttribute(vertices[num-1], "size", "0.6");
								graph.setVertexAttribute(vertices[num-1], "color", "#ffffff");
							}
							else if (nodeList[i].degree<35){
								graph.setVertexAttribute(vertices[num-1], "size", "0.8");
								graph.setVertexAttribute(vertices[num-1], "color", "#ff00ff");
							}
							else{
								graph.setVertexAttribute(vertices[num-1], "size", "1.6");
								graph.setVertexAttribute(vertices[num-1], "color", "#ff0000");
								graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
								graph.setVertexAttribute(vertices[num-1], "fontsize", "12");
							}
							if (j<1300&&nodeList[i].degree>20){
								graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
								graph.setVertexAttribute(vertices[num-1], "fontsize", "12");
							}
							//graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
							//graph.setVertexAttribute(vertices[num-1], "fontsize", "1");
							graph.setVertexAttribute(vertices[num-1], "shapedetail", "1");
						}
						if(nodeList[i].byear+50==j){
							int num=nodeList[i].namedef;
							graph.removeVertex(vertices[num-1]);
						}		
					}
					for (int i=0; i < 56705; i++){
						if (j==edgesB[i]){
							edges[i]=graph.newEdge(vertices[linkList[i].node1-1], vertices[linkList[i].node2-1]);
						}
						else if (j==edgesD[i]){
							graph.removeEdge(i);
						}

					}
				}
			}catch(InterruptedException ie){System.out.println(ie);}
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
			//System.out.println(sArr[i].namedef);
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