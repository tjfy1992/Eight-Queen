import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EightQueen {
	//起始状态列表
	public static List<State> startStates = new ArrayList<State>();
	
	//棋盘的行列数和要放置的皇后数量
	public static final int lineNum = 6;
	
	//一个N×N的棋盘
	public static Point[][] allPoints = new Point[lineNum][lineNum];
	
	//解法数量
	public static int count = 0;
	
	public static void main(String[] args) {
		
		//初始化棋盘
		for(int i=0; i<lineNum; i++){
			for(int j=0; j<lineNum; j++){
				allPoints[i][j] = new Point(i, j);
			}
		}
		
		//初始化起始状态列表。每个State的PointList分别存放了第一行的8个坐标，并且设置第一行为遍历初始行
		for(int i=0; i<lineNum; i++){
			State state = new State();
			state.getPointList().add(new Point(0, i));
			state.setLineNum(0);
			startStates.add(state);
		}
		
		//对于初始化state列表中的每个state,进行遍历操作。
		for(State state : startStates){
			calculate(state);
		}
		System.out.println("总数为：" + count); 
	}
	
	public static void calculate(State state)
	{
		Stack<State> stack = new Stack<State>();
		stack.push(state);
		while(!stack.isEmpty()){
			//从stack里取出一个状态
			State state2 = stack.pop();
			//如果已经遍历到最后一行，输出这个解
			if(state2.getLineNum() == lineNum - 1){
				for(Point goalpoint : state2.getPointList()){
					for(int i=0; i<lineNum; i++){
						if(i!=goalpoint.getY())
							System.out.print("_ ");
						else
							System.out.print("Q ");
					}
					System.out.println(); 
				}
				System.out.println();
				count++;
				continue;
			}
			
			//否则寻找下一行可以放置皇后的位置
			int currentLineNum = state2.getLineNum() + 1;
			for(Point point : allPoints[currentLineNum]){
				//如果该点可以放置皇后
				if(isSatisfied(point, state2.getPointList()))
				{
					//创建一个state对象
					State newState = new State();
					//把这个新的state的pointList设置为前一个点的pointList里的所有点加上当前的点的坐标
					for(Point point2 : state2.getPointList()){
						newState.getPointList().add(new Point(point2.getX(), point2.getY()));
					}
					newState.getPointList().add(point);
					//设置新的state的行数为下一行
					newState.setLineNum(currentLineNum);
					//入栈
					stack.push(newState);
				}
			}
		}
	}
	
	//判断一个点是否可以放置皇后
	public static boolean isSatisfied(Point point, List<Point> list){
		for(Point point2 : list){
			//两个皇后不能再同一条横线、直线、斜线上。由于我们直接遍历的是下一行的点，所以肯定不会出现X坐标相同的情况
			if(point2.getY() == point.getY() 
					|| Math.abs(point2.getX() - point.getX()) == Math.abs(point2.getY() - point.getY()))
				return false;
		}
		return true;
	}

}
