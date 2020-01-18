package linkSpam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
 

public class StatsRank {
	String label[] = new String[11402];//��¼��վ���ʵ�����
	String name[] = new String[11402];
	void addLabel(String inFilePath) throws Exception {
		//ͨ��" "�����IdNameLabel.txt�ļ���ÿһ�е���Ϣ�ֳ����ݣ�����Label����Ϣ�洢�ڵ�������
		File labelOut = new File(inFilePath);
		BufferedReader labelFBR = new BufferedReader(new FileReader(labelOut));
		
		for(int i=0;i<11402;i++)
		{
			label[i]= " ";	
			name[i]=" ";
		}
		
		String labelLine,labelArray[];
		//labelLine = labelFBR.readLine();
		while((labelLine = labelFBR.readLine()) != null) {
			labelArray = labelLine.split(" ");
			//System.out.println(" "+labelArray[0]+" "+labelArray[1]+" "+labelArray[2]);	
			label[Integer.parseInt(labelArray[0])] = labelArray[2];
			name[Integer.parseInt(labelArray[0])] = labelArray[1];
		}
		
		labelFBR.close();
	
	}
	
	static int numNormal1 = 0;
	static int numNormal2 = 0;
	static int numNormal3 = 0;
	static int numNormal4=0;
	static int numSpam1 = 0;
	static int numSpam2 = 0;
	static int numSpam3 = 0;
	static int numSpam4=0;
	
	void statistic(RankValue[] arr) throws Exception {
		
		
		//ͳ��ǰN����վ��normal��spam������
		int numNormal = 0;
		int numSpam = 0;
		//N=1000
		for(int i=0;i<1000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal1=numNormal;
			numSpam1=numSpam;
			
		}
		System.out.println("��N=1000ʱ,normal��վ��������:"+numNormal +"��spam��վ��������"+numSpam );
		//N=2000
		for(int i=1000;i<2000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal2=numNormal;
			numSpam2=numSpam;
		}
		System.out.println("��N=2000ʱ,normal��վ��������:"+numNormal +"��spam��վ��������"+numSpam );
		//N=3000
		for(int i=2000;i<3000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal3=numNormal;
			numSpam3=numSpam;
		}
		System.out.println("��N=3000ʱ,normal��վ��������:"+numNormal +"��spam��վ��������"+numSpam );
		//N=4000
		for(int i=3000;i<4000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal4=numNormal;
			numSpam4=numSpam;
		}
		System.out.println("��N=4000ʱ,normal��վ��������:"+numNormal +"��spam��վ��������"+numSpam );
		
		Histogram();
	}
	
	@SuppressWarnings("deprecation")
	void Histogram () {
		CategoryDataset mDataset = GetDataset();		
		JFreeChart mBarChart = ChartFactory.createBarChart(
				"��վ����������״ͼ", 	//ͼ�����
				"ǰN����վ", 		//���ᣨĿ¼�ᣩ��ǩ
				"����", 			//���ᣨ��ֵ�ᣩ��ǩ
				mDataset, 		//���ݼ�
				PlotOrientation.VERTICAL,	//ͼ����
				true, 			//�Ƿ���ʾͼ��
				true,			//�Ƿ�������ʾ����
				false);			//�Ƿ�����url����
		//ͼ���������
		TextTitle mTextTitle = mBarChart.getTitle();
		mTextTitle.setFont(new Font("����", Font.BOLD, 20));
	
		//ͼ��ͼ������
		LegendTitle mLegend = mBarChart.getLegend();
		if(mLegend != null)
			mLegend.setItemFont(new Font("����", Font.CENTER_BASELINE, 15));
	
		//������״ͼ��
		CategoryPlot mPlot = (CategoryPlot)mBarChart.getPlot();
		
		//x��
		CategoryAxis mDomainAxis = mPlot.getDomainAxis();
		//����x����������
		mDomainAxis.setLabelFont(new Font("����", Font.PLAIN, 15));
		//����x����������
		mDomainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 15));
		//y��
		ValueAxis mValueAxis = mPlot.getRangeAxis();
		//����y���������
		mValueAxis.setLabelFont(new Font("����", Font.PLAIN, 15));
		//����y����������
		mValueAxis.setTickLabelFont(new Font("����", Font.PLAIN, 15));
		//������ʾ��ֵ
		BarRenderer mRenderer= new BarRenderer();
		mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		mRenderer.setItemLabelFont(new Font("����", Font.PLAIN, 10));
		mRenderer.setItemLabelsVisible(true);
		mPlot.setRenderer(mRenderer);
		
		ChartFrame mChartFrame = new ChartFrame("��վ����������״ͼ", mBarChart);
		mChartFrame.pack();//�Ժ��ʵĴ�Сչ��ͼ��
		mChartFrame.setVisible(true);//ͼ���Ƿ�ɼ�
	}
	
	public static CategoryDataset GetDataset()
	{
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		mDataset.addValue(numNormal1, "normal", "ǰ1000����վ");
		mDataset.addValue(numSpam1, "spam", "ǰ1000����վ");
		
		mDataset.addValue(numNormal2, "normal", "ǰ2000����վ");
		mDataset.addValue(numSpam2, "spam", "ǰ2000����վ");
		
		mDataset.addValue(numNormal3, "normal", "ǰ3000����վ");
		mDataset.addValue(numSpam3, "spam", "ǰ3000����վ");

		mDataset.addValue(numNormal4, "normal", "ǰ4000����վ");
		mDataset.addValue(numSpam4, "spam", "ǰ4000����վ");
		 
		return mDataset;
	}
}
