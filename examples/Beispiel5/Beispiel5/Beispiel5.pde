import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this); //Karte bleibt schwarz

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
 
   colorMode(HSB,360,100,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList maltetpl;
  maltetpl = mapper.importData("malte_spitz.csv");
  
  Filter serviceFilterSMS = new Filter();
  serviceFilterSMS.setService("SMS");
  TrackpointList serviceSMS;
  serviceSMS = serviceFilterSMS.apply(maltetpl);
  
  Filter serviceFilterTelefonie = new Filter();
  serviceFilterTelefonie.setService("Telefonie");
  TrackpointList serviceTelefonie;
  serviceTelefonie = serviceFilterTelefonie.apply(maltetpl);
  
  Filter serviceFilterInternet = new Filter();
  serviceFilterInternet.setService("Internet");
  TrackpointList serviceInternet;
  serviceInternet = serviceFilterInternet.apply(maltetpl);
  
  Filter wohnortfilter = new Filter();
  wohnortfilter.setStarttime("02:00");
  wohnortfilter.setEndtime("05:00");
  wohnortfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(maltetpl);
  
  
  
  for ( Trackpoint tp : serviceSMS) {
      MarkerSMS marker = new MarkerSMS(tp);
      marker.setSize(10);
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
    wohnort.setColor("rot");
    mapper.addMarker(wohnort);
  }

}
