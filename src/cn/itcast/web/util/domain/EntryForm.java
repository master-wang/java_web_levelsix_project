package cn.itcast.web.util.domain;

public class EntryForm {
    private int e_id;
    private int s_id;
    private int u_id;
    private String G_time;
    private String e_grade;
    private String is_qualified;
    private String ticket_number;

    public String getE_grade() {
        return e_grade;
    }

    public void setE_grade(String e_grade) {
        this.e_grade = e_grade;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getG_time() {
        return G_time;
    }

    public void setG_time(String g_time) {
        G_time = g_time;
    }


    public String getIs_qualified() {
        return is_qualified;
    }

    public void setIs_qualified(String is_qualified) {
        this.is_qualified = is_qualified;
    }

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }

    @Override
    public String toString() {
        return "EntryForm{" +
                "e_id=" + e_id +
                ", s_id=" + s_id +
                ", u_id=" + u_id +
                ", G_time='" + G_time + '\'' +
                ", e_grade='" + e_grade + '\'' +
                ", is_qualified='" + is_qualified + '\'' +
                ", ticket_number='" + ticket_number + '\'' +
                '}';
    }
}
