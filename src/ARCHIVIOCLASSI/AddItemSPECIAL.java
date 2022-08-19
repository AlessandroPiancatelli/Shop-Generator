/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ARCHIVIOCLASSI;

import PiancsShopGenerator.Items;
import PiancsShopGenerator.Menu1old;
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
public class AddItemSPECIAL extends javax.swing.JFrame {
          ArrayList<Items> items;
          ArrayList<String> librarynames;
          String [][] Tablelines;
    /**
     * Creates new form AddItemSPECIAL
     */
    public AddItemSPECIAL() {
        initComponents();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        ToArray();
        RowToTable();
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
        String [] tipoArray = new String [librarynames.size()];
     
         for (int i=0 ; i < librarynames.size(); i++)
        {
                tipoArray[i] = librarynames.get(i);
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
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
     
     public void populateitemlibrary()
    {
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

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(NameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 6, 136, -1));
        getContentPane().add(typeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 74, 136, -1));
        getContentPane().add(costTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 108, 136, -1));

        weigthTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weigthTFActionPerformed(evt);
            }
        });
        getContentPane().add(weigthTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 142, 136, -1));

        jLabel1.setText("Name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 12, -1, -1));

        jLabel2.setText("Category:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setText("type:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 80, -1, -1));

        jLabel4.setText("cost:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 114, -1, -1));

        jLabel5.setText("weigth:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 148, -1, -1));

        DescriptionTA.setColumns(20);
        DescriptionTA.setRows(5);
        jScrollPane1.setViewportView(DescriptionTA);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 176, 347, 135));

        jLabel6.setText("Description:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 176, -1, -1));

        jButton1.setText("back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 198, -1, -1));

        jButton2.setText("salva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 232, -1, -1));

        jLabel8.setText("library:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 11, -1, -1));

        magicCB.setLabel("magic");
        magicCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magicCBActionPerformed(evt);
            }
        });
        getContentPane().add(magicCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 147, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 6, 164, -1));

        jLabel7.setText("selected library items");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 12, -1, -1));

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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 62, -1, 249));

        jButton3.setText("Delete selected item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 6, 224, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void weigthTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weigthTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weigthTFActionPerformed

    private void magicCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magicCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_magicCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   if (NameTF.getText().isEmpty()||typeTF.getText().isEmpty()||costTF.getText().isEmpty()||weigthTF.getText().isEmpty()||DescriptionTA.getText().isEmpty())
   {
         JOptionPane.showMessageDialog(null, "please enter all fields");
   }
   else
   {
       String a = costTF.getText();
       String a1 = a.replaceAll("[^0-9]", "");
       String b = weigthTF.getText();
       String b1 = b.replaceAll("[^0-9]", "");
       if(a1.isEmpty()||b1.isEmpty())
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
            java.util.logging.Logger.getLogger(AddItemSPECIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItemSPECIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItemSPECIAL().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox magicCB;
    private javax.swing.JTextField typeTF;
    private javax.swing.JTextField weigthTF;
    // End of variables declaration//GEN-END:variables
}
