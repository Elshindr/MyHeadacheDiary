package com.example.myheadachediary;

import java.util.Date;
import java.util.List;

public class Headache {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    private String date;
    private String duration;
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

    public Headache(String date, String duration, String severity, String dizziness, String aura, String nausea, String sideHeadache, String otherSymp, String hypersensibility, String triggers, String medication) {
        this.date = date;
        this.duration = duration;
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
}
