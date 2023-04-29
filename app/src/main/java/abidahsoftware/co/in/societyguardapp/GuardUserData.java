package abidahsoftware.co.in.societyguardapp;

public class GuardUserData {
    String name, securityService, stateName, cityName ,societyName, shiftDate, shiftTime, gateId;

    public GuardUserData(String name, String securityService, String stateName, String cityName, String societyName, String shiftDate, String shiftTime, String gateId) {
        this.name = name;
        this.securityService = securityService;
        this.stateName = stateName;
        this.cityName = cityName;
        this.societyName = societyName;
        this.shiftDate = shiftDate;
        this.shiftTime = shiftTime;
        this.gateId = gateId;
    }

    public GuardUserData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityService() {
        return securityService;
    }

    public void setSecurityService(String securityService) {
        this.securityService = securityService;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }
}
