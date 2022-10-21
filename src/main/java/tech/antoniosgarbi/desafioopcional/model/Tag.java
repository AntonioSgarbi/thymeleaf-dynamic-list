package tech.antoniosgarbi.desafioopcional.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
//    @JsonManagedReference
//    private List<Word> words;

}
