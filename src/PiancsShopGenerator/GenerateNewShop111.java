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
public class GenerateNewShop111 extends javax.swing.JFrame {
        ArrayList<String> shopnames;
        String selected;
         ArrayList<String> librarynames;
    /**
     * Creates new form GenerateNewShop11
     */
    public GenerateNewShop111() {
        initComponents();
        setcenter();
        shopnames = new ArrayList<String>();
        librarynames = new ArrayList<String>();
        populateshoplist();
        setShoplist();
        populatelibrarylist();
        selected = null;
        
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
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
           //JOptionPane.showMessageDialog(null, "You did not make any shop yet");
       }
    }
           public void setShoplist()
    {
        
        String [] tipoArray = new String [shopnames.size()];
     
         for (int i=0 ; i < shopnames.size(); i++)
        {
                tipoArray[i] = shopnames.get(i);
        }
        jList1.setModel(new javax.swing.DefaultComboBoxModel<>(tipoArray));
 
    }
    
        public void Deleteshopfile()
    {
        int a = jList1.getSelectedIndex();
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
    
      public void saveShopToFile()
     {
     
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
           String a2 = "You have to create a library before u can generate shops";
           String [] b2 = new String[1];
           b2[0]=a2;
           jList1.setModel(new javax.swing.DefaultComboBoxModel<>(b2));
       }
    }
       public void setcenter()
       {
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
        jList1 = new javax.swing.JList<>();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 130));

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Generate selected Shop");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("New Shop");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 140, -1));

        jButton13.setBackground(new java.awt.Color(51, 51, 51));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Back");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 100, -1));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Delete seleted shop");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/9.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 400, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       this.dispose();
       new Menu1old().setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       if(librarynames.size()>0){
       this.dispose();
       new NewCustom111_11().setVisible(true);
       }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(!((jList1.getSelectedIndex()==-1)||(shopnames.isEmpty()))){
        new NewShop1(shopnames.get(jList1.getSelectedIndex())).setVisible(true);
        this.dispose();
        }
        else
        {
            
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                   if(!((jList1.getSelectedIndex()==-1)||(shopnames.isEmpty()))){
                   int a = jList1.getSelectedIndex();  
                   System.out.print(a);
                   Deleteshopfile(); 
                   shopnames.remove(a);
                   saveShopToFile();
                   setShoplist();
                   }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateNewShop111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateNewShop111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateNewShop111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateNewShop111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateNewShop111().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
