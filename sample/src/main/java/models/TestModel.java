package models;

/**
 * Created by rickk on 12-6-2017.
 */

public class TestModel {

    private String url;
    private String doel;
    private String inzet;
    private String onderwerp;
    private String titel;
    private String uitvoering;
    private String opdracht;
    private String bronnen;

    public TestModel(String doel, String inzet, String onderwerp, String titel, String uitvoering, String url, String bronnen, String opdracht) {
        this.doel = doel;
        this.inzet = inzet;
        this.onderwerp = onderwerp;
        this.titel = titel;
        this.uitvoering = uitvoering;
        this.url = url;
        this.bronnen = bronnen;
        this.opdracht = opdracht;
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

    public String geturl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpdracht() {
        return opdracht;
    }

    public void setOpdracht(String opdracht) {
        this.opdracht = opdracht;
    }

    public String getBronnen() {
        return bronnen;
    }

    public void setBronnen(String bronnen) {
        this.bronnen = bronnen;
    }
}
