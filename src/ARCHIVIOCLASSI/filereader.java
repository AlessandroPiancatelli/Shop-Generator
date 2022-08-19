/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ARCHIVIOCLASSI;
import PiancsShopGenerator.Items;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author aless
 */
public class filereader {
    ArrayList<Items> PHBItems;
    public ArrayList<Items> filereader()
    {
        PHBItems = new ArrayList<Items>();
       try {
			Scanner scanner = new Scanner(new File("here we go.txt"));
			while (scanner.hasNextLine()) {
				String a = scanner.nextLine();
                                String b = scanner.nextLine();
                                String c = scanner.nextLine();
                                String d = scanner.nextLine().replaceAll("[^0-9,.]", "");
                                double d1 = Double.parseDouble(d);
                                String e = scanner.nextLine().replaceAll("[^0-9,.]", "");
                                double e1 = Double.parseDouble(e);
                                String T = scanner.nextLine();
                                String f = scanner.nextLine();
                                String T1= scanner.nextLine();
                                Items newitem = new Items(a, b, c, d1, e1, f, false);
                                String AA = newitem.getNome();
                                System.out.println(AA);
                                PHBItems.add(newitem);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
       
       return PHBItems;
       
       
       
       
       
       
	}
}
    
    
   

