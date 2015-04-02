Tutorial for creating an dynamic offline map for unfolding maps.
German Version below.

Caution:
The final rendering requires an enormous computing power.
The example is rendered with following resources:
- 16 GB RAM
- AMD 3,5Ghz (8 Cores)
- win7 (64Bit)

It tooks 34 min to render Berlinstreets in an optimized version.
The Databases were edited (indexed) to reduce the computational costs.
The tutorial how to arrange the database:
https://developmentseed.org/blog/2011/mar/29/speed-optimizations-tilemill-shapefile-indexes/

Without the optimized database, it would have taken 3 days to render just the streets of Berlin.
The computational costs depending on the displayed information in a map.
As more information are displayed e.g. buildings or landusage, as more time is needed to render the map.

-----------------------------------------------required tools and data--------------------------------
- Tilemill 
	https://www.mapbox.com/tilemill/
- Map shaders e.g. roads, buildings and rivers and infoamtion about the places in databases
  which are going to be depicted in the Map.
	possible sources:
	- http://metro.teczno.com
	- http://www.naturalearthdata.com/
	- http://download.geofabrik.de/europe/germany.html
- a jdbc driver for JAVA/processing to hande the DB of the map
	possible source:
	- https://bitbucket.org/xerial/sqlite-jdbc/

----------------------------------------------How to create an offline map----------------------------	
1.) create a new TilMill-project with a decent name
2.) Data for creating the Map e.g. Shader (.shp) and DB's (attached to the shader-files) 
    should be already present in ZIB-files or as stand alone files.
	
3.) create a new Layer and involve the data.
	3.1.) the downloaded ZIP-file includes shaders (****.shp) files and some other files.
		  The shader-files are pictures with DB's attached e.g. streetnamestables 
	
	adding layers in TileMill		
	3.2)  -> click at the bottom left on the layerbutton
		  -> click on  "Add Layer" to create a new Layer 
			 (the functionallity of the layers is very similar to image editing programms) 
		  -> type a decent name for the layer in the ID-Textbox 
		  -> choose the specific shader in Datasource e.g. berlin.osm-lines.shp
		  -> the insertion - encoding"LATIN1" - in the advanced-field 
			 converts every character to latin1. This might be useful 
			 to display Names in foreign languages
			 CAUTION: some characters can get lost in the coneversion
			 
	3.3)  click Save, so the layer with your specific shader will be created
	
	3.3.1)by clicking "save&style," the layer will be created and the basic elements will be displayed 
		  immediately in the map, so the matching Carto_CSS code will be added to the code automatically.

-------------------------------------------------Map-------------------------------------------------------------------		  
Bringing the dafault World-Map to a smaller format to
decrease the Data-amount and therefore the filesize. 
This us usefull for faster map-loading in the processing-programm.

4)	Settings: (the little wrench in the upper right corner) 
		If the current projekt gets a new name in settings,
		it will save the current project as a new one, under the new name.
		Otherwise the current project will be overwritten.
Example:
Berlin (Berlin_Lines.shp Layer already exists)
-> setting the frame around berlin in zoomfactor Z10
	-> edit the frame around your choosen centerpoint. 
	   (repeat until the framesize is as tight as possible around the area you want to display.
	    The frame is the outline of the final offline map.)
	-> set the centermarker on the point you want to center your map
	   (the zoomfactor is shown inside the marker)
	
	Example: Berlin	mit Zoomfactor 10
    Cetnter: 13.3923,52.5196,10
    Bounds: 13.0078,52.3085,13.8318,52.7280

Settings on the right side next to the framed map (still the setting under the wrench):	
-> set the zoomfactor to 10-18 (the final offline map will start in zoomfactor 10 and will end in zoomfactor 18) 

	CAUTION: the depth of the zoomfactor influences the final filesize of the offline map. (shown below the slider)
			(one zoomfactor downwads, into the details, will increase the filesize exponentially,
			 because one tile is going to be devided in four smaler tiles and so on and so forth)
			 -> this is a part of the dynamic zoom effect the offlime map 
			 
-> click save and the new project will be created in TilMill			 

Editing the map:      
5.) TileMill uses Carto-CSS. You might want to see Carto-css tutorials. 
	
Example: 	
	//displaying Berlins highways ONLY
	#berlin_lines [highway="motorway"], //uses the DB of the Shader "berlin_lines" to display the "motorways" only
	#berlin_lines [highway="motorway_link"]{
	//graphic settings for displaying the highways : 
	line-width:5;
	line-color: black;

-------------------------------------------Export------------------------------------------------------------------------
exporting the created offline map:

6.) The expotbutton: (upper right corner, next to the little wrench)
	-> Export the map as MBtile-file. With this filerformat, a DB will be attached to the offline map.
	   The attached DB will be include all the Data, even those which are not displayed e.g. streetfeatures, environment etc.
 
6.1)One more setting-window will pop up, in which you can check your final settings. (zoomfactor, frame etc.)  
7.) Click export and the offline map, with all your settings and designs, will be rendered.
CAUTION: this can take a long time. The rendering-time depends on your designs, 
         as more details have to be rendered, as more time it will take. 
8.) After the rendering finished, the  ****.mbtiles file have to be stored.


------------------------------------------integrate the map in unfolding maps-----------------------------------------------------------------------
The integration of the offline map into unfolding maps and the processing code.

9.) create a folder in which the ****.pde (processing-file) is placed
9.1)create two sub-folders, one named code and one named data in this folder

Example:
Folder/****.pde-file , code-folder, data-folder 

10.)  paste your offline map, the ****.mbtiles file, into the Data-folder 
10.1) Download the sqlitejdbc driver from https://bitbucket.org/xerial/sqlite-jdbc/ 
	  and paste it into the Code-folder
	  This driver makes the DB handling for JAVA/processing possible 	
	  
Example:
Folder/****.pde-file , code-folder/sqlitejdc-file, data-folder/****.mbtiles-file 

----------------------------------------in Processing---------------------------------------------------------------------
11.) Inserting the offline map in processing
EXample:

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;

UnfoldingMap map;

void setup() {
  size(800, 600, P2D);
  
  // insert the path where your map-file is placed
  String mbTilesString = sketchPath("data/Berlin_offline.mbtiles");

  map = new UnfoldingMap(this, new MBTilesMapProvider(mbTilesString));
  MapUtils.createDefaultEventDispatcher(this, map);
}

void draw() {
  background(240);
  map.draw();
}
------------------------------------------------German Version------------------------------------------------------------
Anleitung zum Erstellen einer eigenen dynamischen Offline Karte.
Hierzu wird immense Rechenleistung benötigt um die erstellte Karte
zu rendern. 
Getestet auf dem System: 
- 16GB RAM
- AMD 3,5GHz in 8 Kernen
- win7(64bit)


Unter diesem System hat es für das rendern von Berlins Straßen ganze 34min gedauert.
Dabei wurden die Datenbanken mit Indizes versehen um den Rechenaufwand zu reduzieren.
Eine Anleitung dazu:
https://developmentseed.org/blog/2011/mar/29/speed-optimizations-tilemill-shapefile-indexes/

Es wurden NUR Straßen dargestellt ohne weitere Anzeigen.
Ohne diese Optimierung/Indizierung hätte es 3 Tage gedauert eine Straßenkarte von Berlin ohne weitere Darstellungen
wie Straßennamen zu rendern.
Es ist zu beachten, dass weitere Darstellungen von z.B. Straßennamen, Gebäuden, Flüssen etc.
die Redering-Zeit exponentiell erhöht hätten. 
 


1.) Neues TileMill Projekt erstellen z.B. Berlin_offline
2.) Daten zum Erstellen der Karte z.B. Shader mit DB's herunterladen.
	Beispielquellen:
	- http://metro.teczno.com
	- http://www.naturalearthdata.com/
	- http://download.geofabrik.de/europe/germany.html
	
3.) einen Neuen Layer erstellen und die Daten einbinden.
	3.1.) in der heruntergeladenen Zip-Datei befinden sich u.a. Shader dateien (***.shp),
		  an denen i.d.R. Daten-Banken hängen welche z.B. Staßennamen etc. beinhalten
	
	Einbinden/Hinzufügen von Layern.		
	3.2)  In TileMill unten links auf den Layerbutton klicken und Add Layer klicken um einen Neuen Layer zu erstellen
		  -> in ID-Textbox einen geeigneten Namen für den Layer wählen
		     BSP: Berlin_lines 
		  -> Unter Datasource den entsprechenden Shader anwählen(BSP: den Heruntergeladenen berlin.osm-lines.shp)
		  -> Für nicht Europäische Karten, im Advanced-feld - encoding"LATIN1" - einfügen
			 (Damit werden alle Schriftzeichen auf LATIN1 codiert. 
			  ACHTUNG: Umlaute in Deutsch können verloren gehen)
			  
	3.3)  Klick auf Save und der Layer wird zur Karte hinzugefügt.
	
	3.3.1)Mit Klick auf save&style wird der Layer angewendet aber auch gleich ALLE daten wie z.B. Straßen dargestellt.
          Das kann viel Rechenleistung fordern und dementsrpechend lange dauern. 
		  Hier wird auch direkt der entsprechende CSS code automatisch ins Editorfeld hinzugefügt.

----------------------------------------Karte-------------------------------------------------------------------------		  
Die Default-Weltkarte auf ein gewünschtes Gebiet beschränken.
(Damit wird nur das eingeschränkte Gebiet und dessen DB geladen)
Die Offline-Karte hat dann die Größe des eingeschränkten Gebietes.

4)   unter Einstellungen: (der kleine Maulschüssel oben rechts)
     unbedingt neuen Namen geben, da damit nach dem Save-Vorgang ein neues Projekt erstellt wird,
	 welches nur die Skalierung lädt und nicht die gesamte Weltkarte.
	 Wird kein neuer Name angegeben, wird das Bestehende Projekt überschrieben.

BSP: Berlin (Berlin_Lines.shp ist als Layer vorhanden)
-> sklalieren auf berlin mit entspr. Zoomfaktor Z10
	-> Rahmen skalieren und zum gewünschten Punk zoomen. (wiederholen bis die gewünschte Größe der Karte erreicht ist)
	-> Centermarker mit Maus-click auf das Bewünschte Center setzten
	   (im Marker ist die aktuelle Zoomstufe angezeigt. Dieser dient nun als Kartenmittelpunkt)
	
	BSP: Berlin	mit Zoomstufe 10
    Cetnter: 13.3923,52.5196,10
    Bounds: 13.0078,52.3085,13.8318,52.7280

Einstellungen rechts neben der Karte:	
-> ZOOM einstellen auf 10-18 (spätere Offline Karte wird bei Tile-Größe Zoomstufe 10 geladen und reicht bis Zoomstufe 18)
   -> die Untere Zoomstufe legt fest wie weit maximal aus der offline Karte rausgezoomed werden kann
   -> die Obere Zoomstufe legt fest wie weit in die Karte reingezoomed werden kann. 
      i.d.R. reichen Zommstufen bis 18 um die Details einer Staße sehen zu können  

	ACHTUNG: die Tieferen Zoomstufe vergrößern die zu exportierende Karte (Dateigöße) Exponentiell,
			 da pro Zoomstufe jedes Tile in 4 kleine Tiles geteilt wird. 
			 -> das ist der dynamische Zoom-Effekt und die dynamische Skallierung 
			    die auch bei der Offlinekarte beibehalten werden sollen.
			    Ist keine dynamische Karte gewünscht, kann auch eine Karte als Bild in Unfolding Maps geladen werden
   
-> SAVE klicken und neues Projekt, mit oben angegebenem Namen, wird in TileMill erstellt
      
Die Karte optisch bearbeiten: 
5.) Code erstellen mit Carto-Css in TileMill (siehe Tutorials zu Carto-CSS ) 
	
BSP: 	
	//NUR Berlins Autobahnen anzeigen
	#berlin_lines [highway="motorway"], //greift aud DB des Shaders "berlin_lines" zu
	#berlin_lines [highway="motorway_link"]{
	//Abbildung in der Karte : 
	line-width:5;
	line-color: black;

-------------------------------------------Export------------------------------------------------------------------------
Exportieren der Erstellten Karte

6.)  Exportieren (Button oben rechts neben dem Maulschlüssel) Unbedingt als MBtiles exportieren damit Unfolding Maps
     die DB der erstellten Karte benutzen kann.
6.1) Fenster öffnet sich in dem nochaml alle Einstellungen geprüft werden können.
7.)  Export klicken. Nun wird die Karte mit allen Details und Zoomstufen gerendert (dies kann, je nach Rechenleistung, sehr lange dauern)
8.)  Nachdem der Export abgeschlossen ist, ist die ****.mbtiles Datei zu speichern


------------------------------------------Einbinden-----------------------------------------------------------------------
Die eigene offline-Karte in Unfolding Maps einbinden

9.) Einen Ordner Erstellen in dem die ****.pde (processing-datei) datei liegt.
9.1)Zwei weitere Unter-Ordner, code und data erstellen

BSP:
Ordner/****.pde-datei , code-ordner, data-ordner 

10.)  Die ****.mbtiles Datei in den Data-Ordner einfügen 
10.1) Den sqlitejdbc Treiber von https://bitbucket.org/xerial/sqlite-jdbc/ runterladen
	  und in den Code-Ordner einfügen 
	  Dieser Treiber sorgt für den Umgang der DB der Tiles in der Offline-Karte in JAVA

----------------------------------------in Processing---------------------------------------------------------------------
11.) In Processing-Code einbiden
BSP:

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;

UnfoldingMap map;

void setup() {
  size(800, 600, P2D);
  
  //Hier den Pfad angeben in dem die Karte liegt.
  String mbTilesString = sketchPath("data/Berlin_offline.mbtiles");

  map = new UnfoldingMap(this, new MBTilesMapProvider(mbTilesString));
  MapUtils.createDefaultEventDispatcher(this, map);
}

void draw() {
  background(240);
  map.draw();
}