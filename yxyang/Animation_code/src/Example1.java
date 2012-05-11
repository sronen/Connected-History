import org.ubiety.ubigraph.UbigraphClient;
import java.io.*;
import java.util.Random;
public class Example1{

	public static void main(String[] args) {
		UbigraphClient graph = new UbigraphClient();
		graph.clear();
		int nodeN=2004;
		int linkN=2218;
		int N = 12069;//max namedef
		Random r= new Random();
		try{
			FileReader fin=new FileReader("NodeForPlot.txt");
			BufferedReader in=new BufferedReader(fin);
			NodeReading[] nodeList=readNode(in);
			in.close();

			FileReader fin1=new FileReader("LinkForPlot.txt");
			BufferedReader in1=new BufferedReader(fin1);
			LinkReading[] linkList=readLink(in1);
			in1.close();

			int minby=2000;
			int maxdy=0;
			for (int i=0;i<nodeN;i++){
				if(nodeList[i].byear!=0&&nodeList[i].dyear==0){
					nodeList[i].dyear=nodeList[i].byear+50+r.nextInt(40)-20;
				}
				if(nodeList[i].dyear!=0&&nodeList[i].byear==0){
					nodeList[i].byear=nodeList[i].dyear-50-r.nextInt(40)+20;
				}
			}
			for (int i=0;i<nodeN;i++){
				if (nodeList[i].byear<minby&&nodeList[i].byear!=0){
					minby=nodeList[i].byear;
				}
				if (nodeList[i].dyear>maxdy){
					maxdy=nodeList[i].dyear;
				}
			}
			NodeReading[] expandNode=new NodeReading[N+1];
			for (int i=0;i<nodeN;i++){
				expandNode[nodeList[i].namedef]=nodeList[i];
			}
			System.out.println(minby+" "+maxdy);
			int[] vertices = new int[N];
			int[] verticesB = new int[N];
			int[] verticesD = new int[N];
			int[] edges=new int[linkN];
			int[] edgesB=new int[linkN];
			int[] edgesD=new int[linkN];
			for (int i=0;i<linkN;i++){
				System.out.println(linkList[i].node1+" "+linkList[i].node2);
				edgesB[i]=Math.max(expandNode[linkList[i].node1].byear, expandNode[linkList[i].node2].byear);
				edgesD[i]=Math.min(expandNode[linkList[i].node1].dyear, expandNode[linkList[i].node2].dyear);
			}
			int edgecount=0;
			graph.setVertexStyleAttribute(0, "shape", "sphere");
			graph.setVertexStyleAttribute(0, "color", "#ff0000");
			graph.setVertexStyleAttribute(0, "size", "1.0");

			//smallRed=graph.newVertexStyle(shape="sphere",color="#ff0000",size="0.2");
			try {
				Thread.sleep(10000);
				for (int j=minby;j<maxdy;j++){
					for (int i=0; i < nodeN; i++){
						if (nodeList[i].byear==j){
							int num=nodeList[i].namedef;
							if (num==1){
								System.out.println("1111");
								graph.setVertexStyleAttribute(0, "color", "#ffff00");
								graph.setVertexStyleAttribute(0, "size", "10.0");
							}
							else{
								graph.setVertexStyleAttribute(0, "color", "#ff0000");
								graph.setVertexStyleAttribute(0, "size", "0.6");
							}
							vertices[num-1] = graph.newVertex();
							verticesB[num-1]=nodeList[i].byear;
							verticesD[num-1]=nodeList[i].dyear;
							graph.setVertexAttribute(vertices[1-1], "size", "2.0");
							graph.setVertexAttribute(vertices[1-1], "shapedetail", "15");
							graph.setVertexAttribute(vertices[1-1], "color", "#ffff00");
							//graph.setVertexAttribute(vertices[num-1], "label", nodeList[i].name);
							//graph.setVertexAttribute(vertices[num-1], "fontsize", "1");
							graph.setVertexAttribute(vertices[num-1], "shapedetail", "5");
						}
						if (nodeList[i].dyear==j){
							int num=nodeList[i].namedef;
							graph.removeVertex(vertices[num-1]);
						}
					}
					for (int i=0; i < linkN; i++){
						if (j==edgesB[i]){
							edges[i]=graph.newEdge(vertices[linkList[i].node1-1], vertices[linkList[i].node2-1]);
						}
						if (j==edgesD[i]){
							graph.removeEdge(i);
						}
					}
					Thread.sleep(50);
				}
			}catch(InterruptedException ie){System.out.println(ie);}
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