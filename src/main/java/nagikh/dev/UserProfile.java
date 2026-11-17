package nagikh.dev;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bio")
    private String bio;
    @Column(name = "last_seen_time")
    private LocalDateTime lastSeenTime;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    public UserProfile() {}

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", lastSeenTime=" + lastSeenTime +
                ", user_name=" + user.getName() +
                '}';
    }

    public UserProfile(String bio, LocalDateTime lastSeenTime, User user) {
        this.bio = bio;
        this.lastSeenTime = lastSeenTime;
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(LocalDateTime lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}