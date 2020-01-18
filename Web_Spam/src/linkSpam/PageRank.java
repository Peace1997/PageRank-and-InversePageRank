package linkSpam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class PageRank implements linkSpam{
	
	int []indegree=new int[11402];
	int outdegree[]=new int[11402];
    //得到转换矩阵，linkFilePath为原链接文件路径，degreeFilePath为存放入读、出度的文件路径
	public float [][]getTransMatrix(String linkFilePath,String degreeFilePath) throws Exception{
		float [][]M=new float[11402][11402];
		//初始化转换矩阵
		for(int i=0;i<11402;i++)
			for(int j=0;j<11402;j++)
				M[i][j]=0F;	
		
		//文件操作
		File linkF=new File(linkFilePath);
		BufferedReader linkFBR=new BufferedReader(new FileReader(linkF));
		File degreeF=new File(degreeFilePath);
		BufferedReader degreeFBR=new BufferedReader(new FileReader(degreeF));
		
		//读出入度出度信息
		String degreeLine,degreeArray[];
		degreeLine=degreeFBR.readLine();
		while((degreeLine=degreeFBR.readLine())!=null){
			degreeArray=degreeLine.split(" ");
			outdegree[Integer.parseInt(degreeArray[0])]=Integer.parseInt(degreeArray[1]);
			indegree[Integer.parseInt(degreeArray[0])]=Integer.parseInt(degreeArray[2]);
		}
		
		//读出链接关系，建立转换矩阵
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
	
	
	//计算TrustRank值，其中T为转换矩阵，alpha为调节(衰减)因子，一般为0.85，M为迭代次数，一般用20
	public RankValue[] getRank(float [][]T,float alpha,int M) throws Exception{
		int n=T.length;
		float s1[]=new float[n],s2[]=new float[n],d[]=new float[n];
		int i,j,t;
        //初始化d为等概率向量
		for(i=0;i<n;i++){
			d[i]=1.0F/n;//即此时的d［i]为1／N
		    s1[i]=d[i];
		}
							
		//迭代M次
		for(t=0;t<M;t++){
			for(j=0;j<n;j++){
				s2[j]=s1[j];//保留上一次迭代后的值
				s1[j]=0.0F;
			}
			for(i=0;i<n;i++){//一次循环计算一个元素的PageRank的值
				for(j=0;j<n;j++){
					s1[i]+=alpha*T[i][j]*s2[j]; 
				}
				s1[i]+=(1-alpha)*d[i];
			}
		}
		RankValue s[]=new RankValue[n];
		for(i=0;i<n;i++)
			s[i]=new RankValue(i,s1[i]);//记录每个元素结点及其相应的PageRank值
		return s;
	}
	
	//通过冒泡排序对每个元素的PageRank值从小到大排序
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