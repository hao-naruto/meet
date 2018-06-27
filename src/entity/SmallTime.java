package entity;

/**
 * 单位间隔 小的 时间段  15 分钟间隔
 * @author wanghao
 *
 */
public class SmallTime {
	private int StartT;
	private int EndT;
	public SmallTime() {
		// TODO Auto-generated constructor stub
	}
	public SmallTime(int s, int e){
		this.StartT = s;
		this.EndT = e;
	}
	public int getStartT() {
		return StartT;
	}
	public void setStartT(int startT) {
		StartT = startT;
	}
	public int getEndT() {
		return EndT;
	}
	public void setEndT(int endT) {
		EndT = endT;
	}
	
}