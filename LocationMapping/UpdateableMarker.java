import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class UpdateableMarker extends SimplePointMarker {

  int size = 5;
  int currentsize = 20;
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
    this.setColor( color(100,100,100) );
  }*/
  
  
  public void updateSize(int size){
    this.size = size;
    this.currentsize = size;
  }
  
  public void updateHidden(boolean hidden){
    this.setHidden(hidden);
  }
  //updatet bisher nur äußeren Stroke
  //update visibility?
  //außen innen?
  public void updateColor(int rot, int gelb, int blau){
    this.rot = rot;
    this.gelb = gelb;
    this.blau = blau;
  }
  
  //updateShape
}

