public class Main {
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        Site site1 = new Site("site1");
        Site site2 = new Site("site2");
        webServer.addSite(site1);
        webServer.addSite(site2);

        webServer.handleRequest("site1");
        webServer.handleRequest("site1");
        webServer.handleRequest("site2");
        webServer.displayVisitorCount("site1");
        webServer.displayVisitorCount("site2");
        webServer.handleRequest("site1");
        webServer.handleRequest("site2");
        webServer.displayVisitorCount("site1");
        webServer.displayVisitorCount("site3");
    }
}