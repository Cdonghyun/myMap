package my.myMap.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Bus {
    public Bus(String busNum) {
        this.busNum = busNum;
    }

    private String busNum;
}
