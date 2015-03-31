package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

/**
 * Die Klasse MarkerRectangle stellt einen Marker zur Verfuegung,
 * dessen Form quadratisch ist.
 * MarkerRectangle erweitert StandardMarker.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class MarkerRectangle extends StandardMarker {
	/**
	* Konstruktor für MarkerRectangle Objekte
	*
	* @param location Ort fuer den Marker erstellt werden soll
	*/
	public MarkerRectangle(Location location){
		super(location);
	}
	/**
	* Konstruktor für MarkerRectangle Objekte
	*
	* @param trackpoint Trackpoint fuer den Marker erstellt werden soll
	*/
	public MarkerRectangle(Trackpoint trackpoint) {
	    super(trackpoint.getLocation());
	  }
	/**
	* Zeichnet Marker
	* @param pg Objekt das gezeichnet werden soll
	* @param x X-Koordinate des Markers
	* @param y Y-Koordinate des Markers
	*/
	public void draw(PGraphics pg, float x, float y){
		if (!this.isHidden()){
			pg.pushStyle();
			pg.stroke(hsb_h, hsb_s, hsb_b, transparency);  // kein Rand
			pg.strokeWeight(2);
			pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
			pg.rect(x, y, size, size);  // Form: Rechteck
			pg.popStyle();
	    return;
		}
	}
}