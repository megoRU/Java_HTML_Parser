import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {

  private static final String userName = System.getProperty("user.name");

  public static void main(String[] args) throws IOException {
    StringBuilder builder = new StringBuilder();
    BufferedReader br = new BufferedReader(
        new FileReader("C:/Users/savin/Desktop/Aennye shed lagi.txt"));
    for (; ; ) {
      String line = br.readLine();
      if (line == null) {
        break;
      }
      // builder.append(line + "\n");
      System.out.println(builder.append(line + "\n"));
      String text = "test";
      FileWriter writerFile = new FileWriter(
          "C:/Users/savin/Desktop/Aennye shed lagi.txt", true);
      BufferedWriter bufferWriter = new BufferedWriter(writerFile);

      bufferWriter.write(text + "\n");
      bufferWriter.close();
      break;
    }
  }
}
