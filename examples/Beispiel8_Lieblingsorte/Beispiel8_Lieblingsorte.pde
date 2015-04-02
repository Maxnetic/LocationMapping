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
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("../../data/personX.csv");
    
  LocationFilter lieblingsortfilter = new LocationFilter();
  
  //Mindesthäufigkeit der Besuche wird festgelegt
  lieblingsortfilter.setMinFrequency(500);
  TrackpointList lieblingsorte;
  lieblingsorte = lieblingsortfilter.apply(all);

   // Trackpoints werden eingezeichnet
  for ( Trackpoint tp : lieblingsorte ) {
    PointMarker lieblingsort = new PointMarker(tp);
    lieblingsort.setLabel(String.format("%d",lieblingsorte.getFrequency(tp)));
    mapper.addMarker(lieblingsort);
  }     
}

void draw() {}
