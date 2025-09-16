import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.URL;

public class WordCountClient {
    public static void main(String[] args) {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8080"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Object[] params = new Object[] { "Hello XML RPC word count example" };
            Integer result = (Integer) client.execute("wordcount.countWords", params);

            System.out.println("Word Count: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
