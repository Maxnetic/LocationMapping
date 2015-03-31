import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/** Dieses Beispiel gibt die Orte an, die mindestens 500 Mal besucht wurden
 *  und schreibt die Gesamtzahl der Besuche als Label neben den Marker
 */

void setup() {
  StaticMapper mapper = new StaticMapper(this);
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  LocationFilter lieblingsortfilter = new LocationFilter();
  //Mindesthäufigkeit der Besuche wird festgelegt
  lieblingsortfilter.setMinFrequency(500);
  TrackpointList lieblingsorte;
  lieblingsorte = lieblingsortfilter.apply(all);

   // Trackpoints werden eingezeichnet
  for ( Trackpoint tp : lieblingsorte ) {
    MarkerLabeled lieblingsort = new MarkerLabeled(tp);
    lieblingsort.setLabel("      "+lieblingsorte.getFrequency(tp.getLocation()));
    mapper.addMarker(lieblingsort);
  }
    
    
      
}

void draw() {
      
}

