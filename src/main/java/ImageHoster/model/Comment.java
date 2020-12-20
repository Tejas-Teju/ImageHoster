package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

//@Entity annotation describes that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'. Hence the table named
//'comments' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "comments")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    //id value generated will be of strategy type AUTO (id number will be incremented for each id creation)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database. Here the column name
    //is mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    //@Lob annotation is used to store Large objects i.e text with more than 255 characters
    @Lob
    @Column(name = "text")
    private String text;

    //Creates local date which with respect to local timezone
    @Column(name = "create_date")
    private LocalDate createDate;

    //The ‘comments’ table is mapped to ‘users’ table through this attribute. One user can post multiple comments but
    //one comment should belong to one user
    //Fetch type is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comments' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User user;

    //The ‘comment’ table is mapped to ‘images’ table through this attribute. One image can have multiple comments but
    //one comment can only belong to one image.
    //Fetch type is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comments' table referring the primary key in 'images' table will be 'image_id'
    @JoinColumn(name = "image_id")
    private Image image;

    //Below are the getters and setters for each attributes/variables of this class
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
