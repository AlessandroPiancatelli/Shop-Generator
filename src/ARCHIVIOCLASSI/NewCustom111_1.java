/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ARCHIVIOCLASSI;

import ARCHIVIOCLASSI.NewCustom111;
import ARCHIVIOCLASSI.GenerateNewShop11;
import PiancsShopGenerator.AddItemSPECIAL2;
import PiancsShopGenerator.Items;
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
public class NewCustom111_1 extends javax.swing.JFrame {
          ArrayList<Items> items;
          ArrayList<String> librarynames;
          ArrayList<Items> inventory;
          String [][] Tablelines;
          ArrayList<String> shopnames;
  
    public NewCustom111_1() {
        initComponents();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        inventory = new ArrayList<Items>();
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
        Tablelines = new String [inventory.size()][5];
       for(int i=0; i < inventory.size(); i++) 
       {
          String a = inventory.get(i).getNome();
          String b = inventory.get(i).getType();
          String c = Double.toString(inventory.get(i).getCost());
          String d = Double.toString(inventory.get(i).getWeight());
          String e = inventory.get(i).getCategory();
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = e;
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
     String[] columnsname = {"Name","Type","Cost","Weigth","Category"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     jTable2.setModel(model);
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
           
           JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
             public void saveShopToFile()
     {
         if (shopnames.size()>0){
        try
        {
            FileOutputStream file = new FileOutputStream("Shop List");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< shopnames.size(); i++)
           {
            outputFile.writeObject(shopnames.get(i));
           }
           outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
         else
         {
            JOptionPane.showMessageDialog(null,"There are no shops files!"); 
         }
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
                   inventory.add((Items) inputfile.readObject());
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
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }
     
    
             public void Deleteshopfile()
    {
        int a = jComboBox5.getSelectedIndex();
        String b = shopnames.get(a);
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
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
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

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jButton1.setText("Add item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Create new item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Remove item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("item amount");

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
        jScrollPane3.setViewportView(jTable1);

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
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jButton4.setText("Weapons");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Armor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("consumables");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Tools");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("PotionsAndScrolls");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("adventure gear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("misc");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("misc");

        jButton12.setText("magic");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton17.setText("All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addGap(80, 80, 80)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton17))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 557, -1));

        jButton13.setText("Save new custom");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 30, 130, -1));

        jButton14.setText("delete custom");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 60, 110, -1));

        jButton15.setText("back");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, -1));

        jButton16.setText("sort by");
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 90, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 124, 140, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        jComboBox3.setToolTipText("");
        jComboBox3.setName(""); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 100, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Library" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 100, -1));

        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 0, 130, -1));

        jButton18.setText("sort by");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 100, -1));

        jLabel2.setText("Library:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, -1, -1));

        jLabel3.setText("Category:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, -1, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 1, 90, -1));

        jButton19.setText("nuovo");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        shopnames.add(jTextField2.getText());
        saveShopToFile();
        saveitemsinshop();
        setShoplist();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
         int a = jComboBox5.getSelectedIndex();           
                   shopnames.remove(a);
                   Deleteshopfile(); 
                   setShoplist();
    
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new AddItemSPECIAL2().setVisible(true);
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
        new GenerateNewShop11().setVisible(true);
       
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
        if(a.equals("All")){
            int b = jTable1.getSelectedRow();
            if (b == -1)
            {
               JOptionPane.showMessageDialog(null,"Please select the item to add"); 
            }
            else
            {
            inventory.add(items.get(b));
            ToInventoryArray();
            RowToTableinventory();
            
            }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Please set category to All to add an item");    
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String a = "Weapons";
       if (inventory.size()>0 ){
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        ArrayList<Items> tipoArray = new ArrayList<Items>();
        for (int i=0 ; i < inventory.size(); i++)
        {
            if(inventory.get(i).getCategory().trim().equals(a))
            {
            tipoArray.add(inventory.get(i));
            }      
        }
        Tablelines = new String [tipoArray.size()][5];
       for(int i=0; i < tipoArray.size(); i++) 
        {
          String e = tipoArray.get(i).getNome();
          String f = tipoArray.get(i).getType();
          String c = Double.toString(tipoArray.get(i).getCost());
          String d = Double.toString(tipoArray.get(i).getWeight());
          String g = tipoArray.get(i).getCategory();
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = g;
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
        // TODO add your handling code here:
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
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
