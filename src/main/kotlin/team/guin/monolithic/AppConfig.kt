package team.guin.monolithic

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun modelMapper(): ModelMapper? {
        val modelMapper = ModelMapper()
        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        return modelMapper
    }
}