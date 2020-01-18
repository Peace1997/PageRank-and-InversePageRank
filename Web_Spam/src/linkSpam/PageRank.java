package linkSpam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class PageRank implements linkSpam{
	
	int []indegree=new int[11402];
	int outdegree[]=new int[11402];
    //�õ�ת������linkFilePathΪԭ�����ļ�·����degreeFilePathΪ�����������ȵ��ļ�·��
	public float [][]getTransMatrix(String linkFilePath,String degreeFilePath) throws Exception{
		float [][]M=new float[11402][11402];
		//��ʼ��ת������
		for(int i=0;i<11402;i++)
			for(int j=0;j<11402;j++)
				M[i][j]=0F;	
		
		//�ļ�����
		File linkF=new File(linkFilePath);
		BufferedReader linkFBR=new BufferedReader(new FileReader(linkF));
		File degreeF=new File(degreeFilePath);
		BufferedReader degreeFBR=new BufferedReader(new FileReader(degreeF));
		
		//������ȳ�����Ϣ
		String degreeLine,degreeArray[];
		degreeLine=degreeFBR.readLine();
		while((degreeLine=degreeFBR.readLine())!=null){
			degreeArray=degreeLine.split(" ");
			outdegree[Integer.parseInt(degreeArray[0])]=Integer.parseInt(degreeArray[1]);
			indegree[Integer.parseInt(degreeArray[0])]=Integer.parseInt(degreeArray[2]);
		}
		
		//�������ӹ�ϵ������ת������
		String lineLinkgraph,lineLinkArray[],allLink[];
		while((lineLinkgraph=linkFBR.readLine())!=null){
			lineLinkArray=lineLinkgraph.split("-");
			int q=Integer.parseInt(lineLinkArray[0].trim());
			allLink=lineLinkArray[1].trim().split(" ");				
			for(int i=1;i<allLink.length;i++){
				String []linkArray=allLink[i].split(":");
				int p=Integer.parseInt(linkArray[0]);
				M[p][q]=1.0F/outdegree[q];
			}//for
		}//while
		
		linkFBR.close();
		degreeFBR.close();
		return M;
	}
	
	
	//����TrustRankֵ������TΪת������alphaΪ����(˥��)���ӣ�һ��Ϊ0.85��MΪ����������һ����20
	public RankValue[] getRank(float [][]T,float alpha,int M) throws Exception{
		int n=T.length;
		float s1[]=new float[n],s2[]=new float[n],d[]=new float[n];
		int i,j,t;
        //��ʼ��dΪ�ȸ�������
		for(i=0;i<n;i++){
			d[i]=1.0F/n;//����ʱ��d��i]Ϊ1��N
		    s1[i]=d[i];
		}
							
		//����M��
		for(t=0;t<M;t++){
			for(j=0;j<n;j++){
				s2[j]=s1[j];//������һ�ε������ֵ
				s1[j]=0.0F;
			}
			for(i=0;i<n;i++){//һ��ѭ������һ��Ԫ�ص�PageRank��ֵ
				for(j=0;j<n;j++){
					s1[i]+=alpha*T[i][j]*s2[j]; 
				}
				s1[i]+=(1-alpha)*d[i];
			}
		}
		RankValue s[]=new RankValue[n];
		for(i=0;i<n;i++)
			s[i]=new RankValue(i,s1[i]);//��¼ÿ��Ԫ�ؽ�㼰����Ӧ��PageRankֵ
		return s;
	}
	
	//ͨ��ð�������ÿ��Ԫ�ص�PageRankֵ��С��������
	public void bubbleSort(RankValue[] arr){
		for(int i=0;i<arr.length-1;i++)
			for(int j=0;j<arr.length-i-1;j++)
				if(new Float(arr[j].value).compareTo(new Float(arr[j+1].value))<0){
					RankValue t=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=t;
				}
	}
	
}