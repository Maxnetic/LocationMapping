import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class UpdateableMarker extends SimplePointMarker {
  /*
  * Wir erstellen hier die Variablen, die wir für das updaten brauchen
  * Diese werden an unsere Unterklassen vererbt und in deren draw-Methoden
  */
  int size = 20;
  
  //Für das Ändern der Farben
  int rot = 0;
  int gelb = 0;
  int blau = 0;
  
  public UpdateableMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public UpdateableMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }*/
  
  //update für die Größe des Markers
  public void updateSize(int size){
    this.size = size;
  }
  
  //setzt die Sichtbarkeitseigenschaften
  public void updateHidden(boolean hidden){
    this.setHidden(hidden);
  }
  
  //update für die Farbe, in der drawmethode wird damit teilweise nicht gefüllt
  public void updateColor(int rot, int gelb, int blau){
    this.rot = rot;
    this.gelb = gelb;
    this.blau = blau;
  }

}

