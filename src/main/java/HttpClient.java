
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class HttpClient {
    String s;
    void run() {
        CloseableHttpResponse httpRes = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://speller.yandex.net/services/spellservice/checkText?text=синхрофозотрон+в+дубне");
            httpRes = httpclient.execute(httpGet);
             s= EntityUtils.toString(httpRes.getEntity());
             writeFile(s);

            System.out.println(s);

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                httpRes.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    public  Document stringToDom()
            throws SAXException, ParserConfigurationException, IOException {
        run();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(s)));
    }




        public void writeFile(String yourXML){
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter("outfilename.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.write(yourXML);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

/*
public void StreamXML() {
    try {
        XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));

        while (xmlr.hasNext()) {
            xmlr.next();
            if (xmlr.isStartElement()) {
                System.out.println(xmlr.getLocalName());
            } else if (xmlr.isEndElement()) {
                System.out.println("/" + xmlr.getLocalName());
            } else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                System.out.println("   " + xmlr.getText());
            }
        }
    } catch (FileNotFoundException | XMLStreamException ex) {
        ex.printStackTrace();
    }
}

*/
}
