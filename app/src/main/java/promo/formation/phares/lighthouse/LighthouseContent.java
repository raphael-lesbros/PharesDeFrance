package promo.formation.phares.lighthouse;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import promo.formation.phares.MainActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class LighthouseContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<LighthouseItem> ITEMS = new ArrayList<LighthouseItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, LighthouseItem> ITEM_MAP = new HashMap<String, LighthouseItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(MainActivity.getContext().getAssets().open("phares.json")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            String str = new String (sb.toString());
            JSONObject lighthousesJSON = new JSONObject(str);
            JSONObject lighthousesJSONBis = lighthousesJSON.getJSONObject("phares");
            JSONArray lighthousesJSONArray = lighthousesJSONBis.getJSONArray("liste");

            for (int i = 0; i < lighthousesJSONArray.length(); ++i) {
                JSONObject lighthouse = (JSONObject)lighthousesJSONArray.get(i);
                Integer id = (lighthouse.isNull("id") ? null : Integer.parseInt(lighthouse.getString("id")));
                String nom = (lighthouse.isNull("name") ? null : lighthouse.getString("name"));
                String imgFile = (lighthouse.isNull("filename") ? null : lighthouse.getString("filename"));
                String auteur = (lighthouse.isNull("auteur") ? null : lighthouse.getString("auteur"));
                String region = (lighthouse.isNull("region") ? null : lighthouse.getString("region"));
                Integer construction = (lighthouse.isNull("construction") ? null : Integer.parseInt(lighthouse.getString("construction")));
                Integer hauteur = (lighthouse.isNull("hauteur") ? null : Integer.parseInt(lighthouse.getString("hauteur")));
                Integer periode = (lighthouse.isNull("periode") ? null : Integer.parseInt(lighthouse.getString("periode")));
                Integer portee = (lighthouse.isNull("portee") ? null : Integer.parseInt(lighthouse.getString("portee")));
                Integer automatisation = (lighthouse.isNull("automatisation") ? null : Integer.parseInt(lighthouse.getString("automatisation")));
                String caractere = (lighthouse.isNull("caractere") ? null : lighthouse.getString("caractere"));
                Integer eclat = (lighthouse.isNull("eclat") ? null : Integer.parseInt(lighthouse.getString("eclat")));
                Double lat = (lighthouse.isNull("lat") ? null : Double.parseDouble(lighthouse.getString("lat")));
                Double lon = (lighthouse.isNull("lon") ? null : Double.parseDouble(lighthouse.getString("lon")));
                addItem(new LighthouseItem(id, nom, imgFile, region, construction, hauteur, caractere, eclat, periode, portee, automatisation, lat, lon));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static void addItem(LighthouseItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.nom, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class LighthouseItem {
        public final int id;
        public final String nom;
        public final String imgFile;
        public final String region;
        public final int construction;
        public final int hauteur;
        public final String caractere;
        public final int eclat;
        public final int periode;
        public final int portee;
        public final int automatisation;
        public final double lat;
        public final double lon;

        public LighthouseItem(int id, String nom, String imgFile, String region, int construction, int hauteur, String caractere, int eclat,  int periode, int portee, int automatisation, double lat, double lon) {
            this.id=id;
            this.nom = nom;
            this.imgFile = imgFile;
            this.region = region;
            this.construction = construction;
            this.hauteur = hauteur;
            this.caractere = caractere;
            this.eclat = eclat;
            this.periode = periode;
            this.portee = portee;
            this.automatisation = automatisation;
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public String toString() {
            return nom;
        }
    }
}