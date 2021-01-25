
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class HttpClient {
    String s;

    void run() {
        CloseableHttpResponse httpRes = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://speller.yandex.net/services/spellservice/checkText?text=синхрофозотрон+в+дубне");
            httpRes = httpclient.execute(httpGet);
            s = EntityUtils.toString(httpRes.getEntity());
           

            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpRes.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    public Document stringToDom()
            throws SAXException, ParserConfigurationException, IOException {
        run();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(s)));
    }


}
