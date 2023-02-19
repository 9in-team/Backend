package team.guin.domain.enumeration.account

import javax.persistence.AttributeConverter
import javax.persistence.Convert

@Convert
class AccountRolesConverter : AttributeConverter<AccountRoles, String> {
    override fun convertToDatabaseColumn(attribute: AccountRoles?): String? {
        if (attribute == null) {
            return null
        }
        return attribute.role
    }

    override fun convertToEntityAttribute(dbData: String?): AccountRoles? {
        if (dbData == null) {
            return null
        }
        return AccountRoles.fromCode(dbData)
    }
}
