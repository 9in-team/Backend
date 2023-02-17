package team.guin.domain

import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long,
    @Column
    var email: String,
    @Column
    var nickname: String,
    @Column
    var imageId: String,
    @ColumnDefault("USER")
    var roles: String,
) {
    constructor() : this(0, "", "", "", "")

    fun getRoleList() = roles.split(",")
}
