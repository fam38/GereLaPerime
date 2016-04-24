package SqlLite.object;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Florent on 30/03/2016.
 */
public class ProduitOpenHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "FRIGO_DB";
    public static final int DATABASE_VERSION = 1;

    public static final String PRODUIT_TABLE_NAME = "Produits";
    public static final String PRODUIT_COL_NOM = "NomProduit";
    public static final String PRODUIT_COL_LIBELLE = "Libelle";
    public static final String PRODUIT_COL_CODE = "CodeBarre";
    public static final String PRODUIT_COL_CATEGORIE = "Categorie";

    public static final String REEL_TABLE_NAME = "ProduitsReels";
    public static final String REEL_COL_DATE = "DateExpiration";
    public static final String REEL_COL_PRODUIT = "Produit";
    public static final String REEL_COL_STOCKAGE = "Stockage";

    public static final String STOCKAGE_TABLE_NAME = "Stockages";
    public static final String STOCKAGE_COL_NOM = "nom";
    public static final String STOCKAGE_COL_TYPE = "Type";

    private static final String TABLES_REEL_CREATE = "CREATE TABLE " + REEL_TABLE_NAME +
            " (" + REEL_COL_PRODUIT +" TEXT, "+ REEL_COL_STOCKAGE + " TEXT, "+ REEL_COL_DATE +" TEXT);";

    private static final String TABLES_PRODUIT_CREATE = "CREATE TABLE " + PRODUIT_TABLE_NAME +
            " (" + PRODUIT_COL_NOM +" TEXT, "+ PRODUIT_COL_LIBELLE + " TEXT, "+ PRODUIT_COL_CODE +" INTEGER, " +
            PRODUIT_COL_CATEGORIE + " TEXT);";

    private static final String TABLES_STOCKAGE_CREATE = "CREATE TABLE " + STOCKAGE_TABLE_NAME +
            " (" + STOCKAGE_COL_NOM +" TEXT, "+ STOCKAGE_COL_TYPE +" TEXT);";

    public ProduitOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLES_STOCKAGE_CREATE);
        db.execSQL(TABLES_REEL_CREATE);
        db.execSQL(TABLES_PRODUIT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
        }

    }
}
