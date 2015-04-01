package locationmapping;

import org.joda.time.DateTime;

import processing.core.PGraphics;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;

/**
 * Die Klasse StandardMarker stellt die Visualisierung von Trackpoints zur Verfuegung.
 * Fuer einen bestimmten Trackpoint kann ein Marker auf die Karte gezeichnet werden,
 * der in Groesse, Form, Farbe und Beschriftung variiert werden kann. 
 * StandardMarker erweitert SimpleLinesMarker.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class StandardMarker extends SimplePointMarker {
 /**
 * Groesse des Markers (default = 30)
 */
    int size = 30;
    /**
    * Farbwert der HSB Farbe (default = 359)
    */
    int hsb_h = 359;
    /**
    * Saettigungswert der HSB Farbe (default = 31)
    */
    int hsb_s = 31;
    /**
    * Helligkeitswert der HSB Farbe (default = 84)
    */
    int hsb_b = 84;
    /**
    * Transparenz der Farbe des Markers (default = 50)
    */
    int transparency = 50;
    /**
    * Text für die Beschriftung
    */
    String label = null;
    /**
    * Textgroesse der Beschriftung (default = 12)
    */
    int textsize = 12;
    /**
    * Zeitpunkt des zugehoerigen Trackpoint
    */
    DateTime timestamp = new DateTime(0);

    /**
    * Konstruktor fuer StandardMarker Objekte
    * @param location Ortsangabe des Markers
    */
    public StandardMarker(Location location) {
        super(location);
    }

    /**
     * Konstruktor fuer StandardMarker Objekte
     * @param trackpoint Trackpoint aus dem Marker gezeichnet werden soll
     */
    public StandardMarker(Trackpoint trackpoint) {
        super(trackpoint.getLocation());
        this.timestamp = trackpoint.getTime();
    }

	/**
	 * Gibt die Zeit zurueck
	 * @return Zeitpunkt des Trackpoints des Markers
	 */
    public DateTime getTime(){
        return this.timestamp;
    }

    /**
    * Setzt die Groesse des Markers
    * @param size neue Groesse des Markers
    */
    public void setSize(int size){
        this.size = size;
    }


    /**
    * Setzt die Transparenz des Markers
    * @param trans neuer Transparenzwert des Markers
    */
    public void setTransparency(int trans){
        transparency = trans;
    }

    /**
    * Setzt die Farbe des Markers durch HSB Kodierung
    * @param hsb_h Farbwert
    * @param hsb_s Sättigungswert
    * @param hsb_b Helligkeitswert
    */
    public void setColor(int hsb_h, int hsb_s, int hsb_b){
        this.hsb_h = hsb_h;
        this.hsb_s = hsb_s;
        this.hsb_b = hsb_b;
    }

    /**
    * Setzt Farbe des Markers
    * @param colorstr Farbe
    */
    public void setColor(String colorstr){
		//Formatierung in Kleinbuchstaben
        colorstr.toLowerCase();

		// Fallabfrage
		if (colorstr.equals("rot")){
			hsb_h = 0;
			hsb_s = 99;
			hsb_b = 99;
		} else  if (colorstr.equals("blau")){
			hsb_h = 240;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("grün")){
			hsb_h = 100;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("gelb")){
			hsb_h = 60;
			hsb_s = 99;
			hsb_b = 99;
		} else if (colorstr.equals("grau")){
			hsb_h = 0;
			hsb_s = 1;
			hsb_b = 60;
		} else if (colorstr.equals("schwarz")){
			hsb_h = 0;
			hsb_s = 0;
			hsb_b = 0;
		}else if (colorstr.equals("orange")){
			hsb_h = 30;
			hsb_s = 99;
			hsb_b = 99;
		}else if (colorstr.equals("rosa")){
			hsb_h = 300;
			hsb_s = 99;
			hsb_b = 99;
		}else if (colorstr.equals("türkis")){
			hsb_h = 170;
			hsb_s = 99;
			hsb_b = 99;
		}else if (colorstr.equals("lila")){
			hsb_h = 285;
			hsb_s = 99;
			hsb_b = 99;
		}else if (colorstr.equals("weiß")){
			hsb_h = 0;
			hsb_s = 0;
			hsb_b = 99;
		}else{
			System.out.println("Die Farbe ist nicht in der Liste! Farbe mit HSB Codierung möglich!");
		}
    }



    /**
     * Gibt Label aus
     * @return Beschriftung des Markers
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Setzt Label
     * @param label neues Label des Markers
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Setzt Textgroesse
     * @param size Groesse
     */
    public void setTextSize(int size){
        this.textsize = size;
    }

    /**
     * Textgroesse auslesen
     * @return Textgroesse
     */
    public int getTextSize(){
        return this.textsize;
    }

    /**
    * Zeichnet Marker
    * @param pg Objekt das gezeichnet werden soll
    * @param x X-Koordinate des Markers
    * @param y Y-Koordinate
    */
    public void draw(PGraphics pg, float x, float y){
        pg.colorMode(pg.HSB, 360, 100, 100, 100);
        if (!this.isHidden()){
            pg.pushStyle();
                pg.stroke(hsb_h, hsb_s, hsb_b);  // kein Rand
                pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie Sichtbarkeit
                pg.ellipse(x, y, size, size);  // Form
                pg.popStyle();
        }
    }

}

