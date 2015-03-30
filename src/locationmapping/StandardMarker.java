package locationmapping;

import org.joda.time.DateTime;

import processing.core.PGraphics;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;




public class StandardMarker extends SimplePointMarker {
 /**
 * Größe des Markers
 */
    int size = 30;
    /**
    * Farbwert der HSB Farbe
    */
    int hsb_h = 359;
    /**
    * Sättigungswert der HSB Farbe
    */
    int hsb_s = 31;
    /**
    * Helligkeitswert der HSB Farbe
    */
    int hsb_b = 84;
    /**
    * Transparenz der Farbe des Markers
    */
    int transparency = 50;
    /**
    * Text für die Beschriftung
    */
    String label = null;
    /**
    * Textgröße der Beschriftung
    */
    int textsize = 12;
    /**
    * Zeitpunkt des zugehörigen Trackpoint
    */
    DateTime timestamp = new DateTime(0);

    /**
    * Konstruktor fuer StandardMarker Objekte
    * @param location Ortsangabe des Markers
    * @return neues Objekt vom Typ Marker
    */
    public StandardMarker(Location location) {
        super(location);
    }

    /**
     * Konstruktor fuer StandardMarker Objekte
     * @param trackpoint Trackpoint aus dem Marker gezeichnet werden soll
     * @return neues Objekt von Typ Marker
     */
    public StandardMarker(Trackpoint trackpoint) {
        super(trackpoint.getLocation());
        this.timestamp = trackpoint.getTime();
    }

    public DateTime getTime(){
        return this.timestamp;
    }

    /**
    * Setzt die Größe des Markers
    * @param size neue Größe des Markers
    */
    public void setSize(int size){
        this.size = size;
    }


    /**
    * Setzt die Transparenz des Markers
    * @param trans Transparenzwert
    */
    public void setTransparency(int trans){
        transparency = trans;
    }

    /**
    * Setzt die Farbe des Markers
    * @param hsb_h Farbwert
    * @param hsb_s Sättigungswert
    * @param hsb_b Helligkeitswert
    */
    public void setColor(int hsb_h, int hsb_s, int hsb_b){
        this.hsb_h =hsb_h;
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
		} else if (colorstr.equals("schwarz"){
			hsb_h = 0;
			hsb_s = 0;
			hsb_b = 0;
		}else if (colorstr.equals("orange"){	
			hsb_h = 30;
			hsb_s = 99;
			hsb_b = 99;		
		}else if (colorstr.equals("rosa"){	
			hsb_h = 300;
			hsb_s = 99;
			hsb_b = 99;		
		}else if (colorstr.equals("türkis"){	
			hsb_h = 170;
			hsb_s = 99;
			hsb_b = 99;		
		}else if (colorstr.equals("lila"){	
			hsb_h = 285;
			hsb_s = 99;
			hsb_b = 99;	
		}else if (colorstr.equals("weiß"){	
			hsb_h = 0;
			hsb_s = 0;
			hsb_b = 99;		
		}else{
			System.out.println("Die Farbe ist nicht in der Liste! Farbe mit HSB Codierung möglich!");
		}
    }



    /**
     * Gibt Label aus
     * @return Label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Setzt Label
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Setzt Textgroesse
     * @param size Größe
     */
    public void setTextSize(int size){
        this.textsize = size;
    }

    /**
     * Textgroesse auslesen
     * @return Textgröße
     */
    public int getTextSize(){
        return this.textsize;
    }

    /**
    * Zeichnet Marker
    * @param pg Objekt das gezeichnet werden soll
    * @param x X-Koordinate
    * @param y Y-Koordinate
    */
    public void draw(PGraphics pg, float x, float y){
        pg.colorMode(pg.HSB, 360, 100, 100, 100);
        if (!this.isHidden()){
            pg.pushStyle();
                pg.stroke(hsb_h, hsb_s, hsb_b);  // kein Rand
                pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
                pg.ellipse(x, y, size, size);  // Form
                pg.popStyle();
        }
    }

}

