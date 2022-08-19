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
public class Shopstock extends javax.swing.JFrame {
    ArrayList<itemsinshop> Stock;
    ArrayList<itemsinshop> StockB;
    ArrayList<itemsinshop> Inventory;
     ArrayList<itemsinshop> InventoryB;
    ArrayList<itemsinshop> Cart;
     ArrayList<itemsinshop> CartB;
    ArrayList<String> librarynames;
    ArrayList<Items> items;
    String [][] Tablelines;
    String [][] Tablelines1;
    String a;
    /**
     * Creates new form Shopstock
     */
    public Shopstock(ArrayList<itemsinshop> stock ,ArrayList<itemsinshop> inventory , ArrayList<itemsinshop> cart , String b) {
        initComponents();
        setcenter();
         a = b;
        Stock = stock;
        Inventory = inventory;
        Cart = cart;
        StockB =  new ArrayList<itemsinshop>();
        InventoryB =  new ArrayList<itemsinshop>();
        CartB =  new ArrayList<itemsinshop>();
        librarynames = new ArrayList<String>();
        items =  new ArrayList<Items>();
        StockToTable();
        populatelibrarylist();
        setLibrarylist();
        populateitemlibrary();
        itemtotable();
        fillbackuparrays();
    }

    
    
    public void fillbackuparrays()
    {
        for(int x = 0 ; x<Stock.size();x++)
        {
           itemsinshop y = new itemsinshop(Stock.get(x));
           StockB.add(y);
        }
         for(int x = 0 ; x<Inventory.size();x++)
        {
           itemsinshop y = new itemsinshop(Inventory.get(x));
           InventoryB.add(y); 
        } for(int x = 0 ; x<Cart.size();x++)
        {
            itemsinshop y = new itemsinshop(Cart.get(x));
           CartB.add(y);
        }
    }
    
    
    public void setcenter()
       {
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       }
    public void StockToTable()
    {
     if(!(Stock.size()==0)){
        Tablelines = new String [Stock.size()][6];
       for(int i=0; i < Stock.size(); i++) 
       {
          String a = Stock.get(i).getNome();
          String b = Stock.get(i).getType();
          String c = Double.toString(Stock.get(i).getCost());
          String d = Double.toString(Stock.get(i).getWeight());
          String e = Stock.get(i).getCategory();
          String f = Double.toString(Stock.get(i).getNumber());
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = e;
          Tablelines [i][5] = f;
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     int a1 = jTable1.getSelectedRow();
     jTable1.setModel(model);
     if (!  (a1==-1))
     {
         if(a1<Stock.size())
         {
         jTable1.setRowSelectionInterval(a1, a1);
         }
     }
       }
     }
     else
     {
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
          DefaultTableModel model = new DefaultTableModel(columnsname,0);
         jTable1.setModel(model);
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
           
       }
    }
     public void populateitemlibrary()
    {
       int a = jComboBox1.getSelectedIndex();
       if(!(librarynames.isEmpty())){
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
     public void itemtotable ()
    {
        items.clear();
        populateitemlibrary();
        Tablelines1 = new String [items.size()][5];
       for(int i=0; i < items.size(); i++) 
       {
          String a = items.get(i).getNome();
          String b = items.get(i).getType();
          String c = Double.toString(items.get(i).getCost());
          String d = Double.toString(items.get(i).getWeight());
          String e = items.get(i).getCategory();
          Tablelines1 [i][0] = a;
          Tablelines1 [i][1] = b;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = e;
       }
       String[] columnsname = {"Name","Type","Cost","Weigth","Category"};
       DefaultTableModel model = new DefaultTableModel(Tablelines1, columnsname);
       jTable2.setModel(model);
    }
     public void additem()
     {
         if(!(jTable2.getSelectedRow()==-1)){
            int b = jTable2.getSelectedRow();
            itemsinshop iteminshop = new itemsinshop (items.get(b),jSlider1.getValue()) ; 
                checkforsameitem(iteminshop);
         }
     }
     public void checkforsameitem(itemsinshop checkeditem)
     {
                 int b = 0;
                 for (int i=0; i<Stock.size();i++)
                 {
                     if(checkeditem.getNome()==Stock.get(i).getNome() && checkeditem.getType()==Stock.get(i).getType() && checkeditem.getCost()==Stock.get(i).getCost() && checkeditem.getWeight()==Stock.get(i).getWeight())
                     {
                       itemsinshop  tempitem =  Stock.get(i);
                       tempitem.setNumber((Stock.get(i).getNumber())+(checkeditem.getNumber()));
                       Stock.set(i, tempitem);
                       b++;
                       StockToTable();
                 }
                   
                }
                 if(b==0)
                     {
                         Stock.add(checkeditem);
                         StockToTable();
                     }
             }
     public void removeitem()
               {
                   if(!(jTable1.getSelectedRow()==-1)){
                 
                  String a = new String(Tablelines[jTable1.getSelectedRow()][0]);
                   for(int i =0 ; i<Stock.size(); i++)
                   {
                       if(a.equals(Stock.get(i).getNome()))
                       {
                            itemsinshop b1 = Stock.get(i);
                            double a1 = jSlider1.getValue();
                            double c2 = b1.getNumber();
                            if(c2-a1<=0)
                            {
                                Stock.remove(i);
                            }
                            else
                            {
                                b1.setNumber(c2-a1);
                            }
                       }
                   }
                   StockToTable();
                   
                   
                   }
                   else
                   {  
                       
                   }
               }  
     public void savestock()
     {
    
       
        try
        {
            FileOutputStream file = new FileOutputStream(a);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< Stock.size(); i++)
           {
            outputFile.writeObject(Stock.get(i));
           }
           outputFile.close();
           new NewShop1(a).setVisible(true);
           this.dispose();
           //JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
     
     }
       public void showdescription()
               {
                  if(!(jTable1.getSelectedRow()==-1))
                  {
                    if(!(jTable1.getSelectedRow()==-1)){
                    String a  = new String(Tablelines[jTable1.getSelectedRow()][0]);
                    for(int i=0;i<Stock.size();i++)
                    {
                     if(Stock.get(i).getNome().equals(a))
                    {
                     jTextArea1.setText(Stock.get(i).getDescription());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSlider1 = new javax.swing.JSlider();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 200));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 456, 144));

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 6, -1, 200));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 101, -1));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Remove item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 104, -1));

        jSlider1.setForeground(new java.awt.Color(204, 204, 204));
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(10);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, -1));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Save change");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/56358.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-310, 0, 1280, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        itemtotable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        additem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        removeitem();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new NewShop1(Stock, Inventory, Cart, a).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       new NewShop1(StockB, InventoryB, CartB,a).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Shopstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Shopstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Shopstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Shopstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shopstock(null,null,null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
