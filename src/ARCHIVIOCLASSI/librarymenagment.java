/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ARCHIVIOCLASSI;

import PiancsShopGenerator.Items;
import PiancsShopGenerator.Menu1old;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aless
 */
public class librarymenagment extends javax.swing.JFrame {
     ArrayList<String> librarynames;
     ArrayList<Items> items;
    /**
     * Creates new form librarymenagment
     */
    public librarymenagment() {
        initComponents();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        populatelibrarylist();
        setLibrarylist();
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
    
    
    public void savelibraryToFile()
     {
        try
        {
            FileOutputStream file = new FileOutputStream("Library List");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< librarynames.size(); i++)
           {
            outputFile.writeObject(librarynames.get(i));
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
        jList1.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
    }
    
    
    public void Deletelibraryfromlist()
     {
         
        int a = jList1.getSelectedIndex();
        String b = librarynames.get(a);
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
         
        try
        {
            FileOutputStream file = new FileOutputStream("Library List");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< librarynames.size(); i++)
           {
            outputFile.writeObject(librarynames.get(i));
           }
           outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully deleted!");
       
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void Deletelibraryfile()
    {
        int a = jList1.getSelectedIndex();
        String b = librarynames.get(a);
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
    
    public void populateitemlibrary()
    {
       int a = jList1.getSelectedIndex();
       if(!(a==-1)){
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
           
       }
    }
    
    public void setItemslist()
    {
       
        String [] tipoArray = new String [items.size()];
         for (int i=0 ; i < items.size(); i++)
        {
                tipoArray[i] = items.get(i).getFormat();
        }
        jList2.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
    }
    
    public void saveplaceholderiteminlibrary()
     {
         String b = jTextField2.getText();
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            Items tempitem = new Items("ph", "ph", "ph", 000 , 000 , "this is a place holder , do not delete", true);
            outputFile.writeObject(tempitem);
            outputFile.close();
           
           JOptionPane.showMessageDialog(null,"Successfully saved!");
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("existing librarys:");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel2.setText("item in selected library:");

        jLabel3.setText("description;");

        jLabel4.setText("Create new library:");

        jButton1.setText("Create");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea1);

        jButton3.setText("Delete selected");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addComponent(jButton1))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String a = jTextField2.getText();
        if(a.isEmpty())
        {
             JOptionPane.showMessageDialog(null, "please enter a name");
        }
        else
        {
            librarynames.add(a);
            savelibraryToFile();
            setLibrarylist();
            saveplaceholderiteminlibrary();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
             int a = jList1.getSelectedIndex();
                if (a == -1){
                   JOptionPane.showMessageDialog(null, "No library selected"); 
                }
                else
                {
                   librarynames.remove(a);
                   Deletelibraryfromlist(); 
                   setLibrarylist();
                }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        items.clear();
        populateitemlibrary();
        setItemslist();
    }//GEN-LAST:event_jList1ValueChanged

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
       int a = jList2.getSelectedIndex();
       if(a>=0){
       String b = items.get(a).getDescription();
       jTextArea1.setText(b);
       }
    }//GEN-LAST:event_jList2ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     this.dispose();
     new Menu1old().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(librarymenagment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(librarymenagment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(librarymenagment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(librarymenagment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new librarymenagment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
