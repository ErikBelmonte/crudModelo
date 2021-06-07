package br.ulbra.model;

/**
 *
 * @author Erik
 */
public class Fabricante {
    private int id;
    private String marca;
    private String telefone;
    private String site;
    private String email;
    
    public Fabricante(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSite(){
        return site;
    }

    public void setSite(String site){
        this.site = site;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return "Fabricante{" + "id=" + id + ", marca=" + marca + ", telefone=" + telefone + ", site=" + site + ", email=" + email + '}';
    }
    
}

