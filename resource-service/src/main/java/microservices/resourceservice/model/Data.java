package microservices.resourceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    private Long id;
    private String title;


}
