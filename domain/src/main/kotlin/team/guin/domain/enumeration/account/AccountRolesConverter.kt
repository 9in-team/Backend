package team.guin.domain.enumeration.account

import javax.persistence.AttributeConverter
import javax.persistence.Convert

@Convert
class AccountRolesConverter : AttributeConverter<AccountRoles, String> {
    override fun convertToDatabaseColumn(attribute: AccountRoles?): String? {
        return attribute?.role
    }

    override fun convertToEntityAttribute(dbData: String?): AccountRoles? {
        return dbData?.let { AccountRoles.fromCode(dbData) }
    }
}
