package com.codewr.java_coban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author NXCOMM
 */
public class App {

    public static void main(String[] args) {
        //get content html from web
        String url = "https://www.tutorialspoint.com/log4j/log4j_logging_methods.htm";
        String eleTitle = ".col-md-7.middle-col>h1";
        String eleContent = ".content";
        String type = "JAVA";

//        HashMap<String, String> hashMap = getContentHtmlfromWeb(url, eleTitle, eleContent, type);
//        //insert database with jdbc
//        insertDatabase(hashMap);

    }

    public void init() {
        Properties prop = new Properties();
        InputStream input = null;
        input = App.class.getClassLoader().getResourceAsStream("config.properties");
    }

    public void listCamReadFromFileConf() {
        String pathFileCam = "./conf/";
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("./conf/link.csv");
            br = new BufferedReader(fr);

            String strCurrentLine;
            int count = 0;
            while ((strCurrentLine = br.readLine()) != null) {
                if (strCurrentLine.contains("#")) {
//                    logger.info(strCurrentLine + " -----" + count);
                } else {
                    count++;
                    System.out.println(strCurrentLine);

                }
            }

        } catch (IOException e) {

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

            }

        }
    }

    public static HashMap<String, String> getContentHtmlfromWeb(String url, String eleTitle, String eleContent, String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element title = doc.select(eleTitle).first();
            String titleStr = title.text();
            String map_url = titleStr.toLowerCase().replace(" ", "-");
            Element link = doc.select(eleContent).first();
            String content = link.html();
            System.out.println(titleStr);
            System.out.println(map_url);
            System.out.println(content);
            hashMap.put("title", titleStr);
            hashMap.put("content", content);
            hashMap.put("map_url", map_url);
            hashMap.put("type", type);

        } catch (IOException ex) {
            return null;
        }
        return hashMap;
    }

    public static void insertDatabase(HashMap<String, String> hashMap) {
        String DB_URL = "jdbc:mysql://localhost:3306/codewr";
        String USER = "root";
        String PASS = "";
        Connection conn = null;
//        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
//            stmt = conn.createStatement();
            Integer id = null;
            String title = hashMap.get("title");
            String map_url = hashMap.get("map_url");
            String content = hashMap.get("content");
//            String content = "<b>kaka</b>";
            String type = hashMap.get("type");
            String meta_des = title;
            String meta_keys = title;
            Date newDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int isDeleted = 0;
            int lang = 0;
            int view = 0;
            int isSubmit = 0;

            String query = "INSERT INTO `articles` (id, title, map_url, meta_des, meta_keys, des_article,"
                    + " image, content, createdDate, updatedDate, isDeleted, type, lang, view, isSubmit)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//                    + "null, '"
//                    + title //title
//                    + "', '"
//                    + map_url //map_url
//                    + "', '"
//                    + meta_des //meta_des
//                    + "', '"
//                    + meta_keys //meta_keys
//                    + "', null, null, '" // des, image
//                    + content //content
//                    + "' ,'"
//                    + dateFormat.format(newDate) //created date
//                    + "', null, '"
//                    + isDeleted // is_deleted
//                    + "', '"
//                    + type //type
//                    + "', '"
//                    + lang //lang
//                    + "', '"
//                    + view //view
//                    + "', '"
//                    + isSubmit//isSubmit
//                    + "')";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setObject(1, null);
            preparedStmt.setObject(2, title);
            preparedStmt.setObject(3, map_url);
            preparedStmt.setObject(4, meta_des);
            preparedStmt.setObject(5, meta_keys);
            preparedStmt.setObject(6, null);
            preparedStmt.setObject(7, null);
            preparedStmt.setObject(8, content);
            preparedStmt.setObject(9, dateFormat.format(newDate));
            preparedStmt.setObject(10, null);
            preparedStmt.setObject(11, 0);
            preparedStmt.setObject(12, type);
            preparedStmt.setObject(13, 0);
            preparedStmt.setObject(14, 0);
            preparedStmt.setObject(15, 0);
            boolean insert = preparedStmt.execute();
//            stmt.executeUpdate(sql);
            System.out.println("Inserted records " + insert);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Goodbye!");
    }

    public static void readPageHtml() {
        try {
            URL google = new URL("http://www.google.com/");
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            String inputLine = "";

            while ((inputLine = in.readLine()) != null) {
//                inputLine+=in.readLine();
                // Process each line.
                System.out.println(inputLine);
            }
            in.close();

        } catch (MalformedURLException me) {
            System.out.println(me);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void getHtmlByJsoup() {

        String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        Document doc = (Document) Jsoup.parse(html);
        Element link = doc.select("a").first();

        String text = doc.body().text(); // "An example link"
        String linkHref = link.attr("href"); // "http://example.com/"
        String linkText = link.text(); // "example""

        String linkOuterH = link.outerHtml();
        // "<a href="http://example.com"><b>example</b></a>"
        String linkInnerH = link.html(); // "<b>example</b>"
        System.out.println(text);
        System.out.println(linkHref);
        System.out.println(linkText);
        System.out.println(linkOuterH);
        System.out.println(linkInnerH);
    }
}
