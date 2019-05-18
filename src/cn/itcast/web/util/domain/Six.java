package cn.itcast.web.util.domain;

public class Six {
    private int s_id;
    private String fractional_line;
    private String start_time;
    private String end_time;
    private String release;
    private String is_ending;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getFractional_line() {
        return fractional_line;
    }

    public void setFractional_line(String fractional_line) {
        this.fractional_line = fractional_line;
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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getIs_ending() {
        return is_ending;
    }

    public void setIs_ending(String is_ending) {
        this.is_ending = is_ending;
    }

    @Override
    public String toString() {
        return "Six{" +
                "s_id=" + s_id +
                ", fractional_line='" + fractional_line + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", release='" + release + '\'' +
                ", is_ending='" + is_ending + '\'' +
                '}';
    }
}
