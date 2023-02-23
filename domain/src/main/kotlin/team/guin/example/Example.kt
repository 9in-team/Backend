package team.guin.example

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import team.guin.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@SQLDelete(sql = "UPDATE example SET deleted_at = now() where id = ?")
@Where(clause = "deleted_at IS NULL")
@Entity
class Example(
    @Column
    val name: String,
    @Column
    val age: Int,
) : BaseEntity()
