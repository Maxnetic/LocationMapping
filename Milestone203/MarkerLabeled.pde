import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;

/*
 * Beschriftete Marker
 */

public class MarkerLabeled extends MarkerX {
  
  String label = null;
  int textsize = 12;
  
  /*
  * Konstruktor fuer MarkerLabeled
  * @param location [Location] : Ort fuer den MarkerLabeled erstellt werden soll
  * @return neues Objekt vom Typ MarkerLabeled
  */
  public MarkerLabeled(Location location) {
    super(location);
  }
  
  /*
  * Konstruktor fuer MarkerLabeled der Trackpoint uebergeben bekommt
  * @param trackpoint [Trackpoint] : Trackpoint fuer den MarkerLabeled gezeichnet werden soll
  * @return neues Objekt vom Typ MarkerLabeled
  */
  public MarkerLabeled(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }
  
  /*
   * Label festlegen
   * @param String label
   */
  public void setLabel(String label){
    this.label = label;
  }
  
  /*
   * Label auslesen
   * @return String label
   */
  public String getLabel(){
    return this.label;
  }
  
  /*
   * Textgroesse festlegen
   * @param int size
   */
  public void setTextSize(int size){
    this.textsize = size;
  }
  
  /*
   * Textgroesse auslesen
   * @return int textsize
   */
  public int getTextSize(){
    return this.textsize;
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
    pg.strokeWeight(2);
    pg.fill(color(rot, gelb, blau, 1));  // Farbe sowie sichtbarkeit 
    pg.rect(x, y, size, size);  // Form: Rechteck
    text(label, x +15 , y +9);
    textSize(textsize);
    pg.popStyle();
  }
  
}





