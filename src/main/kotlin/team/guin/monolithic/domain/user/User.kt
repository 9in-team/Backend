package team.guin.monolithic.domain.user

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,
    @Column
    var userEmail: String,
    @Column
    var imageId: Long,
    @Column
    var userRole: String,
) {
    fun getRoleList(): MutableList<out Any?> {
        return if (userRole.length > 0) {
            Arrays.asList(userRole.split(","))
        } else {
            ArrayList<String?>()
        }
    }
}