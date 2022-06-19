package my.myMap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor //생성자
@NoArgsConstructor //디폴트 생성자
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteID {
    private List<itemList> itemList;


}

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class itemList{

    private String gpsX;
    private String gpsY;

}

