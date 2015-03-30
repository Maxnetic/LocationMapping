import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
<<<<<<< HEAD
  

void setup() {
  StaticMapper mapper = new StaticMapper(this);
=======



void setup() {
  StaticMapper mapper = new StaticMapper(this);
  hint(ENABLE_RETINA_PIXELS);
>>>>>>> 4dbe4c4dc5e8695bc2148840fa2eab21c6867f02
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList list1;
  list1 = mapper.importData("malte_spitz.csv");
  TrackpointList list2;
  list2 = mapper.importData("max_mittel.json");
    
  DateTimeFilter vorFilter = new DateTimeFilter();
  vorFilter.setStartTime("08:30");
  vorFilter.setEndTime("10:30");
  vorFilter.setStartDate("2009:09:01");
  vorFilter.setEndDate("2009:09:30");
<<<<<<< HEAD
  vorFilter.setWeekDays("mo-mi ,fr"); 
=======
  vorFilter.setWeekDays("montag-mittwoch,freitag"); 
>>>>>>> 4dbe4c4dc5e8695bc2148840fa2eab21c6867f02
  
  LocationFilter schnittmengenFilter = new LocationFilter();
  TrackpointList vorgefiltert;
  
  
  vorgefiltert = vorFilter.apply(list1);
  vorgefiltert = schnittmengenFilter.compareLocation(vorgefiltert, list2, 1);
  
  
    
  for ( Trackpoint tp : vorgefiltert ) {
    StandardMarker schnittpunkt = new StandardMarker(tp);
    mapper.addMarker(schnittpunkt);
  }
    
    
      
}

void draw() {
      
}

