import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/** In diesem Beispiel werden aus zwei Datensätzen die Orte angezeigt, die von beiden
 *  besucht wurden.
 */
 
 
void setup() {
  StaticMapper mapper = new StaticMapper(this);
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList list1;
  list1 = mapper.importData("malte_spitz.csv");
  TrackpointList list2;
  list2 = mapper.importData("max_mittel.json");
    
  // Der erste Datensatz wird gefiltert, um weniger Vergleiche tätigen zu müssen
  // Aufgrund des Zeitersparnisses wird dieses Vorgehen stark empfohlen.
  DateTimeFilter vorFilter = new DateTimeFilter();
  vorFilter.setStartTime("08:30");
  vorFilter.setEndTime("10:30");
  vorFilter.setStartDate("2009:09:01");
  vorFilter.setEndDate("2009:09:30");
  vorFilter.setWeekDays("mo-mi ,fr"); 
  vorFilter.setWeekDays("montag-mittwoch,freitag"); 
  LocationFilter schnittmengenFilter = new LocationFilter();
  TrackpointList vorgefiltert;
  
  // Hier findet der eigentliche Vergleich der Trackpoints statt.
  vorgefiltert = vorFilter.apply(list1);
  vorgefiltert = schnittmengenFilter.compareLocation(vorgefiltert, list2, 1);
  
  
  //die gefundenen Orte werden eingezeichnet
  for ( Trackpoint tp : vorgefiltert ) {
    StandardMarker schnittpunkt = new StandardMarker(tp);
    mapper.addMarker(schnittpunkt);
  }
    
    
      
}

void draw() {
      
}

