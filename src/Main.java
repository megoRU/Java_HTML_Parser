import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

  public static void main(String[] args) throws IOException {
    try {
      Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Information_asymmetry").get();

      //get page title
//      String title = doc.title();
//      System.out.println("title : " + title);
      Elements mainHeaderElements = doc.select("div#mw-content-text.mw-content-ltr");
      System.out.println(mainHeaderElements.text());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
