package com.jingdianjichi.subject.common.enums.es;

public enum ESIndexNameEnum {
    SUBJECT_INFO_ES("subject_info_es");

    private String indexName;

    ESIndexNameEnum(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexName() {
        return indexName;
    }

    public ESIndexNameEnum getIndex(String name) {
        for (ESIndexNameEnum indexNameEnum : ESIndexNameEnum.values()) {
            if (indexNameEnum.getIndexName().equals(name)) {
                return indexNameEnum;
            }
        }
        return null;
    }
}
