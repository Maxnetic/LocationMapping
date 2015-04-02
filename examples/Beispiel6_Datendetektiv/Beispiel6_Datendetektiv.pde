import locationmapping.*;
import de.fhpotsdam.unfolding.*;

/**
 * In diesem Beispiel werden sowohl der Wohnort, als auch
 * mögliche Arbeitsplätze der getrackten Person angezeigt.
 */
 
void setup(){
  StaticMapper mapper = new StaticMapper(this);
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("../../data/personX.csv");
  
  //Wohnortfilter
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  for ( Trackpoint tp : moeglichewohnorte ) {
    PointMarker wohnort = new PointMarker(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setColor(Const.RED);
    mapper.addMarker(wohnort);
  }
    
  //Arbeitsplatzfilter
  DateTimeFilter arbeitsfilter = new DateTimeFilter();
  arbeitsfilter.setStartTime("10:00");
  arbeitsfilter.setEndTime("16:00");
  arbeitsfilter.setWeekDays("montag-freitag");
  frequencyfilter.setMinFrequency(500);
  TrackpointList moeglicherArbeitsplatz;
  moeglicherArbeitsplatz = arbeitsfilter.apply(all);
  moeglicherArbeitsplatz = frequencyfilter.apply(moeglicherArbeitsplatz);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    PointMarker arbeit = new PointMarker(tp);
    arbeit.setLabel("möglicher Arbeitsplatz");
    arbeit.setColor(Const.YELLOW);
    mapper.addMarker(arbeit);
  }
  
  //Nachtsfilter
  DateTimeFilter nachtsfilter = new DateTimeFilter();
  nachtsfilter.setStartTime("00:00");
  nachtsfilter.setEndTime("06:00");
  TrackpointList nachts;
  nachts = nachtsfilter.apply(all);
  nachts = frequencyfilter.apply(nachts);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    PointMarker marker = new PointMarker(tp);
    marker.setColor(Const.BLUE);
    mapper.addMarker(marker);
  }


}

void draw(){
  
}
