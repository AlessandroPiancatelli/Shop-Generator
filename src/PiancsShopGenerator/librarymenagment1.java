/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PiancsShopGenerator;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.shape.Path;
import javax.swing.JOptionPane;

/**
 *
 * @author aless
 */
public class librarymenagment1 extends javax.swing.JFrame {
     ArrayList<String> librarynames;
     ArrayList<Items> items;
     ArrayList<Items> PHBItems;
    /**
     * Creates new form librarymenagment
     */
    public librarymenagment1() {
        initComponents();
        setcenter();
        librarynames = new ArrayList<String>();
        items = new ArrayList<Items>();
        PHBItems = new ArrayList<Items>();
        populatelibrarylist();
        setLibrarylist();
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
    
     public void savePHB()
     {
         Boolean alreadyadded = false;
         for(int i = 0 ; i<librarynames.size();i++)
         {
             if (librarynames.get(i).equalsIgnoreCase("PHB"))
             {
                 alreadyadded = true;
             }
         }
         if (alreadyadded == false ) {
          try {
			Scanner scanner = new Scanner(new File("herewego"));
			while (scanner.hasNextLine()) {
				String a = scanner.nextLine();
                                String b = scanner.nextLine();
                                String c = scanner.nextLine();
                                String d = scanner.nextLine().replaceAll("[^0-9,.,,]", "");
                                double d1 = Double.parseDouble(d);
                                String e = scanner.nextLine().replaceAll("[^0-9,.,,]", "");
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
        librarynames.add("PHB");
        savelibraryToFile();
        setLibrarylist();
        try
        {
            FileOutputStream file = new FileOutputStream("PHB");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< PHBItems.size(); i++)
           {
            outputFile.writeObject(PHBItems.get(i));
           }
           outputFile.close();
           
           //JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        items.clear();
        populateitemlibrary();
        setItemslist();
         }
         else
         {
            JOptionPane.showMessageDialog(null,"There is already a library named PHB "); 
         }
    }
     
     public void importphb()
     {
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
     }
     
     public void deletelibrary()
     {
        String a = jList1.getSelectedValue();
        Boolean nameremoved = false;
            for (int i =0 ; i<librarynames.size(); i++)
            {
                if(a.equals(librarynames.get(i)))
                {
                    librarynames.remove(i);
                    nameremoved = true;
                }
            }
            if (nameremoved = true){
                try  
            {         
            File f= new File(a);           //file to be delete  
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
            librarynames.clear();
            populatelibrarylist();
            setLibrarylist();
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
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(521, 324));
        setResizable(false);
        setSize(new java.awt.Dimension(521, 324));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("existing librarys:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 214, 86));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 154, 232, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("item in selected library:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("description:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 120, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Create new library:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 12, -1, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 6, 164, -1));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Create");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 120, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 154, 272, 166));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete selected");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 86, -1, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Load phb");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 99, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/92.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, -170, 620, 500));

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
            deletelibrary();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        items.clear();
        populateitemlibrary();
        setItemslist();
    }//GEN-LAST:event_jList1ValueChanged

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
       int a = jList2.getSelectedIndex();
       if(jList1.getSelectedIndex()>=0)
       {
       if(a>=0){
       String b = items.get(a).getDescription();
       jTextArea1.setText(b);
       }
       }
    }//GEN-LAST:event_jList2ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     this.dispose();
     new Menu1old().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        savePHB();
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
            java.util.logging.Logger.getLogger(librarymenagment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(librarymenagment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(librarymenagment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(librarymenagment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new librarymenagment1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
