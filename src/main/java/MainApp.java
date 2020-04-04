import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainApp {
    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame();
        String url = "https://cp-algorithms.com";
        JOptionPane.showMessageDialog(f,"Fetching URL...." + url);
        final Document page = Jsoup.connect(url).get();
        Scraper myScraper = new Scraper(page);
        myScraper.write();
        JOptionPane.showMessageDialog(f, "Downloaded.");
    }
}