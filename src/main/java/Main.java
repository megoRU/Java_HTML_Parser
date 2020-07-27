import com.ninja_beans.crawler.helper.cloudflare.IuamSolver;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main extends JFrame {

  private static final String userName = System.getProperty("user.name");
  private static final String regexURL = "(https?:\\/\\/)?([\\dfincbook\\.-]+)\\.[a-z]+\\/[a-z]+\\/[0-9]+\\/[0-9]+[\\\\#]+[a-z]+[\\\\_]+[a-z]+";
  private static final String regexURL2 = "(https?:\\/\\/)?([\\dfincbook\\.-]+).+";
  private static final String regexURL3 = "(https?:\\/\\/)?([\\dfincbook\\.-]+)\\.[a-zA-Z0-9]*.[a-z]{3}.?[a-zA-Z0-9]+\\/[0-9]+.+";

  private javax.swing.JTextField jTextField1;

  public Main() {
    initComponents();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    JFrame frame = new JFrame("ficbook.net парсер");
    frame.setSize(550, 600);
    setResizable(false);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(
        dim.width/2-this.getSize().width/2,
        dim.height/2-this.getSize().height/2);
    super.setTitle("ficbook.net парсер");
    setIconImage(getImage());
  }

  private Image getImage() {
    String fileName = "icon" + ".png";
    ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
    return icon.getImage();
  }

  private void initComponents() {

    javax.swing.JColorChooser jColorChooser1 = new javax.swing.JColorChooser();
    javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    javax.swing.JButton jButton1 = new javax.swing.JButton();
    javax.swing.JButton jButton2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    Action action = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String jText = jTextField1.getText();
        parsing(jText);
      }
    };

    jTextField1.addActionListener(evt -> jTextField1ActionPerformed(evt));

    jButton1.addActionListener(evt -> {
      String jText = jTextField1.getText();
      parsing(jText);
    });

    jTextField1.addActionListener(action);

    jPanel1.setBackground(new java.awt.Color(0, 128, 128));

    jLabel1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Поле ввода ссылки:");

    jButton1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
    jButton1.setText("Отправить");
    jButton1.setFocusable(false);

    jButton2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
    jButton2.setText("Очистка");
    jButton2.setFocusable(false);
    jButton2.addActionListener(e -> jTextField1.setText(""));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                    false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(13, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    pack();
  }

  private void jTextField1ActionPerformed(ActionEvent evt) {
  }

//  public static class App {
//    public static void main(final String[] args) throws IOException, InterruptedException {
//      var url = "megolox.ru";
//      var result = IuamSolver.solve(url);
//
//      // 1. Create HttpClient
//      var client = HttpClient
//          .newBuilder()
//          .version(Version.HTTP_1_1)
//          .followRedirects(Redirect.NORMAL)
//          .cookieHandler(result.getCookieManager()).build();
//
//      // 2. Send the request and get the response
//      var request = HttpRequest.newBuilder().header("Accept", "*/*")
//          .header("User-Agent", result.getResponse().getUserAgent())
//          .GET()
//          .uri(URI.create(url))
//          .build();
//      var response = client.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
//
//      // 3. Parse the response
//      var doc = Jsoup.parse(response.body(), url);
//      var elm = doc.getElementById("title");
//      System.out.println(doc.title());
//      System.out.println(elm.html());
//    }
//  }

  public void parsing(String textFromJText) {
      try {

        var url = textFromJText;
        var result = IuamSolver.solve(url);

        // 1. Create HttpClient
        var client = HttpClient
            .newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .cookieHandler(result.getCookieManager()).build();

        // 2. Send the request and get the response
        var request = HttpRequest.newBuilder().header("Accept", "*/*")
            .header("User-Agent", result.getResponse().getUserAgent())
            .GET()
            .uri(URI.create(url))
            .build();
        var response = client.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));

        // 3. Parse the response
        var docc = Jsoup.parse(response.body(), url);
        var elm = docc.getElementById("title");
        System.out.println(docc.title());
        System.out.println(elm.html());



        File file = new File("C:/Users/" + userName + "/Desktop/"  + ".txt");
        if (!file.exists()) {
          PrintWriter writer = new PrintWriter(
              "C:/Users/" + userName + "/Desktop/"  + ".txt");
         // writer.println("");
          writer.close();
        }
        if (file.exists()) {
          BufferedReader br = new BufferedReader(
              new FileReader("C:/Users/" + userName + "/Desktop/" + ".txt", StandardCharsets.UTF_8));
          for (; ; ) {
            String line = br.readLine();
            if (line == null) {
              break;
            }
          }
            br.close();
            FileWriter writerFile = new FileWriter(
                            "C:/Users/" + userName + "/Desktop/" + ".txt",
                StandardCharsets.UTF_8, true);
            BufferedWriter bufferWriter = new BufferedWriter(writerFile);
            String lineSeparator = System.getProperty("line.separator");

          bufferWriter.write(lineSeparator);

            bufferWriter.close();
            writerFile.close();
        }

        jTextField1.setText("");
      } catch (Exception ex) {
        ex.printStackTrace();
      }

  }

  public static void main(String[] args) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
  }
}