package SqlLite.object;

import java.util.ArrayList;

/**
 * Created by Martin on 30/03/2016.
 */
public class Produit {
    private String nomProduit;
    private String Libelle;
    private String CodeBarre;
    private String Categorie;

    private static ArrayList<Produit> listeProduit = new ArrayList<>();

    public Produit(String nomProduit, String libelle, String codeBarre, String categorie) {
        this.nomProduit = nomProduit;
        Libelle = libelle;
        CodeBarre = codeBarre;
        Categorie = categorie;
        listeProduit.add(this);
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getCodeBarre() {
        return CodeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        CodeBarre = codeBarre;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public static Produit getProduit(String nomProduit){
        for(Produit produit : Produit.listeProduit){
            if(produit.getNomProduit().equals(nomProduit))
                return produit;
        }
        return null;
    }
}
