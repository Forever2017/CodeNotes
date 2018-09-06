package joker.run.bean;

public class ResultBean {
//     <string name="Name">姓名</string>
//    <string name="SumCircle">总圈数</string>
//    <string name="SumDistance">总距离</string>
//    <string name="TotalTime">用时</string>
//    <string name="Pace">配速</string>
    private String id;
    private String name;
    private String sumCircle;
    private String sumDistance;
    private String totalTime;
    private String pace;

    //当前圈用时、上一圈用时
    private String current;
    private String last;


    public ResultBean() { } //constructor

    public ResultBean(String name, String sumCircle, String sumDistance, String totalTime, String pace) {
        this.name = name;
        this.sumCircle = sumCircle;
        this.sumDistance = sumDistance;
        this.totalTime = totalTime;
        this.pace = pace;
    }

    public ResultBean(String name, String current, String last) {
        this.name = name;
        this.current = current;
        this.last = last;
    }


    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSumCircle() {
        return sumCircle;
    }

    public void setSumCircle(String sumCircle) {
        this.sumCircle = sumCircle;
    }

    public String getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(String sumDistance) {
        this.sumDistance = sumDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getPace() {
        return pace;
    }

    public void setPace(String pace) {
        this.pace = pace;
    }
}
