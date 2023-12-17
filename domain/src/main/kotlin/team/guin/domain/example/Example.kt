package team.guin.domain.example

import org.hibernate.annotations.Where
import team.guin.domain.baseentity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Where(clause = "deleted_at IS NULL")
@Entity
@Table(name = "example")
class Example(
    @Column
    val name: String,
    @Column
    val age: Int,
) : BaseEntity()
