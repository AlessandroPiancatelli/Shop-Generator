/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PiancsShopGenerator;

/**
 *
 * @author aless
 */
public class itemsinshop extends Items{
    private Items item;
    private double number;
    
    public itemsinshop (Items item,double number)
    {
        super(item.getNome(), item.getCategory(), item.getType(), item.getCost(), item.getWeight(), item.getDescription(), item.isMagic());
        this.number = number;
    }
    public itemsinshop(itemsinshop item)
    {
        super(item.getNome(), item.getCategory(), item.getType(), item.getCost(), item.getWeight(), item.getDescription(), item.isMagic());
        Double number1 = item.getNumber();
        this.number = number1;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
    
}
