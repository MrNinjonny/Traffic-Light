package pro;
import java.io.Serializable;

public class TrafficLight implements Serializable{
	
	private static final long serialVersionUID = -7478396453816473169L;
	private int load; // the TrafficLighr's load
	private boolean work; // the TrafficLight's working condition
	
	public TrafficLight() {
		this.work = false;
		this.load = (int)(Math.random()*100 + 1);
	}
	
	
	public String toString() {
		return "TrafficLight [load=" + load + ", work=" + work + "]";
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}
}