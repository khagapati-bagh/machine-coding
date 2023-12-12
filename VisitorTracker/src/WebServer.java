import java.util.ArrayList;
import java.util.List;

public class WebServer {
    private final List<Site> sites;

    public WebServer() {
        this.sites = new ArrayList<>();
    }

    public void addSite(Site site) {
        this.sites.add(site);
    }

    public List<Site> getSites() {
        return this.sites;
    }

    public void handleRequest(String siteName) {
        Site site = findSite(siteName);
        if (site != null) {
            site.getVisitorCounter().incrementCount();
        } else {
            System.out.println("Site not found");
        }
    }

    public void displayVisitorCount(String siteName) {
        Site site = findSite(siteName);
        if (site != null) {
            System.out.println(site.getVisitorCounter().getCount());
        } else {
            System.out.println("Site not found");
        }
    }

    private Site findSite(String siteName) {
        for (Site site : this.sites) {
            if (site.getSiteName().equals(siteName)) {
                return site;
            }
        }
        return null;
    }
}
