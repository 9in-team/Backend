package team.guin.monolithic.domain.user

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,
    @Column
    var email: String,
    @Column
    var nickName: String,
    @Column
    var imageId: String,
    @ColumnDefault("USER")
    var userRole: String,
) {
    fun getRoleList(): MutableList<out Any?> {
        return if (userRole.length > 0) {
            Arrays.asList(userRole.split(","))
        } else {
            ArrayList<String?>()
        }
    }

    @PrePersist
    fun prePersist() {
        if (userRole == null) {
            userRole = "USER"
        } else {
            userRole = userRole
        }
    }
}