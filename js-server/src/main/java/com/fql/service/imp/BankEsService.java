package com.fql.service.imp;

import com.fql.entity.BankEsEntity;
import com.fql.repository.BankEsRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Qian
 */
@Service
public class BankEsService {
    private final BankEsRepository ber ;
    private final ElasticsearchRestTemplate template ;
    BankEsService(BankEsRepository ber,ElasticsearchRestTemplate template) {
        this.template=template;
        this.ber = ber;
    }

    // 自定义 检索邮箱 new1:高亮展示
    public List<BankEsEntity> searchByEmail(String email){
        // 创建一个高光xxx
        HighlightBuilder.Field field = new HighlightBuilder.Field("email")
                .preTags("<fount style='color:red>'")
                .postTags("</fount>");
        // 构建本地查询
        NativeSearchQuery nativ = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("email", email))
                .withHighlightFields(field).build();
        SearchHits<BankEsEntity> result = template.search(nativ, BankEsEntity.class);

        // 高亮数据替换到email
        List<BankEsEntity> list = result.stream().map(x -> {
            BankEsEntity content = x.getContent();
            content.setEmail(x.getHighlightField("email").get(0));
            return content;
        }).collect(Collectors.toList());
        return list ;
    }

    // 添加
    public BankEsEntity save(BankEsEntity entity){
        BankEsEntity save = ber.save(entity);
        return save ;
    }

    // 删除
    public boolean delete(BankEsEntity entity){
        ber.delete(entity);
        return true ;
    }

    // 查询
    public List<BankEsEntity> findAll(){
        Iterable<BankEsEntity> all = ber.findAll();
        ArrayList<BankEsEntity> entities = new ArrayList<>();
        all.forEach((s)->{
            entities.add(s);
        });
        return entities;
    }





}
