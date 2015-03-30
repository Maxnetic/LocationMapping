package locationmapping;

import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * StandardMarker erweitert SimpleLinesMarker.
 * Zeichnet einen Standard-Marker, dessen Groesse,
 * Farbe, Beschriftung und Form angepasst werden kann.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class FireflyMarker extends SimplePointMarker implements Const {
    public int timeToLive = 60;

    public FireflyMarker(Location location){
        super(location);

        this.setColor(Const.LIGHT_RED);
        this.setStrokeWeight(0);
        this.setRadius(4);
    }
    public FireflyMarker(Trackpoint trackpoint){
        this(trackpoint.getLocation());
    }
    public FireflyMarker(Trackpoint trackpoint, int ttl){
        this(trackpoint);
        this.timeToLive = ttl;
    }
}