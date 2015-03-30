package locationmapping;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.marker.*;

/**
 * StaticMapper erweitert Mapper.
 * Erzeugt ein Mapper-Objekt, das Marker statisch zeichnet.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class StaticMapper extends Mapper {
    /**
     * Konstruktor für StaticMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper laeuft
     */
    public StaticMapper(PApplet app){
        super(app);
    }

    /**
    * Fuegt Marker hinzu
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        map.addMarker(marker);
    }
}