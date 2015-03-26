import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;

StaticMapper mapper = new StaticMapper(this);
Trackpoint start = null;  
int i = 0;


void setup() {
  hint(ENABLE_RETINA_PIXELS);
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Iterator<Trackpoint> iter;
  iter =   all.iterator();
  
  while (iter.hasNext()){
    
    Trackpoint end = iter.next();
    if ((start != null) && (end != null)){
      SimpleLinesMarker track = new SimpleLinesMarker(start.getLocation(), end.getLocation());
      track.setColor(0);
      mapper.addMarker(track);
    }
    start = end;
    
  }
}    
  
//Screenshot-Funktion?
//GPX Import?
//Extend SimpleLinesMarker to get more functions

void draw() {
  
}

