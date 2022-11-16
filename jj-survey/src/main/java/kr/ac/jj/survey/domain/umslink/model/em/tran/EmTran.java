package kr.ac.jj.survey.domain.umslink.model.em.tran;

import java.io.Serializable;
import java.util.Date;

public class EmTran implements Serializable {

    private static final long serialVersionUID = -3644418659606979100L;

    protected Long tranPr;
    protected String tranRefkey;
    protected String tranId;
    protected String tranPhone;
    protected String tranCallback;
    protected String tranStatus;
    protected Date tranDate;
    protected Date tranRsltdate;
    protected Date tranReportdate;
    protected String tranRslt;
    protected String tranNet;
    protected String tranMsg;
    protected String tranEtc1;
    protected String tranEtc2;
    protected String tranEtc3;
    protected Long tranEtc4;
    protected Integer tranType;

    public Long getTranPr() {
        return this.tranPr;
    }

    public void setTranPr(Long tranPr) {
        this.tranPr = tranPr;
    }

    public String getTranRefkey() {
        return this.tranRefkey;
    }

    public void setTranRefkey(String tranRefkey) {
        this.tranRefkey = tranRefkey;
    }

    public String getTranId() {
        return this.tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranPhone() {
        return this.tranPhone;
    }

    public void setTranPhone(String tranPhone) {
        this.tranPhone = tranPhone;
    }

    public String getTranCallback() {
        return this.tranCallback;
    }

    public void setTranCallback(String tranCallback) {
        this.tranCallback = tranCallback;
    }

    public String getTranStatus() {
        return this.tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public Date getTranDate() {
        return this.tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Date getTranRsltdate() {
        return this.tranRsltdate;
    }

    public void setTranRsltdate(Date tranRsltdate) {
        this.tranRsltdate = tranRsltdate;
    }

    public Date getTranReportdate() {
        return this.tranReportdate;
    }

    public void setTranReportdate(Date tranReportdate) {
        this.tranReportdate = tranReportdate;
    }

    public String getTranRslt() {
        return this.tranRslt;
    }

    public void setTranRslt(String tranRslt) {
        this.tranRslt = tranRslt;
    }

    public String getTranNet() {
        return this.tranNet;
    }

    public void setTranNet(String tranNet) {
        this.tranNet = tranNet;
    }

    public String getTranMsg() {
        return this.tranMsg;
    }

    public void setTranMsg(String tranMsg) {
        this.tranMsg = tranMsg;
    }

    public String getTranEtc1() {
        return this.tranEtc1;
    }

    public void setTranEtc1(String tranEtc1) {
        this.tranEtc1 = tranEtc1;
    }

    public String getTranEtc2() {
        return this.tranEtc2;
    }

    public void setTranEtc2(String tranEtc2) {
        this.tranEtc2 = tranEtc2;
    }

    public String getTranEtc3() {
        return this.tranEtc3;
    }

    public void setTranEtc3(String tranEtc3) {
        this.tranEtc3 = tranEtc3;
    }

    public Long getTranEtc4() {
        return this.tranEtc4;
    }

    public void setTranEtc4(Long tranEtc4) {
        this.tranEtc4 = tranEtc4;
    }

    public Integer getTranType() {
        return this.tranType;
    }

    public void setTranType(Integer tranType) {
        this.tranType = tranType;
    }

}
