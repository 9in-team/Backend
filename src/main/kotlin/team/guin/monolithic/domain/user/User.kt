package team.guin.monolithic.domain.user

import jakarta.persistence.*


@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var userId: Long? = null,
    @Column
    var userNickname: String,
    @Column
    var imageId: Long
)