package com.mj.core.springboot.utils;

import com.mj.core.springboot.model.QueryParam;
import com.mongodb.BasicDBList;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/31/19
 */

public final class MongoUtil {

    private MongoUtil() {
        //Empty Constructor
    }

    public static Query createQuery(final QueryParam param, final boolean ignoreNulls) {
        Query query = new Query();

        Map<String, Object> queryParams = param.getFields(ignoreNulls);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        return query;
    }

    public static Criteria createCriteria(final QueryParam param, final boolean ignoreNulls) {
        Criteria criteria = new Criteria();

        Map<String, Object> queryParams = param.getFields(ignoreNulls);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            criteria = criteria.and(entry.getKey()).is(entry.getValue());
        }

        return criteria;
    }

    public static boolean isEmptyQuery(Query query) {
        return Objects.isNull(query) || query.getQueryObject().isEmpty();
    }

    public static boolean isNotEmptyQuery(Query query) {
        return !isEmptyQuery(query);
    }

    public static boolean isEmptyCriteria(Criteria criteria) {
        return Objects.isNull(criteria) || criteria.getCriteriaObject().isEmpty();
    }

    public static boolean isNotEmptyCriteria(Criteria criteria) {
        return !isEmptyCriteria(criteria);
    }

    public static boolean isEmptyDocument(Document document) {
        return Objects.isNull(document) || document.isEmpty();
    }

    public static boolean isNotEmptyDocument(Document document) {
        return !isEmptyDocument(document);
    }

    public static boolean haveValue(Document document, String key) {
        return isNotEmptyDocument(document) && !((Document) document.get(key)).isEmpty();
    }

    public static BasicDBList toBsonList(List<Criteria> criteriaList) {
        BasicDBList dbList = new BasicDBList();

        if (Objects.nonNull(criteriaList)) {
            for (Criteria criteria : criteriaList) {
                dbList.add(criteria.getCriteriaObject());
            }
        }

        return dbList;
    }
}
