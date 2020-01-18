package linkSpam;

import java.util.Scanner;

public class main
{
	public static void main(String args[]) throws Exception{
		
		test t =new test();
		t.printContent();
		 @SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int select=0;
		for(int i=0;i<20;i++) {
			select=in.nextInt();
			if(select==9) { System.out.println("退出成功"); break;}
	      switch(select)
	      {
	      	 case 0 :
	      		 t.printContent();
	      		 break;
	         case 2 :
	            t.testDegree();
	            break;
	         case 3 :
	        	 t.testPageRank();
	        	 break;
	         case 5 :
	            t.testInversePageRank();
	            break;
	         case 6 :
	            t.testStatsPageRank();
	            break;
	         case 7 :
	            t.testInverseStatsPageRank();
	            break;
	         default :
	            System.out.println("未知数字,请重新输入");
	      }
	      System.out.println();
		}
	}
	
}