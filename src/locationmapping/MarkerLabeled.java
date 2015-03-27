package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;

public class MarkerLabeled extends StandardMarker {
	/**
	* Konstruktor für MarkerLabeled Objekte
	*
	* @param location Ort fuer den Marker erstellt werden soll
	* @return neues Objekt vom Typ MarkerLabeled
	*/
	public MarkerLabeled(Location location){
		super(location);
	}
	/**
	* Konstruktor für MarkerLabeled Objekte
	*
	* @param trackpoint Trackpoint fuer den Marker erstellt werden soll
	* @return neues Objekt vom Typ MarkerLabeled
	*/
	public MarkerLabeled(Trackpoint trackpoint) {
	    super(trackpoint.getLocation());
	}
	/**
	* Zeichnet Marker
	* @param pg Objekt das gezeichnet werden soll
	* @param x X-Koordinate
	* @param y Y-Koordinate
	*/
	public void draw(PGraphics pg, float x, float y){
		if (!this.isHidden()){
			pg.pushStyle();
		    pg.stroke(hsb_h,hsb_s,hsb_b);  // kein Rand
		    pg.strokeWeight(2);
		    pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
		    pg.rect(x, y, size, size);  // Form: Rechteck
		    pg.text(label, x + size + 5 , y + size/2);
		    pg.textSize(textsize);
		    pg.popStyle();
		}
	}
}