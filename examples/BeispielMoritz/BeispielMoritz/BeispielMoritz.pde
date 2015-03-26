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
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Iterator<Trackpoint> iter;
  iter =   all.iterator();
  
  while (iter.hasNext()){
    Trackpoint end = iter.next();
    //System.out.println("1");
    if (start != null) {
      i++;
      System.out.println(i);
      TrackMarker track = new TrackMarker(start, end);
      //wohnort.setLabel("möglicher Wohnort");
      //wohnort.setStyle("Labeled");
      mapper.addMarker(track);
    }
    start = end;
    }
  }
    
    


void draw() {
      
}

