import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class UpdateableMarker extends SimplePointMarker {
  //Attribute
  
  /*
  * Beinhaltet die größe des Markers
  */  
  int size = 5;
   
 /*
 * int für die Farbkodierung des Markers, die Anwendung erfolgt in color(rot,gelb,blau)
 */
  int rot = 0;
  int gelb = 0;
  int blau = 0;
 
  /*
  *Konstruktor
  *@param location [Location]: Ortsangabe des Markers
  */
  public UpdateableMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public UpdateableMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }*/
  
  /*
  * updated die Größe des Markers
  * @param size [int]: die neue Größe des Markers
  */
  public void updateSize(int size){
    this.size = size;
  }
  
  /* Die Funktion setzt die Sichtbarkeitseigenschaften des Markers
  *@param hidden [boolean]: True oder False für die SIchtbarkeitseigenschaft
  */
  public void updateHidden(boolean hidden){
    this.setHidden(hidden);
  }
  
  /*
  *Die Funktion updated die Farbe des Markers
  *@param rot [int]: Rotfarbanteil
  *@param gelb [int]: Gelbfarbanteil
  *@param blau [int]: Blaufarbanteil
  */
  public void updateColor(int rot, int gelb, int blau){
    this.rot = rot;
    this.gelb = gelb;
    this.blau = blau;
  }

}

