package com.hana.springboot.data.dao.queryRepository;

import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.domain.entity.QProduct;
import com.hana.springboot.data.domain.entity.QProductFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hana.springboot.data.domain.entity.QProduct.*;
import static com.hana.springboot.data.domain.entity.QProductFile.productFile;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ProductListDto> findAllByMemberCode(String memberCode, Pageable pageable) {
        List<ProductListDto> result = queryFactory.select(
                Projections.fields(ProductListDto.class,
                        product.productCode,
                        product.memberCode,
                        product.name,
                        product.price,
                        product.quantity,
                        productFile.filePath
                        )
        ).from(product)
                .join(productFile)
                .on(productFile.productCode.eq(product.productCode))
                .where(product.memberCode.eq(memberCode),
                        product.isVisible.eq(true),
                        product.isDelete.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(product.count())
                .from(product)
                .where(product.memberCode.eq(memberCode))
                .join(productFile)
                .on(productFile.productCode.eq(product.productCode))
                .fetch().get(0);

        return new PageImpl<>(result, pageable, total);
    }
}
