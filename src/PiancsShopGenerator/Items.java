package PiancsShopGenerator;


import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aless
 */
public class Items implements Serializable {
    private String nome;
    private String category;
    private String type;
    private double cost;
    private double weight;
    private String description;
    private boolean magic;
        
        public Items (String nome,String category,String type,double cost,double weight,String description,boolean magic)
        {
           this.nome = nome;
           this.category = category;
           this.type = type;
           this.cost = cost;
           this.weight = weight;
           this.description = description;
           this.magic = magic;
        }

    public boolean isMagic() {
        return magic;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getFormat()
    {
        
       
        String a = nome+"/"+category+"/"+type+"/"+cost+"/"+weight;
            return a;
    }
    public boolean getmagicstatus()
    {
        return magic;
    }
}
