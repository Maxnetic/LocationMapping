import locationmapping.*;
import de.fhpotsdam.unfolding.*;


/**
* In dem Beispiel werden zwei Filterobjekt erzeugt, die zum einen Aufethaltsorte am Wochenende filtern
* und zum anderen Aufenthaltsorte Montag und Dienstag filtern
*
*/

void setup(){
  StaticMapper mapper = new StaticMapper(this);
  mapper.init();
    
  TrackpointList all = mapper.importData("../../data/personX.csv");
    
  DateTimeFilter wochenende = new DateTimeFilter();
  DateTimeFilter montagDienstag = new DateTimeFilter();
  
  //Wochenendefilter wird gesetzt
  wochenende.setWeekDays("samstag, sonntag");
  wochenende.setStartTime("20:00").setEndTime("21:00");
  
  //Filter für Montag und Dienstag wird gesetzt
  montagDienstag.setWeekDays("montag, dienstag");
  montagDienstag.setStartTime("08:00").setEndTime("12:00");
  
  //Anwenden des Wochenendfilters
  TrackpointList wochenendetpl;
  wochenendetpl = wochenende.apply(all);
  
  //Anwenden des MontagDienstag Filters
  TrackpointList montagDienstagtpl;
  montagDienstagtpl = montagDienstag.apply(all);
    
  //Die Marker werden der Karte hinzugefügt  
  for ( Trackpoint tp : wochenendetpl) {
    PointMarker marker = new PointMarker(tp);
    marker.setSize(10).setColor(Const.RED);
    mapper.addMarker(marker);
  } 
  
    
  //Die Marker werden der Karte hinzugefügt 
  for ( Trackpoint tp : montagDienstagtpl) {
    PointMarker marker = new PointMarker(tp);
    marker.setSize(10).setColor(Const.BLUE);
    mapper.addMarker(marker);
  } 
}
  
void draw(){

}  
  
  
