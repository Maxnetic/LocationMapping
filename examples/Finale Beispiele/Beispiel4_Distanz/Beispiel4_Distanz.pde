import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
  StaticMapper mapper = new StaticMapper(this);
  colorMode(HSB, 360, 100 ,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList maltetpl;
  TrackpointList maxtpl;
  //Import
  maltetpl = mapper.importData("malte_spitz.csv");
  int coveredDistance = 0;
  Trackpoint lastTp = null;
  
  for(Trackpoint tp: maltetpl){ 
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(20);
      marker.setColor("rot");
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
