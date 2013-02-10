objectDao(objectName, fields) ::= <<

/**
 * 
 */
package <daopackage>;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.Assert;


/**
 * 
 *
 */
public class <objectName>Dao extends JdbcDaoSupport implements <objectName>DaoInterface {

	private transient static Log log = LogFactory.getLog(<objectName>Dao.class.getName());

	@Value("${dao.<objectName>Dao.retrieve<objectName>}")
	private String query_retrieve<objectName>;

	/** 
	 * 
	 */
	@Override
	public <objectName> retrieve<objectName>(String id) {
            
		log.debug("Executing:" + query_retrieve<objectName> + " with params: " + id);

		List\<<objectName>\> resultList = super.getJdbcTemplate().query(query_retrieve<objectName>, new Object[] { id },
				new RowMappers.<objectName>Mapper());

		if (resultList.size() == 0) {
			return null;
		}

		return resultList.get(0);

	}
}



>>

