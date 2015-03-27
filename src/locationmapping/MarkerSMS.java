package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;

public class MarkerSMS extends StandardMarker {

	/**
	* Konstruktor für MarkerSMS Objekte
	*
	* @param location Ort fuer den Marker erstellt werden soll
	* @return neues Objekt vom Typ MarkerSMS
	*/
	public MarkerSMS(Location location){
		super(location);
	}
	
	/**
	* Konstruktor für MarkerSMS Objekte
	*
	* @param trackpoint Trackpoint fuer den Marker erstellt werden soll
	* @return neues Objekt vom Typ MarkerSMS
	*/
	public MarkerSMS(Trackpoint trackpoint) {
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
		    pg.noStroke();
		    pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
		    pg.ellipse(x, y, size, size);  // Form
		    pg.stroke(0, 0, 0, 200);  //Zeichnung des Symbols für die obere Ecke
		    pg.rect(x + size/2 , y - size ,  size, size / 2);  //Umschlag

		    //Setzt die beiden Linien
		    pg.line(x+ size/2 , y - size , x + size , y - size * 0.75f );
		    pg.line(x + size , y - size * 0.75f , x + 1.5f * size , y - size  );
		    pg.popStyle();
		}
	}
}