package com.hx.core.es.utils;

import com.hx.core.es.entity.SearchRequestParam;
import com.hx.core.exception.BaseException;
import com.hx.core.utils.ObjectHelper;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hx.core.es.entity.EsPage;
import com.hx.core.utils.JsonUtils;

import javax.annotation.PostConstruct;
import javax.swing.plaf.synth.SynthSpinnerUI;

import java.util.*;



/**
 * Created by Ro on 2018/4/25.
 */
@Component
public class ElasticsearchUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchUtils.class);

	@Autowired
	private TransportClient transportClient;

	private static TransportClient client;

	@PostConstruct
	public void init() {
		client = this.transportClient;
	}

	/**
	 * 创建索引
	 *
	 * @param index
	 * @return
	 */
	public static boolean createIndex(String index) {
		if (!isIndexExist(index)) {
			LOGGER.info("Index is not exits!");
		}
		CreateIndexResponse indexresponse = client.admin().indices().prepareCreate(index).execute().actionGet();
		LOGGER.info("执行建立成功？" + indexresponse.isAcknowledged());

		return indexresponse.isAcknowledged();
	}


	 /**
     * 创建mapping(feid("indexAnalyzer","ik")该字段分词IK索引 ；feid("searchAnalyzer","ik")该字段分词ik查询；具体分词插件请看IK分词插件说明)
     * 
     *  .startObject("userinfo").field("type", "string").field("store", "yes")
	       .field("term_vector","with_positions_offsets").field("analyzer","ik_max_word")
	       .field("search_analyzer","ik_max_word").field("include_in_all","true").field("boost",8).endObject()
	    一	通用属性
    	index_name：该属性是存储在索引中的字段名称。如果未指定，则默认为字段定义的对象的名称。通常忽略该属性。
    	index：该属性的取值可为analyzed或no，如果为analyzed，则该字段将被索引，
    		因而是可以搜索的；如果为no，则该字段不可被搜索。默认值是analyzed。
    		字符串型的字段有一个额外的选项－－not_analyzed，意思是该字段会被索引但不需要分析。
    		因此，该字段按原样写入索引，只有完全匹配的搜索才能查到该字段。
    	store：该属性的取值可以为yes或no，用于指定字段的原始值是否存入索引。默认值是no，意味着不能在结果中返回字段的原始值（没有原始值也可以通过_source字段返回原始值）
    	boost：该属性默认为1。用于定义该字段在文档中的重要性，值越高表示该字段的取值越重要。
    	null_value：该属性指定如果某字段在被索引的文档中不存在时应写入何值。默认行为是忽略该字段。
    	include_in_all：该属性指定某字段是否应被包含到_all字段中。默认情况下，如果启用_all字段则包含所有的字段
             二     字符串型
       term_vector（词向量）：该属性的取值可以为no（默认值）、yes、with_offsets、with_positions、with_positions_offsets。
       	该属性表示是否对该字段计算lucene词向量，如果使用的是高亮则需要计算词向量。
       omit_norms：取值可以为true或false，默认是false，如果为true则禁用该字段的lucene norms计算，则不能使用索引时加权。
       omit_term_freq_and_positions：该属性的取值可为true或false，默认为false。若想建立索引时忽略词频和位置的计算，可将属性设为true。（0.20版本以后开始弃用）
       index_options：用于设置索引选项。可能的取值是docs（索引文档的数量）、freqs（索引的文档数量和词频）、positions（索引的文档数量、词频和单词出现位置）。默认值是freqs（从0.20版本开始可用）。
       analyzer：用于索引和搜索的分析器名称，默认是全局定义的分析器。
       index_analyzer：用于索引的分析器的名称。
       search_analyzer：用于处理作用在该字段的查询的分析器的名称。
       ignore_above：字段的最大长度，超出指定长度的部分会被忽略。如果只关心字段的前N个字符，则该属性非常有用。
           三    日期型
       format：用于指定日期格式。默认值是dateOperationalTime。可选格式参考官方date-format.html文档
       precision_step：该属性设置为字段的每个取值生成的项数。值越低则生成的项数越多，进行range查询时就越快（但索引也会变大），默认值是4。
       ignore_malformed：取值可以为true或false，默认值是false。若想要忽略格式错误的数值，则应该设置为true。
          四、布尔型：取值为true或false 
          五、二进制型（binary）
       	二进制字段是指用base64来表示索引中存储的二进制数据，可用来存储二进制形式的数据，例如图像。默认情况下，该类型的字段只存储不索引。二进制类型只支持index_name属性
          六、数值型
       precision_step：该属性设置为字段的每个取值生成的项数。值越低则生成的项数越多，进行range查询时就越快（但索引也会变大），默认值是4。
       ignore_malformed：取值可以为true或false，默认值是false。若想要忽略格式错误的数值，则应该设置为true。
       
       
     * @param index 索引名称；
     * @param type 索引类型
     * @throws Exception
     */
    public static boolean createMapping(String index,String type)throws Exception{

        XContentBuilder builder=XContentFactory.jsonBuilder()
                .startObject().startObject("properties")
                .startObject("spotId").field("type", "long").field("store", "yes").endObject()
                .startObject("spotName").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("country").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("province").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("district").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("city").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("city_code").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("area_code").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("description").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("brief").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("subtitle").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("rating").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("telephones").field("type", "string").field("store", "yes").endObject()
                .startObject("geoPoint").field("type", "string").field("store", "yes").endObject()
                .startObject("tags").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("imageUrls").field("type", "string").field("store", "yes").endObject()
                .startObject("rank").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("openingHours").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("location").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("ticketInfo").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").endObject()
                .startObject("dataUrl").field("type", "string").field("store", "yes").endObject()
                .startObject("dataSources").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("isHide").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("dataTime").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("visitsNum").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("isHot").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("isRecommend").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
                .endObject().endObject();
        PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder);
        System.out.println(mapping);
        PutMappingResponse actionGet = client.admin().indices().putMapping(mapping).get();

        return actionGet.isAcknowledged();

    }
    
    public static boolean createMappingESQ(String index,String type)throws Exception{
    	  XContentBuilder builder=XContentFactory.jsonBuilder()
                  .startObject().startObject("properties")
                  .startObject("id").field("type", "long").field("store", "yes").endObject()
                  .startObject("name_id").field("type", "long").field("store", "yes").endObject()
                  .startObject("name").field("type", "string").field("store", "yes").field("boost", 5).field("analyzer", "ik_max_word").endObject()
                  .startObject("location").field("type", "string").field("store", "yes").field("boost", 3).field("analyzer", "ik_max_word").endObject()
                  .startObject("tar").field("type", "string").field("store", "yes").field("boost", 4).field("analyzer", "ik_max_word").endObject()
                  .startObject("create_time").field("type", "long").field("store", "yes").endObject()
                  .startObject("type").field("type", "integer").field("store", "yes").endObject()
                  .startObject("is_hot").field("type", "integer").field("store", "yes").endObject()
                  .startObject("is_hide").field("type", "integer").field("store", "yes").endObject()
                  .startObject("sales").field("type", "integer").field("store", "yes").endObject()
                  .startObject("comment_count").field("type", "integer").field("store", "yes").endObject()
                  .startObject("comment_score").field("type", "float").field("store", "yes").endObject()
                  .startObject("price").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("geo_point").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("area_code").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("area_code_name").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("image").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("user_icon").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .startObject("autograph").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                  .endObject().endObject();
    	
    	  PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder);
          System.out.println(mapping);
          PutMappingResponse actionGet = client.admin().indices().putMapping(mapping).get();

          return actionGet.isAcknowledged();
    	
    }
	/**
	 * 删除索引
	 *
	 * @param index
	 * @return
	 */
	public static boolean deleteIndex(String index) {
		if (!isIndexExist(index)) {
			LOGGER.info("Index is not exits!");
		}
		DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
		if (dResponse.isAcknowledged()) {
			LOGGER.info("delete index " + index + "  successfully!");
		} else {
			LOGGER.info("Fail to delete index " + index);
		}
		return dResponse.isAcknowledged();
	}

	/**
	 * 判断索引是否存在
	 *
	 * @param index
	 * @return
	 */
	public static boolean isIndexExist(String index) {
		IndicesExistsResponse inExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index))
				.actionGet();
		if (inExistsResponse.isExists()) {
			LOGGER.info("Index [" + index + "] is exist!");
		} else {
			LOGGER.info("Index [" + index + "] is not exist!");
		}
		return inExistsResponse.isExists();
	}

	/**
	 * 数据添加，正定ID
	 *
	 * @param jsonObject
	 *            要增加的数据
	 * @param index
	 *            索引，类似数据库
	 * @param type
	 *            类型，类似表
	 * @param id
	 *            数据ID
	 * @return
	 */
	public static String addData(Object jsonObject, String index, String type, String id) {

		IndexResponse response = client.prepareIndex(index, type, id)
				.setSource(JsonUtils.Bean2Json(jsonObject), XContentType.JSON).get();

		LOGGER.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());

		return response.getId();
	}

	/**
	 * 数据添加
	 *
	 * @param jsonObject
	 *            要增加的数据
	 * @param index
	 *            索引，类似数据库
	 * @param type
	 *            类型，类似表
	 * @return
	 */
	public static String addData(Object jsonObject, String index, String type) {
		return addData(jsonObject, index, type, UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
	}

	/**
	 * 通过ID删除数据
	 *
	 * @param index
	 *            索引，类似数据库
	 * @param type
	 *            类型，类似表
	 * @param id
	 *            数据ID
	 */
	public static void deleteDataById(String index, String type, String id) {

		DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();

		LOGGER.info("deleteDataById response status:{},id:{}", response.status().getStatus(), response.getId());
	}

	/**
	 * 通过ID 更新数据
	 *
	 * @param jsonObject
	 *            要增加的数据
	 * @param index
	 *            索引，类似数据库
	 * @param type
	 *            类型，类似表
	 * @param id
	 *            数据ID
	 * @return
	 */
	public static void updateDataById(Object jsonObject, String index, String type, String id) {

		UpdateRequest updateRequest = new UpdateRequest();

		updateRequest.index(index).type(type).id(id).doc(jsonObject);

		client.update(updateRequest);

	}

	/**
	 * 通过ID获取数据
	 *
	 * @param index
	 *            索引，类似数据库
	 * @param type
	 *            类型，类似表
	 * @param id
	 *            数据ID
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @return
	 */
	public static Map<String, Object> searchDataById(String index, String type, String id, String fields) {

		GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);

		if (StringUtils.isNotEmpty(fields)) {
			getRequestBuilder.setFetchSource(fields.split(","), null);
		}

		GetResponse getResponse = getRequestBuilder.execute().actionGet();

		return getResponse.getSource();
	}

	/**
	 * 使用分词查询
	 *
	 * @param index
	 *            索引名称
	 * @param type
	 *            类型名称,可传入多个type逗号分隔
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @param matchStr
	 *            过滤条件（xxx=111,aaa=222）
	 * @return
	 */
	public static List<Map<String, Object>> searchListData(String index, String type, String fields, String matchStr) {
		return searchListData(index, type, 0, 0, null, fields, null, false, null, matchStr);
	}

	/**
	 * 使用分词查询
	 *
	 * @param index
	 *            索引名称
	 * @param type
	 *            类型名称,可传入多个type逗号分隔
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @param sortField
	 *            排序字段
	 * @param matchPhrase
	 *            true 使用，短语精准匹配
	 * @param matchStr
	 *            过滤条件（xxx=111,aaa=222）
	 * @return
	 */
	public static List<Map<String, Object>> searchListData(String index, String type, String fields, String sortField,
			boolean matchPhrase, String matchStr) {
		return searchListData(index, type, 0, 0, null, fields, sortField, matchPhrase, null, matchStr);
	}

	/**
	 * 使用分词查询
	 *
	 * @param index
	 *            索引名称
	 * @param type
	 *            类型名称,可传入多个type逗号分隔
	 * @param size
	 *            文档大小限制
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @param sortField
	 *            排序字段
	 * @param matchPhrase
	 *            true 使用，短语精准匹配
	 * @param highlightField
	 *            高亮字段
	 * @param matchStr
	 *            过滤条件（xxx=111,aaa=222）
	 * @return
	 */
	public static List<Map<String, Object>> searchListData(String index, String type, Integer size, String fields,
			String sortField, boolean matchPhrase, String highlightField, String matchStr) {
		return searchListData(index, type, 0, 0, size, fields, sortField, matchPhrase, highlightField, matchStr);
	}

	/**
	 * 使用分词查询
	 *
	 * @param index
	 *            索引名称
	 * @param type
	 *            类型名称,可传入多个type逗号分隔
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param size
	 *            文档大小限制
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @param sortField
	 *            排序字段
	 * @param matchPhrase
	 *            true 使用，短语精准匹配
	 * @param highlightField
	 *            高亮字段
	 * @param matchStr
	 *            过滤条件（xxx=111,aaa=222）
	 * @return
	 */
	public static List<Map<String, Object>> searchListData(String index, String type, long startTime, long endTime,
			Integer size, String fields, String sortField, boolean matchPhrase, String highlightField,
			String matchStr) {

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		if (StringUtils.isNotEmpty(type)) {
			searchRequestBuilder.setTypes(type.split(","));
		}
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

		if (startTime > 0 && endTime > 0) {
			boolQuery.must(QueryBuilders.rangeQuery("processTime").format("epoch_millis").from(startTime).to(endTime)
					.includeLower(true).includeUpper(true));
		}

		// 搜索的的字段
		if (StringUtils.isNotEmpty(matchStr)) {
			for (String s : matchStr.split(",")) {
				String[] ss = s.split("=");
				if (ss.length > 1) {
					if (matchPhrase == Boolean.TRUE) {
						boolQuery.must(QueryBuilders.matchPhraseQuery(s.split("=")[0], s.split("=")[1]));
					} else {
						boolQuery.must(QueryBuilders.matchQuery(s.split("=")[0], s.split("=")[1]));
					}
				}

			}
		}

		// 高亮（xxx=111,aaa=222）
		if (StringUtils.isNotEmpty(highlightField)) {
			HighlightBuilder highlightBuilder = new HighlightBuilder();

			 highlightBuilder.preTags("<span style='color:red' >");//设置前缀
			 highlightBuilder.postTags("</span>");//设置后缀

			// 设置高亮字段
			highlightBuilder.field(highlightField);
			searchRequestBuilder.highlighter(highlightBuilder);
		}

		searchRequestBuilder.setQuery(boolQuery);

		if (StringUtils.isNotEmpty(fields)) {
			searchRequestBuilder.setFetchSource(fields.split(","), null);
		}
		searchRequestBuilder.setFetchSource(true);

		if (StringUtils.isNotEmpty(sortField)) {
			searchRequestBuilder.addSort(sortField, SortOrder.DESC);
		}

		if (size != null && size > 0) {
			searchRequestBuilder.setSize(size);
		}

		// 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
		LOGGER.info("\n{}", searchRequestBuilder);
		long start = System.currentTimeMillis();
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		System.err.println(System.currentTimeMillis()- start);

		long totalHits = searchResponse.getHits().totalHits;
		long length = searchResponse.getHits().getHits().length;

		LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

		if (searchResponse.status().getStatus() == 200) {
			// 解析对象
			return setSearchResponse(searchResponse, highlightField);
		}

		return null;

	}




	/**
	 * 使用分词查询
	 * @param param
	 * @return
	 */
	public static List<Map<String, Object>> searchListData(SearchRequestParam param){

		SearchRequestBuilder searchRequestBuilder;
		try{
			searchRequestBuilder = getSearchRequestBuilder(param);
		}catch (IllegalArgumentException e){
			LOGGER.error(e.getMessage());
			throw new BaseException(e.getMessage());
		}
		// 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
		LOGGER.info("\n{}", searchRequestBuilder);
		
		
		long start = System.currentTimeMillis();
		//调用请求获取返回
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

		System.err.println(System.currentTimeMillis()- start);
		long totalHits = searchResponse.getHits().totalHits;
		long length = searchResponse.getHits().getHits().length;

		LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

		if (searchResponse.status().getStatus() == 200) {
			// 解析对象
			return setSearchResponse(searchResponse, param.getHighlightField());
		}

		return null;
	}


	/**
	 * 组装Search请求参数
	 * @param param
	 * @return
	 */
	public static SearchRequestBuilder getSearchRequestBuilder(SearchRequestParam param){
		if (ObjectHelper.isEmpty(param.getIndexes())){
			throw new IllegalArgumentException("索引名称不能为空！");
		}
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(param.getIndexes());
		if (ObjectHelper.isNotEmpty(param.getTypes())){
			searchRequestBuilder.setTypes(param.getTypes());
		}
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		//时间区间
		if (param.getStartTime() > 0 && param.getEndTime() >0){
			boolQuery.must(QueryBuilders.rangeQuery("processTime").format("epoch_millis").from(param.getStartTime()).to(param.getEndTime())
					.includeLower(true).includeUpper(true));
		}
		// 搜索的的字段  should
		if (null != param.getShouldStr() && !param.getShouldStr().isEmpty()){
			param.getShouldStr().keySet().stream().forEach(k -> {
				if (param.isMatchPhrase()){
					boolQuery.should(QueryBuilders.matchPhraseQuery(k, param.getShouldStr().get(k)==null?"":param.getShouldStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}else {
					boolQuery.should(QueryBuilders.matchQuery(k, param.getShouldStr().get(k)==null?"":param.getShouldStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}
			});
		}
		// 搜索字段可以多个值得情况
		if (null != param.getShouldStrs() && param.getShouldStrs().size()>0){
			param.getShouldStrs().stream().forEach(k -> {
				if (k!=null){
					if (param.isMatchPhrase()){
						boolQuery.should(QueryBuilders.matchPhraseQuery(k.getKey(), k.getValue()==null?"":k.getValue()).boost(param.getBoots().get(k.getKey())==null?1.0F:param.getBoots().get(k.getKey())));
					}else {
						boolQuery.should(QueryBuilders.matchQuery(k.getKey(), k.getValue()==null?"":k.getValue()).boost(param.getBoots().get(k.getKey())==null?1.0F:param.getBoots().get(k.getKey())));
					}
				}
				
			});
		}
		// 搜索的的字段  must
		if (null != param.getMustStr() && !param.getMustStr().isEmpty()){
			param.getMustStr().keySet().stream().forEach(k -> {
				if (param.isMatchPhrase()){
					boolQuery.must(QueryBuilders.matchPhraseQuery(k, param.getMustStr().get(k)==null?"":param.getMustStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}else {
					boolQuery.must(QueryBuilders.matchQuery(k, param.getMustStr().get(k)==null?"":param.getMustStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}
			});
		}
		
		
		//搜索的的字段  notmust
		if (null != param.getNotMustStr() && !param.getNotMustStr().isEmpty()){
			param.getNotMustStr().keySet().stream().forEach(k -> {
				if (param.isMatchPhrase()){
					boolQuery.mustNot(QueryBuilders.matchPhraseQuery(k, param.getNotMustStr().get(k)==null?"":param.getNotMustStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}else {
					boolQuery.mustNot(QueryBuilders.matchQuery(k, param.getNotMustStr().get(k)==null?"":param.getNotMustStr().get(k)).boost(param.getBoots().get(k)==null?1.0F:param.getBoots().get(k)));
				}
			});
		}
		
		// 搜索 notmust 同一个字段 多个的情况
		if (null != param.getNotMustStrs() && param.getNotMustStrs().size()>0){
			param.getNotMustStrs().stream().forEach(k -> {
				if (k!=null){
					if (param.isMatchPhrase()){
						boolQuery.mustNot(QueryBuilders.matchPhraseQuery(k.getKey(), k.getValue()==null?"":k.getValue()).boost(param.getBoots().get(k.getKey())==null?1.0F:param.getBoots().get(k.getKey())));
					}else {
						boolQuery.mustNot(QueryBuilders.matchQuery(k.getKey(), k.getValue()==null?"":k.getValue()).boost(param.getBoots().get(k.getKey())==null?1.0F:param.getBoots().get(k.getKey())));
					}
				}
				
			});
		}
		// 高亮
		if (ObjectHelper.isNotEmpty(param.getHighlightField())) {
			HighlightBuilder highlightBuilder = new HighlightBuilder();
			Arrays.asList(param.getHighlightField()).stream().forEach(h -> {
                highlightBuilder.preTags("<span style='color:red' >");//设置前缀
                highlightBuilder.postTags("</span>");//设置后缀
                // 设置高亮字段
                highlightBuilder.field(h);
                searchRequestBuilder.highlighter(highlightBuilder);
            });
		}
		searchRequestBuilder.setQuery(boolQuery);
		if (ObjectHelper.isNotEmpty(param.getFields())) {
			searchRequestBuilder.setFetchSource(param.getFields(), null);
		}
		searchRequestBuilder.setFetchSource(true);

		if (null != param.getSortField() && !param.getSortField().isEmpty()) {
			param.getSortField().keySet().stream().forEach(k -> {
				searchRequestBuilder.addSort(k, param.getSortField().get(k));
			});
		}

		if (param.getSize() != null && param.getSize() > 0) {
			searchRequestBuilder.setSize(param.getSize());
		}
		return searchRequestBuilder;
	}


	/**
	 * 使用分词查询,并分页
	 * @param param
	 * @return
	 */
	public static EsPage searchDataPage(SearchRequestParam param){
		SearchRequestBuilder searchRequestBuilder;
		try{
			searchRequestBuilder = getSearchRequestBuilder(param);
		}catch (IllegalArgumentException e){
			LOGGER.error(e.getMessage());
			throw new BaseException(e.getMessage());
		}
		//设置分页
		searchRequestBuilder.setFrom((param.getCurrentPage()-1)*param.getPageSize()).setSize(param.getPageSize());
		// 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
		LOGGER.info("\n{}", searchRequestBuilder);
		// 执行搜索,返回搜索响应信息
		long start = System.currentTimeMillis();
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		System.err.println(System.currentTimeMillis()- start);
		long totalHits = searchResponse.getHits().totalHits;
		long length = searchResponse.getHits().getHits().length;

		LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

		if (searchResponse.status().getStatus() == 200) {
			// 解析对象
			List<Map<String, Object>> sourceList = setSearchResponse(searchResponse, param.getHighlightField());

			return new EsPage(param.getCurrentPage(), param.getPageSize(), (Long) totalHits, sourceList);
		}

		return null;
	}

	/**
	 * 使用分词查询,并分页
	 *
	 * @param index
	 *            索引名称
	 * @param type
	 *            类型名称,可传入多个type逗号分隔
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param fields
	 *            需要显示的字段，逗号分隔（缺省为全部字段）
	 * @param sortField
	 *            排序字段
	 * @param matchPhrase
	 *            true 使用，短语精准匹配
	 * @param highlightField
	 *            高亮字段
	 * @param matchStr
	 *            过滤条件（xxx=111,aaa=222）
	 * @return
	 */
	public static EsPage searchDataPage(String index, String type, int currentPage, int pageSize, long startTime,
			long endTime, String fields, String sortField, boolean matchPhrase, String highlightField,
			String matchStr) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		if (StringUtils.isNotEmpty(type)) {
			searchRequestBuilder.setTypes(type.split(","));
		}
		searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);

		// 需要显示的字段，逗号分隔（缺省为全部字段）
		if (StringUtils.isNotEmpty(fields)) {
			searchRequestBuilder.setFetchSource(fields.split(","), null);
		}

		// 排序字段
		if (StringUtils.isNotEmpty(sortField)) {
			searchRequestBuilder.addSort(sortField, SortOrder.DESC);
		}

		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

		if (startTime > 0 && endTime > 0) {
			boolQuery.must(QueryBuilders.rangeQuery("processTime").format("epoch_millis").from(startTime).to(endTime)
					.includeLower(true).includeUpper(true));
		}

		// 查询字段
		if (StringUtils.isNotEmpty(matchStr)) {
			for (String s : matchStr.split(",")) {
				String[] ss = s.split("=");
				if (matchPhrase == Boolean.TRUE) {
					boolQuery.must(QueryBuilders.matchPhraseQuery(s.split("=")[0], s.split("=")[1]));
				} else {
					boolQuery.must(QueryBuilders.matchQuery(s.split("=")[0], s.split("=")[1]));
				}
			}
		}

		// 高亮（xxx=111,aaa=222）
		if (StringUtils.isNotEmpty(highlightField)) {
			HighlightBuilder highlightBuilder = new HighlightBuilder();
			for (String s : highlightField.split(",")) {

				 highlightBuilder.preTags("<span style='color:red' >");//设置前缀
				 highlightBuilder.postTags("</span>");//设置后缀

				// 设置高亮字段
				highlightBuilder.field(s);
				searchRequestBuilder.highlighter(highlightBuilder);
			}

		}

		searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
		searchRequestBuilder.setQuery(boolQuery);

		// 分页应用
		searchRequestBuilder.setFrom(currentPage).setSize(pageSize);

		// 设置是否按查询匹配度排序
		searchRequestBuilder.setExplain(true);

		// 打印的内容 可以在 Elasticsearch head 和 Kibana 上执行查询
		LOGGER.info("\n{}", searchRequestBuilder);

		long start = System.currentTimeMillis();
		// 执行搜索,返回搜索响应信息
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		System.err.println(System.currentTimeMillis()- start);
		long totalHits = searchResponse.getHits().totalHits;
		long length = searchResponse.getHits().getHits().length;

		LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

		if (searchResponse.status().getStatus() == 200) {
			// 解析对象
			List<Map<String, Object>> sourceList = setSearchResponse(searchResponse, highlightField);

			return new EsPage(currentPage, pageSize, (Long) totalHits, sourceList);
		}

		return null;

	}

    /**
     * 高亮结果集 特殊处理
     * @param searchResponse
     * @param highlightFields
     * @return
     */
	private static List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse,String[] highlightFields){
        List<Map<String, Object>> sourceList = new ArrayList<>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());
            StringBuffer stringBuffer = new StringBuffer();

            if (ObjectHelper.isNotEmpty(highlightFields)) {
                Arrays.asList(highlightFields).stream().forEach(h -> {
                	Text[] text = null;
            		HighlightField highlightField = searchHit.getHighlightFields().get(h);
            		if (highlightField!=null){
            			text  = highlightField.getFragments();
            		}
                    if (text != null) {
                        for (Text str : text) {
                            stringBuffer.append(str.string());
                        }
                        // 遍历 高亮结果集，覆盖 正常结果集
                        searchHit.getSourceAsMap().put(h, stringBuffer.toString());
                    }
                });
            }
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
    }

	/**
	 * 高亮结果集 特殊处理
	 *
	 * @param searchResponse
	 * @param highlightField
	 */
	private static List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse, String highlightField) {
		List<Map<String, Object>> sourceList = new ArrayList<>();


		for (SearchHit searchHit : searchResponse.getHits().getHits()) {
			searchHit.getSourceAsMap().put("id", searchHit.getId());
			StringBuffer stringBuffer = new StringBuffer();

			if (StringUtils.isNotEmpty(highlightField)) {
				for (String s : highlightField.split(",")) {
					System.out.println("遍历 高亮结果集，覆盖 正常结果集" + searchHit.getSourceAsMap());
					Text[] text = searchHit.getHighlightFields().get(s).getFragments();

					if (text != null) {
						for (Text str : text) {
							stringBuffer.append(str.string());
						}
						// 遍历 高亮结果集，覆盖 正常结果集
						searchHit.getSourceAsMap().put(s, stringBuffer.toString());
					}
				}
			}
			sourceList.add(searchHit.getSourceAsMap());
		}

		return sourceList;
	}

	public static List<Map<String, Object>> test(String tete) {

			MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "tete");
	        HighlightBuilder hiBuilder=new HighlightBuilder();
	        hiBuilder.preTags("<h2>");
	        hiBuilder.postTags("</h2>");
	        hiBuilder.field("name");
	        // 搜索数据
	        SearchResponse response = client.prepareSearch("blog")
	                .setQuery(matchQuery)
	                .highlighter(hiBuilder)
	                .execute().actionGet();
	        //获取查询结果集
	        SearchHits searchHits = response.getHits();
	        System.out.println("共搜到:"+searchHits.getTotalHits()+"条结果!");
	        //遍历结果
	        for(SearchHit hit:searchHits){
	            System.out.println("String方式打印文档搜索内容:");
	            System.out.println(hit.getSourceAsString());
	            System.out.println("Map方式打印高亮内容");
	            System.out.println(hit.getHighlightFields());

	            System.out.println("遍历高亮集合，打印高亮片段:");
	            Text[] text = hit.getHighlightFields().get("title").getFragments();
	            for (Text str : text) {
	                System.out.println(str.string());
	            }
	        }
		return null;

	}
}
