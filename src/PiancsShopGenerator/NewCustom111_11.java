/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PiancsShopGenerator;

import ARCHIVIOCLASSI.NewCustom111;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aless
 */
public class NewCustom111_11 extends javax.swing.JFrame {
          ArrayList<Items> items;
          ArrayList<String> librarynames;
          ArrayList<itemsinshop> inventory;
          String [][] Tablelines;
          String [][] Tablelines1;
          ArrayList<String> shopnames;
  
    public NewCustom111_11() {
        initComponents();
        setcenter();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        inventory = new ArrayList<itemsinshop>();
        shopnames = new ArrayList<String>();
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        //setItemslist();
        ToArray();
        RowToTable();
        populateshoplist();
        setShoplist();
        populateshopitems();
        ToInventoryArray();
        RowToTableinventory();
        
    }
      public NewCustom111_11(ArrayList<itemsinshop>  TEMPinvetory) {
        initComponents();
        setcenter();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        inventory = TEMPinvetory;
        shopnames = new ArrayList<String>();
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        //setItemslist();
        ToArray();
        RowToTable();
        populateshoplist();
        setShoplist();
        //populateshopitems();
        ToInventoryArray();
        RowToTableinventory();
        
    }

    
    public void setcenter()
       {
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       }
    
     public void populatelibrarylist()
    {
       try
       {
           FileInputStream file = new FileInputStream("Library List");
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   librarynames.add((String) inputfile.readObject());
               }
               catch (EOFException e)
               {
                   endOfFile = true ;
               }
               catch (Exception f)
               {
                   JOptionPane.showMessageDialog(null, f.getMessage());
               }
                   
               }
           inputfile.close();
           }
       catch (IOException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }
     
     public void setLibrarylist()
    {
        String [] tipoArray = new String [librarynames.size()];
     
         for (int i=0 ; i < librarynames.size(); i++)
        {
                tipoArray[i] = librarynames.get(i);
        }
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
    }
    
    public void populateitemlibrary()
    {
        if(!(librarynames.size()<=0)){
       int a = jComboBox4.getSelectedIndex();
       String b = librarynames.get(a);
       try
       {
           FileInputStream file = new FileInputStream(b);
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   items.add((Items) inputfile.readObject());
               }
               catch (EOFException e)
               {
                   endOfFile = true ;
               }
               catch (Exception f)
               {
                   JOptionPane.showMessageDialog(null, f.getMessage());
               }
                   
               }
           inputfile.close();
           }
       catch (IOException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       
    }
        else{
           JOptionPane.showMessageDialog(null,"You cannot make any preset until u create a library with some items");
           disposethis();
           
          
        }
    }
    
     public void setItemslist()
    {
       
        String [] tipoArray = new String [items.size()];
         for (int i=0 ; i < items.size(); i++)
        {
                tipoArray[i] = items.get(i).getFormat();
        }
       // jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
    }
    
      public void ToArray()
    {
        //items.clear();
        //populateitemlibrary();
        Tablelines = new String [items.size()][5];
       for(int i=0; i < items.size(); i++) 
       {
          String a = items.get(i).getNome();
          String b = items.get(i).getType();
          String c = Double.toString(items.get(i).getCost());
          String d = Double.toString(items.get(i).getWeight());
          String e = items.get(i).getCategory();
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = e; 
          
       }
    }
      
      public void ToInventoryArray()
    {
        //items.clear();
        //populateitemlibrary();
        Tablelines1 = new String [inventory.size()][6];
       for(int i=0; i < inventory.size(); i++) 
       {
          String a = inventory.get(i).getNome();
          String b = inventory.get(i).getType();
          String c = Double.toString(inventory.get(i).getCost());
          String d = Double.toString(inventory.get(i).getWeight());
          String e = inventory.get(i).getCategory();
          String f = Double.toString(inventory.get(i).getNumber());
          Tablelines1 [i][0] = a;
          Tablelines1 [i][1] = b;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = e;
          Tablelines1 [i][5] = f;
       }
    }
     
       public void ToSortArray()
    {
        items.clear();
        populateitemlibrary();
        Tablelines = new String [items.size()][4];
       for(int i=0; i < items.size(); i++) 
       {
          String a = items.get(i).getNome();
          String b = items.get(i).getType();
          String c = Double.toString(items.get(i).getCost());
          String d = Double.toString(items.get(i).getWeight());
          String e = items.get(i).getCategory();
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = e;
       }
    }
      
    
      public void RowToTable()
    {
     String[] columnsname = {"Name","Type","Cost","Weigth","Category"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     jTable1.setModel(model);
    }

           public void RowToTableinventory()
    {
     String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
     DefaultTableModel model = new DefaultTableModel(Tablelines1, columnsname);
     int a = jTable2.getSelectedRow();
     jTable2.setModel(model);
     if(!(a==-1)){
         if(a<inventory.size()){
     jTable2.setRowSelectionInterval(a, a);
         }
     }
    }

             public void saveitemsinshop()
     {
         String b = jTextField2.getText();
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< inventory.size(); i++)
           {
            outputFile.writeObject(inventory.get(i));
           }
           outputFile.close();
           
           //JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
             public void saveShopToFile(boolean C)
     {
        // if (shopnames.size()>0){
        try
        {
            FileOutputStream file = new FileOutputStream("Shop List");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< shopnames.size(); i++)
           {
            outputFile.writeObject(shopnames.get(i));
           }
           outputFile.close();
           if(C == true){
           JOptionPane.showMessageDialog(null,"Successfully Deleted!");
           }
           else
                   {
                      JOptionPane.showMessageDialog(null,"Successfully Saved!"); 
                   }
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
   // }
        // else
        // {
          //  JOptionPane.showMessageDialog(null,"There are no shops files!"); 
         //}
     }
               public void setShoplist()
    {
        if(shopnames.size()>0){
        String [] tipoArray = new String [shopnames.size()];
     
         for (int i=0 ; i < shopnames.size(); i++)
        {
                tipoArray[i] = shopnames.get(i);
        }
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
    }
        else{
            String [] tipoArray = new String [1];
            tipoArray[0]="No existing preset";
           jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        }
    }
    
                public void populateshopitems()
    {
        if(shopnames.size()>0){
        inventory.clear();
        if(jComboBox5.getSelectedIndex()>-1){
       int a = jComboBox5.getSelectedIndex();
       String b = shopnames.get(a);
       try
       {
           FileInputStream file = new FileInputStream(b);
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   inventory.add((itemsinshop) inputfile.readObject());
               }
               catch (EOFException e)
               {
                   endOfFile = true ;
               }
               catch (Exception f)
               {
                   JOptionPane.showMessageDialog(null, f.getMessage());
               }
                   
               }
           inputfile.close();
           }
       catch (IOException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       
    }
        else{
            
        }
    }
        else{
            
        }
    }
                 public void populateshoplist()
    {
       try
       {
           FileInputStream file = new FileInputStream("Shop List");
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   shopnames.add((String) inputfile.readObject());
               }
               catch (EOFException e)
               {
                   endOfFile = true ;
               }
               catch (Exception f)
               {
                   JOptionPane.showMessageDialog(null, f.getMessage());
               }
                   
               }
           inputfile.close();
           }
       catch (IOException e){
           // JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }
     
    
             public void Deleteshopfile()
    {
        if (!(getshopnameindex()==-1)){
        String b = shopnames.get(getshopnameindex());
        try  
            {         
            File f= new File(b);           //file to be delete  
            if(f.delete())                      //returns Boolean value  
            {  
            System.out.println(f.getName() + " deleted");   //getting and printing the file name  
            }  
            else  
                {  
            System.out.println("failed");  
            }  
            }  
            catch(Exception e)  
            {  
            e.printStackTrace();  
            } 
            } 
    }

             public void checkforsameitem(itemsinshop checkeditem)
             {
                 int b = 0;
                 for (int i=0; i<inventory.size();i++)
                 {
                     if(checkeditem.getNome()==inventory.get(i).getNome() && checkeditem.getType()==inventory.get(i).getType() && checkeditem.getCost()==inventory.get(i).getCost() && checkeditem.getWeight()==inventory.get(i).getWeight())
                     {
                       itemsinshop  tempitem =  inventory.get(i);
                       tempitem.setNumber((inventory.get(i).getNumber())+(checkeditem.getNumber()));
                       inventory.set(i, tempitem);
                       b++;
                       ToInventoryArray();
                       RowToTableinventory();
                 }
                   
                }
                 if(b==0)
                     {
                         inventory.add(checkeditem);
                         ToInventoryArray();
                         RowToTableinventory(); 
                     }
             }
              public void saveitemsinexistentshop()
     {
     
         if(shopnames.size()>0)
         {
             String b = jComboBox5.getSelectedItem().toString();
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< inventory.size(); i++)
           {
            outputFile.writeObject(inventory.get(i));
           }
           outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     }

               public void removeitem()
               {
                   if(!(jTable2.getSelectedRow()==-1)){
                  String a = new String(Tablelines1[jTable2.getSelectedRow()][0]);
                   for(int i =0 ; i<inventory.size(); i++)
                   {
                       if(a.equals(inventory.get(i).getNome()))
                       {
                            itemsinshop b1 = inventory.get(i);
                            double a1 = jSlider1.getValue();
                            double c2 = b1.getNumber();
                            if(c2-a1<=0)
                            {
                                inventory.remove(i);
                            }
                            else
                            {
                                b1.setNumber(c2-a1);
                            }
                       }
                   }
                   ToInventoryArray();
                   RowToTableinventory();
                   
                   
                   }
                   else
                   {  
                       
                   }
               }                 
               
               public void showdescription()
               {
                  if(!(jTable1.getSelectedRow()==-1))
                  {
                    if(!(jTable1.getSelectedRow()==-1)){
                    String a  = new String(Tablelines[jTable1.getSelectedRow()][0]);
                    for(int i=0;i<items.size();i++)
                    {
                     if(items.get(i).getNome().equals(a))
                    {
                     jTextArea1.setText(items.get(i).getDescription());
                    }
                    }
                    }
                    else
                    {
          
                    }
                  }    
                  else
                  {
                      if(!(jTable2.getSelectedRow()==-1))
                      {
                           if(!(jTable2.getSelectedRow()==-1)){
                             String a  = new String(Tablelines1[jTable2.getSelectedRow()][0]);
                                for(int i=0;i<inventory.size();i++)
                                {
                                if(inventory.get(i).getNome().equals(a))
                                {
                                 jTextArea1.setText(inventory.get(i).getDescription());
                                }
                                }
                                }                         
                                else
                                 {
          
                                 }
                      }
                  }
               }
               
               public void disposethis()
               {
                   this.dispose();
               }
              
              public int getshopnameindex()
              {
                  int index = -1;
                  String a  =  jComboBox5.getSelectedItem().toString();
                  for (int i =0 ; i<shopnames.size() ; i++)
                  {
                      if(a.equals(shopnames.get(i)))
                      {
                          index = i;
                      }
                  }
                  return index;
              }
              
             
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton17 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setBackground(new java.awt.Color(51, 51, 51));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Save new custom");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 130, -1));

        jButton14.setBackground(new java.awt.Color(51, 51, 51));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Delete custom");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 110, -1));

        jButton15.setBackground(new java.awt.Color(51, 51, 51));
        jButton15.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Back");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 120, 80));

        jButton16.setBackground(new java.awt.Color(51, 51, 51));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Sort by");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 140, -1));

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        jComboBox3.setToolTipText("");
        jComboBox3.setName(""); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 100, -1));

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Library" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 100, -1));

        jTextField2.setBackground(new java.awt.Color(51, 51, 51));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("Custom name here....");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 130, -1));

        jButton18.setBackground(new java.awt.Color(51, 51, 51));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Sort by");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 100, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Library:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Category:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, -1, -1));

        jComboBox5.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 160, 50));

        jButton19.setBackground(new java.awt.Color(51, 51, 51));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("New custom");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        jButton20.setBackground(new java.awt.Color(51, 51, 51));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Modify selected custom");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 410, 190));

        jButton17.setBackground(new java.awt.Color(51, 51, 51));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jButton12.setBackground(new java.awt.Color(51, 51, 51));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("magic");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 73, -1));

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Misc");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 120, -1));

        jButton7.setBackground(new java.awt.Color(51, 51, 51));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Tools");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Consumables");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Adventure gear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Armor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        jButton8.setBackground(new java.awt.Color(51, 51, 51));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("PotionsAndScrolls");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 117, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Weapons");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jSlider1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jSlider1.setForeground(new java.awt.Color(255, 255, 255));
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(10);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.setValue(1);
        jSlider1.setName(""); // NOI18N
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 378, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 427, 174));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Create new item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Remove item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 118, -1));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 118, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 551, 219));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Item amount");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 580, 90));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/3ff1ac0e5ecc5491673d41ac25dafa83.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(!(jTextField2.getText().isEmpty()))
        {
        boolean C = false;
        for (int i = 0 ; i<shopnames.size();i++)
        {
            if(jTextField2.getText().equals(shopnames.get(i)))
            {
                C = true;
            }
        }
        if(C==false)
        {
        shopnames.add(jTextField2.getText());
        saveShopToFile(false);
        saveitemsinshop();
        setShoplist();
        }
        else
        {
           JOptionPane.showMessageDialog(null,"There is already a custom preset with that name!"); 
        }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
            if(!(getshopnameindex()==-1))
            {
                   Deleteshopfile(); 
                   shopnames.remove(getshopnameindex());
                   saveShopToFile(true);
                   setShoplist();
            }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new AddItemSPECIAL3(inventory).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

        String a = jComboBox3.getSelectedItem().toString().trim();
        if(!a.equals("All")){
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < items.size(); i++)
        {
            if(items.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(items.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][4];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
        }
        RowToTable();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
        }
        else
        {
        items.clear();
        populateitemlibrary();
        ToArray();
        RowToTable();
        //setItemslist();
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        this.dispose();
        new GenerateNewShop111().setVisible(true);
       
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
      if(jComboBox1.getSelectedItem().toString().equals("cost")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(items, new Comparator<Items>()
        {
           public int compare (Items s1, Items s2) 
           {
               return Double.valueOf(s1.getCost()).compareTo(s2.getCost());
           }
        
        });}
           if(jComboBox1.getSelectedItem().toString().equals("weight")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(items, new Comparator<Items>()
        {
           public int compare (Items s1, Items s2) 
           {
               return Double.valueOf(s1.getWeight()).compareTo(s2.getWeight());
           }
        
        });}
           if(jComboBox1.getSelectedItem().toString().equals("name")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(items, new Comparator<Items>()
        {
           public int compare (Items s1, Items s2) 
           {
               return String.valueOf(s1.getNome()).compareTo(s2.getNome());
           }
        
        });}
           if(jComboBox1.getSelectedItem().toString().equals("type")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(items, new Comparator<Items>()
        {
           public int compare (Items s1, Items s2) 
           {
               return String.valueOf(s2.getType()).compareTo(s1.getType());
           }
        
        });}
       // setItemslist();
       ToArray();
       RowToTable();
        
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String a = jComboBox3.getSelectedItem().toString().trim();
        Items temp;
          
            int b = jTable1.getSelectedRow();
            if (b == -1)
            {
               JOptionPane.showMessageDialog(null,"Please select the item to add"); 
            }
            else
            {
            String nomeT = new String(Tablelines[jTable1.getSelectedRow()][0]);
            for(int i = 0 ; i < items.size();i++)
            {
              if(nomeT.equals(items.get(i).getNome()))
              {
                  temp = new Items(items.get(i).getNome(), items.get(i).getCategory(), items.get(i).getType(), items.get(i).getCost(), items.get(i).getWeight(), items.get(i).getDescription(),items.get(i).getmagicstatus());
                   itemsinshop iteminshop = new itemsinshop (temp,jSlider1.getValue()) ; 
                checkforsameitem(iteminshop);
              }
            }
           
          
            
            }
        
 
        
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       removeitem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String a = "Weapons";
       if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String a = "Armor";
         if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     
       String a = "Consumables";
         if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       String a = "tools";
        if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         String a = "magic";
        if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String a = "PotionsAndScrolls";
        if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String a = "adventure gear";
       if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String a = "misc";
         if (inventory.size()>0 ){
        ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines1 = new String [tipoArray.size()][6];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          String h = Double.toString(tipoArray.get(i).getNumber());
          Tablelines1 [i][0] = e;
          Tablelines1 [i][1] = f;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = g;
          Tablelines1 [i][5] = h;
        }
        RowToTableinventory();
        //jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        tipoArray = null;   
       }
       else
       {
           
       }
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
         populateshopitems();
        ToInventoryArray();
        RowToTableinventory();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        populateshopitems();
        ToInventoryArray();
        RowToTableinventory();
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       inventory.clear();
       ToInventoryArray();
       RowToTableinventory();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        items.clear();
        populateitemlibrary();
        ToArray();
        RowToTable();
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
         if(jComboBox2.getSelectedItem().toString().equals("cost")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return Double.valueOf(s1.getCost()).compareTo(s2.getCost());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("weight")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return Double.valueOf(s1.getWeight()).compareTo(s2.getWeight());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("name")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return String.valueOf(s1.getNome()).compareTo(s2.getNome());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("type")){
        jComboBox3.setSelectedIndex(0);
        Collections.sort(inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return String.valueOf(s2.getType()).compareTo(s1.getType());
           }
        
        });}
       // setItemslist();
       ToInventoryArray();
       RowToTableinventory();
        
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
      saveitemsinexistentshop();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
   
        
              
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
     
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
       jTable1.clearSelection();
       showdescription();
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        jTable2.clearSelection();
        showdescription();
    }//GEN-LAST:event_jTable1MousePressed
           
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewCustom111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCustom111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCustom111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCustom111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCustom111().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
