public class VisitorCounter {
    private int count;

    public VisitorCounter() {
        this.count = 0;
    }

    public void incrementCount() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}
