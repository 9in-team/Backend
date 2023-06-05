package team.guin.example

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.example.Example

@Repository
interface ExampleRepository : JpaRepository<Example, Long>
