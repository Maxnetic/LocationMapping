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
   StaticMapper mapper = new StaticMapper(this); //Karte bleibt schwarz
   colorMode(HSB,360,100,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList maltetpl;
  xtpl = mapper.importData("../../data/personX.csv");
  LocationFilter serviceFilterSMS = new LocationFilter();
  serviceFilterSMS.setService("SMS");
  TrackpointList serviceSMS;
  serviceSMS = serviceFilterSMS.apply(xtpl);
  
  xtpl = mapper.importData("malte_spitz.csv");
  LocationFilter serviceFilterTelefonie = new LocationFilter();
  serviceFilterTelefonie.setService("Telefonie");
  TrackpointList serviceTelefonie;
  serviceTelefonie = serviceFilterTelefonie.apply(xtpl);
  
  LocationFilter serviceFilterInternet = new LocationFilter();
  serviceFilterInternet.setService("Internet");
  TrackpointList serviceInternet;
  serviceInternet = serviceFilterInternet.apply(xtpl);
  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(100);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(xtpl);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  
  
  for ( Trackpoint tp : serviceSMS) {
      MarkerSMS marker = new MarkerSMS(tp);
      marker.setSize(10);
      marker.setColor("rot");
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : serviceTelefonie) {
      MarkerAnruf marker = new MarkerAnruf(tp);
      marker.setSize(10);
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : serviceInternet) {
      MarkerInternet marker = new MarkerInternet(tp);
      marker.setSize(10);
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : moeglichewohnorte ) {
    MarkerLabeled wohnort = new MarkerLabeled(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setSize(30);
    wohnort.setColor("grün");
    mapper.addMarker(wohnort);
  }

}

void draw(){
  
}
