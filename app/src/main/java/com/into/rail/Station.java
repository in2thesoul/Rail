package com.into.rail;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by USER on 10/23/2016.
 */
public class Station implements Parcelable {
    private static final String TAG = "Station";
    private int stationId;
    private String initials;
    private String heName;
    private String enName;
    private boolean disabledAccess;
    private boolean isInterchange;
    private Location location;
    private boolean hasParking;
    private boolean isParkingChargeable;

    public Station() {
        super();
    }

    public Station(int stationId, String initials, String heName, String enName,
                   boolean disabledAccess, boolean isInterchange, Location location,
                   boolean hasParking, boolean isParkingChargeable) {
        super();
        this.stationId = stationId;
        this.initials = initials;
        this.heName = heName;
        this.enName = enName;
        this.disabledAccess = disabledAccess;
        this.isInterchange = isInterchange;
        this.location = location;
        this.hasParking = hasParking;
        this.isParkingChargeable = isParkingChargeable;
    }

    protected Station(Parcel in) {
        stationId = in.readInt();
        initials = in.readString();
        heName = in.readString();
        enName = in.readString();
        disabledAccess = in.readByte() != 0;
        isInterchange = in.readByte() != 0;
        double latitude = in.readDouble();
        double longitude = in.readDouble();
        location = new Location(enName);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        hasParking = in.readByte() != 0;
        isParkingChargeable = in.readByte() != 0;
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    private static boolean getJsonBoolean(int value) {
        return value == 2;
    }

    public static Station parseJson(JSONObject jsonObject) {
        try {
            int stationId = jsonObject.getInt("StationId");
            String initials = jsonObject.getString("DescriptionEn");
            String heName = jsonObject.getString("DescriptionHe");
            String enName = jsonObject.getString("EngName");
            boolean disabledAccess = getJsonBoolean(jsonObject.getInt("Handicap"));
            boolean isInterchange = getJsonBoolean(jsonObject.getInt("InterChangeStation"));
            boolean hasParking = getJsonBoolean(jsonObject.getInt("Parking"));
            boolean isParkingChargeable = getJsonBoolean(jsonObject.getInt("ParkingPay"));
            Location location = new Location(enName);
            if (!jsonObject.isNull("Location")) {
                location.setLatitude(jsonObject.getJSONObject("Location").getDouble("Latitude"));
                location.setLongitude(jsonObject.getJSONObject("Location").getDouble("Longitude"));
            }
            return new Station(stationId, initials, heName, enName, disabledAccess,
                    isInterchange, location, hasParking, isParkingChargeable);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            return new Station();
        }
    }

    public int getStationId() {
        return stationId;
    }

    public String getInitials() {
        return initials;
    }

    public String getHeName() {
        return heName;
    }

    public String getEnName() {
        return enName != null? enName : "";
    }

    public boolean isDisabledAccess() {
        return disabledAccess;
    }

    public boolean isInterchange() {
        return isInterchange;
    }

    public Location getLocation() {
        return location;
    }

    public boolean hasParking() {
        return hasParking;
    }

    public boolean isParkingChargeable() {
        return isParkingChargeable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeInt(this.getStationId());
            parcel.writeString(this.getInitials());
            parcel.writeString(this.getHeName());
            parcel.writeString(this.getEnName());
            parcel.writeByte((byte) (this.isDisabledAccess() ? 1 : 0));
            parcel.writeByte((byte) (this.isInterchange() ? 1 : 0));
            parcel.writeDouble(this.getLocation().getLatitude());
            parcel.writeDouble(this.getLocation().getLongitude());
            parcel.writeByte((byte) (this.hasParking() ? 1 : 0));
            parcel.writeByte((byte) (this.isParkingChargeable() ? 1 : 0));
        } catch (NullPointerException e) {
            Log.e(TAG, e.toString());
        }
    }


}
