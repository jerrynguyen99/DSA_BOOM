package main;


import com.Karacter;
import com.MainGUI;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    public static void main(String[] args){
        try(InputStream input = new FileInputStream("config.properties")){
            Properties properties = new Properties();
            properties.load(input);
            Karacter character1 = new Karacter( properties.getProperty("NAME"),
                                                Integer.parseInt(properties.getProperty("COUNT")),
                                                Integer.parseInt(properties.getProperty("BLAST")),
                                                Integer.parseInt(properties.getProperty("SPEED")),
                                                Integer.parseInt(properties.getProperty("MAXCOUNT")),
                                                Integer.parseInt(properties.getProperty("MAXBLAST")),
                                                Integer.parseInt(properties.getProperty("MAXSPEED")));
            System.out.println(character1.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        new MainGUI();
        }

    }
