import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


DynamicMapper mapper = new DynamicMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  for ( Trackpoint tp : all ) {
    if (tp.hasNext()) {
      TrackMarker track = new Trackmarker(tp, tp.next());
      //wohnort.setLabel("möglicher Wohnort");
      //wohnort.setStyle("Labeled");
      mapper.addMarker(track);
    }
  }
    
    
      
}

void draw() {
      
}

