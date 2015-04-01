package locationmapping;

import de.fhpotsdam.unfolding.geo.Location;

/**
 * Die Klasse Const beinhaltet alle gesetzten Konstanten von locationmapping.
 * Dazu gehoeren zu importierende Formate, Locations und Farben.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public interface Const {
    /**
     * Zeitformate fuer Importer
     */
    public static final int UNIX = 0;
    public static final int ISO8601 = 1;
    public static final int EXPONENT_APPLE = 2;
    public static final int MDY_DATETIME = 3;

    /**
     * Einige Locations
     */
    public static final Location BERLIN = new Location(52.5f, 13.4f);
    public static final Location GERMANY = new Location(51.16f, 10.45f);
    public static final Location HAMBURG = new Location(53.55f, 9.99f);
    public static final Location MUENCHEN = new Location(48.14f, 11.57f);
    public static final Location KOELN = new Location(50.94f, 6.95f);
    public static final Location FRANKFURT = new Location(50.12f, 8.68f);
    public static final Location STUTTGART = new Location(48.78f, 9.19f);

    /**
    * Helle Farben
    */
    public static final int LIGHT_TEXT_COLOR = -8421505;
    public static final int LIGHT_BUTTON_COLOR1 = -855310;
    public static final int LIGHT_BUTTON_COLOR2 = -1710619;
    public static final int LIGHT_RED = -2714732;

    /**
    * Dunkle Farben
    */
    public static final int DARK_TEXT_COLOR = -8947849;
    public static final int DARK_BUTTON_COLOR1 = -15856114;
    public static final int DARK_BUTTON_COLOR2 = -14277082;
    public static final int DARK_RED = -1210035;
    public static final int DARK_YELLOW = -1712033;
    public static final int DARK_GREEN = -9602252;
    public static final int DARK_BLUE = -9794127;
}