package cn.itcast.web.util.domain;

public class UserSixGrade {
    private int e_id;
    private String G_time;
    private String e_grade;
    private String is_qualified;

    private int id;
    private String name;

    private String username;
    private String sex;
    private String school;
    private String major;
    private String grade;

    private int s_id;
    private String start_time;
    private String end_time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "UserSixGrade{" +
                "e_id=" + e_id +
                ", G_time='" + G_time + '\'' +
                ", e_grade='" + e_grade + '\'' +
                ", is_qualified='" + is_qualified + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", s_id=" + s_id +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                '}';
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getG_time() {
        return G_time;
    }

    public void setG_time(String g_time) {
        G_time = g_time;
    }

    public String getE_grade() {
        return e_grade;
    }

    public void setE_grade(String e_grade) {
        this.e_grade = e_grade;
    }

    public String getIs_qualified() {
        return is_qualified;
    }

    public void setIs_qualified(String is_qualified) {
        this.is_qualified = is_qualified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
