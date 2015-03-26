import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;

StaticMapper mapper = new StaticMapper(this); //DynamicMapper funktioniert nicht
Trackpoint start = null;  
int i = 0;


void setup() {
  hint(ENABLE_RETINA_PIXELS);
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Iterator<Trackpoint> iter;
  iter =   all.iterator();
  
  TrackpointList bla = new TrackpointList();
  int id = 1;
  while (iter.hasNext()){
    Trackpoint end = iter.next();
    
    if ((start != null) && (end != null)){

      if ( Math.abs(start.timeDistanceTo(end) ) < 300  && Math.abs(start.locationDistanceTo(end)) > 5   ){
        start.setId(id);
        end.setId(id);
        SimpleLinesMarker track = new SimpleLinesMarker(start.getLocation(), end.getLocation());
        track.setColor(id);
        mapper.addMarker(track);
      } else {
        if (start.getId() != 0){
          id++;
        }
      }
    }
    start = end;
    System.out.println(end.getId());
  }
}    
  
//Screenshot-Funktion?
//GPX Import?
//Extend SimpleLinesMarker to get more functions

void draw() {
  
}

