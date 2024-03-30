package model;

import java.io.Serializable;

public class SharedData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data;

    public SharedData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
