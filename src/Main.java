import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

  private static String regexTitle = "[A-Za-zА-Яа-я]+\\s.+?(?=—)";

  public static void main(String[] args) throws FileNotFoundException {

    PrintWriter writer = new PrintWriter("C:/Users/savin/Desktop/test2.txt");
    try {
      Document doc = Jsoup.connect("https://ficbook.net/readfic/9351982/23970356").get();

      String title = doc.title();

      System.out.println("title : " + title);
      Elements mainHeaderElements = doc.select("div#content");

      String text = mainHeaderElements.text();
      String[] SS = text.split("\\.");

      for (int i = 0; i < SS.length; i++) {
        String write2 = SS[i] + ".";
         writer.write(write2);
      //  System.out.println(write2);
      }
      writer.flush();
      writer.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
