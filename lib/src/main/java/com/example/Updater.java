package com.example;

import com.google.gson.annotations.SerializedName;

public class Updater {

    @SerializedName("actual_version")
    private Integer actualVersion;

    @SerializedName("is_need_force_update")
    private Boolean isNeedForceUpdate;

    public void setActualVersion(final Integer pActualVersion) {
        this.actualVersion = pActualVersion;
    }

    public void setNeedForceUpdate(final Boolean pIsNeedForceUpdate) {
        this.isNeedForceUpdate = pIsNeedForceUpdate;
    }

    public Integer getActualVersion() {

        return actualVersion;
    }

    public Boolean getNeedForceUpdate() {
        return isNeedForceUpdate;
    }
}
