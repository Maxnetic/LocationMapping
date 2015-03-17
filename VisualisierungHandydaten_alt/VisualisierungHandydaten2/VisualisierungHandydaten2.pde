/**
 * Test Visualisierung Handydaten
 * 
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
int farbe = 0; //Farbcodierung: 0 Grau 1: Rot 2: Blau


void setup() {
  size(1000, 600);
  smooth();

  // map = new UnfoldingMap(this, "map"); // default based on OSM(?)
  map = new UnfoldingMap(this, new Microsoft.RoadProvider());
  // map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
  map.setTweening(true);
  
  map.zoomAndPanTo(new Location(52.5f, 13.4f), 14); // Ort und Zoomlevel Init
  MapUtils.createDefaultEventDispatcher(this, map); //für StandardInteraktion

  // lade Daten von MalteSpitz
  DatenImport im= new DatenImport();
  trackpoints = im.ladeStandardCSV("Daten_Malte_Spitz.csv");
  
  
  // filtern und reduzieren
  trackpoints= this.filtereWohnort(trackpoints); // überschreibt trackpoints mit den gefilterten trackpoints
  
  //zeichneMarker
  farbe = 1; // Farbcodierung s.h. oben
  addAllMarker(farbe); //fügt alle marker hinzu (Standardmarker in gau)
  
  trackpoints = im.ladeStandardCSV("Daten_Malte_Spitz.csv");
  filtereAnruf(trackpoints);
  farbe = 2;
  addAllMarker(farbe);
  
}

void draw() {
  map.draw();
  
   if (frameCount % 10 == 0) {
    Trackpoint curr =  trackpoints.get(currentTrackpoint);
    addMarker(curr, farbe); // zeichne aktuellen Marker in rot
    map.panTo(curr.getLocation());
    if (currentTrackpoint >= 999) {
      currentTrackpoint = 100;
    }
    currentTrackpoint++;
  }
}

  
  // Add all Simplemarkers to the map
  void addAllMarker(int farbe) {
    // Create point markers for locations
    Location loc;
    for (int i=0; i<1000; i++) {
      loc=trackpoints.get(i).getLocation();
      SimplePointMarker tmp = new SimplePointMarker(loc);
       //tmp.setColor(color(255, 0, 0, 100));
      map.addMarker(tmp);
    }
  }
  
   // Add markers to the map
  void addMarker(Trackpoint curr, int farbe) {
    // Create point markers for locations
    SimplePointMarker tmp = new SimplePointMarker(curr.getLocation());
    //tmp.setColor(color(255, 0, 0, 100));
    switch(farbe){
      case 0: tmp.setColor(color(50,50,50,50));
      case 1: tmp.setColor(color(255,0,0,100));
      case 2: tmp.setColor(color(0,0,255,100));

    }
    map.addMarker(tmp);
  }
  
  // filtere
  TrackpointList filtereWohnort(TrackpointList trackpoints){
    // nur nächtliche trackpoint
    Filter meinFilter = new Filter();
    meinFilter.startHour=2;
    meinFilter.endHour=4;
    // jetzt müsste noch witerer Filter kommen, der nur die häufigste Location rausfiltert.
    // meinFilter.giveTop(10); // würde die 10 häufigsten Locations rausfiltern
    TrackpointList gefilterteTrackpoints =  meinFilter.filterZeit(trackpoints); // filter wendet den Filter an.
    // Test des Filters
    System.out.println(trackpoints.size());
    System.out.println(gefilterteTrackpoints.size());
    return gefilterteTrackpoints;
  }
  
  // Filter für Anruf 
  TrackpointList filtereAnruf(TrackpointList trackpoints){
    Filter serviceFilter = new Filter();
    serviceFilter.service = "Telefonie";
    TrackpointList anrufTrackpoints = serviceFilter.filterService(trackpoints); // Hier wird die Liste gefiltert
    
    // Überprüfung der Listengrößen
    System.out.println(trackpoints.size());
    System.out.println(anrufTrackpoints.size());
    return (gefilterteTrackpoints);
  }
  
  
