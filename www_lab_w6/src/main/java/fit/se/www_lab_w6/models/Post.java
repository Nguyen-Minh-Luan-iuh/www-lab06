package fit.se.www_lab_w6.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@NamedQueries({
        @NamedQuery(name = "POST.FIND_ALL", query = "SELECT p FROM Post p where p.published = true")
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Post post;
    @Column(name = "meta_title", nullable = false)
    private String metaTitle;

    @Lob
    @Column(nullable = false)
    private String summary;
    @Column
    private boolean published;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "puhblished_at")
    private Instant publishedAt;
    @Lob
    @Column(nullable = false)
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostComment> postComments = new LinkedHashSet<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Post> posts = new LinkedHashSet<>();

    public Post() {
    }

    public Post(String title, User author, Post post, String metaTitle, String summary, boolean published, Instant createdAt, Instant updatedAt, Instant publishedAt, String content) {
        this.title = title;
        this.author = author;
        this.post = post;
        this.metaTitle = metaTitle;
        this.summary = summary;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Post(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", metaTitle='" + metaTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", published=" + published +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                '}';
    }
}
