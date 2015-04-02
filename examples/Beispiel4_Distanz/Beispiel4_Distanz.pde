import locationmapping.*;
import de.fhpotsdam.unfolding.*;

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
  StaticMapper mapper = new StaticMapper(this);
  mapper.init();
    
  TrackpointList maltetpl;
  TrackpointList maxtpl;
  //Import
  maltetpl = mapper.importData("../../data/personX.csv");
  int coveredDistance = 0;
  Trackpoint lastTp = null;
  
  for(Trackpoint tp: maltetpl){ 
      PointMarker marker = new PointMarker(tp);
      marker.setSize(20);
      mapper.addMarker(marker);
      
      if (lastTp != null){
          coveredDistance = coveredDistance + (int) ( 111324 * acos(sin(tp.getLatitude()) * sin(lastTp.getLatitude()) + cos(tp.getLatitude()) * cos(lastTp.getLatitude()) * cos(lastTp.getLongitude() - tp.getLongitude()))  );
      }
      lastTp = tp;
  }
  
  System.out.println("Zurückgelegte Distanz in Metern: " + coveredDistance);
}

void draw(){
  
}
