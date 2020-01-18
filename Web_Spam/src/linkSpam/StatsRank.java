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
	String label[] = new String[11402];//记录网站性质的数组
	String name[] = new String[11402];
	void addLabel(String inFilePath) throws Exception {
		//通过" "分离把IdNameLabel.txt文件中每一行的信息分成三份，其中Label的信息存储在第三份中
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
		
		
		//统计前N个网站中normal和spam的数量
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
		System.out.println("当N=1000时,normal网站的数量是:"+numNormal +"。spam网站的数量是"+numSpam );
		//N=2000
		for(int i=1000;i<2000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal2=numNormal;
			numSpam2=numSpam;
		}
		System.out.println("当N=2000时,normal网站的数量是:"+numNormal +"。spam网站的数量是"+numSpam );
		//N=3000
		for(int i=2000;i<3000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal3=numNormal;
			numSpam3=numSpam;
		}
		System.out.println("当N=3000时,normal网站的数量是:"+numNormal +"。spam网站的数量是"+numSpam );
		//N=4000
		for(int i=3000;i<4000;i++) {
			if(label[arr[i].hostid].equals("normal"))
			{ numNormal++; }
			if(label[arr[i].hostid].equals("spam"))
			{ numSpam++;  }
			
			numNormal4=numNormal;
			numSpam4=numSpam;
		}
		System.out.println("当N=4000时,normal网站的数量是:"+numNormal +"。spam网站的数量是"+numSpam );
		
		Histogram();
	}
	
	@SuppressWarnings("deprecation")
	void Histogram () {
		CategoryDataset mDataset = GetDataset();		
		JFreeChart mBarChart = ChartFactory.createBarChart(
				"网站类型数量柱状图", 	//图表标题
				"前N个网站", 		//横轴（目录轴）标签
				"数量", 			//纵轴（数值轴）标签
				mDataset, 		//数据集
				PlotOrientation.VERTICAL,	//图表方向
				true, 			//是否显示图例
				true,			//是否生成提示工具
				false);			//是否生成url连接
		//图表标题设置
		TextTitle mTextTitle = mBarChart.getTitle();
		mTextTitle.setFont(new Font("黑体", Font.BOLD, 20));
	
		//图表图例设置
		LegendTitle mLegend = mBarChart.getLegend();
		if(mLegend != null)
			mLegend.setItemFont(new Font("宋体", Font.CENTER_BASELINE, 15));
	
		//设置柱状图轴
		CategoryPlot mPlot = (CategoryPlot)mBarChart.getPlot();
		
		//x轴
		CategoryAxis mDomainAxis = mPlot.getDomainAxis();
		//设置x轴标题的字体
		mDomainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));
		//设置x轴坐标字体
		mDomainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));
		//y轴
		ValueAxis mValueAxis = mPlot.getRangeAxis();
		//设置y轴标题字体
		mValueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));
		//设置y轴坐标字体
		mValueAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));
		//柱体显示数值
		BarRenderer mRenderer= new BarRenderer();
		mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 10));
		mRenderer.setItemLabelsVisible(true);
		mPlot.setRenderer(mRenderer);
		
		ChartFrame mChartFrame = new ChartFrame("网站类型数量柱状图", mBarChart);
		mChartFrame.pack();//以合适的大小展现图形
		mChartFrame.setVisible(true);//图形是否可见
	}
	
	public static CategoryDataset GetDataset()
	{
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		mDataset.addValue(numNormal1, "normal", "前1000个网站");
		mDataset.addValue(numSpam1, "spam", "前1000个网站");
		
		mDataset.addValue(numNormal2, "normal", "前2000个网站");
		mDataset.addValue(numSpam2, "spam", "前2000个网站");
		
		mDataset.addValue(numNormal3, "normal", "前3000个网站");
		mDataset.addValue(numSpam3, "spam", "前3000个网站");

		mDataset.addValue(numNormal4, "normal", "前4000个网站");
		mDataset.addValue(numSpam4, "spam", "前4000个网站");
		 
		return mDataset;
	}
}
