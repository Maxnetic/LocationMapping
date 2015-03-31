package locationmapping;

import processing.core.PApplet;
import processing.data.*;

/**
 * Die Klasse DataExporter ist dafuer zustaendig Daten aus
 * TrackpointLists in .csv-Dateien zu exportieren.
 * Die .csv-Datei beinhaltet dann die Informationen: Location, Timestamp, Service.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class DataExporter {
    /**
    * laufende Processing Applet Instanz
    */
    PApplet app;
    /**
    * Maximale Anzahl der zu exportierenden Daten, -1 = alle
    */
    private int maxExportSize = -1;

    /**
    * Erzeugt neues DataExporter Objekt im Applet
    *
    * @param app laufendes Processing Applet in das importiert werden soll
    */
    public DataExporter(PApplet app) {
        this.app = app;
    }

    /**
    * Setzt maximale Anzahl der zu exportierenden Datenpunkte
    *
    * @param maxExportSize maximale Anzahl einzulesender Datenpunkte
    */
    public void setMaxExportSize(int maxExportSize){
        this.maxExportSize = maxExportSize;
    }

 	/**
	 * Schreibt die csv-Datei.
	 *
	 * @param trackpointlist zu exportierende trackpointlist
	 * @param filename Name der csv-Datei
	 * @return Wahrheitswert, ob Export erfolgreich
	 */
    public boolean write(TrackpointList trackpointlist, String filename){
        try {
            //Anlegen der Tabelle
            Table table = new Table();
            table.addColumn("Timestamp");
            table.addColumn("Longitude");
            table.addColumn("Latitude");
            table.addColumn("Service");

            //Durchgehen der TrackpointList und überschreiben
            int counter = 0;
            for ( Trackpoint trackpoint : trackpointlist ){
                if ( counter > this.maxExportSize && this.maxExportSize > 0 )
                    break;
                TableRow newRow = table.addRow();
                newRow.setLong("Timestamp", trackpoint.getTimestamp());
                newRow.setFloat("Longitude", trackpoint.getLongitude());
                newRow.setFloat("Latitude", trackpoint.getLatitude());
                newRow.setString("Service", trackpoint.getService());
                counter++;
            }
            app.saveTable(table, "data/" + filename + ".csv");
            return true;
        } catch(Exception e) {
            return false;
        }
    }


}

