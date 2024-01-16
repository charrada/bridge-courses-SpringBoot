package project.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ImageCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     String type;
     int  idCourse;

    @Lob // use @Lob annotation to store large binary data
    @Column(name = "picByte", columnDefinition = "LONGBLOB")

    byte[] picByte;





    







}
