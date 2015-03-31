import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;

/**
 * In diesem Beispiel wird gezeigt, wie man nur zusammenhängende Routen
 * anzeigen lassen kann.
 */

Trackpoint start = null;  
int i = 0;

void setup() {
  StaticMapper mapper = new StaticMapper(this); //DynamicMapper funktioniert nicht
  hint(ENABLE_RETINA_PIXELS);
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("../../data/personX.csv");
    
  Iterator<Trackpoint> iter;
  iter =   all.iterator();
  
  TrackpointList bla = new TrackpointList();
  int id = 2;
  int index = 0;
  int[] colors = {0, 25, 50, 75, 100, 125, 150, 175, 200, 225, 250};
  
  
  while (iter.hasNext()){
    Trackpoint end = iter.next();
    
    if ((start != null) && (end != null)){

      if ( Math.abs(start.timeDistanceTo(end) ) < 600  && Math.abs(start.locationDistanceTo(end)) > 0.1   ){
        start.setId(id);
        end.setId(id);
        SimpleLinesMarker track = new SimpleLinesMarker(start.getLocation(), end.getLocation());
        if (index == 11) {
            index = 0;
        }
        track.setColor(colors[index]);
        index++;
        mapper.addMarker(track);
      } else {
        if (start.getId() != 1){
          id++;
        }
      }
    }
    start = end;
    System.out.print(end.getId() + ", ");
  }
}    
  
//Screenshot-Funktion?
//GPX Import?
//Extend SimpleLinesMarker to get more functions

void draw() {
  
}

