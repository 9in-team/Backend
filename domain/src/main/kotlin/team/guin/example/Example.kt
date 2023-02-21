package team.guin.example

import team.guin.BaseEntity
import java.lang.reflect.Constructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Example(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long= 0,
)
