/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ARCHIVIOCLASSI;

import PiancsShopGenerator.GenerateNewShop111;
import PiancsShopGenerator.itemsinshop;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aless
 */
public class NewShop extends javax.swing.JFrame {
    String b ;
    boolean alreadyadded;
    ArrayList<itemsinshop> Stock;
    ArrayList<itemsinshop> Inventory;
    ArrayList<itemsinshop> Cart;
    ArrayList<itemsinshop> temp;
    String [][] Tablelines;
    String [][] Tablelines1;
    /**
     * Creates new form Shop
     */
    public NewShop(String selected) {
        initComponents();
        b = selected;
        Stock = new ArrayList<itemsinshop>();
        Inventory = new ArrayList<itemsinshop>();
        Cart = new ArrayList<itemsinshop>();
        temp = new ArrayList<itemsinshop>();
        alreadyadded = false;
        populatestockitems();
        fillInventory();
        InvetoryToTable();
        System.out.println(Stock.size());
    }

    public void populatestockitems()
    {
       try
       {
           FileInputStream file = new FileInputStream(b);
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   Stock.add((itemsinshop) inputfile.readObject());
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
    public void InvetoryToTable()
    {
     if(!(Inventory.size()==0)){
        Tablelines = new String [Inventory.size()][6];
       for(int i=0; i < Inventory.size(); i++) 
       {
          String a = Inventory.get(i).getNome();
          String b = Inventory.get(i).getType();
          String c = Double.toString(Inventory.get(i).getCost());
          String d = Double.toString(Inventory.get(i).getWeight());
          String e = Inventory.get(i).getCategory();
          String f = Double.toString(Inventory.get(i).getNumber());
          Tablelines [i][0] = a;
          Tablelines [i][1] = b;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = e;
          Tablelines [i][5] = f;
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
     DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
     jTable1.setModel(model);
       }
     }
     else
     {
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
          DefaultTableModel model = new DefaultTableModel(columnsname,0);
         jTable1.setModel(model);
     }
    }                 
    public void fillInventory()
    {
        for(int i = 0; i<Stock.size(); i++)
        {
            Inventory.add(Stock.get(i));
        }
    }
    public void CartToTable()
    {
     if(!(Cart.size()==0)){
        Tablelines1 = new String [Cart.size()][6];
       for(int i=0; i < Cart.size(); i++) 
       {
          String a = Cart.get(i).getNome();
          String b = Cart.get(i).getType();
          String c = Double.toString(Cart.get(i).getCost());
          String d = Double.toString(Cart.get(i).getWeight());
          String e = Cart.get(i).getCategory();
          String f = Double.toString(Cart.get(i).getNumber());
          Tablelines1 [i][0] = a;
          Tablelines1 [i][1] = b;
          Tablelines1 [i][2] = c;
          Tablelines1 [i][3] = d; 
          Tablelines1 [i][4] = e;
          Tablelines1 [i][5] = f;
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
     DefaultTableModel model = new DefaultTableModel(Tablelines1, columnsname);
     jTable2.setModel(model);
       }
     }
      else
     {
         String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
          DefaultTableModel model = new DefaultTableModel(columnsname,0);
         jTable2.setModel(model);
     }
    }  
    public void AddToCart()
    {
         if(!(jTable1.getSelectedRow()==-1)&&!(jSlider1.getValue()==0))
        {
             itemsinshop c = new itemsinshop(Inventory.get(jTable1.getSelectedRow()));
             itemsinshop a = new itemsinshop(c);
             itemsinshop b1 = new itemsinshop(c);
              System.out.println(b1.getNumber());
             a.setNumber(jSlider1.getValue());
             if(a.getNumber()<=b1.getNumber())
        {
            System.out.println(a.getNumber());
            System.out.println(b1.getNumber());
        Cart.add(a);
        CartToTable();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"The shop does not have that many "+a.getNome());
        }
            a = null;    
        }
        else
        {
           JOptionPane.showMessageDialog(null,"Select an item and a quantity greater than 0"); 
        }
    }
    public void CategorySort()
    {
       String a = jComboBox1.getSelectedItem().toString().trim();
        if(!a.equals("All")){
            ArrayList<itemsinshop> tipoArray = new ArrayList<itemsinshop>();
            for (int i=0 ; i < Inventory.size(); i++)
             {
                 if(Inventory.get(i).getCategory().trim().equals(a))
                    {    
                        tipoArray.add(Inventory.get(i));
                    }  
             }
             Tablelines = new String [tipoArray.size()][6];
            for(int i=0; i < tipoArray.size(); i++) 
                {
                    String e = tipoArray.get(i).getNome();
                    String f = tipoArray.get(i).getType();
                    String c = Double.toString(tipoArray.get(i).getCost());
                    String d = Double.toString(tipoArray.get(i).getWeight());
                    String h = tipoArray.get(i).getCategory();
                    String g = Double.toString(tipoArray.get(i).getNumber());
                    Tablelines [i][0] = e;
                    Tablelines [i][1] = f;
                    Tablelines [i][2] = c;
                    Tablelines [i][3] = d; 
                    Tablelines [i][4] = h;
                    Tablelines [i][5] = g;
                }
            String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
            DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
            jTable1.setModel(model);
            tipoArray = null;   
        }
        else
        {
            Tablelines = new String [Inventory.size()][6];
       for(int i=0; i < Inventory.size(); i++) 
        {
          String e = Inventory.get(i).getNome();
          String f = Inventory.get(i).getType();
          String c = Double.toString(Inventory.get(i).getCost());
          String d = Double.toString(Inventory.get(i).getWeight());
          String h = Inventory.get(i).getCategory();
          String g = Double.toString(Inventory.get(i).getNumber());
          Tablelines [i][0] = e;
          Tablelines [i][1] = f;
          Tablelines [i][2] = c;
          Tablelines [i][3] = d; 
          Tablelines [i][4] = h;
          Tablelines [i][5] = g;
        }
       String[] columnsname = {"Name","Type","Cost","Weigth","Category","Quantity"};
       DefaultTableModel model = new DefaultTableModel(Tablelines, columnsname);
       jTable1.setModel(model);        
        }
    }
    public void AttributesSort()
    {
           if(jComboBox2.getSelectedItem().toString().equals("cost")){
        Collections.sort(Inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return Double.valueOf(s1.getCost()).compareTo(s2.getCost());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("weight")){
        Collections.sort(Inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return Double.valueOf(s1.getWeight()).compareTo(s2.getWeight());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("name")){
        Collections.sort(Inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return String.valueOf(s1.getNome()).compareTo(s2.getNome());
           }
        
        });}
           if(jComboBox2.getSelectedItem().toString().equals("type")){
        Collections.sort(Inventory, new Comparator<itemsinshop>()
        {
           public int compare (itemsinshop s1, itemsinshop s2) 
           {
               return String.valueOf(s2.getType()).compareTo(s1.getType());
           }
        
        });}
       CategorySort();
        
        
    }
    public void ShowDescriptioninv()
    {
      if(!(jTable1.getSelectedRow()==-1)){
      String a  = new String(Tablelines[jTable1.getSelectedRow()][0]);
      for(int i=0;i<Inventory.size();i++)
      {
          if(Inventory.get(i).getNome().equals(a))
          {
              jTextArea1.setText(Inventory.get(i).getDescription());
          }
      }
      }
      else
      {
          
      }
    }
    public void AddToCart1()
    {
         String a1  = new String(Tablelines[jTable1.getSelectedRow()][0]);
      for(int i=0;i<Inventory.size();i++)
      {
          if(Inventory.get(i).getNome().equals(a1))
          {
             itemsinshop c = new itemsinshop (Inventory.get(i));
             temp.add(c);
          }
      }
        
        
         if(!(jTable1.getSelectedRow()==-1)&&!(jSlider1.getValue()==0)&&temp.size()>0)
        {
             itemsinshop c= new itemsinshop(temp.get(0));
             temp.clear();
             itemsinshop a = new itemsinshop(c);
             itemsinshop b1 = new itemsinshop(c);
             System.out.println(b1.getNumber());
             a.setNumber(jSlider1.getValue());
             if(a.getNumber()<=b1.getNumber())
        {
            System.out.println(a.getNumber());
            System.out.println(b1.getNumber());
        Cart.add(a);
        CartToTable();
        }
        else
        {
            
            JOptionPane.showMessageDialog(null,"The shop does not have that many "+a.getNome());
        }
            a = null;    
        }
        else
        {
           temp.clear();
           JOptionPane.showMessageDialog(null,"Select an item and a quantity greater than 0"); 
        }
    }
    public void AddToCart2()
    {
     if(!(jTable1.getSelectedRow()== -1))
     {
        int index = 0;
         String a1  = new String(Tablelines[jTable1.getSelectedRow()][0]);
      for(int i=0;i<Inventory.size();i++)
      {
          if(Inventory.get(i).getNome().equals(a1))
          {
             itemsinshop c = new itemsinshop (Inventory.get(i));
             temp.add(c);
             index = i;
          }
      }
        
        
         if(!(jTable1.getSelectedRow()==-1)&&!(jSlider1.getValue()==0)&&temp.size()>0)
        {
             itemsinshop c= new itemsinshop(temp.get(0));
             temp.clear();
             itemsinshop a = new itemsinshop(c);
             itemsinshop b1 = new itemsinshop(c);
             System.out.println(b1.getNumber());
             a.setNumber(jSlider1.getValue());
             if(a.getNumber()<=b1.getNumber())
        {
           
            Inventory.get(index).setNumber(    (Inventory.get(index).getNumber())  -   (jSlider1.getValue())   );
            if(Inventory.get(index).getNumber()<=0)
            {
                Inventory.remove(index);
            }
     for(int i=0;i<Cart.size();i++)
      {
          if(Cart.get(i).getNome().equals(a1))
          {
             Cart.get(i).setNumber( (Cart.get(i).getNumber())+a.getNumber()        );
             alreadyadded = true;
          }
          
      }    
     if (alreadyadded == false)
     {
        Cart.add(a);
     }
        alreadyadded = false;
        CartToTable();
        InvetoryToTable();
        }
        else
        {
            
            JOptionPane.showMessageDialog(null,"The shop does not have that many "+a.getNome());
        }
            a = null;    
        }
        else
        {
           temp.clear();
           JOptionPane.showMessageDialog(null,"Select a quantity greater than 0"); 
        }
         
     }
     else
     {
        JOptionPane.showMessageDialog(null,"Select an item ");  
     }
  
         
         
         
         
         
         
    }
    public void removefromCart()
    {
     if(!(jTable2.getSelectedRow()== -1))
     {
        int index = 0;
         String a1  = new String(Tablelines1[jTable2.getSelectedRow()][0]);
      for(int i=0;i<Cart.size();i++)
      {
          if(Cart.get(i).getNome().equals(a1))
          {
             itemsinshop c = new itemsinshop (Cart.get(i));
             temp.add(c);
             index = i;
          }
      }
        
        
         if(!(jTable2.getSelectedRow()==-1)&&!(jSlider2.getValue()==0)&&temp.size()>0)
        {
             itemsinshop c= new itemsinshop(temp.get(0));
             temp.clear();
             itemsinshop a = new itemsinshop(c);
             itemsinshop b1 = new itemsinshop(c);
             System.out.println(b1.getNumber());
             a.setNumber(jSlider2.getValue());
             if(a.getNumber()<=b1.getNumber())
        {
           
            Cart.get(index).setNumber(    (Cart.get(index).getNumber())  -   (jSlider2.getValue())   );
            if(Cart.get(index).getNumber()<=0)
            {
                Cart.remove(index);
            }
     for(int i=0;i<Inventory.size();i++)
      {
          if(Inventory.get(i).getNome().equals(a1))
          {
             Inventory.get(i).setNumber( (Inventory.get(i).getNumber())+a.getNumber()        );
             alreadyadded = true;
          }
          
      }    
     if (alreadyadded == false)
     {
        Inventory.add(a);
     }
        alreadyadded = false;
        CartToTable();
        InvetoryToTable();
        }
        else
        {
            
            JOptionPane.showMessageDialog(null,"The shop does not have that many "+a.getNome());
        }
            a = null;    
        }
        else
        {
           temp.clear();
           JOptionPane.showMessageDialog(null,"Select a quantity greater than 0"); 
        }
         
     }
     else
     {
        JOptionPane.showMessageDialog(null,"Select an item ");  
     }
  
         
         
         
         
         
         
    }
    public void ShowDescriptioncart()
    {
      if(!(jTable2.getSelectedRow()==-1)){
      String a  = new String(Tablelines1[jTable2.getSelectedRow()][0]);
      for(int i=0;i<Cart.size();i++)
      {
          if(Cart.get(i).getNome().equals(a))
          {
              jTextArea1.setText(Cart.get(i).getDescription());
          }
      }
      }
      else
      {
          
      }
    }
    public void showtotal()
    {
        Double total = 0.00;
        for(int i=0;i<Cart.size();i++)
        {
           total = total +(Cart.get(i).getCost()*Cart.get(i).getNumber());
        }
        jTextField3.setText(Double.toString(total));
    }
    public void applydiscount()
    {
        String z = jTextField1.getText();
       String z1 = z.replaceAll("[0-9,.]", "");
       String x = jTextField3.getText();
       String x1 = x.replaceAll("[0-9,.]", "");
       if(!(z1.isEmpty()) || !(x1.isEmpty()))
       {
           JOptionPane.showMessageDialog(null, "please enter only numbers in discount field and set the total first");

       }
       else{
        if(Double.parseDouble(jTextField1.getText().trim())>0 && Double.parseDouble(jTextField1.getText().trim())<100){
           double total = Double.parseDouble(jTextField3.getText().trim()); 
           double discount = Double.parseDouble(jTextField1.getText().trim());
           total = total - ( (total/100)*discount  );
           jTextField3.setText(Double.toString(total));
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
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jSlider2 = new javax.swing.JSlider();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1119, 583));
        setMinimumSize(new java.awt.Dimension(1119, 583));
        setSize(new java.awt.Dimension(800, 450));
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 528, 231));

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
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 6, 570, 231));

        jButton1.setText("sort by");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 243, 78, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 104, -1));

        jButton2.setText("sort by");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 279, 78, -1));

        jLabel1.setText("category");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        jButton4.setText("sort by");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, -1));

        jButton5.setText("sort by");
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 100, -1));

        jLabel4.setText("category");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, -1, -1));

        jLabel7.setText("Total:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));

        jButton7.setText("add item to cart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(10);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(0);
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 279, -1, -1));

        jLabel8.setText("Items Amount");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 279, -1, -1));

        jButton8.setText("open shop stock");
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 130, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 104, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight" }));
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 100, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 342, 348, 162));

        jLabel2.setText("item description");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 342, -1, -1));

        jButton3.setText("apply discount");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, -1, -1));

        jButton6.setText("remove discount");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 240, 130, -1));

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 50, -1));

        jLabel3.setText("%");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 250, -1, -1));

        jLabel5.setText("1.00 = 1 gold ; 0.1 = 1 silver ; 0.01 = 1 copper");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, -1, -1));

        jLabel6.setText("10.00 = 1 platinum ; es: 12.32 = 1 platinum,2gold,3silver,2copper");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, -1, -1));

        jButton9.setText("SaveShop");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 400, 150, 80));

        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 370, 150, -1));

        jButton10.setText("Buy");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 280, -1, -1));

        jButton11.setText("remove from cart");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));

        jButton12.setText("ShowDescription");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, -1));

        jSlider2.setMajorTickSpacing(1);
        jSlider2.setMaximum(10);
        jSlider2.setMinorTickSpacing(1);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setSnapToTicks(true);
        jSlider2.setValue(0);
        getContentPane().add(jSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        jButton13.setText("ShowDescription");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 170, -1));

        jButton14.setText("show total");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 280, -1, -1));

        jTextField3.setText("total..");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 110, -1));

        jButton15.setText("Refill invetory");
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 130, -1));

        jButton16.setText("back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

      AddToCart2();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      CategorySort();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      AttributesSort();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       ShowDescriptioninv();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        removefromCart();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        ShowDescriptioncart();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        showtotal();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        applydiscount();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        this.dispose();
        new GenerateNewShop111().setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(NewShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewShop(null).setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
