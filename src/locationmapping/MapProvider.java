package locationmapping;

import de.fhpotsdam.unfolding.providers.AbstractMapTileUrlProvider;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.geo.MercatorProjection;
import de.fhpotsdam.unfolding.geo.Transformation;
import de.fhpotsdam.unfolding.providers.*;

/**
 * Klasse mit Ausgewaelten Karten Providern
 */
public class MapProvider {
    /**
     * Abstrakte Klasse fuer Karten Provider die auf Leaflet Providern beruhen
     * http://leaflet-extras.github.io/leaflet-providers/preview/index.html
     */
    public static abstract class GenericLeafletProvider extends AbstractMapTileUrlProvider {
        /**
         * Konstruktor fuer Leaflet basierte Karten Provider
         */
        public GenericLeafletProvider() {
            super(new MercatorProjection(26, new Transformation(1.068070779e7, 0.0, 3.355443185e7, 0.0,
                    -1.068070890e7, 3.355443057e7)));
        }

        /**
         * Konstruiert Parameter fuer
         *
         * @param zoom Zoom Koordinate des Map Tiles
         * @param a zweite Koordinate des Map Tiles, je nach Provider entweder row oder column
         * @param b dritte Koordinate des Map Tiles, je nach Provider entweder column oder row
         * @return String der Form 'z/a/b' mit Tile Koordinaten
         */
        public String getZoomString(int zoom, int a, int b) {
            return zoom + "/" + a + "/" + b;
        }

        /**
         * Gibt Tile Breite zurueck
         *
         * @return Standard Tile Breite von 256
         */
        public int tileWidth() {
            return 256;
        }

        /**
         * Gibt Tile Hoehe zurueck
         *
         * @return Standard Tile Hoehe von 256
         */
        public int tileHeight() {
            return 256;
        }

        /**
         * Konstruiert URL fuer Map Tiles
         *
         * @param coordinate Koordinate des Map Tiles
         * @return Stringarray mit Map Tile URL
         */
        public abstract String[] getTileUrls(Coordinate coordinate);
    }

    /**
     * CartoDB Positron Map Provider (basierend auf OSM)
     */
    public static class Light extends GenericLeafletProvider {
        /**
         * Konstruiert URL fuer Map Tiles
         *
         * @param coordinate Koordinate des Map Tiles
         * @return Stringarray mit Map Tile URL
         */
        public String[] getTileUrls(Coordinate coordinate) {
            String url = "http://a.basemaps.cartocdn.com/light_all/" + getZoomString((int)coordinate.zoom, (int)coordinate.column, (int)coordinate.row) + ".png";
            return new String[] { url };
        }
    }

    /**
     * CartoDB DarkMatter Map Provider (basierend auf OSM)
     */
    public static class Dark extends GenericLeafletProvider {
        /**
         * Konstruiert URL fuer Map Tiles
         *
         * @param coordinate Koordinate des Map Tiles
         * @return Stringarray mit Map Tile URL
         */
        public String[] getTileUrls(Coordinate coordinate) {
            String url = "http://a.basemaps.cartocdn.com/dark_all/" + getZoomString((int)coordinate.zoom, (int)coordinate.column, (int)coordinate.row) + ".png";
            return new String[] { url };
        }
    }

    /**
     * Standard Google Map Provider, nur Umbenennung des unfolding Providers
     */
    public static class GoogleMap extends Google.GoogleMapProvider {}

    /**
     * Terrain Google Map Provider, nur Umbenennung des unfolding Providers
     */
    public static class GoogleTerrain extends Google.GoogleTerrainProvider {}

    /**
     * Satelite Google Map Provider
     */
    public static class Satelite extends Google.GoogleProvider {
        /**
         * Konstruktor fuer Google Satelite Map Provider
         */
        public Satelite() {}

        /**
         * Konstruiert URL fuer Google Satelite Map Tiles
         *
         * @param coordinate Koordinate des Map Tiles
         * @return Stringarray mit Map Tile URL
         */
        public String[] getTileUrls(Coordinate coordinate) {
            String url = "http://mt1.google.com/vt/lyrs=s&x=" + (int) coordinate.column + "&y="
                    + (int) coordinate.row + "&z=" + (int) coordinate.zoom;
            return new String[] { url };
        }
    }

    /**
     * Hybrid Google Map Provider
     */
    public static class Hybrid extends Google.GoogleProvider {
        /**
         * Konstruktor fuer Google Hybrid Map Provider
         */
        public Hybrid() {}

        /**
         * Konstruiert URL fuer Google Hybrid Map Tiles
         *
         * @param coordinate Koordinate des Map Tiles
         * @return Stringarray mit Map Tile URL
         */
        public String[] getTileUrls(Coordinate coordinate) {
            String url = "http://mt1.google.com/vt/lyrs=y&x=" + (int) coordinate.column + "&y="
                    + (int) coordinate.row + "&z=" + (int) coordinate.zoom;
            return new String[] { url };
        }
    }

}
