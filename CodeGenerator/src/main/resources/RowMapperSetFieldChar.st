rowMapperSetFieldChar(fieldName, resultSetFieldType) ::= <<

String <fieldName>AsString = rs.getString("<fieldName>");
if(<fieldName>AsString.length()>0)
{
    result.set<fieldName> (<fieldName>AsString.charAt(0));
}

>>
