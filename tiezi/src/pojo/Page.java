package pojo;

public class Page {
	private int currNo = 1;
	private int size = 2;
	private int countCurrNo;
	private int countSize;

	public int getCurrNo() {
		return currNo;
	}

	public void setCurrNo(int currNo) {
		this.currNo = currNo <= 0 ? 1 : currNo > this.countCurrNo ? this.countCurrNo : currNo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size <= 0 ? this.size : size;
	}

	public int getCountCurrNo() {
		return countCurrNo;
	}

	public void setCountCurrNo(int countCurrNo) {
		this.countCurrNo = countCurrNo;
	}

	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
		this.countCurrNo = this.countSize == 0 ? 1
				: this.countSize % this.size == 0 ? this.countSize / this.size : this.countSize / this.size + 1;

	}

}
