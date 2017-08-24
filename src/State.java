import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Point> pointList = new ArrayList<Point>();
	private int lineNum;

	public List<Point> getPointList() {
		return pointList;
	}
	
	public int getLineNum(){
		return lineNum;
	}
	
	public void setLineNum(int lineNum){
		this.lineNum = lineNum;
	}
	
}
