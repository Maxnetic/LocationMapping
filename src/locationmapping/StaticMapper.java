package locationmapping;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.marker.*;

public class StaticMapper extends Mapper {
    /**
     * Konstruktor für StaticMapper Objekte
     *
     * @param app Processing Applet, in dem Mapper läuft
     */
    public StaticMapper(PApplet app){
        super(app);
    }

    public void addMarker(Marker marker) {
        map.addMarker(marker);
    }
}