package SqlLite.object;

import java.util.ArrayList;

/**
 * Created by Martin on 30/03/2016.
 */
public class Stockage {
    private String nomStockage;
    private String type;
    private static ArrayList<Stockage> listeStockage = new ArrayList<>();

    public Stockage(String nomStockage, String type) {
        this.nomStockage = nomStockage;
        this.type = type;
        this.listeStockage.add(this);
    }

    public String getNomStockage() {
        return nomStockage;
    }

    public String getType() {
        return type;
    }

    public void setNomStockage(String nomStockage) {
        this.nomStockage = nomStockage;
    }

    public static Stockage getStockage(String nomStockage){
        for(Stockage stockage : Stockage.listeStockage)
        {
            if(stockage.getNomStockage().equals(nomStockage))
                return stockage;
        }
        return null;
    }
}
