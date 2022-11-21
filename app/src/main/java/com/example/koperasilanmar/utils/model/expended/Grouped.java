
package com.example.koperasilanmar.utils.model.expended;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Grouped {

    @SerializedName("personil")
    @Expose
    private List<Personil> personil = null;

    public List<Personil> getPersonil() {
        return personil;
    }

    public void setPersonil(List<Personil> personil) {
        this.personil = personil;
    }

}
