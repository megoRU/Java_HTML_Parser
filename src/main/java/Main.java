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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame {

  private static final String USER_NAME = System.getProperty("user.name");
  private static final String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.ROOT);
  private static final String regexURL = "https?:\\/\\/?[\\dfincbook.net\\/readfic]+\\/[0-9]+\\/[0-9]+[#a-z_]+";
  private static final String regexURL2 = "https?:\\/\\/?[\\dfincbook.net\\/readfic]+\\/[0-9]+\\/[0-9]+";
  private static final String regexURL3 = "https?:\\/\\/?[\\dfincbook.net\\/readfic]+\\/[0-9]+";
  private static final String regexURL4 = "https?:\\/\\/?[\\dfincbook.net\\/readfic]+\\/[0-9]+.+";
  private static String pathDesktopParse = null;
  private static String pathBeforeDesktopParse = null;
  private static String pathLastDesktopParse = null;
  private javax.swing.JTextField jTextField1;
  private static String title = null;

  public Main() {
    initComponents();
    JFrame frame = new JFrame("ficbook.net парсер");
    frame.setSize(550, 600);
    setResizable(false);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(
        dim.width / 2 - this.getSize().width / 2,
        dim.height / 2 - this.getSize().height / 2);
    super.setTitle("ficbook.net парсер");
    setIconImage(getImage());
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

  public void parsing(String textFromJText) {
    if (!textFromJText.matches(regexURL)
        && !textFromJText.matches(regexURL2)
        && !textFromJText.matches(regexURL3)
        && !textFromJText.matches(regexURL4)) {
      jTextField1.setText("URL адрес неверный!");
      jTextField1.setText("");
      return;
    }
    try {
      if (textFromJText.matches(regexURL)
          || textFromJText.matches(regexURL2)
          || textFromJText.matches(regexURL3)
          || textFromJText.matches(regexURL4)) {

        //C:\java_GitHub\ficbook.net_Parser\src\main\java\ficbook_parser_cloudflare.py
        String dir = System.getProperty("user.dir");
        System.out.println(dir);

        if (dir.equals("/home/" + USER_NAME)) {
          dir = dir + "/Desktop";
        }

        if (OS_NAME.contains("linux") || OS_NAME.contains("mac")) {
          //Запускает python скрипт
          Process process = Runtime.getRuntime().exec(
              "python3 " + dir + "/ficbook_parser_cloudflare.py "
                  + dir +
                  "/ficbook_parse.txt"
                  + " "
                  + textFromJText);
          Thread.sleep(800);
        }

        if (OS_NAME.contains("win")) {
          //Запускает python скрипт
          Process process = Runtime.getRuntime().exec(
              "python " + dir + "/ficbook_parser_cloudflare.py "
                  + dir +
                  "/ficbook_parse.txt"
                  + " "
                  + textFromJText);
          Thread.sleep(800);
        }

        getTitleFromFile(dir + "/ficbook_parse.txt");

        //Фикс для разных OS чтобы нормально файлы сохранять. Такой вот супер костыль
        if (OS_NAME.contains("win")) {
          pathDesktopParse = dir + "/ficbook_parse.txt";
          pathBeforeDesktopParse = "C:/Users/" + USER_NAME + "/Desktop/" + title + "NOT_FINAL" + ".txt";
          pathLastDesktopParse = "C:/Users/" + USER_NAME + "/Desktop/" + title + ".txt";
        }

        if (OS_NAME.contains("linux")) {
          pathDesktopParse = dir + "/ficbook_parse.txt";
          pathBeforeDesktopParse = "/home/" + USER_NAME + "/Desktop/" + title + "NOT_FINAL" + ".txt";
          pathLastDesktopParse = "/home/" + USER_NAME + "/Desktop/" + title + "FINAL.txt";
        }

        if (OS_NAME.contains("mac")) {
          pathDesktopParse = dir + "/ficbook_parse.txt";
          pathBeforeDesktopParse = "/Users/" + USER_NAME + "/Desktop/" + title + "NOT_FINAL" + ".txt";
          pathLastDesktopParse = "/Users/" + USER_NAME + "/Desktop/" + title + "FINAL.txt";
        }

        //Путь для сохранения почти готового результата

        //Возвращает индекс чтобы удалять до или после
        int valueToDeleteFirst = linesFirst(pathDesktopParse); // + 3
        delete(pathDesktopParse, pathBeforeDesktopParse, valueToDeleteFirst);

        //Возвращает индекс чтобы удалять до или после
        int valueToDeleteSecond = linesSecond(pathBeforeDesktopParse); //тут надо проверить

        System.out.println(pathLastDesktopParse);
        deleteSecond(pathBeforeDesktopParse, pathLastDesktopParse, valueToDeleteSecond);

        //Удаление лишних файлов в процессе работы
        Path path = Paths.get(pathBeforeDesktopParse);
        Path path2 = Paths.get(pathDesktopParse);
        try {
          Files.deleteIfExists(path);
          Files.deleteIfExists(path2);
          jTextField1.setText("");
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void getTitleFromFile(String filePathIn) {
    File inputFile = new File(filePathIn);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        if (currentLine.contains("<title>")) {
          title = currentLine
              .trim()
              .replaceAll("<title>", "")
              .replaceAll("</title>", "")
              .replaceAll("    ", "")
              .replaceAll("<h2 itemprop=\"headline\">", "")
              .replaceAll("</h2>", "")
              .replaceAll(":", " ")
              .replaceAll("/", "")
              .replaceAll("\\*", "")
              .replaceAll("\"", "")
              .replaceAll("\"", "")
              .replaceAll("\\?", "")
              .replaceAll("<", "")
              .replaceAll(">", "")
              .trim();
        }
      }
      reader.close();
    } catch (IOException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    }

  }

  public static void delete(String filePathIn, String filePathOut, int toRemove) {
    int count = 0;
    int first = 0;
    File inputFile = new File(filePathIn);
    File tempFile = new File(filePathOut);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
      FileWriter writerFile = new FileWriter(tempFile, StandardCharsets.UTF_8, true);
      BufferedWriter bufferWriter = new BufferedWriter(writerFile);

      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        count++;
        if (count < toRemove) {
          continue;
        }
        if (count > toRemove) {
          if (first < 1) {
            String firstLine = currentLine
                .replaceAll("data-is-adult=\"1\" itemprop=\"articleBody\">", "")
                .replaceAll("data-is-adult=\"\" itemprop=\"articleBody\">", "");
            bufferWriter.write(firstLine + System.getProperty("line.separator"));
            bufferWriter.write(System.getProperty("line.separator"));
          }
          if (first > 1) {
            bufferWriter.write(currentLine.trim() + System.getProperty("line.separator"));
          }
          first++;
        }
      }
      bufferWriter.close();
      reader.close();
    } catch (IOException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    }
  }

  public static void deleteSecond(String filePathIn, String filePathOut, int toRemove) {
    int count = 0;
    File inputFile = new File(filePathIn);
    File tempFile = new File(filePathOut);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
      FileWriter writerFile = new FileWriter(tempFile, StandardCharsets.UTF_8, true);
      BufferedWriter bufferWriter = new BufferedWriter(writerFile);

      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        count++;
        if (count > toRemove) {
        }
        if (count <= toRemove) {
          bufferWriter.write(currentLine
              .trim()
              .replaceAll("&nbsp;", "")
              .replaceAll("<b>", "")
              .replaceAll("</b>", "")
              .replaceAll("</div>", "")
              .replaceAll("</i>", "")
              .replaceAll("<i>", "")
              .replaceAll("<p align=\"center\" style=\"margin: 0;\">", "")
              .replaceAll("</p>", "")
              .replaceAll("<div class=\"part-comment-bottom mx-10 mx-xs-5\">", "")
              .replaceAll("<strong>", "")
              .replaceAll("</strong>", "")
              .replaceAll("<div class=\"urlize\">", "")
              .replaceAll("<br />", "")
              .replaceAll("<br>", "")
              .replaceAll("<p align=\"right\" style=\"margin: 0;\"><b>", "")
              .replaceAll("<p align=\"right\" style=\"margin: 0;\">", "")
              .replaceAll("</s>", "")
              .replaceAll("<s>", "") + System.getProperty("line.separator"));
        }
      }
      bufferWriter.close();
      reader.close();
    } catch (IOException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    }
  }

  public static int linesFirst(String filePathIn) {
    int lineCount = 0;
    try {
      File inputFile = new File(filePathIn);
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String needWorld = "articleBody";
      while (!reader.readLine().trim().contains(needWorld)) {
        lineCount++;
      }

      reader.close();
      return lineCount;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static int linesSecond(String filePathIn) {
    int lineCount = 0;
    try {
      File inputFile = new File(filePathIn);
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String needWorld = "</article>";
      while (!reader.readLine().trim().contains(needWorld)) {
        lineCount++;
      }
      reader.close();
      return lineCount - 2; //Зачем. Но лучше не трогать.
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}