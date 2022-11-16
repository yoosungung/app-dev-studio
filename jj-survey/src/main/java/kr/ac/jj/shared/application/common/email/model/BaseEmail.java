package kr.ac.jj.shared.application.common.email.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message.RecipientType;

import kr.ac.jj.shared.domain.main.model.com.email.TbComEmail;
import kr.ac.jj.shared.domain.main.model.com.email.atchfile.TbComEmailAtchFile;
import kr.ac.jj.shared.domain.main.model.com.email.recptn.TbComEmailRecptn;
import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;

public class BaseEmail {

    private TbComEmail tbComEmail;
    private List<TbComEmailRecptn> tbComEmailRecptnList;
    private List<TbComEmailAtchFile> tbComEmailAtchFileList;
    private List<TbComEmailSndng> tbComEmailSndngList;

    public TbComEmail getTbComEmail() {
        if (this.tbComEmail == null) {
            this.setTbComEmail(new TbComEmail());
        }

        return this.tbComEmail;
    }

    public void setTbComEmail(TbComEmail tbComEmail) {
        this.tbComEmail = tbComEmail;
    }

    public List<TbComEmailRecptn> getTbComEmailRecptnList() {
        return this.tbComEmailRecptnList;
    }

    public void setTbComEmailRecptnList(List<TbComEmailRecptn> tbComEmailRecptnList) {
        this.tbComEmailRecptnList = tbComEmailRecptnList;
    }

    public List<TbComEmailAtchFile> getTbComEmailAtchFileList() {
        return this.tbComEmailAtchFileList;
    }

    public void setTbComEmailAtchFileList(List<TbComEmailAtchFile> tbComEmailAtchFileList) {
        this.tbComEmailAtchFileList = tbComEmailAtchFileList;
    }

    public List<TbComEmailSndng> getTbComEmailSndngList() {
        return this.tbComEmailSndngList;
    }

    public void setTbComEmailSndngList(List<TbComEmailSndng> tbComEmailSndngList) {
        this.tbComEmailSndngList = tbComEmailSndngList;
    }

    public void setEmailSj(String emailSj) {
        this.getTbComEmail().setEmailSj(emailSj);
    }

    public void setEmailCn(String emailCn) {
        this.getTbComEmail().setEmailCn(emailCn);
    }

    public void setSender(String senderEmailAdres) {
        this.setSender(senderEmailAdres, null, null);
    }

    public void setSender(String senderEmailAdres, String senderNm) {
        this.setSender(senderEmailAdres, senderNm, null);
    }

    public void setSender(String senderEmailAdres, String senderNm, String senderPersonId) {
        TbComEmail tbComEmail = this.getTbComEmail();
        tbComEmail.setSenderEmailAdres(senderEmailAdres);
        tbComEmail.setSenderNm(senderNm);
        tbComEmail.setSenderPersonId(senderPersonId);
    }

    public void addRecptnTo(String rcverEmailAdres) {
        this.addRecptn(RecipientType.TO, rcverEmailAdres, null, null);
    }

    public void addRecptnTo(String rcverEmailAdres, String rcverNm) {
        this.addRecptn(RecipientType.TO, rcverEmailAdres, rcverNm, null);
    }

    public void addRecptnTo(String rcverEmailAdres, String rcverNm, String rcverPersonId) {
        this.addRecptn(RecipientType.TO, rcverEmailAdres, rcverNm, rcverPersonId);
    }

    public void addRecptnCc(String rcverEmailAdres) {
        this.addRecptn(RecipientType.CC, rcverEmailAdres, null, null);
    }

    public void addRecptnCc(String rcverEmailAdres, String rcverNm) {
        this.addRecptn(RecipientType.CC, rcverEmailAdres, rcverNm, null);
    }

    public void addRecptnCc(String rcverEmailAdres, String rcverNm, String rcverPersonId) {
        this.addRecptn(RecipientType.CC, rcverEmailAdres, rcverNm, rcverPersonId);
    }

    public void addRecptnBcc(String rcverEmailAdres) {
        this.addRecptn(RecipientType.BCC, rcverEmailAdres, null, null);
    }

    public void addRecptnBcc(String rcverEmailAdres, String rcverNm) {
        this.addRecptn(RecipientType.BCC, rcverEmailAdres, rcverNm, null);
    }

    public void addRecptnBcc(String rcverEmailAdres, String rcverNm, String rcverPersonId) {
        this.addRecptn(RecipientType.BCC, rcverEmailAdres, rcverNm, rcverPersonId);
    }

    public void addRecptn(RecipientType recptnTy, String rcverEmailAdres) {
        this.addRecptn(recptnTy, rcverEmailAdres, null, null);
    }

    public void addRecptn(RecipientType recptnTy, String rcverEmailAdres, String rcverNm) {
        this.addRecptn(recptnTy, rcverEmailAdres, rcverNm, null);
    }

    public void addRecptn(RecipientType recptnTy, String rcverEmailAdres, String rcverNm, String rcverPersonId) {
        TbComEmailRecptn tbComEmailRecptn = new TbComEmailRecptn();
        tbComEmailRecptn.setRecptnTyEnum(recptnTy);
        tbComEmailRecptn.setRcverEmailAdres(rcverEmailAdres);
        tbComEmailRecptn.setRcverNm(rcverNm);
        tbComEmailRecptn.setRcverPersonId(rcverPersonId);

        this.addRecptn(tbComEmailRecptn);
    }

    public void addRecptn(TbComEmailRecptn tbComEmailRecptn) {
        if (this.getTbComEmailRecptnList() == null) {
            this.setTbComEmailRecptnList(new ArrayList<TbComEmailRecptn>());
        }

        this.getTbComEmailRecptnList().add(tbComEmailRecptn);
    }

    public void addAtchFile(String filePath, String fileNm) {
        TbComEmailAtchFile tbComEmailAtchFile = new TbComEmailAtchFile();
        tbComEmailAtchFile.setFilePath(filePath);
        tbComEmailAtchFile.setFileNm(fileNm);

        this.addAtchFile(tbComEmailAtchFile);
    }

    public void addAtchFile(TbComEmailAtchFile tbComEmailAtchFile) {
        if (this.getTbComEmailAtchFileList() == null) {
            this.setTbComEmailAtchFileList(new ArrayList<TbComEmailAtchFile>());
        }

        this.getTbComEmailAtchFileList().add(tbComEmailAtchFile);
    }

    public TbComEmailSndng getTbComEmailSndng() {
        if (this.getTbComEmailSndngList() == null || this.getTbComEmailSndngList().size() == 0) {
            return null;
        }

        return this.getTbComEmailSndngList().get(0);
    }

    public void setTbComEmailSndng(TbComEmailSndng tbComEmailSndng) {
        if (this.getTbComEmailSndngList() == null) {
            this.setTbComEmailSndngList(new ArrayList<TbComEmailSndng>());
        }

        this.getTbComEmailSndngList().clear();
        this.getTbComEmailSndngList().add(tbComEmailSndng);
    }

}
