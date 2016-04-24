package SqlLite.object;

import java.util.Date;

/**
 * Created by Martin on 30/03/2016.
 */
public class ProduitReel {
    private Date DateExpiration;
    private Produit produit;
    private Stockage stockage;

    public ProduitReel(Produit produit, Stockage stockage, Date dateExpiration) {
        DateExpiration = dateExpiration;
        this.produit = produit;
        this.stockage = stockage;
    }

    public Date getDateExpiration() {
        return DateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        DateExpiration = dateExpiration;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Stockage getStockage() {
        return stockage;
    }

    public void setStockage(Stockage stockage) {
        this.stockage = stockage;
    }
}

