import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class MarkerInternet extends MarkerX {
  
  /* Konstruktor
  * @param location [Location]: Ortsangabe des Markers
  * @return Gibt einen neuen MarkerInternet zurück
  */
  public MarkerInternet(Location location) {
    super(location);
  }
  
  /* Alternativ Konstruktor
  * @param trackpoint [Trackpoint]: Der Trackpoint, aus dem der Marker erstellt wird
  * @return Erstellt einen neuen MarkerInternet 
  */
  public MarkerInternet(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }
  
  /*
  * Zeichenmethode
  * @param pg [PGraphics]: Ein PGraphic Object, das zum zeichnen benötigt wird
  * @param x [float]: x Koordinate der location
  * @param y [float]: y Koordinate der Location
  */
  public void draw(PGraphics pg, float x, float y) {
    
    //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
    
    //Hier geschieht die eigentliche Zeichnung
    pg.pushStyle();
    pg.noStroke();
    pg.fill(rot, gelb, blau, 100);  // Farbe sowie sichtbarkeit 
    pg.ellipse(x, y, size, size);  // Form
    
    //Zeichnung des Symbols für die obere Ecke
    textSize(size);
    stroke(5);
    pg.fill(0, 0, 0, 100); 
    text("@",x - size *0.45,y + size*0.35);
    pg.popStyle();
  }
  

}

