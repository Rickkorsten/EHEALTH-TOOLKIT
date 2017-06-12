package models;

/**
 * Created by rickk on 12-6-2017.
 */

public class TestModel {

    private String doel;
    private String inzet;
    private String onderwerp;
    private String titel;
    private String uitvoering;

    public TestModel(String doel, String inzet, String onderwerp, String titel, String uitvoering) {
        this.doel = doel;
        this.inzet = inzet;
        this.onderwerp = onderwerp;
        this.titel = titel;
        this.uitvoering = uitvoering;
    }

    public TestModel(){

    }

    public String getDoel() {
        return doel;
    }

    public void setDoel(String doel) {
        this.doel = doel;
    }

    public String getInzet() {
        return inzet;
    }

    public void setInzet(String inzet) {
        this.inzet = inzet;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUitvoering() {
        return uitvoering;
    }

    public void setUitvoering(String uitvoering) {
        this.uitvoering = uitvoering;
    }
}
