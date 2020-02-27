package cn.edu.zjnu.acm.entity.oj;

import cn.edu.zjnu.acm.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
public class Comment implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Comment father = null;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Instant postTime;
    @ManyToOne(optional = false)
    private User user;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String text;

    public Comment(User user, String text, Comment father) {
        this.user = user;
        this.text = text;
        this.father = father;
        postTime = Instant.now();
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postTime=" + postTime +
                ", user=" + user +
                ", text='" + text + '\'' +
                '}';
    }



    @Override
    public int compareTo(Object o) {
        Comment c = (Comment) o;
        return getPostTime().compareTo(c.getPostTime()) * -1;
    }
}



