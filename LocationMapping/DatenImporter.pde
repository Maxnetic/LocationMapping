class DatenImporter {
    /**
    * Mindestabstand zwischen zwei einzulesenden Zeitpunkten in Sekunden (default = "60")
    */
    private long minTimeDistance = 60;
    /**
    * Maximale Anzahl einzulesender Datenpunkte
    */
    private int maxImportSize = 50000;
    /**
    * Genauigkeit in der Ortskoordinaten eingelesen werden sollen in Grad (default = "0.0001")
    */
    private double accuracy = 0.0001;

    /**
    * Setzt maximale Anzahl der einzulesenden Datenpunkte
    *
    * @param maxImportSize [int]: maximale Anzahl einzulesender Datenpunkte
    */
    public void setMaxImportSize(int maxImportSize){
        this.maxImportSize = maxImportSize;
    }

    /**
    * Setzt Mindestzeitabstand zwischen zwei Zeitpunkten fest
    *
    * @param minTimeDistance [long]: mindestabstand zwischen zwei Zeitpunkten in Sekunden
    */
    public void setMinTimeDistance(long minTimeDistance){
        this.minTimeDistance = minTimeDistance;
    }

    /**
    * Setzt Genauigkeit für Datenimporter
    *
    * @param accuracy [double]: Zahl zu derem Vielfachen gerundet wird in Grad
    */
    public void setAccuracy(double accuracy){
        this.accuracy = accuracy;
    }

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
    * Importiert von Google exportierte JSON Daten
    *
    * @param filename [String]: Name der zu importierenden Datei im data Ordner
    * @param id [int]: (default = 1) Identifikationsnummer der Datensatzes
    * @retrun [Trackpointlist]: Trackpointlist mit Datenpunkten
    */
    public TrackpointList loadGoogleJSON(String filename){
        return loadGoogleJSON(filename, 1);
    }
    public TrackpointList loadGoogleJSON(String filename, int id){
        TrackpointList trackpointList = new TrackpointList();

        // Extrahiere Array aus Daten
        JSONObject wrapperObject = loadJSONObject(filename);
        JSONArray data = wrapperObject.getJSONArray("locations");

        // speichert letzten Zeitstempel für Überprüfung der minTimeDistance
        long lastTimestamp = 0;

        // Laufe über Array mit Daten
        int counter = 0;
        for ( int i=0; i<data.size(); i++ ) {
            JSONObject row = data.getJSONObject(i);

            // Brich Import ab, falls maxImportSize überschritten
            if ( counter > this.maxImportSize )
                break;

            // Zeitstempel der Zeile
            long timestamp = Long.parseLong(row.getString("timestampMs"));

            // ignoriere Zeile, falls Zeitunterschied kleiner als minTimeDistance
            if ( Math.abs(lastTimestamp - timestamp) > this.minTimeDistance*1000 ){
                lastTimestamp = timestamp;
                counter++;

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

    /**
    * Importiert
    *
    * @param filename [String]: Name der zu importierenden Datei im data Ordner
    * @param id [int]: (default = 7) Identifikationsnummer der Datensatzes
    * @retrun [Trackpointlist]: Trackpointlist mit Datenpunkten
    */
    public TrackpointList loadSpreadsheet(String filename){
        return loadSpreadsheet(filename, 7);
    }
    public TrackpointList loadSpreadsheet(String filename, int id){
        TrackpointList trackpointList = new TrackpointList();

        // Extrahiere Array aus Daten
        Table data = loadTable(filename, "header");

        // speichert letzten Zeitstempel für Überprüfung der minTimeDistance
        Timestamp lastTimestamp = new Timestamp(0);

        // Laufe über Array mit Daten
        int counter = 0;
        for ( TableRow row : data.rows() ) {

            // Brich Import ab, falls maxImportSize überschritten
            if ( counter > this.maxImportSize )
                break;

            // Zeitstempel der Zeile
            Timestamp timestamp = new Timestamp(0);
            try {
                timestamp = parseDateTimeString(row.getString("DateTime"));
            } catch(IllegalArgumentException e){
                timestamp = parseTimestampString(row.getString("Timestamp"));
            }

            // ignoriere Zeile, falls Zeitunterschied kleiner als minTimeDistance
            if ( Math.abs(lastTimestamp.getTime() - timestamp.getTime()) > this.minTimeDistance*1000 ){
                lastTimestamp = timestamp;
                counter++;

                // Breitengrad und Längengrad der Zeile
                float latitude = row.getFloat("Latitude");
                float longitude = row.getFloat("Longitude");

                // Service der Zeile
                String service = "";
                try {
                    service = row.getString("Service");
                } catch(IllegalArgumentException e) {}


                // erstelle Trackpoint und füge ihn zu Liste hinzu
                //Location location = new Location(round(latitude, this.accuracy), round(longitude, this.accuracy));
                Location location = new Location(latitude, longitude);
                Trackpoint trackpoint  = new Trackpoint(timestamp, location, id, service);
                trackpointList.add(trackpoint);
                System.out.println(counter + ": " + trackpoint);
            }
        }
        return trackpointList;
    }

    Timestamp parseDateTimeString(String dateTimeString){
        int month =  Integer.parseInt(dateTimeString.substring(0,2));
        int day =  Integer.parseInt(dateTimeString.substring(3,5));
        int year =  Integer.parseInt(dateTimeString.substring(6,10));
        int hour = Integer.parseInt(dateTimeString.substring(11,13));
        int minute = Integer.parseInt(dateTimeString.substring(14,16));
        int second = Integer.parseInt(dateTimeString.substring(17,19));
        System.out.println(year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second);
        return new Timestamp(year-1900, month, day, hour, minute, second, 0);
    }

    Timestamp parseTimestampString(String timestampString){
        long timestamp = Long.parseLong(timestampString.substring(0, 1) + timestampString.substring(2, 13));
        return new Timestamp(timestamp);
    }

}
