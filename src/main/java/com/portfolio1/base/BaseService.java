package com.portfolio1.base;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public abstract class BaseService extends EgovAbstractServiceImpl {

    protected abstract BaseDao getDao();

    protected Object select(String id, Object parameters) {
        if (id == null)
            id = "";
        return getDao().select(getNameSpace()+".select"+id, parameters);
    }

    protected String getNameSpace() {
        String cname = getClass().getName();
        if (cname.endsWith("Service")) {
            return cname.substring(0, cname.length() - "Service".length());
        } else {
            return cname;
        }
    }
}
