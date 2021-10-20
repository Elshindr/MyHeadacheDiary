package com.example.myheadachediary.model;

import java.io.Serializable;

public class Episode implements Serializable {
    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }


    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDizziness() {
        return dizziness;
    }

    public void setDizziness(String dizziness) {
        this.dizziness = dizziness;
    }

    public String getAura() {
        return aura;
    }

    public void setAura(String aura) {
        this.aura = aura;
    }

    public String getNausea() {
        return nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    public String getSideHeadache() {
        return sideHeadache;
    }

    public void setSideHeadache(String sideHeadache) {
        this.sideHeadache = sideHeadache;
    }

    public String getOtherSymp() {
        return otherSymp;
    }

    public void setOtherSymp(String otherSymp) {
        this.otherSymp = otherSymp;
    }

    public String getHypersensibility() {
        return hypersensibility;
    }

    public void setHypersensibility(String hypersensibility) {
        this.hypersensibility = hypersensibility;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(int idEpisode) {
        this.idEpisode = idEpisode;
    }

    private int idEpisode;
    private String dateStart;
    private String dateEnd;
    private String severity;
    private String dizziness;
    private String aura;
    private String nausea;
    private String sideHeadache;
    private String otherSymp;

    private String hypersensibility;
    private String triggers;

    private int medIbupf;
    private int medPara;
    private int medZolmi;
    private int medOther;

    private String medication;

    public Episode() {
    }

    public Episode(int idEpisode, String date, String dateEnd, String severity, String dizziness, String aura, String nausea, String sideHeadache, String otherSymp, String hypersensibility, String triggers, String medication) {
        this.idEpisode = idEpisode;
        this.dateStart = date;
        this.dateEnd = dateEnd;
        this.severity = severity;
        this.dizziness = dizziness;
        this.aura = aura;
        this.nausea = nausea;
        this.sideHeadache = sideHeadache;
        this.otherSymp = otherSymp;
        this.hypersensibility = hypersensibility;
        this.triggers = triggers;
        this.medication = medication;
    }
    @Override
    public String toString() {

        return  "<episode>" +
                "       <idEpisode>"+idEpisode+"</idEpisode>" +
                "       <dateStart>"+ dateStart +"</dateStart>" +
                "       <dateEnd>"+ dateEnd +"</dateEnd>" +
                "       <severity>"+ severity +"</severity>" +
                "       <dizziness>"+ dizziness +"</dizziness>" +
                "       <aura>"+ aura +"</aura>" +
                "       <nausea>"+ nausea +"</nausea>" +
                "</episode>";
    }
}
