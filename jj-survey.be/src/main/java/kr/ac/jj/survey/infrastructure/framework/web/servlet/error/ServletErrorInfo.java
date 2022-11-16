package kr.ac.jj.survey.infrastructure.framework.web.servlet.error;

public class ServletErrorInfo {
    private boolean visible;
    private String type;
    private int number;

    public ServletErrorInfo(boolean visible, String type, int number) {
        this.setVisible(visible);
        this.setType(type);
        this.setNumber(number);
    }

    public ServletErrorInfo(boolean visible, String type) {
        this.setVisible(visible);
        this.setType(type);
    }

    public ServletErrorInfo(boolean visible, int number) {
        this.setVisible(visible);
        this.setNumber(number);
    }

    public ServletErrorInfo(String type, int number) {
        this.setType(type);
        this.setNumber(number);
    }

    public ServletErrorInfo(boolean visible) {
        this.setVisible(visible);
    }

    public ServletErrorInfo(String type) {
        this.setType(type);
    }

    public ServletErrorInfo(int number) {
        this.setNumber(number);
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
