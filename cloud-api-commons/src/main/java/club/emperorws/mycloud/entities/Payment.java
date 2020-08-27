package club.emperorws.mycloud.entities;

import java.io.Serializable;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 16:21
 * @Description:
 **/
public class Payment implements Serializable {
    private Integer id;
    private String serial;

    public Payment() {
    }

    public Payment(Integer id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getId() {
        return id;
    }

    public String getSerial() {
        return serial;
    }
}
