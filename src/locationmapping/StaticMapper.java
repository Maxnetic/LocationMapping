package locationmapping;

import java.util.HashMap;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.marker.*;

/**
 * Die Klasse StaticMapper erzeugt ein Mapper-Objekt, das Marker statisch auf die Karte zeichnet.
 * StaticMapper erweitert Mapper.
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
    *
    * @param marker Marker der hinzugefuegt werden soll
    */
    public void addMarker(Marker marker) {
        // Schriftart für Marker setzen
        try {
            HashMap<String,Object> properties = marker.getProperties();
            properties.put("font", this.iconFont);
            marker.setProperties(properties);
        } catch(Exception e){;}

        // Marker zur Liste hinzufügen
        this.map.addMarker(marker);
    }
}