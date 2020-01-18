package linkSpam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Degree{
	
		//��ȡ��ȳ�����Ϣ��inoutdegree.txt
		public void getDegree(String linkFilePath,String degreeFilePath) throws Exception{
			int []inDegree=new int[11402];
			int outDegree[]=new int[11402];
			File linkF=new File(linkFilePath);
			BufferedReader linkFBR=new BufferedReader(new FileReader(linkF));
			String lineLinkgraph,lineLinkArray[],allLink[];
			//�������ӹ�ϵ������ת������ ������ȳ��ȡ�
			while((lineLinkgraph=linkFBR.readLine())!=null){
				lineLinkArray=lineLinkgraph.split("-");			
				int q=Integer.parseInt(lineLinkArray[0].trim());//outnode q�ĳ��ȼ�һ
				allLink=lineLinkArray[1].trim().split(" ");						
				for(int i=1;i<allLink.length;i++){
					String []linkArray=allLink[i].split(":");
					int p=Integer.parseInt(linkArray[0]);	//innode p����ȼ�һ
					inDegree[p]++;
					outDegree[q]++;
				}//for
			}//while
			File writeName=new File("C:/Users/z1205/Desktop/inoutDegree.txt");
			FileWriter writer=new FileWriter(writeName);
			BufferedWriter out=new BufferedWriter(writer);
			out.write("id out in");
			out.newLine();
			for(int n=0;n<11402;n++)
			{
				out.write(n+" "+outDegree[n]+" "+inDegree[n]+"\n");
				out.flush();
			}
			linkFBR.close();
			out.close();
		}
		
}