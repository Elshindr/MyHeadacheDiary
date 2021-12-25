package com.example.myheadachediary.model;

import java.io.Serializable;
import java.util.Dictionary;

/**
 * This Episode Class represent an episode when headache occurs. A Headache can have one or severals episode.
 * @see Headache
 */
public class Episode implements Serializable {

    //@XStreamAlias("episode")
    public  Episode() {
        super();
    }
    private int idEpisode;
    private String dateStart;
    private String dateEnd;
    private String severity;
    private String dizziness;
    private String aura;
    private String nausea;
    private String sideHeadache;
    private String sensibility;
    private String triggers;

    private int medIbupf;
    private int medPara;
    private int medZolmi;
    private int medOther;

    private String medication;
    private String commentEp;
    private Dictionary<String, String> dictSymp;

    public Dictionary<String, String> getDictSymp() {
        return dictSymp;
    }

    public void setDictSymp(Dictionary<String, String> dictSymp) {
        this.dictSymp = dictSymp;
    }

    /**
     * This is the constructor Episode method
     * @param date
     * @param dateEnd
     * @param severity
     * @param dizziness
     * @param aura
     * @param nausea
     * @param sideHeadache
     * @param sensibility
     * @param triggers
     * @param medication

    public Episode(String date, String dateEnd, String severity, String dizziness, String aura, String nausea, String sideHeadache, String sensibility, String triggers, String medication, String commentEp) {
        super();
        this.dateStart = date;
        this.dateEnd = dateEnd;
        this.severity = severity;
        this.dizziness = dizziness;
        this.aura = aura;
        this.nausea = nausea;
        this.sideHeadache = sideHeadache;
        this.sensibility = sensibility;
        this.triggers = triggers;
        this.medication = medication;
        this.commentEp = commentEp;
    }*/

    public Episode(String date, String dateEnd, Dictionary<String, String> dictSymp, String sensibility, String triggers, String medication, String commentEp) {
        super();
        this.dateStart = date;
        this.dateEnd = dateEnd;
        this.dictSymp = dictSymp;
        this.sensibility = sensibility;
        this.triggers = triggers;
        this.medication = medication;
        this.commentEp = commentEp;
    }

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

    public String getSensibility() {
        return sensibility;
    }

    public void setSensibility(String sensibility) {
        this.sensibility = sensibility;
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
    public int getMedIbupf() {
        return medIbupf;
    }

    public void setMedIbupf(int medIbupf) {
        this.medIbupf = medIbupf;
    }

    public int getMedPara() {
        return medPara;
    }

    public void setMedPara(int medPara) {
        this.medPara = medPara;
    }

    public int getMedZolmi() {
        return medZolmi;
    }

    public void setMedZolmi(int medZolmi) {
        this.medZolmi = medZolmi;
    }

    public int getMedOther() {
        return medOther;
    }

    public void setMedOther(int medOther) {
        this.medOther = medOther;
    }

    public String getCommentEp() {
        return commentEp;
    }

    public void setCommentEp(String commentEp) {
        this.commentEp = commentEp;
    }
}
