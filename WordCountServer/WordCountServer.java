import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;

public class WordCountServer {
    public static void main(String[] args) {
        try {
            System.out.println("Starting XML-RPC Server...");
            WebServer webServer = new WebServer(8080);
            XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("wordcount", WordCountHandler.class);
            xmlRpcServer.setHandlerMapping(phm);
            XmlRpcServerConfigImpl config = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
            config.setEnabledForExtensions(true);
            config.setContentLengthOptional(false);
            webServer.start();
            System.out.println("Server started successfully on port 8080! Waiting for a client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}