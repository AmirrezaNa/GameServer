package view;

import java.io.Serializable;

public class SettingsPanel implements Serializable {
    private static final long serialVersionUID = 1L;

    public int level;
    public int sense;

    public SettingsPanel() {
        this.level = 2;
        this.sense = 2;
    }
}
