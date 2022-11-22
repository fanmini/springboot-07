package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.BankEsEntity;
import com.fql.entity.EsNewsEntity;
import com.fql.entity.NewsModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.NewsMapper;
import com.fql.repository.es.NewsEsRepository;
import com.fql.repository.jpa.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Qian
 * 新闻类添加了es查询高光展示功能
 */
@Service
@Transactional
@Slf4j
public class NewsServiceImpl extends BaseServiceImpl<NewsModel,Integer, NewsRepository> {
    public NewsServiceImpl(NewsRepository repository) {
        super(repository, Rediskey.NEWS_KEY.getKey());
    }
    @Autowired
    private  NewsEsRepository repository ;
    @Autowired
    private  ElasticsearchRestTemplate template ;
    @Resource
    private NewsMapper mapper ;



    @Override
    public <S extends NewsModel> ResultModel save(S entity) {
        // 调用父类的添加修改方法,并同时同步到es当中去
        ResultModel result = super.save(entity);
        EsNewsEntity esNews = copyNewsEntity((NewsModel) result.getData());
        try {
            repository.save(esNews);
        } catch (Exception ignored) {

        }
        return result ;
    }

    @Override
    public ResultModel deleteById(Integer integer) {
        repository.delete(new EsNewsEntity(integer));
        return super.deleteById(integer);
    }

    @Override
    public ResultModel findAll() {
        return super.findAll();
    }

    @Override
    public ResultModel findById(Integer integer) {
        return super.findById(integer);
    }

    /**
     * es or mysql 查询
     * @param entity
     * @return
     */
    @Override
    public ResultModel findAllByLike(NewsModel entity) {
        // 判断是走的es还是MySQL的模糊查询
        if(StringUtils.hasText(entity.getContent())){
            // es
            List<EsNewsEntity> esNewsEntityList = searchByContent(entity.getContent());
            return ResultModel.getResultModel(esNewsEntityList);
        }
        // 模糊
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

    /**
     * 自定义 检索邮箱 new1:高亮展示
     * @param content
     * @return
     */
    public List<EsNewsEntity> searchByContent(String content){
        // 创建一个高光xxx
        HighlightBuilder.Field field = new HighlightBuilder.Field("content")
                .preTags("<fount style='color:yellow>'")
                .postTags("</fount>");
        // 构建本地查询
        NativeSearchQuery nativ = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("content", content))
                .withHighlightFields(field).build();
        SearchHits<EsNewsEntity> result = template.search(nativ, EsNewsEntity.class);



        // 高亮数据替换到email
        List<EsNewsEntity> list = result.stream().map(x -> {
            EsNewsEntity esNews = x.getContent();
            esNews.setContent(x.getHighlightField("content").get(0));
            return esNews;
        }).collect(Collectors.toList());
        return list ;
    }


    /**
      *  把数据库的newsModel 转换为es的entity
      */
    private EsNewsEntity copyNewsEntity(NewsModel entity){
        EsNewsEntity esNews = new EsNewsEntity();
        esNews.setIndex(entity.getId());
        esNews.setContent(entity.getContent());
        esNews.setTitle(entity.getTitle());
        esNews.setDate(entity.getDate());
        esNews.setImgHref(entity.getImgHref());
        esNews.setNavId(entity.getNavId());
        esNews.setEnable(entity.getEnable());
        return esNews ;
    }
}