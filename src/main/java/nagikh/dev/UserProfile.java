package nagikh.dev;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class UserProfile {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
