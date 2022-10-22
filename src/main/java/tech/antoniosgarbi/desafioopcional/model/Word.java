package tech.antoniosgarbi.desafioopcional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "value_name")
    private String value;
    @ManyToMany
    private List<Tag> tags;

}
