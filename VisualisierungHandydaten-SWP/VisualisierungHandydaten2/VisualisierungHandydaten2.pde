/**
 * Test Visualisierung Handydaten
 * 
 * 16.3. 13:14 @dominik/max: added resizable screen feature
 * 16.3. 13:15 @dominik/max: working on day filter in Filter.java
 */

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
//import de.fhpotsdam.unfolding.providers.Microsoft;
import java.util.*;
import java.text.*; 


UnfoldingMap map; 
TrackpointList trackpoints;
int currentTrackpoint = 0;
String[] trackdata;
TrackpointList gefilterteTrackpoints;

void setup() {
  size(1000, 600);
  if(frame != null){
    frame.setResizable(true);
  }
  smooth();

  // map = new UnfoldingMap(this, "map"); // default based on OSM(?)
  map = new UnfoldingMap(this, new Microsoft.RoadProvider());
  // map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
  map.setTweening(true); // richtiges smooth Movement
  
  map.zoomAndPanTo(new Location(52.5f, 13.4f), 14); // Ort und Zoomlevel Init
  MapUtils.createDefaultEventDispatcher(this, map); //für StandardInteraktion

  // lade Daten von MalteSpitz
  DatenImport im= new DatenImport();
  trackpoints = im.ladeStandardCSV("Daten_Malte_Spitz.csv");
  
  
  // filtern und reduzieren
  //trackpoints = this.filtereWohnort(trackpoints); // überschreibt trackpoints mit den gefilterten trackpoints für Wohnort
  
  //trackpoints = this.filtere
  
  // zeichneMarker
  addAllMarker(); //fügt alle marker hinzu (Standardmarker in gau)

}

void draw() {
  map.draw();
  
   if (frameCount % 10 == 0) {
    Trackpoint curr =  trackpoints.get(currentTrackpoint);
    addMarker(curr); // zeichne aktuellen Marker in rot
    map.panTo(curr.getLocation());
    if (currentTrackpoint >= 999) {
      currentTrackpoint = 100;
    }
    currentTrackpoint++;
  }
}

  
  // Add all Simplemarkers to the map
  void addAllMarker() {
    // Create point markers for locations
    Location loc;
    for (int i=0; i<100; i++) {          // i < x, wobei x Anzahl der vorher angezeigten Markern in Grau entspricht
      loc=trackpoints.get(i).getLocation();
      SimplePointMarker tmp = new SimplePointMarker(loc);
       //tmp.setColor(color(255, 0, 0, 100));
      map.addMarker(tmp);
    }
  }
  
   // Add markers to the map
  void addMarker(Trackpoint curr) {
    // Create point markers for locations
    SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
    tmp.setColor(color(255, 0, 0, 100));
    map.addMarker(tmp);
  }
  
  // filtere
  TrackpointList filtereWohnort(TrackpointList trackpoints){
    // nur nächtliche trackpoint
    Filter meinFilter = new Filter();
    meinFilter.startHour=2;
    meinFilter.endHour=4;
    // jetzt müsste noch weiterer Filter kommen, der nur die häufigste Location rausfiltert.
    // meinFilter.giveTop(10); // würde die 10 häufigsten Locations rausfiltern
    TrackpointList gefilterteTrackpoints =  meinFilter.filter(trackpoints); // filter wendet den Filter an.
    // Test des Filters
    System.out.println(trackpoints.size());
    System.out.println(gefilterteTrackpoints.size());
    return gefilterteTrackpoints;
  }
  
  //TrackpointList filtereDatum(TrackpointList trackpoints){
    
    
 // }
  
  
