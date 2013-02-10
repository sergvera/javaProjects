
rowMapper(objectName, fields) ::= <<
        /** <objectName> **/
	public static final class <objectName>Mapper implements RowMapper\<<objectName>\> {

		public <objectName> mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			<objectName> result = new <objectName>();
                        
			<fields>
            
			
			return result;
		}
	}
>>