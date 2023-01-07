package uz.developers.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacsiya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transacsiyaUuid;
    @ManyToOne
    private Carrier carrier;
    @ManyToOne
    private Request request;
    @ManyToOne
    private Offer offer;
    private Integer score = 0;


}
