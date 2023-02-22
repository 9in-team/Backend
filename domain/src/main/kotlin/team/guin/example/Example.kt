package team.guin.example

import team.guin.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Example(
    @Column
    val name: String,
    @Column
    val age: Int,
) : BaseEntity()
