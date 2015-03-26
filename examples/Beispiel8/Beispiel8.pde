import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Filter lieblingsortfilter = new Filter();
  lieblingsortfilter.setMinFrequency(500);
  TrackpointList lieblingsorte;
  lieblingsorte = lieblingsortfilter.apply(all);

    
  for ( Trackpoint tp : lieblingsorte ) {
    StandardMarker lieblingsort = new StandardMarker(tp);
    lieblingsort.setLabel("      "+lieblingsorte.getFrequency(tp.getLocation()));
    lieblingsort.setStyle("Labeled");
    mapper.addMarker(lieblingsort);
  }
    
    
      
}

void draw() {
      
}

