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
    * Default Farben
    */
    public static final int DEFAULT_TEXT_COLOR = -9474195;      // #6f6f6d
    public static final int DEFAULT_BUTTON_COLOR1 = -1;         // #ffffff
    public static final int DEFAULT_BUTTON_COLOR2 = -1710619;   // #e5e5e5
    public static final int DEFAULT_HIGHLIGHT_COLOR = -2992050; // #d2584e
    public static final int RED = -2992050;             // #d2584e
    public static final int YELLOW = -864700;           // #f2ce44
    public static final int GREEN = -12347057;          // #43994f
    public static final int BLUE = -12681790;           // #3e7dc2

    /**
    * Helle Farben
    */
    public static final int LIGHT_TEXT_COLOR = -8421505;        // #
    public static final int LIGHT_BUTTON_COLOR1 = -855310;      // #
    public static final int LIGHT_BUTTON_COLOR2 = -1710619;     // #
    public static final int LIGHT_HIGHLIGHT_COLOR = -2714732;   // #
    public static final int LIGHT_RED = -2714732;               // #
    public static final int LIGHT_YELLOW = -864700;             // #f2ce44
    public static final int LIGHT_GREEN = -12347057;            // #43994f
    public static final int LIGHT_BLUE = -12681790;             // #3e7dc2

    /**
    * Dunkle Farben
    */
    public static final int DARK_TEXT_COLOR = -8947849;         // #777777
    public static final int DARK_BUTTON_COLOR1 = -15856114;     // #0e0e0e
    public static final int DARK_BUTTON_COLOR2 = -14277082;     // #262626
    public static final int DARK_HIGHLIGHT_COLOR = -12368080;   // #434730
    public static final int DARK_RED = -1210035;                // #ed894d
    public static final int DARK_YELLOW = -1712033;             // #e5e05f
    public static final int DARK_GREEN = -9602252;              // #6d7b34
    public static final int DARK_BLUE = -9794127;               // #6a8db1

    /**
    * String mit Icon Unicodes
    */
    public static final String INTERNET = "\uf0ac";
    public static final String PHONE = "\uf095";
    public static final String EMAIL = "\uf0e0";
    public static final String CHAT = "\uf075";

}