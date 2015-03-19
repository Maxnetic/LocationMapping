import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class FormedMarker extends UpdateableMarker {
  
  
  /*
  * Konstruktor fuer formedMarker
  * @param location [Location] : Ort fuer den formedMarker erstellt werden soll
  * @return neues Objekt vom Typ formedMarker
  */
  public FormedMarker(Location location) {
    super(location);
  }
  
  /*
  * Konstruktor fuer formedMarker der Trackpoint uebergeben bekommt
  * @param trackpoint [Trackpoint] : Trackpoint fuer den formedMarker gezeichnet werden soll
  * @return neues Objekt vom Typ FormedMarker
  */
  public FormedMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
    this.setColor( color(100,100,100) );
  }
  
  //Zeichenmethode
  /*
   * Zeichenmethode fuer formedMarker
   * @param pg [PGraphics] : Objekt, das fuers Zeichnen benoetigt wird
   * @param x [float] : Koordinate der Location
   * @param y [float] : Koordinate der Location
   */
  public void draw(PGraphics pg, float x, float y) {
    
    //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
      
    //Hier wird gezeichnet
    pg.pushStyle();
    pg.stroke(color(rot, gelb, blau));  // kein Rand
    pg.strokeWeight(4);
    pg.fill(color(rot, gelb, blau, 100));  // Farbe sowie sichtbarkeit 
    pg.rect(x, y, size, size);  // Form: Rechteck
    pg.popStyle();
  }
  
}

