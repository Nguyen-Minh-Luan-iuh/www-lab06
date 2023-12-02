package fit.se.www_lab_w6.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "post_comments")
@Getter
@Setter
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private PostComment parent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column
    private boolean published;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "published_at")
    private Instant publishedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<PostComment> postComments = new LinkedHashSet<>();

    public PostComment() {
    }

    public PostComment(Post post, PostComment parent, User user, String title, String content, boolean published, Instant createdAt, Instant publishedAt, Set<PostComment> postComments) {
        this.post = post;
        this.parent = parent;
        this.user = user;
        this.title = title;
        this.content = content;
        this.published = published;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", post=" + post +
                ", parent=" + parent +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", published=" + published +
                ", createdAt=" + createdAt +
                ", publishedAt=" + publishedAt +
                ", postComments=" + postComments +
                '}';
    }
}
