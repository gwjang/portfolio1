package com.portfolio1.base;

import com.portfolio1.exception.DataAccessException;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.jdbc.UncategorizedSQLException;

public class BaseDao extends EgovAbstractMapper {
    public Object select(String id, Object parameters) {
        try {
            return getSqlSession().selectOne(id, parameters);
        }
        catch(UncategorizedSQLException se) {
            throw new DataAccessException(se);
        }
    }
}
