public class Site {
    private final String siteName;
    private final VisitorCounter visitorCounter;

    public Site(String siteName) {
        this.siteName = siteName;
        this.visitorCounter = new VisitorCounter();
    }
    public String getSiteName() {
        return this.siteName;
    }

    public VisitorCounter getVisitorCounter() {
        return visitorCounter;
    }
}
