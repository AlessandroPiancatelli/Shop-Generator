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
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aless
 */
public class OldShop extends javax.swing.JFrame {
    String b ;
    boolean alreadyadded;
    ArrayList<itemsinshop> Stock;
    ArrayList<itemsinshop> Inventory;
    ArrayList<itemsinshop> Cart;
    ArrayList<itemsinshop> temp;
    ArrayList<ArrayList> test;
    ArrayList<String> shopnames;
    String [][] Tablelines;
    String [][] Tablelines1;
    /**
     * Creates new form Shop
     */
    public OldShop(String selected) {
        initComponents();
        b = selected;
        Stock = new ArrayList<itemsinshop>();
        Inventory = new ArrayList<itemsinshop>();
        Cart = new ArrayList<itemsinshop>();
        temp = new ArrayList<itemsinshop>();
        test = new ArrayList<ArrayList>();
        shopnames = new ArrayList<String>();
        alreadyadded = false;
        populateArraylistofArray();
        populateallArrays();
        InvetoryToTable();
        CartToTable();
        setcenter();
    }
    
    public void setcenter()
       {
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
     int a1 = jTable1.getSelectedRow();
     jTable1.setModel(model);
     if (!(a1==-1))
     {
         if(a1<Inventory.size()){
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
     int a1 = jTable2.getSelectedRow();
     jTable2.setModel(model);
     if(!(a1==-1))
     {
         if(!(Cart.isEmpty())){
         jTable2.setRowSelectionInterval(a1, a1);
         }
     }
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
             if (a.getNumber() >= b1.getNumber())
             {
                 a.setNumber(b1.getNumber());
             }
           
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
        if (a.getNumber() >= b1.getNumber())
             {
                 a.setNumber(b1.getNumber());
             }
           
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

    public void SaveShop( boolean c)
    {
        if(c==true)
        {
                 test.clear();
        test.add(Stock);
        test.add(Inventory);
        test.add(Cart);
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< test.size(); i++)
           {
            outputFile.writeObject(test.get(i));
           }
           outputFile.close();
           
          // JOptionPane.showMessageDialog(null,"Successfully saved!");
        }
        
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
        else{
        test.clear();
        test.add(Stock);
        test.add(Inventory);
        test.add(Cart);
        try
        {
            FileOutputStream file = new FileOutputStream(b);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
           for (int i = 0 ; i< test.size(); i++)
           {
            outputFile.writeObject(test.get(i));
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
    public void populateArraylistofArray()
    {
       try
       {
           FileInputStream file = new FileInputStream(b);
           ObjectInputStream inputfile = new ObjectInputStream(file);
           
           boolean endOfFile = false;
           
           while (!endOfFile)
           {
               try{
                   test.add((ArrayList) inputfile.readObject());
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
    public void populateallArrays()
    {
         for (int x = 0 ; x<test.get(0).size(); x++)
         {
             Stock.add((itemsinshop) test.get(0).get(x));
         }
         for (int x = 0 ; x<test.get(1).size(); x++)
         {
             Inventory.add((itemsinshop) test.get(1).get(x));
         }
         for (int x = 0 ; x<test.get(2).size(); x++)
         {
             Cart.add((itemsinshop) test.get(2).get(x));
         }
     }
    public void RefillFromStock()
    {
        Inventory.clear();
        for (int i = 0 ; i < Stock.size();i++)
        {
            itemsinshop a = new itemsinshop(Stock.get(i));
            Inventory.add(a);
        }
        InvetoryToTable();
    }
      public void showdescription()
               {
                  if(!(jTable1.getSelectedRow()==-1)&&!(Inventory.isEmpty()))
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
                  else
                  {
                      if(!(jTable2.getSelectedRow()==-1)&&!(Cart.isEmpty()))
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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jSlider2 = new javax.swing.JSlider();
        jButton14 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1128, 490));
        setMaximumSize(new java.awt.Dimension(1128, 490));
        setMinimumSize(new java.awt.Dimension(1128, 490));
        setPreferredSize(new java.awt.Dimension(1128, 520));
        setResizable(false);
        setSize(new java.awt.Dimension(1128, 500));
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 6, 570, 231));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Sort by");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 243, 78, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 104, -1));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sort by");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 279, 78, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Sort by");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 70, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Sort by");
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 70, -1));

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Weapons", "Armor", "Consumables", "tools", "magic", "PotionAndScrolls", "adventure gear", "misc" }));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 100, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));

        jButton7.setBackground(new java.awt.Color(51, 51, 51));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Add item to cart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        jSlider1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSlider1.setForeground(new java.awt.Color(255, 255, 255));
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(10);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(1);
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 279, -1, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Items Amount");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 279, -1, -1));

        jButton8.setBackground(new java.awt.Color(51, 51, 51));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Shop Stock");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 170, -1));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 104, -1));

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "name", "type", "cost", "weight" }));
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 100, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Click on an item to get the description");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 348, 162));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Apply discount");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, -1, -1));

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Remove discount");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 240, 130, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 50, -1));

        jLabel3.setText("%");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("1.00 = 1 gold ; 0.1 = 1 silver ; 0.01 = 1 copper");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("10.00 = 1 platinum ; es: 12.32 = 1 platinum,2gold,3silver,2copper");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, -1, -1));

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("SaveShop");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 370, 150, 80));

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Buy");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 280, -1, -1));

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Remove from cart");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));

        jSlider2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSlider2.setForeground(new java.awt.Color(255, 255, 255));
        jSlider2.setMajorTickSpacing(1);
        jSlider2.setMaximum(10);
        jSlider2.setMinimum(1);
        jSlider2.setMinorTickSpacing(1);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setSnapToTicks(true);
        jSlider2.setToolTipText("");
        jSlider2.setValue(1);
        getContentPane().add(jSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, -1));

        jButton14.setBackground(new java.awt.Color(51, 51, 51));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Show total");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 280, -1, -1));

        jTextField3.setText("total..");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 110, -1));

        jButton15.setBackground(new java.awt.Color(51, 51, 51));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Refill invetory");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 170, -1));

        jButton16.setBackground(new java.awt.Color(51, 51, 51));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 450, 150, -1));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 370, 80));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PiancsShopGenerator/8.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 500));

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

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        removefromCart();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        showtotal();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        showtotal();
        Cart.clear();
        CartToTable();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        applydiscount();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
            SaveShop(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       new Shoparchive().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       SaveShop(true);
        new Shopstock1(b).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       RefillFromStock();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        jTable1.clearSelection();
        showdescription();
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
         jTable2.clearSelection();
        showdescription();
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OldShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OldShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OldShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OldShop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new OldShop(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
