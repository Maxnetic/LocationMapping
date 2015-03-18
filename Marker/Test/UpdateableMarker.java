import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class UpdateableMarker extends SimplePointMarker {

  int size = 20;
  int currentsize = 20;
  
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
}

