package com.decidir.sdk.dto;

/**
 * Created by biandra on 07/07/16.
 */
public class NotFoundError extends ApiError {

    private String entityName;
    private String id;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
