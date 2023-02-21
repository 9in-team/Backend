package team.guin.example.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.example.Example

@Repository
interface ExampleRepository : JpaRepository<Example, Long>
