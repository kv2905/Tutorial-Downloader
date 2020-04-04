
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Scraper {
    private Document page;
    Scraper(Document page) {
        this.page = page;
    }
    
    void createFile(String title) throws FileNotFoundException {
        String fileName = title + ".html";
        try(PrintWriter out = new PrintWriter(fileName)) {
            out.write(page.outerHtml());
        }
    }
    
    void write(){
         page.body().select("a").forEach((Element e) -> {
            String title = e.text();
            String link = e.attr("href");
            StringBuilder absLink = new StringBuilder(link);
            absLink.replace(0, 1, "https://cp-algorithms.com");
            link = absLink.toString();
             try {
                 page = Jsoup.connect(link).get();
             } catch (IOException ex) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"A URL not found.");
             }
             try {
                 createFile(title);
             } catch (FileNotFoundException ex) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Unable to create file.");
             }
         });  
    }
}


