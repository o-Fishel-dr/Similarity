public class Results implements Comparable<Results> {

	private double sim;
	private String major;

	public Results(double sim, String m) {
		this.sim = sim;
		this.major = m;

	}
	public String toString() {
		String sim2 = String.format("%.2f", sim);
		return "Major is " + major + " with similarity of " + sim2 + "%";
	}



	@Override
	public int compareTo(Results o) {
		double cmp =  this.sim - o.sim;
		if      (cmp < 0) return -1;
		else if (cmp > 0) return 1;
		return 0;
	}
}
