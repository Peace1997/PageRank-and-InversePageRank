package linkSpam;


class RankValue{
	int hostid;
	float value;
	RankValue(int id,float v){
		hostid=id;
		value=v;
	}
}

public interface linkSpam{
	public float [][]getTransMatrix(String linkFilePath,String degreeFilePath) throws Exception;
	public RankValue[] getRank(float [][]T,float alpha,int M) throws Exception;
	public void bubbleSort(RankValue[] arr);
}