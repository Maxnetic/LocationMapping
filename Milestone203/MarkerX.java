import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;

/*
 * MarkerX Oberklasse für alle Marker
 * @param size Größe des Markers
 * @param rot, gelb, blau Farbe des Markers 
 */

public class MarkerX extends SimplePointMarker {
  //Attribute
  // Beinhaltet die größe des Markers
  int size = 5;
   

  // int für die Farbkodierung des Markers, die Anwendung erfolgt in color(rot,gelb,blau)
  int rot = 0;
  int gelb = 0;
  int blau = 0;
  
  String text = null;
 
  /*
  * Konstruktor fuer UpdatableMarker Objekte
  * @param location [Location]: Ortsangabe des Markers
  * @return neues Objekt vom Typ Marker
  */
  public MarkerX(Location location) {
    super(location);
  }
  
  
  /*
   * Konstruktor der Trackpoint uebergeben bekommt
   * @param trackpoint [Trackpoint] : Trackpoint aus dem Marker gezeichnet werden soll
   * @return neues Objekt von Typ Marker
   */
  public MarkerX(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }
  
  /*
  * updated die Größe des Markers
  * @param size [int]: die neue Größe des Markers
  */
  public void setSize(int size){
    this.size = size;
  }
  

  /*
  *Die Funktion updated die Farbe des Markers
  *@param rot [int]: Rotfarbanteil
  *@param gelb [int]: Gelbfarbanteil
  *@param blau [int]: Blaufarbanteil
  */
  public void setColor(int rot, int gelb, int blau){
    this.rot = rot;
    this.gelb = gelb;
    this.blau = blau;
  }

}

