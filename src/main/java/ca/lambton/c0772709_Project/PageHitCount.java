package ca.lambton.c0772709_Project;

public class PageHitCount {

	private Integer pageHitCounter;

	public PageHitCount() {
		pageHitCounter = 0;
	}

	public void incrementCounter() {
		pageHitCounter++;
	}

	public Integer getCounter() {
		return pageHitCounter;
	}
}
