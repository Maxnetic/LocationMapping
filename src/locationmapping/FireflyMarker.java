package locationmapping;

import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * FireflyMarker erweitert SimpleLinesMarker.
 * Zeichnet einen Firefly-Marker, die nach einer bestimmten
 * Zeit wieder verschwinden.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class FireflyMarker extends SimplePointMarker implements Const {
	/**
	 * Anzahl der Frames, die ein Marker sichtbar ist.
	 */
    public int timeToLive = 60;

	/**
	 * Konstruktor fuer FireflyMarker
	 * @param location Location des Markers
	 */
    public FireflyMarker(Location location){
        super(location);

        this.setColor(Const.DARK_YELLOW);
        this.setStrokeWeight(0);
        this.setRadius(4);
    }

	/**
	 * Konstruktor fuer FireflyMarker
	 * @param trackpoint Trackpoint des Markers
	 */
    public FireflyMarker(Trackpoint trackpoint){
        this(trackpoint.getLocation());
    }

	/**
	 * Konstruktor fuer FireflyMarker
	 * @param trackpoint Trackpoint des Markers
	 * @param ttl Frames, die der FireflyMarker angezeigt werden soll.
	 */
    public FireflyMarker(Trackpoint trackpoint, int ttl){
        this(trackpoint);
        this.timeToLive = ttl;
    }
}