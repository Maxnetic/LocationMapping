package locationmapping;

import java.util.List;

import processing.core.PConstants;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.MapPosition;

/**
 * Die Klasse ColoredLinesMarker stellt Marker zur Verfuegung,
 * in Form von Linien zwischen Trackpoints.
 * Möglich ist dies zwischen zweien oder mit mehreren Trackpoints.
 * ColoredLinesMarker erweitert SimpleLinesMarker.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class ColoredLinesMarker extends SimpleLinesMarker {
    /**
    * Groesse des Markers (default == 30)
    */
    int size = 30;
    /**
    * Farbwert der HSB Farbe
    */
    int hsb_h = 0;
    /**
    * Saettigungswert der HSB Farbe
    */
    int hsb_s = 0;
    /**
    * Helligkeitswert der HSB Farbe
    */
    int hsb_b = 0;
    /**
    * Transparenz der Farbe des Markers
    */
    int transparency = 100;

    /**
    * Kosntruktor fuer ColoredLineMarker Objekte
    */
    public ColoredLinesMarker() {
        super();
    }


    /**
    * Konstruktor fuer ColoredLineMarker Objekte
    *
    * @param start Startpunkt
    * @param end Endpunkt
    */
    public ColoredLinesMarker(Location start, Location end) {
        super(start, end);
    }


    /**
    * Konstruktor fuer ColoredLineMarker Objekte
    *
    * @param locations Liste aus der Line gezeichnet werden soll
    */
    public ColoredLinesMarker(List<Location> locations){
        super(locations);
    }
    /**
    * Setzt Tansparenz
    *
    * @param trans Transparenzwert
    */
    public void setTransparency(int trans){
        transparency = trans;
      }

      /**
      * Setzt Farbe des Markers durch HSB-Kodierung
      *
      * @param hsb_h Farbwert
      * @param hsb_s Saettigungswert
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
        colorstr.toLowerCase();
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
        }else{
        System.out.println("Die Farbe ist nicht in der Liste! Farbe mit HSB Codierung möglich!");
        }

      }

    /**
     * Zeichenmethode
	 * 
	 * @param pg Objekt das gezeichnet wird
	 * @param mapPositions Liste der Punkte, die die Linie enthaelt.
     */
    public void draw(PGraphics pg, List<MapPosition> mapPositions){
        if (!this.isHidden()){
            if ( mapPositions.isEmpty() || isHidden() ){
                return;
            }
            pg.pushStyle();
            pg.noFill();
            if ( isSelected() ){
              pg.stroke(highlightColor);
            } else {
                pg.stroke(hsb_h, hsb_s, hsb_b, transparency);
            }
            pg.strokeWeight(strokeWeight);
            pg.smooth();

            pg.beginShape(PConstants.LINES);
            MapPosition last = mapPositions.get(0);
            for (int i = 1; i < mapPositions.size(); ++i) {
                MapPosition mp = mapPositions.get(i);
                pg.vertex(last.x, last.y);
                pg.vertex(mp.x, mp.y);
                last = mp;
            }
            pg.endShape();
            pg.popStyle();
        }
    }
}


