package linkSpam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class test
{
		String linkF="C:/Users/z1205/Desktop/originalhostgraph_weighted.txt";
		String degreeF="C:/Users/z1205/Desktop/inoutDegree.txt";
		
		PageRank PR=new PageRank();
		InversePageRank IPR=new InversePageRank();
		RankValue[] y;
		RankValue[] y2;
		
		//������ȳ���
		void testDegree() throws Exception {
			Degree gD = new Degree();
			gD.getDegree(linkF,degreeF);
			System.out.println("��ȳ�����Ϣ�ѱ�����inoutDegree.txt");
		}

		//test PageRank
		void testPageRank() throws Exception {
		
		float[][] U1=PR.getTransMatrix(linkF, degreeF);
		y=PR.getRank(U1,0.85F, 20);
		PR.bubbleSort(y);
		File PageRankF=new File("C:/Users/z1205/Desktop/PageRank.txt");
		BufferedWriter PageRankFFBW=new BufferedWriter(new FileWriter(PageRankF));
		PageRankFFBW.write("hostid              PageRank              outdegree              indegree");
		PageRankFFBW.newLine();
		float pageranksum=0.0F;
		for(int i=0;i<y.length;i++){
			PageRankFFBW.write(y[i].hostid+"              "+y[i].value+"              "+PR.outdegree[y[i].hostid]+"                         "+PR.indegree[y[i].hostid]);
			PageRankFFBW.newLine();
			pageranksum+=y[i].value;
		}
		PageRankFFBW.close();
		System.out.println("������PageRankֵ�ѱ�����PageRank.txt��pageranksum="+pageranksum);	
		}
		
		//test InversePageRank
		void testInversePageRank() throws Exception {
		float[][] U2=IPR.getTransMatrix(linkF, degreeF);
		y2=IPR.getRank(U2,0.85F, 20);
		IPR.bubbleSort(y2);
		File PageRankF2=new File("C:/Users/z1205/Desktop/InversePageRank.txt");
		BufferedWriter PageRankFFBW2=new BufferedWriter(new FileWriter(PageRankF2));
		PageRankFFBW2.write("hostid              InversePageRank              outdegree              indegree");
		PageRankFFBW2.newLine();
		float pageranksum2=0.0F;
		for(int i=0;i<y2.length;i++){
			PageRankFFBW2.write(y2[i].hostid+"              "+y2[i].value+"                         "+IPR.outdegree[y2[i].hostid]+"                            "+IPR.indegree[y2[i].hostid]);
			PageRankFFBW2.newLine();
			pageranksum2+=y2[i].value;
		}
		PageRankFFBW2.close();
		System.out.println("������InversePageRankֵ�ѱ�����InversePageRank.txt��pageranksum="+pageranksum2);
		
		}
		
		//test StatsPageRank
		void testStatsPageRank() throws Exception {
		StatsRank SPR = new StatsRank();
		SPR.addLabel("C:/Users/z1205/Desktop/IdNameLabel.txt");
		File labelIn = new File("C:/Users/z1205/Desktop/StatsPageRank.txt");
		labelIn.createNewFile();
		BufferedWriter labelFBW = new BufferedWriter (new FileWriter(labelIn));
		labelFBW.write("hostid              PageRank              outdegree              indegree              label              name");
		labelFBW.newLine();
		for(int i=0; i<y.length;i++)
		{
			labelFBW.write(y[i].hostid+"              "+y[i].value+"              "+PR.outdegree[y[i].hostid]+"                            "+PR.indegree[y[i].hostid]+"              "+SPR.label[y[i].hostid]+"              "+SPR.name[y[i].hostid]);
			labelFBW.newLine();
		}
		System.out.println("���Label,Name���Ե�StasPageRank.txt�ѱ������,���У�");
		SPR.statistic(y);
		labelFBW.close();
		}
		
		//test InverseStatsPageRank
		void testInverseStatsPageRank() throws Exception {
		StatsRank SIPR = new StatsRank();
		SIPR.addLabel("C:/Users/z1205/Desktop/IdNameLabel.txt");
		File labelIn2 = new File("C:/Users/z1205/Desktop/StatsInversePageRank.txt");
		labelIn2.createNewFile();
		BufferedWriter labelFBW2 = new BufferedWriter (new FileWriter(labelIn2));
		labelFBW2.write("hostid              InversePageRank              outdegree              indegree              label              name");
		labelFBW2.newLine();
		for(int i=0; i<y2.length;i++)
		{
			labelFBW2.write(y2[i].hostid+"              "+y2[i].value+"                            "+IPR.outdegree[y2[i].hostid]+"                            "+IPR.indegree[y2[i].hostid]+"              "+SIPR.label[y2[i].hostid]+"              "+SIPR.name[y2[i].hostid]);
			labelFBW2.newLine();
		}
		System.out.println("���Label,Name���Ե�StasInversePageRank.txt�ѱ�����ϣ����У�");
		SIPR.statistic(y2);
		labelFBW2.close();
		}
		
		void printContent() {
		
		System.out.print("ʵѵ����: PageRank��Inverse PageRank�㷨�������ʵ��\r");
		System.out.print("******************��ѡ����Ҫʵ�ֵĹ���************\r");
		System.out.println();
		System.out.print("2----������ȳ���\r");
		System.out.print("3----����PageRank\r");
		System.out.print("5----����InversePageRank\r");
		System.out.print("6----ͳ��normal/spam����(PageRank)\r");
		System.out.print("7----ͳ��normal/spam����(InversePageRank)\r");
		System.out.print("0----�ص�������\r");
		System.out.print("9----�˳�\r");
		System.out.println();
		System.out.print("**************************************************\r");
		}
}