package com.oplus.osense.eventinfo;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class OsenseConfig implements Parcelable {
    private int mEventType;
    private Bundle mExtra;

    public OsenseConfig(int eventType, Bundle extra) {
        this.mEventType = eventType;
        this.mExtra = extra;
    }

    protected OsenseConfig(Parcel in) {
        this.mEventType = in.readInt();
        this.mExtra = in.readBundle(getClass().getClassLoader());
    }

    public static final Creator<OsenseConfig> CREATOR = new Creator<OsenseConfig>() {
        @Override
        public OsenseConfig createFromParcel(Parcel in) {
            return new OsenseConfig(in);
        }

        @Override
        public OsenseConfig[] newArray(int size) {
            return new OsenseConfig[size];
        }
    };

    public int getEventType() {
        return this.mEventType;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OsenseConfig that = (OsenseConfig) object;
        return mEventType == that.mEventType;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{mEventType});
    }

    @Override
    public String toString() {
        return "OsenseConfig{mEventType=" + this.mEventType + ", mExtra=" + (this.mExtra == null ? "null" : this.mExtra) + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mEventType);
        dest.writeBundle(this.mExtra);
    }
}
