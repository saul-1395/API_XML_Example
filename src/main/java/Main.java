import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        httpClient.run();
        try {
            Ghola.runner(httpClient.stringToDom());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
