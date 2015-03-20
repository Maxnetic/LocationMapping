class Importer {
    private TrackpointList trackpointList = new TrackpointList();
    private long minTimeDistance = 60;
    private double accuracy = 0.0001;

    /**
    * Rundet Zahl auf ganzzahliges vielfaches eines Inkrements
    *
    * @param number [double]: zu rundende Zahl
    * @param increment [double]: Inkrement zu dessen vielfachem gerundet werden soll
    */
    double round(double number, double increment){
        return ((double) Math.round(number/increment))*increment;
    }

    /**
    * Rundet Zahl auf ganzzahliges vielfaches eines Inkrements
    *
    * @param number [double]: zu rundende Zahl
    * @param increment [double]: Inkrement zu dessen vielfachem gerundet werden soll
    * @retrun [Trackpointlist]: Trackpointlist mit Datenpunkten
    */
    public TrackpointList loadGoogleJSON(String filename){
        return loadGoogleJSON(filename, 1);
    }
    public TrackpointList loadGoogleJSON(String filename, int id){
        // Extrahiere Array aus Daten
        JSONObject wrapperObject = loadJSONObject(filename);
        JSONArray data = wrapperObject.getJSONArray("locations");

        // speichert letzten Zeitstempel für Überprüfung der minTimeDistance
        long lastTimestamp = 0;

        // Laufe über Array mit Daten
        for ( int i=0; i<data.size(); i++ ) {
            JSONObject row = data.getJSONObject(i);

            // Zeitstempel der Zeile
            long timestamp = Long.parseLong(row.getString("timestampMs"));

            // ignoriere Zeile, falls Zeitunterschied kleiner als minTimeDistance
            if ( Math.abs(lastTimestamp - timestamp) > this.minTimeDistance*1000 ){
                lastTimestamp = timestamp;

                // berechne float mit Längengrad der Zeile
                String longitudeString = Long.toString(row.getLong("longitudeE7"));
                longitudeString = longitudeString.substring(0, 2) + "." + longitudeString.substring(2);
                float longitude = Float.parseFloat(longitudeString);

                // berechne float mit Breitengrad der Zeile
                String latitudeString = Long.toString(row.getLong("latitudeE7"));
                latitudeString = latitudeString.substring(0, 2) + "." + latitudeString.substring(2);
                float latitude = Float.parseFloat(latitudeString);

                // erstelle Trackpoint und füge ihn zu Liste hinzu
                Location location = new Location(round(latitude, this.accuracy), round(longitude, this.accuracy));
                Timestamp times = new Timestamp(timestamp);
                Trackpoint trackpoint  = new Trackpoint(times, location, id, "GPS");
                trackpointList.add(trackpoint);
                System.out.println("added " + trackpoint);
            }// end if
        }// end for
        return trackpointList;
    }

}
