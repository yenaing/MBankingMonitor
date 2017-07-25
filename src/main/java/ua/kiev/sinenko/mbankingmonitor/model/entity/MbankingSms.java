package ua.kiev.sinenko.mbankingmonitor.model.entity;

/**
 * Created by a.sinenko on 01.02.2016.
 */
import ua.kiev.sinenko.mbankingmonitor.model.CardStatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import ua.kiev.sinenko.mbankingmonitor.model.ProcessingStage;
import ua.kiev.sinenko.mbankingmonitor.model.MBankingSmsState;


@Entity
@Table(name = "TMBANKING_SMS")
@NamedQueries({
        @NamedQuery(name = "MbankingSms.findAll", query = "SELECT m FROM MbankingSms m ORDER BY m.id_sms DESC"),
        @NamedQuery(name = "MbankingSms.findLinkedRecods", query = "SELECT m FROM MbankingSms m where m.originalIdSms = :idsms"),
        @NamedQuery(name = "MbankingSms.findByIdSms", query = "SELECT m FROM MbankingSms m where m.id_sms = :idsms")
})
public class MbankingSms {


    public static final String findAll = "MbankingSms.findAll";
    public static final String findByIdSms = "MbankingSms.findByIdSms";
    public static final String findLinkedRecods="MbankingSms.findLinkedRecods";

    @Transient
    private ProcessingStage processingStage;

    public ProcessingStage getProcessingStage() {
        return processingStage;
    }
    public void setProcessingStage(ProcessingStage processingStage) {
        this.processingStage = processingStage;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_dss")
    @SequenceGenerator(name = "sequence_dss", sequenceName = "SEQ_SMSID", allocationSize = 1)
    @javax.persistence.Column(name = "id_sms", nullable = false, insertable = true, updatable = true, precision = -127)
    private BigDecimal id_sms;
    public BigDecimal getId_sms() {        return id_sms;    }
    public void setId_sms(BigDecimal id_sms) {        this.id_sms = id_sms;    }

    
    @Basic
    @javax.persistence.Column(name = "STATE", nullable = true, insertable = true, updatable = true, precision = -127)
    private Integer state;
    public Integer getState() {        return state;    }
    private void setState(Integer state) {        this.state = state;    }
    public void setState(MBankingSmsState state){
        this.state=state.getValue();
    }
    

    @Basic
    @javax.persistence.Column(name = "ERR_CDE", nullable = true, insertable = true, updatable = true, precision = -127)
    private BigDecimal errCde;
    public BigDecimal getErrCde() {
        return errCde;
    }
    public void setErrCde(BigDecimal errCde) {
        this.errCde = errCde;
    }


    @Basic
    @javax.persistence.Column(name = "IN_SMS", nullable = false, insertable = true, updatable = true, length = 160)
    private String inSms;
    public String getInSms() {
        return inSms;
    }
    public void setInSms(String inSms) {
        this.inSms = inSms;
    }


    @Basic
    @javax.persistence.Column(name = "PARSED_SMS", nullable = true, insertable = true, updatable = true, length = 160)
    private String parsedSms;
    public String getParsedSms() {
        return parsedSms;
    }
    public void setParsedSms(String parsedSms) {
        this.parsedSms = parsedSms;
    }


    @Basic
    @javax.persistence.Column(name = "OUT_SMS", nullable = true, insertable = true, updatable = true, length = 160)
    private String outSms;
    public String getOutSms() {
        return outSms;
    }
    public void setOutSms(String outSms) {
        this.outSms = outSms;
    }


    @Basic
    @javax.persistence.Column(name = "PANID", nullable = true, insertable = true, updatable = true, length = 19)
    private String panid;
    public String getPanid() {
        return panid;
    }
    public void setPanid(String panid) {
        this.panid = panid;
    }


    @Basic
    @javax.persistence.Column(name = "PHONE", nullable = false, insertable = true, updatable = true, length = 50)
    private String phone;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Basic
    @javax.persistence.Column(name = "SMS_DATE", nullable = false, insertable = true, updatable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date smsDate;
    public Date getSmsDate() {
        return smsDate;
    }
    public void setSmsDate(Date smsDate) {
        this.smsDate = smsDate;
    }


    @Basic
    @javax.persistence.Column(name = "ORIGINAL_ID_SMS", nullable = true, insertable = true, updatable = true, precision = -127)
    private BigDecimal originalIdSms;
    public BigDecimal getOriginalIdSms() {
        return originalIdSms;
    }
    public void setOriginalIdSms(BigDecimal originalIdSms) {
        this.originalIdSms = originalIdSms;
    }


    @Basic
    @javax.persistence.Column(name = "FIID", nullable = true, insertable = true, updatable = true, length = 4)
    private String fiid;
    public String getFiid() {
        return fiid;
    }
    public void setFiid(String fiid) {
        this.fiid = fiid;
    }


    @Basic
    @javax.persistence.Column(name = "ACCOUNT", nullable = true, insertable = true, updatable = true, length = 19)
    private String account;
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }


    @Basic
    @javax.persistence.Column(name = "CARD_STAT", nullable = true, insertable = true, updatable = true, length = 1)
    private String cardStat;
    public String getCardStat() {
        return cardStat;
    }
    public void setCardStat(String cardStat) {
        this.cardStat = cardStat;
    }


    @Basic
    @javax.persistence.Column(name = "EXPDATE", nullable = true, insertable = true, updatable = true, length = 4)
    private String expdate;
    public String getExpdate() {
        return expdate;
    }
    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }


    @Basic
    @javax.persistence.Column(name = "OPRTN_CDE", nullable = false, insertable = true, updatable = true, precision = -127)
    private BigDecimal oprtnCde;
    public BigDecimal getOprtnCde() {
        return oprtnCde;
    }
    public void setOprtnCde(BigDecimal oprtnCde) {
        this.oprtnCde = oprtnCde;
    }


    @Basic
    @javax.persistence.Column(name = "FL_ID", nullable = false, insertable = true, updatable = true, precision = -127)
    private BigDecimal flId;
    public BigDecimal getFlId() {
        return flId;
    }
    public void setFlId(BigDecimal flId) {
        this.flId = flId;
    }


    @Basic
    @javax.persistence.Column(name = "MBR", nullable = true, insertable = true, updatable = true, precision = -127)
    private BigDecimal mbr;
    public BigDecimal getMbr() {
        return mbr;
    }
    public void setMbr(BigDecimal mbr) {
        this.mbr = mbr;
    }


    @Basic
    @javax.persistence.Column(name = "NEW_CARD_STAT", nullable = true, insertable = true, updatable = true, length = 1)
    private String newCardStat;
    public String getNewCardStat() {
        return newCardStat;
    }
    public void setNewCardStat(String newCardStat) {
        this.newCardStat = newCardStat;
    }
    public void setNewCardStat(CardStatusEnum newCardStat) {
        if(newCardStat==null){
            return;
        }
        this.newCardStat = String.valueOf(newCardStat.ordinal());
    }
    

    @Basic
    @javax.persistence.Column(name = "FHM_RESP_CDE", nullable = true, insertable = true, updatable = true, length = 30)
    private String fhmRespCde;
    public String getFhmRespCde() {
        return fhmRespCde;
    }
    public void setFhmRespCde(String fhmRespCde) {
        this.fhmRespCde = fhmRespCde;
    }


    @Basic
    @javax.persistence.Column(name = "ACCOUNTTYPE", nullable = true, insertable = true, updatable = true, length = 2)
    private String accountType;
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    

    @Basic
    @javax.persistence.Column(name = "PANCR", nullable = true, insertable = true, updatable = true, length = 60)
    private String panCr;
    public String getPanCr() {
        return panCr;
    }
    public void setPanCr(String panCr) {
        this.panCr = panCr;
    }
       

    @Basic
    @javax.persistence.Column(name = "WAIT_UNTIL", nullable = false, insertable = true, updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date waitUntil;
    public Date getWaitUntil() {
        return waitUntil;
    }
    public void setWaitUntil(Date waitUntil) {
        this.waitUntil = waitUntil;
    }


    @Basic
    @javax.persistence.Column(name = "ATTEMPT", nullable = true, insertable = true, updatable = true, precision = -127)
    private BigDecimal attempt;
    public BigDecimal getAttempt() {
        return attempt;
    }
    public void setAttempt(BigDecimal attempt) {
        this.attempt = attempt;
    }


    @Basic
    @javax.persistence.Column(name = "PANMASK", nullable = true, insertable = true, updatable = true, length = 19)
    private String panmask;
    public String getPanmask() {
        return panmask;
    }
    public void setPanmask(String panmask) {
        this.panmask = panmask;
    }


    @Basic
    @javax.persistence.Column(name = "OPERATION_EXTRACT_CODE", nullable = true, insertable = true, updatable = true, length = 2)
    private String operationExtractCode;
    public String getOperationExtractCode() {
        return operationExtractCode;
    }
    public void setOperationExtractCode(String operationExtractCode) {
        this.operationExtractCode = operationExtractCode;
    }
    
    
    @Override
    public String toString() {
        return "DBTmbankingSms{" +
                "state=" + state +
                ", id_sms=" + id_sms +
                ", errCde=" + errCde +
                ", inSms='" + inSms + '\'' +
                ", parsedSms='" + parsedSms + '\'' +
                ", outSms='" + ((outSms == null) ? outSms : outSms.replaceAll("\\r\\n|\\r|\\n", " ")) + '\'' +
                ", panid='" + panid + '\'' +
                ", phone='" + phone + '\'' +
                ", smsDate=" + smsDate +
                ", originalIdSms=" + originalIdSms +
                ", fiid='" + fiid + '\'' +
                ", account='" + account + '\'' +
                ", cardStat='" + cardStat + '\'' +
                ", expdate='" + expdate + '\'' +
                ", oprtnCde=" + oprtnCde +
                ", flId=" + flId +
                ", mbr=" + mbr +
                ", newCardStat='" + newCardStat + '\'' +
                ", fhmRespCde='" + fhmRespCde + '\'' +
                ", waitUntil=" + waitUntil +
                ", attempt=" + attempt +
                ", panmask='" + panmask + '\'' +
                '}';
    }

}