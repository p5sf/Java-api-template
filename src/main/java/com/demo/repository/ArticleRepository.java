package com.demo.repository;

import com.demo.model.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Yan
 *
 */
public interface ArticleRepository extends MongoRepository<Article, Long> {

    List<Article> findByTitleLike(String title);

    Page<Article> findByTitleLike(String title, Pageable pageable);

    default Article findByTitle(String title) {
        Article article = new Article();
        article.setTitle(title);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Article> ex = Example.of(article, matcher);
        return findOne(ex).orElse(null);
        // 如果为空返回null
    }

}
