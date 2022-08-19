/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ARCHIVIOCLASSI;

import PiancsShopGenerator.Items;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author aless
 */
public class TestTableObject extends javax.swing.JFrame {
     ArrayList<Items> items;
     ArrayList<String> librarynames;
     String [][] Tablelines;
    /**
     * Creates new form TestTableObject
     */
    public TestTableObject() {
        initComponents();
        items = new ArrayList<Items>();
        librarynames = new ArrayList<String>();
        
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        Tablelines = new String [items.size()][4];
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
    
    public void RowToTable()
    {
     String[] columnsname = {"Name","Type","Cost","Weigth"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     jTable1.setModel(model);
    }
    
    
    
    
    
    public void ToArray()
    {
       for(int i=0; i < items.size(); i++) 
       {
          String a = items.get(i).getNome();
          String b = items.get(i).getType();
          String c = Double.toString(items.get(i).getCost());
          String d = Double.toString(items.get(i).getWeight());
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Cost", "Weigth"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(136, 136, 136))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestTableObject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestTableObject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestTableObject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestTableObject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestTableObject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
