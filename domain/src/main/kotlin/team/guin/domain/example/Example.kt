package team.guin.domain.example

import org.hibernate.annotations.Where
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Where(clause = "deleted_at IS NULL")
@Entity
class Example(
    @Column
    val name: String,
    @Column
    val age: Int,
) : BaseEntity()
