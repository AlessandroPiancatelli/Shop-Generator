/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PiancsShopGenerator;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aless
 */
public class AddItemSPECIAL2 extends javax.swing.JFrame {
          ArrayList<Items> items;
          ArrayList<String> librarynames;
          String [][] Tablelines;
    /**
     * Creates new form AddItemSPECIAL
     */
    public AddItemSPECIAL2() {
        initComponents();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        ToArray();
        RowToTable();
        setcenter();
    }

    public void setcenter()
       {
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       }
    public void saveiteminlibrary()
     {
         int a = jComboBox1.getSelectedIndex();
         String b = librarynames.get(a);
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< items.size(); i++)
           {
            outputFile.writeObject(items.get(i));
           }
           outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void setLibrarylist()
    {
        if(!(librarynames.size()<=0))
        {
        String [] tipoArray = new String [librarynames.size()];
     
         for (int i=0 ; i < librarynames.size(); i++)
        {
                tipoArray[i] = librarynames.get(i);
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
        }
        else
        {
         NameTF.setText("///");
         typeTF.setText("///");
         costTF.setText("///");
         weigthTF.setText("///");
         DescriptionTA.setText("To create new items you have to Create a library First , you can import the PHB items library in the Librarymanagment tab");
         magicCB.setSelected(false);
        }
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
           
       }
    }
     
     public void populateitemlibrary()
    {
        if (!(librarynames.size()<=0)){
       int a = jComboBox1.getSelectedIndex();
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
        else
        {
           // JOptionPane.showMessageDialog(null, "Please Create a library before filling it with items");
        }
    }
    
     public void ToArray()
    {
        items.clear();
        populateitemlibrary();
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
    
      public void RowToTable()
    {
     String[] columnsname = {"Name","Type","Cost","Weigth","Category"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     jTable1.setModel(model);
    }
      
      public void deleteitemfromlibrary()
    {
       int a = jComboBox1.getSelectedIndex();
       String b = librarynames.get(a);
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< items.size(); i++)
           {
            outputFile.writeObject(items.get(i));
           }
           outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully deleted!");      
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
      
    }
    
     public void fillfields()
     {
         int a = jTable1.getSelectedRow();
         NameTF.setText(items.get(a).getNome());
         setcategory();
         typeTF.setText(items.get(a).getType());
         costTF.setText(Double.toString(items.get(a).getCost()));
         weigthTF.setText(Double.toString(items.get(a).getWeight()));
         DescriptionTA.setText(items.get(a).getDescription());
         magicCB.setSelected(items.get(a).getmagicstatus());
     }
     
     public void setcategory() 
     {
          int a = jTable1.getSelectedRow();
          String b = items.get(a).getCategory();
          if(b.equals("Weapons"))
          {
              jComboBox2.setSelectedIndex(0);
          }
          if(b.equals("Armor"))
          {
              jComboBox2.setSelectedIndex(1);
          }
          if(b.equals("Consumables"))
          {
              jComboBox2.setSelectedIndex(2);
          }
          if(b.equals("tools"))
          {
              jComboBox2.setSelectedIndex(3);
          }
          if(b.equals("magic"))
          {
              jComboBox2.setSelectedIndex(4);
          }
          if(b.equals("PotionAndScrolls"))
          {
              jComboBox2.setSelectedIndex(5);
          }
          if(b.equals("adventure gear"))
          {
              jComboBox2.setSelectedIndex(6);
          }
          if(b.equals("misc"))
          {
              jComboBox2.setSelectedIndex(7);
          }
          
          
     }
    
     public void modifyitem()
     {
         int b = jTable1.getSelectedRow();
         Items a = items.get(b);
              {
          if (NameTF.getText().isEmpty()||typeTF.getText().isEmpty()||costTF.getText().isEmpty()||weigthTF.getText().isEmpty()||DescriptionTA.getText().isEmpty())
   {
         JOptionPane.showMessageDialog(null, "please enter all fields");
   }
   else
   {
       String a2 = costTF.getText();
       String a1 = a2.replaceAll("[0-9,.]", "");
       String b2 = weigthTF.getText();
       String b1 = b2.replaceAll("[0-9,.]", "");
       if(!(a1.isEmpty())||!(b1.isEmpty()))
       {
           JOptionPane.showMessageDialog(null, "please enter only numbers in the cost and weigth fields");
       }
       else
       {
           String name = NameTF.getText().trim();
           String category = jComboBox2.getSelectedItem().toString().trim();
           String type = typeTF.getText().trim();
           double cost = Double.parseDouble(costTF.getText().trim());
           double weigth = Double.parseDouble(weigthTF.getText().trim());
           String description = DescriptionTA.getText();
           boolean magic = false;
           if(magicCB.isSelected())
           {
               magic = true;
           }
           a.setNome(name);
           a.setCategory(category);
           a.setCost(cost);
           a.setType(type);
           a.setWeight(weigth);
           a.setDescription(description);
           a.setMagic(magic);
           saveiteminlibrary();
           ToArray();
           RowToTable();
     }
   }
              }
     }
   
     
     public void saveitem()
     {
          if (NameTF.getText().isEmpty()||typeTF.getText().isEmpty()||costTF.getText().isEmpty()||weigthTF.getText().isEmpty()||DescriptionTA.getText().isEmpty())
   {
         JOptionPane.showMessageDialog(null, "please enter all fields");
   }
   else
   {
       String a = costTF.getText();
       String a1 = a.replaceAll("[0-9,.]", "");
       String b = weigthTF.getText();
       String b1 = b.replaceAll("[0-9,.]", "");
       if(!(a1.isEmpty())||!(b1.isEmpty()))
       {
           JOptionPane.showMessageDialog(null, "please enter only numbers in the cost and weigth fields");
       }
       else
       {
           String name = NameTF.getText().trim();
           String category = jComboBox2.getSelectedItem().toString().trim();
           String type = typeTF.getText().trim();
           double cost = Double.parseDouble(costTF.getText().trim());
           double weigth = Double.parseDouble(weigthTF.getText().trim());
           String description = DescriptionTA.getText();
           boolean magic = false;
           if(magicCB.isSelected())
           {
               magic = true;
           }
           Items item = new Items(name,category,type,cost,weigth,description,magic);
           items.add(item);
           saveiteminlibrary();
           ToArray();
           RowToTable();
       }
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
        NameTF = new javax.swing.JTextField();
        typeTF = new javax.swing.JTextField();
        costTF = new javax.swing.JTextField();
        weigthTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescriptionTA = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        magicCB = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(NameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 6, 136, -1));
        getContentPane().add(typeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 136, -1));
        getContentPane().add(costTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 136, -1));

        weigthTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weigthTFActionPerformed(evt);
            }
        });
        getContentPane().add(weigthTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 136, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Category:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 46, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Type:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cost:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Weigth:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 50, 20));

        DescriptionTA.setColumns(20);
        DescriptionTA.setLineWrap(true);
        DescriptionTA.setRows(5);
        DescriptionTA.setWrapStyleWord(true);
        jScrollPane1.setViewportView(DescriptionTA);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 188, 347, 170));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Description:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 188, -1, -1));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 210, -1, -1));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Library:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        magicCB.setForeground(new java.awt.Color(255, 255, 255));
        magicCB.setText("Magic");
        magicCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magicCBActionPerformed(evt);
            }
        });
        getContentPane().add(magicCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 164, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Selected library items");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, 320));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete selected item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 224, -1));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 41, 136, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Modify item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 140, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Fill fields with selected item");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 180, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/91.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -280, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void weigthTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weigthTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weigthTFActionPerformed

    private void magicCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magicCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_magicCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean C = false;
        if(librarynames.size()>0){
            for (int i = 0 ; i<items.size();i++)
            {
                if(NameTF.getText().equals(items.get(i).getNome()))
                {
                    C = true;
                }
            }
            if(C==false)
            {
        saveitem();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "An item with that name already exists in this library");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        items.clear();
        populateitemlibrary();
        ToArray();
        RowToTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new Menu1old().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         int a = jTable1.getSelectedRow();
                if (a == -1){
                   JOptionPane.showMessageDialog(null, "No selected item"); 
                }
                else
                {
                   items.remove(a);
                   deleteitemfromlibrary();
                    items.clear();
                    populateitemlibrary();
                    ToArray();
                    RowToTable();
    }                              
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!(librarynames.isEmpty())){
            fillfields();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(!(librarynames.isEmpty())){
        modifyitem();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(AddItemSPECIAL2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItemSPECIAL2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea DescriptionTA;
    private javax.swing.JTextField NameTF;
    private javax.swing.JTextField costTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox magicCB;
    private javax.swing.JTextField typeTF;
    private javax.swing.JTextField weigthTF;
    // End of variables declaration//GEN-END:variables
}
