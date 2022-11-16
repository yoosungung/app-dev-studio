package kr.ac.jj.shared.application.admin.logmanage.emaillog.model;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.email.TbComEmail;
import kr.ac.jj.shared.domain.main.model.com.email.atchfile.TbComEmailAtchFile;
import kr.ac.jj.shared.domain.main.model.com.email.recptn.TbComEmailRecptn;
import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;

/**
 * 이메일 로그 관리 Model
 */
public class EmailLogManageModel {

    private TbComEmail tbComEmail;
    private List<TbComEmailRecptn> tbComEmailRecptnList;
    private List<TbComEmailAtchFile> tbComEmailAtchFileList;
    private List<TbComEmailSndng> tbComEmailSndngList;
    private List<TbComFile> atchFileList;

    public TbComEmail getTbComEmail() {
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

    public List<TbComEmailAtchFile> toTbComEmailAtchFileList() {
        List<TbComEmailAtchFile> tbComEmailAtchFileList = new ArrayList<TbComEmailAtchFile>();

        if (this.atchFileList == null) {
            return tbComEmailAtchFileList;
        }

        for (TbComFile tbComFile : this.atchFileList) {
            TbComEmailAtchFile tbComEmailAtchFile = new TbComEmailAtchFile();
            tbComEmailAtchFile.setFilePath(tbComFile.getCanonicalPath());
            tbComEmailAtchFile.setFileNm(tbComFile.getOrginlFileNm());
            tbComEmailAtchFileList.add(tbComEmailAtchFile);
        }

        return tbComEmailAtchFileList;
    }

    public List<TbComFile> getAtchFileList() {
        return this.atchFileList;
    }

    public void setAtchFileList(List<TbComFile> atchFileList) {
        this.atchFileList = atchFileList;
    }

}
